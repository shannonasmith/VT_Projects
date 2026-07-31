<div align="center">

# 🔧 P6 Troubleshooting Log — ShaeBookstoreState

### A record of a multi-hour environment debugging session: from a persistent MySQL connection timeout to a fully working, freshly-deployed frontend

![Type](https://img.shields.io/badge/Type-Postmortem-orange)
![Scope](https://img.shields.io/badge/Scope-Environment%20%7C%20Build%20Pipeline-blueviolet)
![Resolved](https://img.shields.io/badge/Status-Resolved-brightgreen)

</div>

---

## 🧠 Context

This project hadn't been run in over two years. Every Tomcat-hosted Java app on the dev machine (`ShaeBookstoreState`, `ShaeBookstoreOrder`, `ShaeBookstoreRest`) was failing to connect to a local MySQL instance with `java.net.ConnectException: Connection timed out: connect`, despite MySQL itself running correctly and accepting CLI connections instantly. This log captures the full diagnostic path and every issue actually found and fixed, in the order they surfaced — including the dead ends, since ruling those out was part of what got us to the real causes.

---

## Issue 1 — `Connection timed out` on every Tomcat-hosted app

### Symptoms
- MySQL service running, CLI connects instantly
- Bare Java `Socket` test (no Tomcat) connects to `localhost`, `127.0.0.1`, and `::1` instantly
- Every Tomcat-hosted project failed identically — confirmed environment-wide, not project-specific
- Firewall disabled, IntelliJ proxy cleared, explicit `'root'@'127.0.0.1'` grant added, full reboot — no change

### Ruled out (in order)
1. MySQL health/config — confirmed healthy
2. Firewall — disabled, no change
3. IntelliJ proxy settings — no change
4. DNS/hostname resolution — `nslookup localhost` failed as expected (routers don't resolve "localhost"), but `ping localhost` and `InetAddress.getByName("localhost")` inside the JVM both correctly resolved to loopback
5. `hosts` file — default, untouched, both loopback lines commented out
6. VPN client (IPVanish TAP adapter) — present but confirmed **disconnected** at the time of failure
7. JVM-level hosts override (`jdk.net.hosts.file` in `net.properties`) — checked, not set
8. Environment variables (`JAVA_TOOL_OPTIONS`, `JDK_JAVA_OPTIONS`, `JAVA_OPTS`, `CATALINA_OPTS`) — all unset
9. Tomcat's `setenv.bat` — did not exist
10. `catalina.properties` and Tomcat's global `context.xml` — both clean, default, no overrides
11. Gradle dependency CVEs (`jersey-media-json-jackson`, `mysql-connector-java`, `jackson-core`) — all unrelated to networking; ruled out on inspection

### Root cause
**A stale/zombie Tomcat process was already bound to ports 8080 and 8005.** This wasn't caught by a clean "Address already in use" error every time — depending on how Tomcat was launched, the app would instead surface the problem several layers downstream as a JDBC connection timeout, because the request was being handled (or silently dropped) by the existing broken instance rather than a fresh one.

### Fix
```
netstat -ano | findstr :8080
taskkill /PID <pid> /F
```
Confirm the port is fully free (no `LISTENING` line) before starting Tomcat again, whether via IntelliJ or `catalina.bat run`. Once the zombie process was killed and a clean instance started, the JNDI-based MySQL connection worked immediately, in both IntelliJ's launcher and a bare `catalina.bat run`.

### Lesson
Check `netstat -ano | findstr :8080` (and `:8005`) *before* starting Tomcat any time behavior seems inconsistent between runs — this is now a standing habit, not a one-off fix.

---

## Issue 2 — `catalina.bat` failing outside IntelliJ

Two separate small issues surfaced while trying to reproduce the bug outside IntelliJ, for isolation purposes:

- **`'catalina.bat' is not recognized`** — caused by running `cd` across drive letters in `cmd.exe`, which changes the path but not the active drive. Fixed by switching drives explicitly first (`E:` then `cd ...`).
- **`Neither the JAVA_HOME nor the JRE_HOME environment variable is defined`** — the JDK only existed at a user-specific path (`C:\Users\<user>\.jdks\ms-17.0.20`) known to IntelliJ, not on a system-wide `JAVA_HOME`. Fixed per-session with `set JAVA_HOME=C:\Users\<user>\.jdks\ms-17.0.20` before running `catalina.bat run`.

---

## Issue 3 — Book cover images 404ing (category images fine)

### Symptoms
Category tile images rendered correctly; book cover images did not, across every book.

### Root cause
The Vue component responsible for building book image filenames (`CategoryBookListItem.vue`) generated a slug from the book title and appended `.jpg`:
```ts
return `${name}.jpg`
```
but the actual asset files in `public/book-images/` were all `.png`. Every single book image request 404'd for the same reason.

### Fix
One-line change:
```ts
return `${name}.png`
```

### Lesson
When only some visual elements on a page fail to load, check whether they're served through a different mechanism (static files matched by naming convention vs. a database-driven path) before assuming the root cause is shared.

---

## Issue 4 — Frontend fix not reflected after rebuilding

### Symptoms
After fixing Issue 3, the Vite dev server (`localhost:5173`) showed the fix correctly; the Tomcat-served app (`localhost:8080/ShaeBookstoreState/`) still showed broken image icons, even after multiple WAR rebuilds.

### Root cause
`ShaeBookstoreState`'s `build.gradle` has **no automation** connecting the Vue client project to the Java backend's WAR. `npm run build` (client) produces a `dist/` folder completely independently; nothing copies that output into `ShaeBookstoreState/src/main/webapp` automatically. Without that manual copy step, `gradlew clean build` just re-bundles whatever stale frontend files were already sitting in `webapp` — in this case, an `index.html` and `assets/` folder last touched in 2023.

### Fix
Manual pipeline, every time the frontend changes:
```
cd shae-bookstore-state-client
npm run build
```
then copy `dist/index.html` and `dist/assets/` into `ShaeBookstoreState/src/main/webapp`, **replacing** the old versions, before running:
```
gradlew clean build
```

### Lesson
Vite's production build hashes JS/CSS filenames per build (`index-<hash>.js`); comparing the hash referenced in `webapp/index.html` against the hash actually present in `webapp/assets/` is a fast, reliable way to confirm whether a copy step genuinely happened, without needing to deploy or open a browser at all.

---

## Issue 5 — Stale `index.html` served from browser cache

### Symptoms
Even after confirming (via direct WAR inspection) that a freshly built, fully correct WAR was deployed, the browser continued requesting old `.jpg`-based, old-hash asset filenames.

### Root cause
Vite's production `index.html` has no content hash in its own filename (unlike its JS/CSS references), so browsers can cache it more aggressively across reloads than the hashed assets it points to. A normal refresh continued serving a cached copy of the *old* `index.html`, which in turn requested the *old*, no-longer-present JS bundle.

### Fix
DevTools → Network tab → "Disable cache" checkbox, combined with a hard refresh (`Ctrl+Shift+R`), while sitting on the app's root route.

### Lesson
When "the deployed file is definitely correct but the browser is definitely not showing it," suspect the entry HTML file's cache behavior specifically — hashed assets busted their own cache; the unhashed entry point did not.

---

## Issue 6 — 404 on hard refresh at a deep client-side route

### Symptoms
```
GET http://localhost:8080/ShaeBookstoreState/category/LGBTQ+ 404 (Not Found)
```
occurred specifically when hard-refreshing while sitting on a category page.

### Root cause
`/category/LGBTQ+` is a **Vue Router** client-side route, not a real server-side path. Tomcat has no file or servlet mapped to it, so a direct/full-page request for that exact URL correctly 404s — this is standard single-page-app behavior, not a bug introduced by anything above.

### Workaround used
Navigate to the app's root (`/`) before hard-refreshing, then use in-app navigation (which Vue Router handles client-side, no server round-trip) to reach a category page.

### Proper fix (not yet applied)
Add a 404 fallback to `web.xml` inside the existing `<web-app>` root element (not as a second top-level tag):
```xml
<error-page>
    <error-code>404</error-code>
    <location>/index.html</location>
</error-page>
```
This lets Tomcat serve `index.html` for any unmatched path, allowing Vue Router to take over and render the correct view client-side.

---

## Summary Table

| # | Symptom | Root Cause | Fix |
|---|---|---|---|
| 1 | JDBC connection timeout, every app | Zombie Tomcat process on 8080/8005 | Kill stale process before starting Tomcat |
| 2 | `catalina.bat` errors outside IntelliJ | Drive-letter `cd` + missing `JAVA_HOME` | Switch drive explicitly; `set JAVA_HOME` per session |
| 3 | Book images 404 | Slugify function used `.jpg`, files are `.png` | One-line extension fix |
| 4 | Frontend fix not appearing in deployed app | No Gradle automation linking client build → webapp | Manual `npm run build` → copy `dist` → `gradlew clean build` |
| 5 | Browser shows stale build despite correct WAR | Unhashed `index.html` aggressively cached | Disable cache + hard refresh |
| 6 | 404 on hard-refresh at a deep route | SPA route with no server-side mapping | Refresh at root; add `error-page` fallback to `web.xml` (pending) |

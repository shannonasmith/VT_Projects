<div align="center">

# 🛠️ Environment Setup & Troubleshooting Guide

![Focus](https://img.shields.io/badge/Focus-Local%20Dev%20Environment-orange?style=for-the-badge)
![Platform](https://img.shields.io/badge/Platform-Windows%20%7C%20IntelliJ-blue?style=for-the-badge)
![Covers](https://img.shields.io/badge/Covers-Node%20%7C%20Tomcat%20%7C%20MySQL%20%7C%20Gradle-9cf?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Living%20Document-brightgreen?style=for-the-badge)
![Type](https://img.shields.io/badge/Type-Reference%2C%20Not%20a%20Changelog-lightgrey?style=for-the-badge)

</div>

---

## 🧭 Why This Document Exists

This document captures environment setup and troubleshooting steps needed to get the
Vue client, Tomcat server, MySQL, and the build/deploy pipeline working correctly on a
local development machine. It is **not** a log of bugs in the bookstore application code
itself — it's infrastructure and tooling setup, kept here so the same fixes don't need to
be rediscovered at each new project stage. Started during P3, extended during P6/P7.

---

## 🟢 Node.js and npm

### Add `npm` to PATH

If `npm` is not recognized in a terminal, confirm Node is actually installed:
```powershell
Test-Path "C:\Program Files\nodejs\node.exe"
```
If `True` but `npm` still isn't recognized, add it to PATH:
```powershell
[Environment]::SetEnvironmentVariable("Path", $env:Path + ";C:\Program Files\nodejs", "User")
```
Close and reopen your terminal afterward.

### PowerShell execution policy blocking `npm`

Symptom:
```
File C:\Program Files\nodejs\npm.ps1 cannot be loaded because running scripts is disabled on this system.
```
This is a Windows default that blocks PowerShell scripts, including npm's own `.ps1`
wrapper. Fix (safe, standard developer setting — allows local scripts, still requires a
signature for scripts downloaded from the internet):
```powershell
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

### `esbuild` platform-mismatch error on `npm run dev` / `npm run build`

Symptom:
```
You installed esbuild for another platform than the one you're currently using...
```
This happens when `node_modules` was copied between machines (or between projects)
rather than freshly installed — native binaries inside it (like esbuild's) are
platform-specific. Fix: delete and reinstall clean on the current machine.
```powershell
Remove-Item -Recurse -Force node_modules
Remove-Item -Force package-lock.json
npm install
```

If `npm install` reports pending install scripts that need approval (a newer npm security
feature):
```powershell
npm approve-scripts esbuild
npm approve-scripts vue-demi
npm install
```

### Downloaded `.ps1` scripts won't run ("not recognized")

If a script sits confirmed in your current directory (`dir *.ps1` shows it) but still
won't run, it's likely flagged by Windows' "Mark of the Web" as downloaded/untrusted,
independent of execution policy:
```powershell
Unblock-File -Path ".\scriptname.ps1"
```

### PowerShell won't run a script/executable sitting in the current folder

Symptom:
```
The term '.\something.bat' is not recognized...
```
or, without the `.\` prefix, `'something.bat' is not recognized as the name of a cmdlet...`.
Unlike `cmd.exe`, PowerShell requires an explicit `.\` prefix to run anything from the
current directory rather than requiring it be on PATH. Fix: prefix the command —
`.\catalina.bat run`, `.\gradlew.bat clean build`, etc.

### `cd` across drive letters doesn't switch the active drive

In `cmd.exe`, `cd E:\some\path` while sitting on `C:` changes the *path* but not the
active *drive* — the very next command still runs from `C:` and fails to find anything.
Fix: switch drives explicitly first.
```
E:
cd E:\some\path
```

---

## 🐘 Tomcat / IntelliJ Configuration

### Deployed app shows a 404, or the URL shows `Gradle___[Name]Project_war`

The Tomcat run configuration's **Application context** field defaulted to an
auto-generated artifact name instead of the intended path. Fix: **Run → Edit
Configurations → [your Tomcat config] → Deployment tab → Application context**, set it
explicitly to `/[Name]Project` (matching what `context.xml` and the project spec expect),
then redeploy.

### Gradle JDK mismatch ("Unsupported class file major version")

If an older Gradle wrapper version is paired with a newer JDK (e.g., Gradle 7.x with JDK
20/21), sync will fail. Fix: **File → Settings → Build, Execution, Deployment → Build
Tools → Gradle → Gradle JDK**, select a JDK version actually compatible with the project's
Gradle version (JDK 17 is broadly safe for this course).

### `catalina.bat` fails with "Neither JAVA_HOME nor JRE_HOME is defined"

Happens when the JDK is only registered inside IntelliJ (e.g. under
`C:\Users\<user>\.jdks\...`) rather than as a system-wide `JAVA_HOME`. IntelliJ's own
Tomcat launcher supplies this silently; a bare `catalina.bat run` from a terminal does
not. Fix, per terminal session:
```powershell
$env:JAVA_HOME = "C:\Users\<user>\.jdks\ms-17.0.20"
.\catalina.bat run
```

### `Connection timed out` connecting to MySQL from every Tomcat-hosted app, but the MySQL CLI and a bare Java socket test both connect fine

This is almost never MySQL, DNS, the firewall, or the JDBC driver — despite how it looks
from the exception (`java.net.ConnectException` deep inside `StandardSocketFactory`).
Before investigating further, check for the most common actual cause:

**A stale/zombie Tomcat process is still bound to ports 8080 and/or 8005** from a
previous session that didn't fully terminate. A new launch attempt (from IntelliJ or
`catalina.bat`) doesn't always throw a clean "address already in use" error in this
state — it can instead surface several layers downstream as a JDBC timeout, since the
request is being handled or silently dropped by the existing broken instance.
```powershell
netstat -ano | findstr :8080
netstat -ano | findstr :8005
taskkill /PID <pid> /F
```
Confirm both ports are fully clear (no `LISTENING` line) before starting Tomcat again.
**Make this a standing check before every Tomcat start**, not just a one-time fix — it's
by far the fastest thing to rule out first.

### `Execution failed for task ':war'` — duplicate `WEB-INF/classes` entry

Symptom:
```
Entry WEB-INF/classes/api/SomeClass.class is a duplicate but no duplicate handling
strategy has been set.
```
Caused by compiled `classes/` and/or `lib/` folders sitting directly inside
`src/main/webapp/WEB-INF` in the source tree, rather than only ever being generated by
Gradle's `war` plugin at build time. `src/main/webapp/WEB-INF` should only ever contain
hand-authored config (`web.xml`, etc.) — never `.class` or `.jar` files. Delete any stray
`classes`/`lib` folders found there; Gradle regenerates them automatically.

### Missing `gradlew.bat` (only `gradlew` present)

Some project copies only have the Unix wrapper script, not the Windows one — usually
because the project originated on macOS/Linux. If you have a system Gradle install:
```powershell
gradle wrapper
```
regenerates both `gradlew` and `gradlew.bat`. Commit both going forward so a fresh clone
works on Windows without this step. If no system Gradle is available, build via
IntelliJ's Gradle panel (right-edge tab → Tasks → build → `build`) instead of the
terminal.

### 404 when hard-refreshing on a client-side route (e.g. `/category/FICTION`)

Symptom:
```
GET http://localhost:8080/SomeProject/category/FICTION 404 (Not Found)
```
Routes like `/category/:name` or `/cart` are handled entirely client-side by Vue Router
— Tomcat has no actual file or servlet mapped to them. Normal in-app navigation (clicking
a link) never hits the server for these paths, so this only surfaces when directly
loading or hard-refreshing on a "deep" URL. **Workaround:** navigate to the app's root
(`/`) before hard-refreshing, then use in-app navigation to reach the deep route again.
**Proper fix (apply once, not yet applied as of P7):** add a 404 fallback inside the
existing `<web-app>` root element of `web.xml` (nesting matters — a second top-level tag
will throw "Multiple root tags"):
```xml
<error-page>
    <error-code>404</error-code>
    <location>/index.html</location>
</error-page>
```
This lets Tomcat serve `index.html` for any unmatched path, allowing Vue Router to take
over and render the correct view client-side.

---

## 🖼️ Static Assets (Images, Icons, Logo)

### Client and server maintain separate copies

Each project stage typically has two independent halves — a Vite-based client
(`*-client`) and a Jakarta EE/Tomcat server. They do **not** share a filesystem at
runtime. The client serves static files from its own `public/` folder; the server serves
from its own `webapp/` folder. A file added to one will not automatically appear when
running the other — it must be copied to both, and if it's added to the client's
`public/` folder *after* the last production build, it won't be in `dist/` either until
`npm run build` runs again.

### Exact filename and extension matching

Image references in Vue components and stylesheets are often literal string paths (e.g.
`url("/site-images/background-image2.png")`). The actual file on disk must match that
string **exactly** — filename, numbering, and extension all have to line up. When an
image doesn't appear despite the file existing somewhere in the project, search the Vue
source for the actual reference before assuming a folder-location problem:
```powershell
Get-ChildItem -Path ".\src" -Recurse -File | Select-String -Pattern "keyword" -List
```
A recurring specific case across multiple project stages: a component's image-filename
generator function hardcodes `.jpg`, but the actual asset files are `.png` (or vice
versa). Every image request 404s identically until the extension is corrected in the
generator function itself — check this specifically before assuming a deeper bug.

### Flattening genre-subfoldered images into a flat structure

Some project stages' Vue code expects book images directly under `book-images/`, not
nested inside genre subfolders. A reusable script for this is included in this repository
(`flatten-and-convert-images.ps1`) — see its header comment for usage.

### Converting JPG to PNG in place

Some project stages' Vue code specifically expects `.png` book/site images rather than
`.jpg`. A reusable script for this is included in this repository
(`convert-to-png.ps1`) — see its header comment for usage. By default it preserves the
original `.jpg` files; pass `-DeleteOriginals` once you've confirmed the converted images
render correctly.

### Images not updating after a fix

If a corrected or newly added image still doesn't appear after confirming the file is in
the right place with the right name, this is very often **browser caching**, not a real
problem. Try, in order:
1. Hard refresh: **Ctrl+Shift+R**
2. DevTools (F12) → Network tab → check **"Disable cache"** → reload
3. An Incognito/Private browser window
4. Restart the Vite dev server (`Ctrl+C`, then `npm run dev` again)
5. As a last resort, clear Vite's cache: `Remove-Item -Recurse -Force node_modules\.vite`

**Note:** on the Tomcat-served (production-bundled) side specifically, steps 1–2 are not
always sufficient — a regular browser tab can keep serving a fully stale `index.html`
(and everything it references) even with a hard refresh and "disable cache" checked,
apparently well past what those two steps are supposed to guarantee. If images/behavior
still look wrong on `localhost:8080` after confirming (by directly inspecting the
deployed files on disk) that the server is genuinely serving the correct build, jump
straight to step 3 (Incognito) to confirm the server side is actually fine before
troubleshooting further — then clear that specific site's stored data in the regular
browser (site settings → cookies/site data → clear, for `localhost`) rather than
continuing to hard-refresh.

---

## 🔄 Build & Deploy Pipeline (Client → Server)

### The Gradle build does not automatically pull in the client's production build

`build.gradle` for these projects has no task that runs `npm run build` or copies the
client's `dist/` output into `src/main/webapp` — this is a fully manual step, easy to
forget, especially since the *symptom* of skipping it (an old build still showing) looks
identical to a caching problem rather than a "forgot a step" problem. Pipeline, every
time the client changes:
```powershell
cd <client-project>
npm run build
```
then manually copy `dist/index.html` and `dist/assets/` (and any `dist`-root static
folders like images, if the client's `public/` folder changed) into
`<server-project>/src/main/webapp`, **replacing** what's there — before running
`gradlew clean build`.

### Confirming a copy actually happened, without deploying or opening a browser

Vite hashes JS/CSS filenames per production build (`index-<hash>.js`). Compare the hash
referenced in `webapp/index.html` against the hash actually present in `webapp/assets/`
— if they match and are recent, the copy worked. This is the fastest way to rule out
"the fix isn't really in the build" versus "the build is fine but something downstream is
stale," and is much faster than a browser round-trip.

### Confirming what's actually inside a built WAR

Since the WAR is just a zip file, its contents can be inspected directly without
deploying it — useful for confirming `index.html`'s referenced hashes, whether
`META-INF/context.xml` is present with the right JNDI resource, and whether
`WEB-INF/classes` looks like a single clean compiled set with no leftover duplicates.
```powershell
Expand-Archive -Path project.war -DestinationPath extracted
```

### Client's `dist/` and `node_modules/` should never be committed or copied between machines

`node_modules` contains platform-specific native binaries (see the esbuild issue above);
`dist` is fully regenerated by `npm run build`. Both belong in `.gitignore`. Similarly,
`src/main/webapp/index.html` and `src/main/webapp/assets/` on the server side are
generated output copied in from the client — not really hand-authored "source," and
prone to going stale if committed and forgotten about.

### A required-but-unused component prop can silently fail the build

`vue-tsc` type-checking (run as part of `npm run build` via `run-p type-check
build-only`) will fail the whole `npm run build` command if a component declares a
required prop via `defineProps<{...}>()` that the parent never actually passes — even if
the prop is never used inside the component and the actual bundling (`vite build`) step
succeeds independently. Watch for `type-check` exiting non-zero in the build output even
when `dist/` files were still written; delete unused prop declarations rather than
leaving them as dead code.

### Two independent frontend builds always exist side by side

The Vite dev server (`npm run dev`, typically port 5173) and the Tomcat-bundled
production build (port 8080) are **completely separate build artifacts** at all times. A
fix made to the source code is live instantly on 5173 (hot reload) but does **not**
affect 8080 until the full build-and-copy pipeline above is run again. When comparing
behavior between the two, always check the browser tab's/console's actual asset hash
(e.g. `index-<hash>.js` in a console error) to confirm which build is actually being
observed before concluding a fix "isn't working."

---

## 🌐 Networking Deep-Dive Reference (rarely needed, but useful if the above doesn't resolve it)

If a connection issue is *not* explained by a port conflict (above), these were ruled out
in one full pass and are documented here so they don't need to be re-checked from
scratch:
- MySQL service health, `Threads_connected`, `max_connections`
- Windows Firewall (fully disabled as a test)
- IntelliJ proxy settings
- DNS/hostname resolution for `localhost`, at both the OS level (`ping`, `nslookup`) and
  inside the JVM (`InetAddress.getByName("localhost")`)
- Windows `hosts` file contents
- Active VPN adapters (`ipconfig /all` — check `Media State`, a present-but-disconnected
  adapter is not the cause)
- JVM-level hosts override (`jdk.net.hosts.file` in the JDK's `conf/net.properties`)
- Environment variables (`JAVA_TOOL_OPTIONS`, `JDK_JAVA_OPTIONS`, `JAVA_OPTS`,
  `CATALINA_OPTS`)
- Tomcat's `setenv.bat`, `catalina.properties`, and global `conf/context.xml`
- Known CVEs in project dependencies (checked, but these don't cause connection-level
  symptoms — a CVE in a JSON/logging library has no code path affecting sockets or DNS)

If all of the above are clean and a connection issue persists, use packet-level tools
(RawCap for Windows loopback capture, since standard Wireshark can't see loopback traffic
without extra setup) or Resource Monitor's TCP Connections tab to see whether the OS is
even attempting the handshake before assuming an application-level cause.

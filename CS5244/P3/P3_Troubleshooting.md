<div align="center">

# 🛠️ P3 – Environment Setup & Troubleshooting Guide

![Focus](https://img.shields.io/badge/Focus-Local%20Dev%20Environment-orange?style=for-the-badge)

</div>

---

## 🧭 Why This Document Exists

This document captures environment setup and troubleshooting steps needed to get the
Vue client, Tomcat server, and static assets working correctly on a local development
machine. It is **not** a log of bugs in the bookstore application code itself — it's
infrastructure and tooling setup, kept here so the same fixes don't need to be
rediscovered at each new project stage.

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

### `esbuild` platform-mismatch error on `npm run dev`

Symptom:
```
You installed esbuild for another platform than the one you're currently using...
```
This happens when `node_modules` was copied between machines rather than freshly
installed — native binaries inside it (like esbuild's) are platform-specific. Fix: delete
and reinstall clean on the current machine.
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

---

## 🖼️ Static Assets (Images, Icons, Logo)

### Client and server maintain separate copies

Each project stage typically has two independent halves — a Vite-based client
(`*-client`) and a Jakarta EE/Tomcat server. They do **not** share a filesystem at
runtime. The client serves static files from its own `public/` folder; the server serves
from its own `webapp/` folder. A file added to one will not automatically appear when
running the other — it must be copied to both.

### Exact filename and extension matching

Image references in Vue components and stylesheets are often literal string paths (e.g.
`url("/site-images/background-image2.png")`). The actual file on disk must match that
string **exactly** — filename, numbering, and extension all have to line up. When an
image doesn't appear despite the file existing somewhere in the project, search the Vue
source for the actual reference before assuming a folder-location problem:
```powershell
Get-ChildItem -Path ".\src" -Recurse -File | Select-String -Pattern "keyword" -List
```

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


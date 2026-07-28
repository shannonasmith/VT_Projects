<div align="center">

# 📚 CS 5244 – Web Application Development (Virginia Tech)
## 🛒 Shae's Books — Full-Stack Bookstore Application

![Focus](https://img.shields.io/badge/Focus-Vue.js%20%7C%20Java%20REST%20API%20%7C%20MySQL-blue?style=for-the-badge)
![Approach](https://img.shields.io/badge/Approach-Design%20→%20Static%20→%20Dynamic%20→%20Transactional-success?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-In%20Progress-yellow?style=for-the-badge)

</div>

---

## 🧠 What This Is

A full-stack online bookstore application, "Shae's Books," built in ten staged projects -
starting from Figma wireframes, through static HTML/CSS, a Vue.js single-page application,
a Java DAO/REST API backend, and finishing with server-side transactions and hardened
routing. Each stage adds one architectural layer on top of the last, culminating in a
complete client-server e-commerce flow.

**⚠️ Status note:** This repo is being rebuilt from an earlier version that depended on a
MySQL database hosted on Virginia Tech's course server, which is no longer reachable
post-graduation. Projects 1-3 (design and static/SPA implementation, no backend
dependency) are complete below. Projects 4-10 depend on a replacement database setup and
are in progress.

---

## 🧬 Project Progression

---

### 🎨 P1 — [Application Design](./P1) 🔗

![Focus](https://img.shields.io/badge/Focus-Figma%20%7C%20UX%20Design-blue)

| Category | Details |
|---|---|
| Focus | Wireframing a Welcome Page and Category Page |
| Type | Design deliverable (Figma) |
| Output | Two annotated page mockups |

**What it does:** Designs the visual identity and layout for Shae's Books - logo, header/
footer, category navigation, and book grid - applying accessibility guidance and core
design principles before any code is written.

**Why it matters:** Establishes a consistent design system (colors, spacing, component
layout) that every later stage - static HTML, then Vue, then the full application -
implements against, rather than improvising layout decisions mid-build.

---

### 🏗️ P2 — [Page Views (HTML/CSS)](./P2) 🔗

![Focus](https://img.shields.io/badge/Focus-Static%20HTML%2FCSS-blue)

| Category | Details |
|---|---|
| Focus | Translating the Figma design into real markup |
| Type | Static HTML/CSS site |
| Output | `index.html`, `category.html` |

**What it does:** Implements the Welcome Page and Category Page as plain HTML and CSS - no
JavaScript, no frameworks, no preprocessors - with CSS deliberately split across files that
anticipate the Vue component boundaries introduced in the next project.

**Why it matters:** Confirms the design holds up as real, responsive markup (1000px-1400px)
before any framework complexity is introduced.

---

### 🖇️ P3 — [Page Views in Vue](./P3) 🔗

![Focus](https://img.shields.io/badge/Focus-Vue%203%20SPA-blue)

| Category | Details |
|---|---|
| Focus | Migrating static pages into a Vue Single-Page Application |
| Type | Vue 3 + Vite client, Jakarta EE/Tomcat server shell |
| Output | Home view and category view, client-side routed |

**What it does:** Re-implements the same two pages as Vue 3 views within one SPA, using
client-side routing (`/category/mystery`) in place of separate HTML files, with a Jakarta
EE server module set up to host the built client for deployment.

**Why it matters:** First stage with an actual client/server project pair - the
architecture every subsequent project builds on - even though no dynamic data is served
yet.

---

### 🔌 P4 — [DAO Pattern and REST API](./P4) 🔗

![Focus](https://img.shields.io/badge/Focus-Java%20DAO%20%7C%20REST%20API-lightgrey)

| Category | Details |
|---|---|
| Focus | MySQL-backed REST API using the DAO pattern |
| Type | Java/Jersey REST API |
| Output | *(in progress - blocked on database replacement)* |

**What it does:** Introduces a database-backed REST API serving category and book data as
JSON, built with the DAO (Data Access Object) pattern to separate persistence logic from
business logic.

---

### 📡 P5 — [Fetch](./P5) 🔗

![Focus](https://img.shields.io/badge/Focus-Client%2FServer%20Integration-lightgrey)

**What it does:** *(in progress)* Replaces the client's static data with real `fetch` calls
to the P4 REST API, with a CORS filter bridging the client and server ports.

---

### 🗃️ P6 — [State Management](./P6) 🔗

![Focus](https://img.shields.io/badge/Focus-Pinia-lightgrey)

**What it does:** *(in progress)* Centralizes category, book, and cart state into Pinia
stores.

---

### 🛍️ P7 — [Session Management](./P7) 🔗

![Focus](https://img.shields.io/badge/Focus-Cart%20%7C%20localStorage-lightgrey)

**What it does:** *(in progress)* Full cart page with quantity controls, persisted to
`localStorage` across sessions.

---

### ✅ P8 — [Client-Side Validation](./P8) 🔗

![Focus](https://img.shields.io/badge/Focus-Vuelidate-lightgrey)

**What it does:** *(in progress)* Checkout form validation on the client, using Vuelidate.

---

### 🛡️ P9 — [Server-Side Validation](./P9) 🔗

![Focus](https://img.shields.io/badge/Focus-Defense%20in%20Depth-lightgrey)

**What it does:** *(in progress)* Mirrors client-side validation on the server, returning
structured JSON errors - never trusting client-side checks alone.

---

### 💳 P10 — [Transactions & Hardening](./P10) 🔗

![Focus](https://img.shields.io/badge/Focus-DB%20Transactions%20%7C%20Robustness-lightgrey)

**What it does:** *(in progress)* Real order/customer/line-item database transactions with
rollback on failure, plus routing robustness - direct URL entry, page reloads, back-button
handling, and 404s.

---

## 🧠 How the Ten Projects Connect

| Stage | What It Adds | Key Technology |
|---|---|---|
| P1-P2 | Design and static implementation | Figma, HTML/CSS |
| P3 | SPA architecture, client/server split | Vue 3, Vite, Jakarta EE |
| P4-P5 | Real backend data, DAO pattern, REST | Java, Jersey, MySQL |
| P6-P7 | Centralized state, persistent cart | Pinia, localStorage |
| P8-P9 | Validation, client and server | Vuelidate, server-side checks |
| P10 | Real transactions, production-grade routing | JDBC transactions |

---

## 🛠️ Tech Stack

| Component | Detail |
|---|---|
| Frontend | Vue 3, Vite, TypeScript, Pinia |
| Backend | Java, Jakarta EE, Eclipse Jersey |
| Database | MySQL (DAO pattern) |
| Server | Apache Tomcat 10 |
| Validation | Vuelidate (client), custom server-side validation |

---

<div align="center">

## 👤 Shannon Smith

Cybersecurity | SOC Operations • Detection Engineering • Incident Response

</div>

<div align="center">

# 📱 CS 5254 – Mobile Application Development (Virginia Tech)
## 🤖 Android Fundamentals • Fragments & Persistence • Networking & Maps

![Focus](https://img.shields.io/badge/Focus-Kotlin%20%7C%20Android%20%7C%20Jetpack-blue?style=for-the-badge)
![Approach](https://img.shields.io/badge/Approach-Activities%20→%20Fragments%20→%20Networking-success?style=for-the-badge)
![Tech](https://img.shields.io/badge/Tech-Room%20%7C%20Retrofit%20%7C%20Navigation-black?style=for-the-badge)

</div>

---

## 🧠 What This Is

Eight Android Studio projects, following the *Big Nerd Ranch Guide* alongside course-specific deviations, progressing from a single `Activity` with basic view binding through Fragment-based navigation, Room-backed persistence, and a fully networked, map-integrated app. Where CS 5704 focused on structural quality behind shared interfaces, this coursework focuses on the Android platform itself - activity/fragment lifecycles, local and remote data, and the platform APIs (camera, sharing, maps) that connect an app to the rest of the device.

---

## 🧬 Project Progression

---

### 🌏 P0 — GeoQuiz — [Single-Activity Fundamentals](./P0) 🔗

![Focus](https://img.shields.io/badge/Focus-View%20Binding%20%7C%20Click%20Listeners-blue)

| Category | Details |
|---|---|
| Focus | Activity lifecycle, view binding, event handling |
| Type | Runnable Kotlin/Android app |
| Output | Multi-question true/false quiz with Snackbar feedback |

**What it does:** A single `AppCompatActivity` cycling through a small set of true/false geography questions, modeled as a lightweight `Question` data class, with immediate `Snackbar` feedback on each answer.

**Why it matters:** Establishes the core mental model every later project builds on - how a layout XML file becomes a live UI, and how user interaction routes back into Kotlin code through listeners - before any of the fragment, persistence, or networking complexity is introduced.

---

### 🧩 P1 — MultiQuiz (1A → 1C) — Multi-Question Quiz with Results

![Focus](https://img.shields.io/badge/Focus-ViewModel%20%7C%20State%20Management-blue)

| Stage | Focus |
|---|---|
| [`P1A`](./P1A) | Multi-answer selection state, functional Kotlin list processing | 
| [`P1B`](./P1B) | ViewModel-backed rotation safety, multi-question cycling with hints |
| [`P1C`](./P1C) | Second activity, Intent-based data passing, instrumented testing | 

**What it does:** Builds a multi-answer selection interface (1A), layers in a `QuizViewModel` so state survives rotation across four cycling questions with a Hint mechanic (1B), then adds a second `ResultsActivity` reached via Intent extras, tracking correct/submitted/hint counts with a full-reset option (1C).

**Why it matters:** This is where state management stops being trivial - once a screen can rotate at any moment and there's more than one question to track, keeping "what the UI shows" in sync with "what the quiz actually knows" requires a deliberate pattern, which is exactly what `ViewModel` and Intent-based activity communication solve.

---

### 💭 P2 — DreamCatcher (2A → 2C) — Fragments, Room & Full CRUD

![Focus](https://img.shields.io/badge/Focus-Fragments%20%7C%20Room%20%7C%20Navigation-green)

| Stage | Focus | 
|---|---|
| [`P2A`](./P2A) | List-detail fragments, no navigation yet | 
| [`P2B`](./P2B) | Room persistence, Navigation Component, reflection dialog | 
| [`P2C`](./P2C) | Dream creation, swipe-to-delete, sharing, camera, RecyclerView | 

**What it does:** Establishes independent list and detail fragments for tracking personal "dreams" and their progress entries (2A), connects them with a Room database and the Jetpack Navigation Component, adding a reflection-entry dialog (2B), then completes the app with dream creation, swipe-to-delete, sharing via implicit intent, camera-based photo attachment, and a scrollable entry RecyclerView (2C).

**Why it matters:** This is the full arc from static mockup to a real, persisted, share-capable app - and the point where the app starts touching the platform beyond its own boundaries, through implicit intents and a `FileProvider` for safely exposing captured photos to other apps.

---

### 🖼️ P3 — FancyGallery — [Networking, WebView & Maps](./P3) 🔗

![Focus](https://img.shields.io/badge/Focus-Retrofit%20%7C%20OSMDroid%20%7C%20WebView-purple)

| Category | Details |
|---|---|
| Focus | REST networking, JSON deserialization, WebView, interactive maps |
| Type | Runnable Kotlin/Android app |
| Output | Bottom-navigation app with a live photo gallery and a marker-based map |

**What it does:** A two-tab app fetching Flickr's interestingness feed via Retrofit and Moshi into a Coil-loaded image grid, with an in-app `WebView` browser for each photo's page, and an OSMDroid map placing photo-icon markers at each image's real-world geo-coordinates - both tabs sharing one Activity-scoped `MainViewModel`.

**Why it matters:** The capstone project of the course - the first to move past local, self-contained data into a fully networked app, and the first to connect two otherwise-independent screens (gallery and map) around one shared dataset via an Activity-scoped ViewModel.

---

## 🧠 How the Eight Projects Connect

| Project | What It Adds | Key Technology |
|---|---|---|
| GeoQuiz | Activity lifecycle, view binding, listeners | Kotlin, View Binding |
| MultiQuiz 1A–1C | ViewModel state, rotation safety, second-activity data passing | Kotlin, ViewModel, Intents |
| DreamCatcher 2A–2C | Fragments, Room persistence, Navigation Component, camera & sharing | Kotlin, Room, Navigation, FileProvider |
| FancyGallery | REST networking, WebView, interactive maps, shared cross-fragment state | Retrofit, Moshi, Coil, OSMDroid |

The progression moves from *single-activity fundamentals* (GeoQuiz) to *ViewModel-driven state across activities* (MultiQuiz) to *fragment-based, database-backed, platform-integrated apps* (DreamCatcher) to *fully networked, multi-screen apps sharing live data* (FancyGallery) - each stage building on the same core idea: keep UI state and underlying data in sync deliberately, using the pattern the platform provides for the job.

---

## 🛠️ Tech Stack

| Component | Detail |
|---|---|
| Language | Kotlin |
| IDE | Android Studio |
| Testing | JUnit 4, Espresso (instrumented) |
| Persistence | Room |
| Networking | Retrofit, Moshi, Coil |
| Navigation | Jetpack Navigation Component |
| Maps | OSMDroid |
| Build | Gradle |

---

<div align="center">

## 👤 Shannon Smith

Cybersecurity | SOC Operations • Detection Engineering • Incident Response

</div>

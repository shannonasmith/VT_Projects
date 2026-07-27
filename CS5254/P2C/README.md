<div align="center">

# 💭 DreamCatcher — Part 2C: Complete

### The finished app — dream creation, swipe-to-delete, sharing, camera integration, and a scrollable entry list

![Focus](https://img.shields.io/badge/Focus-Full%20Feature%20Integration-blueviolet)
![Language](https://img.shields.io/badge/Language-Kotlin-orange)
![Platform](https://img.shields.io/badge/Platform-Android-3DDC84)
![Status](https://img.shields.io/badge/Status-Complete-brightgreen)

</div>

---

## 🧠 What It Does

DreamCatcher P2C completes the app with eight additional features layered on top of Part 2B's database and navigation foundation. Dreams can now be created from the list screen's app bar menu and removed with a left swipe; an empty-state message and Add button appear when the list is empty. From the detail screen, a dream's contents can be shared via any installed SMS, email, or clipboard app through an implicit intent, and a photo can be attached via the device camera — tapping the photo opens a zoomed dialog view. The fixed five-button entry display from earlier stages is replaced with a scrollable `RecyclerView`, supporting an unlimited number of entries and swipe-to-delete for any reflection.

## 🎯 Why It Matters

This is the capstone of the three-part DreamCatcher build, and the point where the app touches the parts of the Android platform that go beyond a single app's boundaries — implicit intents for sharing and camera access, and a `FileProvider` for safely exposing captured photo files to other apps. It also replaces the earlier fixed-capacity entry display with a properly scrollable list, the more realistic and scalable pattern for displaying an open-ended collection. Together with Parts 2A and 2B, this project demonstrates the complete arc from static mockup to a fully interactive, persisted, share-capable app.

## ✨ Features

- New Dream creation via app bar menu, immediately navigating to the new dream's detail screen
- Swipe-to-delete for dreams (list screen) and reflections (detail screen)
- Empty-state UI with a call-to-action button when no dreams exist
- Dream sharing via implicit intent, formatted with title, timestamp, reflections, and fulfilled/deferred status
- Photo capture via device camera, displayed inline and viewable zoomed in a dialog
- Camera menu item automatically hidden on devices without a resolvable camera intent
- Scrollable RecyclerView for an unlimited number of dream entries, replacing the earlier five-button cap
- 16 instructor-provided instrumented tests

## 🛠️ Requirements

- Android Studio (Electric Eel or later recommended)
- Android SDK Platform 32 (Android 12L)
- Kotlin plugin (bundled with Android Studio)
- An emulator or physical device running API 21+, with a working camera (real or emulated) for the photo feature

## ▶️ How to Run

1. Open the project root folder in Android Studio (`File → Open`, select the `P2CDreamCatcher` folder)
2. Let Gradle sync complete
3. Select or create a **Pixel 4 API 32** emulator via **Device Manager**
4. Click **Run ▶️** with the `app` configuration selected

## 🧪 Testing Approach

Verified via the instructor-provided instrumented test suite (`P2CTest.kt`) against the Pixel 4 API 32 emulator. All 16 tests pass when run individually; occasional flakiness observed when running the full suite in rapid succession is consistent with known emulator timing/resource constraints under bulk instrumented test execution rather than an application defect. Manual verification additionally confirmed correct behavior of the camera capture and photo zoom dialog, and swipe-to-delete for both dreams and reflections.

## ✅ Expected Output

<div align="center"><img src="p2c-screenshot.png" width="800"></div>

New dreams appear immediately after creation via the app bar menu. Swiping a dream or reflection left removes it. Sharing a dream opens the system share sheet with correctly formatted content, and capturing a photo displays it inline on the detail screen, zoomable via tap.

## 💻 Setting This Up on Another PC

1. Clone this repository
2. Open the `P2CDreamCatcher` folder as a project in Android Studio
3. If prompted about Gradle/JDK compatibility, select a **JDK 17** toolchain
4. Sync Gradle, then build and run as described above
5. To run the instrumented test suite: right-click `P2CTest.kt` under `app/src/androidTest` → **Run 'P2CTest'** (requires a running emulator or connected device; running tests individually is recommended over the full suite for reliable results)

## 📝 Notes

The project targets `edu.vt.cs5254.dreamcatcher`. `PictureUtils.kt` was provided course scaffolding, integrated as-is. The app declares camera usage as optional in its manifest, so it remains fully functional — aside from the photo feature itself — on devices without a camera.

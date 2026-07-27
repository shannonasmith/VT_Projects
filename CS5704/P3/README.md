<div align="center">

# 🦆 CS 5704 – DuckSim: Design Patterns

![Focus](https://img.shields.io/badge/Focus-Six%20Design%20Patterns-purple?style=for-the-badge)

</div>

---

## 🧠 What It Does

A Kotlin/Swing duck pond simulator demonstrating six classic design patterns: Strategy,
Decorator, Factory/Singleton, Adapter, Observer, and Composite.

## 🎯 Why It Matters

Every pattern is demonstrated through real interactive behavior, not just class structure
- selecting ducks, grouping them into a flock, and watching capture/release/notification
propagate through that flock live in the GUI.

---

## ✨ Features

- **Strategy** - `FlyBehavior` (`FlyWithWings`, `FlyNoWay`) and `QuackBehavior`
  (`QuackNormal`, `QuackNoWay`, `QuackSqueak`, plus `QuackHonk` and `QuackNoise` for the
  Adapter/Composite variants) are swappable per-duck behaviors, independent of duck type
- **Decorator** - `Bling` decorates any duck with `StarBling`, `MoonBling`, or
  `CrossBling`, stacking multiple decorations onto a single duck's display string
- **Factory/Singleton** - `DuckFactory` is a Kotlin `object` (singleton) that assembles a
  base duck with any combination of bling via `createDuck()`
- **Adapter** - `GooseDuck` adapts a plain `Goose` class into the `Duck` interface, using
  the goose's own name/honk instead of duck defaults
- **Observer** - `DuckFactory` (subject) notifies registered ducks (observers) when new
  ducks are created, driving the Welcome!/Beware! message shown to ducks on the Welcoming
  Committee
- **Composite** - `Flock` is itself a `Duck` that holds a collection of ducks, propagating
  capture/release/join/quit actions to every duck it contains
- Six duck types: Mallard, Redhead, Rubber, Decoy, Goose (via adapter), and Flock (via
  composite)
- Interactive GUI: create ducks with custom bling, select ducks (turns the square to the
  duck's color), right-click for a context menu, and group selected ducks into a Flock via
  the "+" button

---

## 🛠️ Requirements

- JDK 11+ (JDK 17/Corretto also works)
- IntelliJ IDEA (Community Edition works fine) with the Kotlin plugin (bundled by default)

## ▶️ How to Run

1. Open IntelliJ IDEA
2. **File → Open**, select this project's folder (the one containing `src/main/kotlin`)
3. Click **Trust Project** if prompted
4. Let IntelliJ finish indexing (progress bar at the bottom)
5. In the Project panel: `src → main → kotlin → Main.kt`
6. Right-click `Main.kt` → **Run 'MainKt'**

A "DuckSim" window should appear with an empty pond and a "+" button in the bottom-left
corner.

### 🎮 Using the Simulator

- Click **+** to open the duck creation dialog: choose a duck type and optionally add up
  to 3 bling decorations, then click **Okay**
- **Left-click** a duck to select/deselect it (its square turns the duck's color when
  selected)
- With one or more ducks selected, click **+** to combine them into a **Flock** instead of
  opening the creation dialog
- **Right-click** a duck (with nothing selected) for a context menu: Fly, Quack, Join/Quit
  DSWC, Capture, Release, Delete
- Ducks on the DuckSim Welcoming Committee (DSWC) show a "W" in their square, and display
  Welcome!/Beware! when a new duck is created, depending on whether they're free or
  captured
- Press **Escape** to clear the current selection

---

## ✅ Expected Output

No automated tests for this project - verification is done by running the GUI directly
and confirming behavior matches the assignment's reference walkthrough (duck creation,
bling stacking, capture/release, DSWC welcome/beware messages, and flock
creation/behavior).

---

## 💻 Setting This Up on Another PC

**Option 1 — Download ZIP**
1. On GitHub, go to this repo → green **Code** button → **Download ZIP**
2. Unzip it wherever you want
3. Open the folder in IntelliJ (`File → Open`) and follow "How to Run" above

**Option 2 — Git Clone**
1. Install Git: https://git-scm.com/downloads
2. `git clone https://github.com/yourusername/your-repo.git`
3. Open the cloned folder in IntelliJ and follow "How to Run" above

---

## 📝 Notes

- `.idea/` and `out/` folders are intentionally not included - these regenerate
  automatically when you open the project fresh in IntelliJ.

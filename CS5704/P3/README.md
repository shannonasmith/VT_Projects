# DuckSim - Design Patterns (CS 5704)

A Kotlin/Swing duck pond simulator demonstrating six classic design patterns:
Strategy, Decorator, Factory/Singleton, Adapter, Observer, and Composite.

## Features

- **Strategy** - `FlyBehavior` (`FlyWithWings`, `FlyNoWay`) and `QuackBehavior`
  (`QuackNormal`, `QuackNoWay`, `QuackSqueak`, plus `QuackHonk` and
  `QuackNoise` for the Adapter/Composite variants) are swappable per-duck
  behaviors, independent of duck type
- **Decorator** - `Bling` decorates any duck with `StarBling`, `MoonBling`,
  or `CrossBling`, stacking multiple decorations onto a single duck's
  display string
- **Factory/Singleton** - `DuckFactory` is a Kotlin `object` (singleton) that
  assembles a base duck with any combination of bling via `createDuck()`
- **Adapter** - `GooseDuck` adapts a plain `Goose` class into the `Duck`
  interface, using the goose's own name/honk instead of duck defaults
- **Observer** - `DuckFactory` (subject) notifies registered ducks
  (observers) when new ducks are created, driving the Welcome!/Beware!
  message shown to ducks on the Welcoming Committee
- **Composite** - `Flock` is itself a `Duck` that holds a collection of
  ducks, propagating capture/release/join/quit actions to every duck it
  contains
- Six duck types: Mallard, Redhead, Rubber, Decoy, Goose (via adapter), and
  Flock (via composite)
- Interactive GUI: create ducks with custom bling, select ducks (turns the
  square to the duck's color), right-click for a context menu (fly, quack,
  join/quit DSWC, capture/release, delete), and group selected ducks into a
  Flock via the "+" button

## Requirements

- JDK 11+ (JDK 17/Corretto also works)
- IntelliJ IDEA (Community Edition works fine) with the Kotlin plugin
  (bundled by default)

## How to Run

1. Open IntelliJ IDEA
2. **File → Open**, select this project's folder (the one containing
   `src/main/kotlin`)
3. Click **Trust Project** if prompted
4. Let IntelliJ finish indexing (progress bar at the bottom)
5. In the Project panel: `src → main → kotlin → Main.kt`
6. Right-click `Main.kt` → **Run 'MainKt'**

A "DuckSim" window should appear with an empty pond and a "+" button in the
bottom-left corner.

## Using the Simulator

- Click **+** to open the duck creation dialog: choose a duck type and
  optionally add up to 3 bling decorations (star/cross/moon), then click
  **Okay**
- **Left-click** a duck to select/deselect it (its square turns the duck's
  color when selected)
- With one or more ducks selected, click **+** to combine them into a
  **Flock** instead of opening the creation dialog
- **Right-click** a duck (with nothing selected) for a context menu: Fly,
  Quack, Join/Quit DSWC, Capture, Release, Delete
- Ducks on the DuckSim Welcoming Committee (DSWC) show a "W" in their square,
  and display Welcome!/Beware! when a new duck is created, depending on
  whether they're free or captured
- Press **Escape** to clear the current selection

## Expected Output

No automated tests for this project - verification is done by running the
GUI directly and confirming behavior matches the assignment's reference
walkthrough (duck creation, bling stacking, capture/release, DSWC
welcome/beware messages, and flock creation/behavior).

## Setting This Up on Another PC

**Option 1: Download only (no git required)**
1. On GitHub, go to this repo -> green **Code** button -> **Download ZIP**
2. Unzip it wherever you want
3. Open the folder in IntelliJ (`File -> Open`) and follow "How to Run" above
4. Note: changes made this way don't sync back to GitHub automatically -
   you'd need to manually re-upload any changed files

**Option 2: Git clone (two-way sync)**
1. Install Git: https://git-scm.com/downloads
2. Open a terminal and run:
   ```
   git clone https://github.com/yourusername/your-repo.git
   ```
3. Open the cloned folder in IntelliJ and follow "How to Run" above
4. To pull future updates: `git pull`
5. To push changes back: `git add .`, `git commit -m "message"`, `git push`

Option 1 is simplest if you're just running/reviewing the project elsewhere.
Option 2 is worth it if you'll be actively editing code from more than one
machine and want changes to sync both ways.

## Notes

- `.idea/` and `out/` folders are intentionally not included - these are
  IDE-specific/build files that regenerate automatically when you open the
  project fresh in IntelliJ.

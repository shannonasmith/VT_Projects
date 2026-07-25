# River Crossing (CS 5704)

A generalized River Crossing puzzle engine, refactored from a single
farmer/wolf/goose/beans implementation into a design that supports multiple
puzzle variants (farmer, monster/munchkin, and a scout variant) sharing a
common engine and GUI.

## Features

- `GameEngine` interface defining the full puzzle contract: item labels,
  colors, driver status, locations, loading/unloading the boat, rowing,
  win/loss detection, and reset
- `AbstractGameEngine` implements the shared logic (item lookups, boat
  loading/unloading with a 2-passenger limit, `copy`-free state management
  via an `EnumMap<Item, GameObject>`) once, so each puzzle variant only needs
  to define its own items, win condition specifics, and rowing rule
- `FarmerGameEngine` - the classic farmer/wolf/goose/beans puzzle, where only
  the farmer can row and the goose can't be left alone with the wolf or beans
- `MonsterGameEngine` - a monster/munchkin variant with 3 monsters and 3
  munchkins, where any combination can row, but monsters can never
  outnumber munchkins on either shore (including whoever is currently on
  the boat, counted toward whichever shore the boat is at)
- `ScoutGameEngine` - a third variant (explorers and beavers)
- `RiverGUI` - a Swing front-end that renders items and the boat based purely
  on the active `GameEngine`'s reported items, labels, colors, and locations
  - not hardcoded to any specific puzzle variant
- `Item` enum supports up to 6 items (`ITEM_0`-`ITEM_5`), letting different
  engines use however many they need

## Requirements

- JDK 17+ (developed/tested with Java 17)
- IntelliJ IDEA (Community Edition works fine)
- JUnit 4

## How to Run

1. Open IntelliJ IDEA
2. **File → Open**, select this project's folder (the one containing `src/` and `test/`)
3. Click **Trust Project** if prompted
4. Let IntelliJ finish indexing (progress bar at the bottom)
5. If prompted with "Cannot resolve symbol 'junit'", click **Add 'JUnit4' to classpath**
   (Alt+Shift+Enter)
6. If the `test` folder shows a "located outside of the module source root" warning,
   right-click the `test` folder → **Mark Directory as → Test Sources Root**
7. In the Project panel: `test → river`
8. Right-click `FarmerGameEngineTest` → **Run**, then repeat for `MonsterGameEngineTest`

To see the actual GUI, right-click `RiverGUI.java` → **Run 'RiverGUI.main()'** - a
window should appear with the farmer puzzle loaded by default. Buttons at the
top let you restart into the Farmer, Monster, or Scout variant.

## Expected Output

All test methods in `FarmerGameEngineTest` and `MonsterGameEngineTest` should
pass (green checkmarks in IntelliJ's test runner panel, process exits with
code 0).

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

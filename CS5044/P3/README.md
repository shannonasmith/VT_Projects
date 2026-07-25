# P2 - Minivan Sliding Door (CS 5044)

Simulates the control logic for a minivan's automatic sliding door, including
door open/close, lock/unlock, child-safety lock, and gear shift state -
enforcing the safety rules that govern when each action is allowed to succeed.

## Features

- Track door open/closed, locked/unlocked, child-safe engaged/disengaged, and gear state
- Enforce safety rules for opening the door via dashboard button, outside handle, or inside handle
  (each has different requirements - e.g. the inside handle additionally respects child-safety)
- Support locking/unlocking and gear changes at any time
- Return a specific `Result` value for every action, following defined precedence rules when
  multiple conditions could apply (e.g. gear-not-in-park takes priority over door-locked)

## Requirements

- JDK 17+ (developed/tested with Java 17)
- IntelliJ IDEA (Community Edition works fine)

## How to Run

1. Open IntelliJ IDEA
2. **File → Open**, select this project's folder (the one containing `src/`)
3. Click **Trust Project** if prompted
4. Let IntelliJ finish indexing (progress bar at the bottom)
5. In the Project panel: `src → edu.vt.cs5044 → MinivanSlidingDoorTester`
6. Right-click `MinivanSlidingDoorTester` → **Run**

## Expected Output

The tester runs 37 sequential scenarios covering door open/close, lock/unlock,
child-safety, gear changes, and null-parameter handling, printing Expected vs.
Actual for each state and result. All values should match.

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
- `Result.java`, `Direction.java`, and `Gear.java` were provided as-is by the
  course and were not modified.

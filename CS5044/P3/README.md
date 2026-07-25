# P3 - Tetris AI (CS 5044)

An AI that plays Tetris by evaluating every possible placement of a falling
piece and selecting the one that minimizes a weighted cost function based on
board quality metrics.

## Features

- Evaluate all valid rotations and column positions for a given piece
- Score each resulting board state using four metrics:
  - **Average Column Height** - overall board height
  - **Column Height Range** - difference between tallest and shortest column
  - **Column Height Variance** - "bumpiness" between adjacent columns
  - **Total Gap Count** - buried empty cells beneath filled ones
- Select the placement with the lowest weighted combined cost

## Requirements

- JDK 17+ (developed/tested with Java 17)
- IntelliJ IDEA (Community Edition works fine)
- JUnit 4 (added as a project library - see setup note below)

## How to Run

1. Open IntelliJ IDEA
2. **File → Open**, select this project's folder (the one containing `src/` and `test/`)
3. Click **Trust Project** if prompted
4. Let IntelliJ finish indexing (progress bar at the bottom)
5. If prompted with "Cannot resolve symbol 'junit'", click **Add 'JUnit4' to classpath**
   (Alt+Shift+Enter) - this pulls in the JUnit 4 library needed to run the tests
6. If the `test` folder shows a "located outside of the module source root" warning,
   right-click the `test` folder → **Mark Directory as → Test Sources Root**
7. In the Project panel: `test → edu.vt.cs5044 → TetrisAITest`
8. Right-click `TetrisAITest` → **Run 'TetrisAITest'**

## Expected Output

All 5 test methods should pass: `testBP` (best placement across all 7 piece
shapes on multiple board layouts), `testACH`, `testCHR`, `testCHV`, and `testTGC`
(the four individual scoring metrics).

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
- The `AI`, `Board`, `Placement`, `Rotation`, and `Shape` classes/interfaces
  were provided by the course as the Tetris framework; `TetrisAI.java` is the
  implementation written for this assignment.

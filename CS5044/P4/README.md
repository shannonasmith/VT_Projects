# P4 - Dots and Boxes Game Engine (CS 5044)

Implements the game logic for Dots and Boxes: players take turns drawing edges
on a grid, claiming a box (and an extra turn) whenever their edge completes
all four sides of it. The game continues until every box is claimed, at which
point the player with the most boxes wins.

## Features

- Initialize a game grid of any size (minimum 2x2)
- Draw edges between grid points, with correct handling of shared edges
  between neighboring boxes
- Track box ownership, awarding an extra turn to whichever player completes
  a box (or two, if a single edge completes two boxes at once)
- Report current player, per-player scores, and game-over state
- Defensive checks: invalid grid sizes, invalid coordinates, null directions,
  and actions attempted before the game is initialized all throw `GameException`

## Requirements

- JDK 17+ (developed/tested with Java 17)
- IntelliJ IDEA (Community Edition works fine)
- JUnit 4 (added as a project library - see setup note below)
- `dab5044.jar` - course-provided framework library (included in this repo -
  see setup note below)

## How to Run

1. Open IntelliJ IDEA
2. **File → Open**, select this project's folder (the one containing `src/` and `test/`)
3. Click **Trust Project** if prompted
4. Let IntelliJ finish indexing (progress bar at the bottom)
5. If prompted with "Cannot resolve symbol 'junit'", click **Add 'JUnit4' to classpath**
   (Alt+Shift+Enter)
6. If the `test` folder shows a "located outside of the module source root" warning,
   right-click the `test` folder → **Mark Directory as → Test Sources Root**
7. If you see "Cannot resolve symbol" errors for `Player`, `Direction`, `Coordinate`,
   `DotsAndBoxes`, or `GameException` - these come from the course-provided
   `dab5044.jar` framework library, which is included in this repo (in the
   project root) and needs to be added to the project manually. Full steps below.

### Adding dab5044.jar to the Project in IntelliJ

1. **Open Project Structure**
   With the project open in IntelliJ, go to **File → Project Structure...**
   (or press **Ctrl+Alt+Shift+S**)

2. **Go to Libraries**
   In the left sidebar of the Project Structure window, click **Libraries**

3. **Add a new library**
   Click the **+** button at the top of the Libraries panel, then select
   **Java** from the dropdown

4. **Locate and select the jar**
   A file browser opens - navigate to `dab5044.jar` in this project's root
   folder, click on it to select it, then click **OK**

5. **Confirm which module it applies to**
   A dialog will pop up asking which module(s) should use this library -
   make sure this project's module is checked, then click **OK**

6. **Apply and close**
   Back in the main Project Structure window, click **Apply**, then **OK**
   to close it

7. **Verify it worked**
   Go back to `Box.java`, `DABGame.java`, or `DABGameTest.java` - the red
   underlines on `Player`, `Direction`, `Coordinate`, `DotsAndBoxes`, and
   `GameException` should be gone. If IntelliJ is still indexing/refreshing,
   give it a few seconds.

**Optional:** if you also want hover-documentation when looking at the
framework classes, repeat steps 3-6 but select `dab5044-api.jar` instead.
This isn't required to fix the errors - it's purely a nice-to-have for
reference while coding.

8. In the Project panel: `test → edu.vt.cs5044 → DABGameTest`
9. Right-click `DABGameTest` → **Run 'DABGameTest'**

## Testing Approach

The assignment required full code coverage, not just correctness, so
`DABGameTest` was written to exercise every method and branch in `DABGame`
and `Box` - including exception paths (uninitialized game access, invalid
grid sizes, invalid coordinates, null directions) and the box-completion
edge cases (a single edge completing one box, two boxes at once, or none).

## Expected Output

All test methods should pass (green checkmarks in IntelliJ's test runner panel,
process exits with code 0). One test (`testGame3x3`) prints a full trace of a
3x3 game being played move-by-move, ending with a final score and winner - this
is expected console output, not an error.

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

- `.idea/`, `out/`, `bin/`, `.settings/`, `.classpath`, and `.project` folders/files
  are intentionally not included - these are IDE-specific/build files that
  regenerate automatically, or leftover Eclipse artifacts not needed for the
  IntelliJ setup.
- `dab5044.jar` is a course-provided framework library, not something written
  for this assignment - it's included in the repo so the project runs without
  needing to track it down separately, but it's referenced as a dependency
  rather than being part of the actual implementation.

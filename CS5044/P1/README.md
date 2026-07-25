# P1 - Shuttle Battery Monitor (CS 5044)

Simulates an electric shuttle's battery usage as it travels between blocks,
tracking location, passenger count, energy usage, and trip statistics.
The shuttle uses energy at a normal rate for short trips and a different
rate for the portion of any trip beyond a configurable "short trip" limit.

## Features

- Track shuttle location and passenger count as it travels
- Calculate energy usage per trip, split between short-trip and long-trip rates
- Support one-off overrides of the short limit / long rate for individual trips
- Recharge the battery by returning to the origin (block zero)
- Report battery charge remaining (%), average energy usage per trip, and
  estimated trips remaining on current charge

## Requirements

- JDK 17+ (developed/tested with Java 17)
- IntelliJ IDEA (Community Edition works fine)

## How to Run

1. Open IntelliJ IDEA
2. **File → Open**, select this project's folder (the one containing `src/`)
3. Click **Trust Project** if prompted
4. Let IntelliJ finish indexing (progress bar at the bottom)
5. In the Project panel: `src → edu.vt.cs5044 → ShuttleBatteryMonitor`
6. Run the provided P1 Tester to verify behavior against the sample test cases

## Expected Output

The P1 Tester should report matching Expected/Actual values for Loc, #Pass,
%Charge, U/T, and #Trips across all sample test case parts, ending with
`Process finished with exit code 0`.

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
  twice. The fix delegates cleanly to `travelHelper()`, matching the pattern
  already used by the overloaded `travelTo(destination, shortLimitOverride,
  longRateOverride)` method.

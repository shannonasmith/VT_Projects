# P0 - Hello5044 (CS 5044)

Simple intro project from CS 5044 - a "Hello World" style starter assignment
that outputs a small ASCII art block spelling out the course number, instead
of a traditional one-line greeting.

## Requirements

- JDK 17+ (developed/tested with Java 17)
- IntelliJ IDEA (Community Edition works fine)

## How to Run

1. Open IntelliJ IDEA
2. **File → Open**, select this project's folder (the one containing `src/`)
3. Click **Trust Project** if prompted
4. Let IntelliJ finish indexing (progress bar at the bottom)
5. In the Project panel: `src → edu.vt.cs5044 → Hello5044`
6. Right-click `Hello5044` → **Run 'Hello5044.main()'**

## Expected Output

```
 CCCC    SSSS     555555   0000   44  44  44  44
CC  CC  SS  SS    55      00  00  44  44  44  44
CC       SS       55555   00  00  44  44  44  44
CC         SS         55  00  00  444444  444444
CC  CC  SS  SS    55  55  00  00      44      44
 CCCC    SSSS      5555    0000       44      44
```

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

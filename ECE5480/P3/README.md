# ECE 5480 – Project 3: Filesystem Automation & DNS Log Analysis

Two Python scripts demonstrating automation and log-analysis techniques useful in security
contexts: programmatically exploring a local filesystem, and parsing and summarizing a
large, semi-structured DNS server log.

## Scripts

### smith_shannon_p3_task_1.py
Prompts for a base directory path, then uses the `os` module to list its contents and sort
each item into a "files" list or a "directories" list, writing each list (with full paths)
to its own output text file. Demonstrates basic filesystem enumeration — the same building
block used for tasks like locating files of interest during an investigation or automating
system inventory.

### smith_shannon_p3_task_2.py
Reads a real-world DNS server log (tens of thousands of records) and filters it down to
valid Type A forward DNS query records, ignoring reverse lookups, error entries, and other
non-standard log lines. From the filtered records, it reports:
1. Earliest record timestamp
2. Latest record timestamp
3. Total number of valid records
4. Number of distinct client IP addresses (ignoring port number)
5. Number of distinct query domains
6. Most common client IP address and its occurrence count
7. Most common query domain and its occurrence count

Demonstrates parsing an inconsistent, real-world log format using only basic Python
constructs (string splitting, dictionaries, and loops) — a common task when triaging DNS
logs for anomalous or high-volume query activity.

## Requirements
- Python 3.x
- No external libraries required (standard library only: `os`, `datetime`, `calendar`)
- `dns_log_file.txt` must be present in the same folder as `smith_shannon_p3_task_2.py`

## How to Run (IntelliJ)

1. Open IntelliJ IDEA and go to **File → Open**, then select the project folder.
2. If needed, install the Python plugin: **File → Settings → Plugins → Marketplace**, search
   "Python", install, restart IntelliJ.
3. Set up a Python interpreter: **File → Project Structure → Project → SDK → Add SDK →
   Python SDK**.
4. For Task 1: right-click `smith_shannon_p3_task_1.py` and select **Run**. In the Run
   console, enter a base directory path to scan (e.g. a folder on your machine). This
   produces `smith_shannon_found_files.txt` and `smith_shannon_found_dirs.txt`.
5. For Task 2: make sure `dns_log_file.txt` is in the project folder, then right-click
   `smith_shannon_p3_task_2.py` and select **Run**. Output prints directly to the console.

**Gotchas:**
- **Task 1** writes paths using Windows-style backslash separators (`\`) by design, matching
  the original assignment's Windows path format. On macOS/Linux the paths will still be
  written correctly relative to the input directory, but with backslashes rather than
  forward slashes.
- **Task 2** is memory-light but processes tens of thousands of lines — expect it to take a
  few seconds to run depending on your machine.

## Testing Approach
Task 1 was verified by running it against a test directory containing a mix of files and
subdirectories and confirming the output files correctly separated and listed each with its
full path. Task 2 was verified against the real DNS log file, and all seven reported values
(record counts, timestamps, unique IP/domain counts, and most-common values) were
cross-checked using independent command-line tools (`awk`, `sort`, `uniq`) to confirm
correctness. The record filter identifies Type A forward DNS queries while explicitly
excluding any record whose queried domain is in reverse-lookup format
(`*.in-addr.arpa`), even if logged under record type A.

## Setting This Up on Another PC

**Option 1 — Download ZIP**
1. Download this folder as a ZIP and extract it.
2. Open the extracted folder in IntelliJ (**File → Open**) and follow "How to Run" above.

**Option 2 — Git Clone**
1. `git clone <repo-url>`
2. Open the cloned folder in IntelliJ (**File → Open**) and follow "How to Run" above.

## Notes
Both scripts are original work written for ECE 5480, Project 3, building on
instructor-provided starter templates and the datetime-parsing pattern demonstrated in the
course's calendar/time example file.

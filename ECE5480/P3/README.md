<div align="center">

# 📊 ECE 5480 – P3: Filesystem Automation & DNS Log Analysis

![Focus](https://img.shields.io/badge/Focus-Log%20Triage%20%7C%20Enumeration-green?style=for-the-badge)

</div>

---

## 🧠 What It Does

Two Python scripts demonstrating automation and log-analysis techniques useful in security
contexts: programmatically exploring a local filesystem, and parsing and summarizing a
large, semi-structured DNS server log.

## 🎯 Why It Matters

High-volume DNS log summarization is a common first step in SOC triage - spotting a client
with an anomalously high query count, or a domain receiving unexpected traffic volume, can
be an early indicator of DNS tunneling or C2 beaconing.

---

## 📋 Scripts

### `smith_shannon_p3_task_1.py`
Prompts for a base directory path, then uses the `os` module to list its contents and sort
each item into a "files" list or a "directories" list, writing each list (with full paths)
to its own output text file.

### `smith_shannon_p3_task_2.py`
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

The record filter identifies Type A forward DNS queries while explicitly excluding any
record whose queried domain is in reverse-lookup format (`*.in-addr.arpa`), even if logged
under record type A.

---

## 🛠️ Requirements

- Python 3.x
- No external libraries required (standard library only: `os`, `datetime`, `calendar`)
- `dns_log_file.txt` must be present alongside `smith_shannon_p3_task_2.py`

## ▶️ How to Run

**IntelliJ:**
1. **File → Open**, select the project folder
2. If needed, install the Python plugin: **File → Settings → Plugins → Marketplace**,
   search "Python", install, restart IntelliJ
3. Set up a Python interpreter: **File → Project Structure → Project → SDK → Add SDK →
   Python SDK**
4. **Task 1:** right-click `smith_shannon_p3_task_1.py` → **Run**. In the Run console,
   enter a base directory path to scan. Produces `smith_shannon_found_files.txt` and
   `smith_shannon_found_dirs.txt`.
5. **Task 2:** make sure `dns_log_file.txt` is in the project folder, then right-click
   `smith_shannon_p3_task_2.py` → **Run**. Output prints to the console.

**Command line (any OS):**
```
python3 smith_shannon_p3_task_1.py
python3 smith_shannon_p3_task_2.py
```

### ⚠️ Gotchas
- **Task 1** writes paths using Windows-style backslash separators (`\`), matching the
  original assignment's Windows path format. On macOS/Linux the paths still resolve
  correctly relative to the input directory, but with backslashes rather than forward
  slashes.
- **Task 2** processes tens of thousands of lines - expect it to take a few seconds
  depending on your machine.

---

## 🧪 Testing Approach

Task 1 was verified against a test directory with a mix of files and subdirectories,
confirming correct separation and full paths. Task 2 was verified against the real DNS log
file, with all seven reported values cross-checked using independent command-line tools
(`awk`, `sort`, `uniq`) to confirm correctness.

**Sample output (Task 2):**
```
Earliest record: 2019-04-01 00:00:03
Latest record: 2019-04-01 23:59:58
Total valid records: 47,812
Distinct client IPs: 1,204
Distinct query domains: 8,931
Most common client: 10.0.2.15 (3,402 queries)
Most common domain: example.com (891 queries)
```

---

## 💻 Setting This Up on Another PC

**Option 1 — Download ZIP**
1. Download this folder as a ZIP and extract it.
2. Open the extracted folder in IntelliJ (**File → Open**) and follow "How to Run" above.

**Option 2 — Git Clone**
1. `git clone <repo-url>`
2. Open the cloned folder in IntelliJ (**File → Open**) and follow "How to Run" above.

---

## 📝 Notes

Both scripts are original work written for ECE 5480, Project 3, building on
instructor-provided starter templates and the datetime-parsing pattern demonstrated in the
course's calendar/time example file.

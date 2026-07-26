# ECE 5480 – Project 1: Python for Security Automation

A set of four short Python scripts exploring core libraries used in security automation and
reconnaissance: fetching and parsing web content, crafting search URLs, and automating host
availability checks.

## Tasks

### Task 1 — Page Fetcher (`smith_shannon_project1_task1.py`)
Prompts the user for a URL, fetches the page contents with `requests` (using a spoofed
User-Agent header to avoid basic bot-blocking), and writes the raw response text to a local
file (`egg.txt`).

### Task 2 — HTML Link Parser (`smith_shannon_project1_task2.py`)
Fetches a target webpage and uses `BeautifulSoup` to parse the HTML and print the first 10
`<a>` link elements found on the page — a basic example of structured content extraction from
an otherwise messy HTML document.

### Task 3 — Scripted Search / Link Harvesting (`smith_shannon_project1_task3.py`)
Takes search terms as command-line arguments, constructs a Google Scholar search URL from
them, parses the results with `BeautifulSoup`, prints the top 5 result links, and opens each
one in a browser tab. Demonstrates command-line argument handling (`sys.argv`) and
programmatic URL construction — the same underlying technique used in URL-injection style
attacks, applied here for benign search automation.

### Task 4 — Host Availability Sweep (`smith_shannon_project1_task4.py`)
Pings a list of IP addresses (3 pings each) and reports each host as UP or DOWN based on the
ping command's return code. A simple example of using Python to wrap and summarize the output
of a system utility — the same pattern used in automated network reconnaissance and health
checks.

## Requirements
- Python 3.x
- `requests`
- `beautifulsoup4`

Install dependencies:
```
pip install requests beautifulsoup4
```

## How to Run (IntelliJ)

1. Open IntelliJ IDEA and go to **File → Open**, then select the project folder.
2. If needed, install the Python plugin: **File → Settings → Plugins → Marketplace**, search
   "Python", install, restart IntelliJ.
3. Set up a Python interpreter: **File → Project Structure → Project → SDK → Add SDK →
   Python SDK**.
4. Install the required packages: open the IntelliJ **Terminal** tab and run
   `pip install requests beautifulsoup4`.
5. Right-click a script in the Project pane and select **Run** to execute it.

**Gotchas:**
- **Task 1** will prompt for a URL in the Run console — enter a full URL including `https://`.
- **Task 3** requires command-line arguments (the search terms). In IntelliJ, set these via
  **Run → Edit Configurations → Program arguments** (e.g. `IOT security`) rather than typing
  them at a prompt.
- **Task 4** uses Windows `ping` syntax (`-n` for ping count). On macOS/Linux, change `-n` to
  `-c` in the `subprocess.Popen` call for the script to work.
- Tasks 1–3 require an active internet connection and depend on the target site's HTML
  structure remaining stable; if a target site changes its layout, the parsing logic may need
  adjusting.

## Testing Approach
Each script was independently verified by running it and confirming output against expected
results (a live HTTP fetch and write for Task 1, parsed link lists for Tasks 2 and 3, and
UP/DOWN host status for Task 4).

## Setting This Up on Another PC

**Option 1 — Download ZIP**
1. Download this folder as a ZIP and extract it.
2. Open the extracted folder in IntelliJ (**File → Open**) and follow "How to Run" above.

**Option 2 — Git Clone**
1. `git clone <repo-url>`
2. Open the cloned folder in IntelliJ (**File → Open**) and follow "How to Run" above.

## Notes
All scripts in this project are original work written for ECE 5480, Project 1.

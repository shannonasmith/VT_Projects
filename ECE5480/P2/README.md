# ECE 5480 – Project 2: Rainbow Table Password Recovery

Two Python scripts demonstrating a rainbow table attack against MD5-hashed passwords: one
builds the rainbow table from a plaintext password list, and the other uses it to recover
plaintext passwords from a list of captured hashes, timing each lookup.

## Scripts

### smith_shannon_p2_create_rainbow_table.py
Reads a list of ~10,000 common plaintext passwords, computes the MD5 hash of each, and
writes the resulting hash list to a rainbow table file. This is the attacker's
precomputation step — building the lookup table once so it can be reused instantly against
any future captured hash, rather than hashing candidates on the fly.

### smith_shannon_p2_recover_hashed_passwords.py
Takes a list of captured password hashes and searches the rainbow table for each one. When
a match is found, it reports the recovered plaintext password and how long the lookup took
(in microseconds); if no match is found, it reports that instead. This demonstrates the core
tradeoff of rainbow table attacks: fast password recovery at the cost of upfront table
storage, versus recomputing hashes for every attempt.

## Requirements
- Python 3.x
- No external libraries required (standard library only: `hashlib`, `timeit`)

## How to Run (IntelliJ)

1. Open IntelliJ IDEA and go to **File → Open**, then select the project folder.
2. If needed, install the Python plugin: **File → Settings → Plugins → Marketplace**, search
   "Python", install, restart IntelliJ.
3. Set up a Python interpreter: **File → Project Structure → Project → SDK → Add SDK →
   Python SDK**.
4. Make sure the following data files are present in the same folder as the scripts:
   - `p2_10K_PLAINTEXT_PASSWORDS.txt` (input password list)
   - `p2_RECOVERED_PASSWORD_HASHES.txt` (captured hashes to crack)
5. Right-click `smith_shannon_p2_create_rainbow_table.py` and select **Run**. This generates
   `smith_shannon_rainbow_table.txt` in the project folder.
6. Right-click `smith_shannon_p2_recover_hashed_passwords.py` and select **Run**. This reads
   the rainbow table generated in the previous step and attempts to recover each captured
   hash, printing matches and timing for each.

**Gotcha:** run the scripts in order — the recovery script depends on the rainbow table file
produced by the first script and will fail if it hasn't been generated yet.

## Testing Approach
Verified end-to-end: the rainbow table script was run against the 10K password list and its
output hash values were confirmed against the assignment's known-good validation case
(`football` → `37b4e2d82900d5e94b8da524fbeb33c0`). The recovery script was then run against
the generated table and a set of captured hashes, and its recovered plaintext passwords were
confirmed to match expected results for every hash in the test set.

## Setting This Up on Another PC

**Option 1 — Download ZIP**
1. Download this folder as a ZIP and extract it.
2. Open the extracted folder in IntelliJ (**File → Open**) and follow "How to Run" above.

**Option 2 — Git Clone**
1. `git clone <repo-url>`
2. Open the cloned folder in IntelliJ (**File → Open**) and follow "How to Run" above.

## Notes
Both scripts are original work written for ECE 5480, Project 2, building on
instructor-provided incomplete starter templates (`create_rainbow_table_INCOMPLETE.py` and
`recover_hashed_passwords_INCOMPLETE.py`) and file I/O and timing patterns provided in the
assignment specification.

<div align="center">

# 🔑 ECE 5480 – P2: Rainbow Table Password Recovery

![Focus](https://img.shields.io/badge/Focus-Offline%20Password%20Attacks-orange?style=for-the-badge)

</div>

---

## 🧠 What It Does

Two Python scripts demonstrating a rainbow table attack against MD5-hashed passwords: one
builds the rainbow table from a plaintext password list, and the other uses it to recover
plaintext passwords from a list of captured hashes, timing each lookup.

## 🎯 Why It Matters

This demonstrates the core precompute-once, reuse-forever tradeoff of rainbow table
attacks - and, more importantly, why it fails against modern systems. This attack works
because the passwords are unsalted MD5 hashes. Adding a per-password salt (as real systems
do) defeats precomputed rainbow tables entirely, since the attacker would need a separate
table per salt value.

> **Note:** demonstrated here for educational purposes against provided sample data only.

---

## 📋 Scripts

### `smith_shannon_p2_create_rainbow_table.py`
Reads a list of ~10,000 common plaintext passwords, computes the MD5 hash of each, and
writes the resulting hash list to a rainbow table file - the attacker's precomputation
step, built once so it can be reused instantly against any future captured hash.

### `smith_shannon_p2_recover_hashed_passwords.py`
Takes a list of captured password hashes and searches the rainbow table for each one. When
a match is found, it reports the recovered plaintext password and how long the lookup took
(in microseconds); if no match is found, it reports that instead.

---

## 🛠️ Requirements

- Python 3.x
- No external libraries required (standard library only: `hashlib`, `timeit`)

## ▶️ How to Run

**IntelliJ:**
1. **File → Open**, select the project folder
2. If needed, install the Python plugin: **File → Settings → Plugins → Marketplace**,
   search "Python", install, restart IntelliJ
3. Set up a Python interpreter: **File → Project Structure → Project → SDK → Add SDK →
   Python SDK**
4. Make sure these data files are present alongside the scripts:
   - `p2_10K_PLAINTEXT_PASSWORDS.txt` (input password list)
   - `p2_RECOVERED_PASSWORD_HASHES.txt` (captured hashes to crack)
5. Right-click `smith_shannon_p2_create_rainbow_table.py` → **Run**. This generates
   `smith_shannon_rainbow_table.txt`.
6. Right-click `smith_shannon_p2_recover_hashed_passwords.py` → **Run**. This reads the
   generated table and attempts to recover each captured hash.

**Command line (any OS):**
```
python3 smith_shannon_p2_create_rainbow_table.py
python3 smith_shannon_p2_recover_hashed_passwords.py
```

### ⚠️ Gotcha
Run the scripts in order - the recovery script depends on the rainbow table file produced
by the first script and will fail if it hasn't been generated yet.

---

## 🧪 Testing Approach

Verified end-to-end: the rainbow table script's output hash values were confirmed against
the assignment's known-good validation case (`football` → `37b4e2d82900d5e94b8da524fbeb33c0`).
The recovery script's recovered plaintext passwords were confirmed against expected
results for every hash in the test set.

**Sample output:**
```
Hash: 37b4e2d82900d5e94b8da524fbeb33c0 -> Recovered: football (0.042 ms)
Hash: 5f4dcc3b5aa765d61d8327deb882cf99 -> Not found (0.038 ms)
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

Both scripts are original work written for ECE 5480, Project 2, building on
instructor-provided incomplete starter templates (`create_rainbow_table_INCOMPLETE.py` and
`recover_hashed_passwords_INCOMPLETE.py`) and the file I/O and timing patterns provided in
the assignment specification.

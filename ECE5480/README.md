<div align="center">

# 🔐 ECE 5480 – Cybersecurity and the Internet of Things
## 🐍 Security Automation • Password Attacks • Web & Network Exploitation

![Focus](https://img.shields.io/badge/Focus-Python%20Automation%20%7C%20Web%20Attacks%20%7C%20DNS%20Attacks-blue?style=for-the-badge)
![Approach](https://img.shields.io/badge/Approach-Automation%20→%20Exploitation%20→%20Analysis-success?style=for-the-badge)
![Tech](https://img.shields.io/badge/Tech-Python%20%7C%20Scapy%20%7C%20SEED%20Labs-black?style=for-the-badge)

</div>

---

## 🧠 What This Is

Coursework from Virginia Tech's ECE 5480, covering Python for security automation,
password/hash attacks, filesystem and log analysis, and hands-on web and network attack
labs (CSRF, XSS, and DNS spoofing/poisoning) in a SEED Labs Docker/VM environment.

Six projects, split into two kinds of work: **automation scripts** you can run and verify
directly, and **attack lab exercises** conducted in an isolated, provided lab environment
against test accounts and applications.

---

## 🧬 Project Progression

---

### 🐍 P1 — [Python for Security Automation](./P1) 🔗

![Focus](https://img.shields.io/badge/Focus-Reconnaissance%20%7C%20Automation-blue)

| Category | Details |
|---|---|
| Focus | Web content fetching, HTML parsing, search automation, host sweeps |
| Type | Runnable Python scripts |
| Output | Fetched page content, parsed link lists, UP/DOWN host status |

**What it does:** Four short scripts exploring core libraries used in security automation
and reconnaissance — fetching and parsing web content with `requests`/`BeautifulSoup`,
constructing search URLs and harvesting result links from command-line arguments, and
sweeping a list of hosts for availability via `ping`.

**Why it matters:** Establishes the foundational automation patterns (structured content
extraction, command-line-driven scripting, wrapping system utilities) that scale directly
into reconnaissance and monitoring tooling.

---

### 🔑 P2 — [Rainbow Table Password Recovery](./P2) 🔗

![Focus](https://img.shields.io/badge/Focus-Offline%20Password%20Attacks-orange)

| Category | Details |
|---|---|
| Focus | MD5 rainbow table construction and lookup |
| Type | Runnable Python scripts |
| Output | Recovered plaintext passwords with per-lookup timing |

**What it does:** Builds an MD5 rainbow table from a list of ~10,000 common plaintext
passwords, then recovers plaintext passwords from a set of captured hashes by lookup
against the precomputed table, timing each match.

**Why it matters:** Demonstrates the core precompute-once, reuse-forever tradeoff behind
rainbow table attacks - and why it fails against salted hashes, which is the actual
defense modern systems rely on.

---

### 📊 P3 — [Filesystem Automation & DNS Log Analysis](./P3) 🔗

![Focus](https://img.shields.io/badge/Focus-Log%20Triage%20%7C%20Enumeration-green)

| Category | Details |
|---|---|
| Focus | Filesystem enumeration and large-scale DNS log parsing |
| Type | Runnable Python scripts |
| Output | Categorized file/directory listings, DNS query statistics |

**What it does:** One script enumerates a directory tree into files and subdirectories.
The other parses tens of thousands of real DNS server log records, filtering to valid
forward A-record queries and reporting record counts, distinct client/domain counts, and
the most frequent client and domain.

**Why it matters:** High-volume DNS log summarization is a common first step in SOC
triage - an anomalously high query count from one client, or unexpected volume to one
domain, can be an early indicator of DNS tunneling or C2 beaconing.

---

### 🎭 P4 — [Cross-Site Request Forgery (CSRF) Attack Lab](./P4) 🔗

![Focus](https://img.shields.io/badge/Focus-Web%20Exploitation-red)

| Category | Details |
|---|---|
| Focus | Forging authenticated actions via a victim's browser |
| Type | Attack lab writeup |
| Output | Forced "add friend" action with no victim interaction |

**What it does:** Forges an authenticated "add friend" request against Elgg using a
1x1 invisible image tag - loading the malicious page silently fires the forged request,
carrying the victim's real session cookie along with it. Also documents Elgg's
token-based countermeasure and why the attacker can't forge a valid token.

**Why it matters:** Shows how much trust a web app implicitly places in "a request came
from a logged-in browser," and why state-changing actions need their own unforgeable
proof of intent, not just a valid session cookie.

---

### 🐛 P5 — [Cross-Site Scripting (XSS) Attack Lab](./P5) 🔗

![Focus](https://img.shields.io/badge/Focus-Web%20Exploitation%20%7C%20Worm-red)

| Category | Details |
|---|---|
| Focus | Script injection, session hijacking, self-propagation |
| Type | Attack lab writeup |
| Output | Self-propagating "add friend" worm modeled on the 2005 Samy worm |

**What it does:** Escalates from a proof-of-concept `alert()` injection through cookie
disclosure, cookie exfiltration to an attacker-controlled listener, and finally a
self-propagating payload that reads the victim's own valid CSRF tokens out of the page
and uses them to add the attacker as a friend automatically on page view.

**Why it matters:** Demonstrates that XSS isn't just "a popup shows up" - injected script
runs with the full trust of the vulnerable site, which is what makes cookie theft and
self-propagating worms possible from the same root cause.

---

### 🌐 P6 — [Local DNS Attack Lab](./P6) 🔗

![Focus](https://img.shields.io/badge/Focus-Network%20Exploitation%20%7C%20DNS-red)

| Category | Details |
|---|---|
| Focus | Response spoofing, cache poisoning, domain-wide NS hijacking |
| Type | Attack lab writeup |
| Output | Forged DNS answers escalating to full-domain compromise |

**What it does:** Three escalating Scapy-based attacks - spoofing a single DNS response
to a victim, poisoning a DNS server's cache so a forged answer persists for every future
query, and forging an Authority-section NS record so the attacker becomes the trusted
nameserver for an entire domain rather than one hostname.

**Why it matters:** Shows how the same root weakness (DNS over UDP has no way to verify
which reply is authentic) scales from a one-time forged answer to compromising every
hostname under a domain, with no further attacker effort required.

---

## 🧠 How the Six Projects Connect

| Project | What It Adds | Key Technology |
|---|---|---|
| P1 | Foundational scripting: fetch, parse, automate | `requests`, `BeautifulSoup` |
| P2 | Offline attack: precompute once, crack instantly | `hashlib`, `timeit` |
| P3 | Enumeration and log triage at scale | `os`, `datetime` |
| P4 | Forging a single authenticated action | SEED Labs Elgg, HTTP Header Live |
| P5 | Script injection escalating to self-propagation | SEED Labs Elgg |
| P6 | Spoofing one answer escalating to domain hijack | Scapy, BIND 9 |

The project set is designed to build in two directions at once: P1-P3 build automation and
analysis skill with real Python, while P4-P6 build attack methodology understanding,
each project escalating the scope of compromise from the last.

---

## 🛠️ Tech Stack

| Component | Detail |
|---|---|
| Language | Python 3 |
| Web/HTML | `requests`, `BeautifulSoup` |
| Hashing | `hashlib` (MD5) |
| Packet Crafting | `Scapy` |
| Lab Environment | SEED Labs (Docker/VirtualBox), Ubuntu 20.04 |
| Target Application | Elgg (open-source social networking platform) |
| DNS Infrastructure | BIND 9 |

---

## 🧠 Course Skill Alignment

| Domain | Project Coverage |
|---|---|
| Security Automation | P1 (recon scripting), P3 (log triage automation) |
| Offline Attacks | P2 (password recovery) |
| Web Exploitation | P4 (CSRF), P5 (XSS) |
| Network Exploitation | P6 (DNS spoofing/poisoning) |
| Log/Data Analysis | P3 (DNS log statistics at scale) |

---

<div align="center">

## 👤 Shannon Smith

Cybersecurity | SOC Operations • Detection Engineering • Incident Response

</div>

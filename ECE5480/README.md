# ECE 5480 – Cybersecurity and the Internet of Things (Virginia Tech)

Coursework from Virginia Tech's ECE 5480, covering Python for
security automation, password/hash attacks, filesystem and log analysis, and hands-on web
and network attack labs (CSRF, XSS, and DNS spoofing/poisoning) in a SEED Labs
Docker/VM environment.

## Contents

| Folder | Type | Summary |
|---|---|---|
| [Project1](./P1) | Scripts | Python for security automation — fetching and parsing web content with `requests`/`BeautifulSoup`, scripted search/link harvesting, and automated host availability sweeps. |
| [Project2](./P2) | Scripts | Rainbow table password attack — builds an MD5 rainbow table from a plaintext password list, then recovers plaintext passwords from captured hashes with per-lookup timing. |
| [Project3](./P3) | Scripts | Filesystem enumeration and large-scale DNS log analysis — parses tens of thousands of real DNS server log records to extract query statistics (unique clients, unique domains, most-common values). |
| [Project4](./P4) | Attack lab writeup | Cross-Site Request Forgery (CSRF) attack against a vulnerable web application (Elgg) — forged an authenticated "add friend" action from a victim's browser with no user interaction, plus the token-based countermeasure that defeats it. |
| [Project5](./P5) | Attack lab writeup | Cross-Site Scripting (XSS) attack progression against the same application — from basic script injection through session cookie exfiltration to a self-propagating worm modeled on the 2005 Samy MySpace worm. |
| [Project6](./P6) | Attack lab writeup | Local DNS attacks using Scapy — direct response spoofing, DNS cache poisoning, and full-domain compromise via forged NS (nameserver) records. |

## Skills Demonstrated

- **Python for security automation:** `requests`, `BeautifulSoup`, `subprocess`, `os`,
  `hashlib`, `datetime`, `Scapy`
- **Offensive techniques:** password cracking (rainbow tables), CSRF, XSS (including
  session hijacking and self-propagating payloads), DNS spoofing and cache poisoning
- **Defensive analysis:** log parsing and triage at scale, understanding and explaining
  countermeasures (CSRF tokens, DNS cache validation)
- **Environment setup:** Docker-based isolated lab environments (SEED Labs), VirtualBox/VM
  administration

## Notes on Structure

Projects 1–3 are runnable Python projects — each has its own README with setup and "How to
Run" instructions for IntelliJ. Projects 4–6 are attack-lab exercises conducted in a
provided VM/Docker environment rather than standalone scripts; each has a README documenting
the attack methodology, payloads, and results rather than run instructions, since the
"program" in those cases is the lab environment itself, not code authored from scratch.

All course-provided lab setup files (Docker configurations, vulnerable web applications,
starter/skeleton code) are excluded from this repo — only original work is included.

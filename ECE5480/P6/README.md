<div align="center">

# 🌐 ECE 5480 – P6: Local DNS Attack Lab

![Focus](https://img.shields.io/badge/Focus-Network%20Exploitation%20%7C%20DNS-red?style=for-the-badge)

</div>

---

## 🧠 What It Does

Three progressively more powerful DNS attacks demonstrated against a local DNS server
environment (a victim user, a local BIND 9 DNS server, and an attacker with a malicious
nameserver), using Scapy for packet sniffing and spoofing - from the SEED Labs DNS Attack
Lab.

## 🎯 Why It Matters

Shows how the same root weakness (DNS over UDP has no way to verify which reply is
authentic) scales from a one-time forged answer to compromising every hostname under a
domain, with no further attacker effort required.

> **Ethical use:** conducted entirely within an isolated, provided lab environment against
> a local test domain; not directed at any real DNS infrastructure.

---

## 🖧 Lab Environment

Four machines on the same LAN:

| Role | IP Address |
|---|---|
| User (victim) | 10.9.0.5 |
| Local DNS server | 10.9.0.53 |
| Attacker | 10.9.0.153 |

The local DNS server forwards any query for `attacker32.com` to the attacker's own
nameserver (a legitimate forwarding rule, exploited here for the attack setup), and the
user's DNS resolver is configured to use the local server as its primary nameserver.

Baseline verification confirmed the setup was correct before any attack was launched:
querying `ns.attacker32.com` correctly resolved to the attacker's IP (10.9.0.153), and
querying `www.example.com` through the local server returned the real, legitimate IP
(93.184.216.34) - while querying `ns.attacker32.com` directly for `www.example.com`
returned a fabricated address the attacker controls.

---

## 🎯 Task 1 — Directly Spoofing a Response to the User

**Goal:** race a forged DNS response to the victim before the real answer arrives.

When a user queries a hostname, their machine sends a DNS request to the local server. If
an attacker on the same network can sniff that request, they can immediately craft a fake
response and send it back - if the forged reply reaches the victim before the legitimate
one, it's accepted, since DNS over UDP has no built-in way to verify which reply is
authentic.

```python
def spoof_dns(pkt):
    if (DNS in pkt and NS_NAME in pkt[DNS].qd.qname.decode('utf-8')):
        ip = IP(...)                    # swap source/destination to impersonate the DNS server
        udp = UDP(...)
        Anssec = DNSRR(...)             # forged answer record with attacker-controlled IP
        dns = DNS(...)
        spoofpkt = ip/udp/dns
        send(spoofpkt)
pkt = sniff(iface='<network interface>', filter='udp and dst port 53', prn=spoof_dns)
```

**Result:** after launching the spoofing script and clearing the DNS server's cache,
querying `www.example.com` from the victim machine returned the forged address instead of
the real one:

```
ANSWER SECTION:
www.example.com.       259200  IN  A     1.2.3.4
```

---

## 🎯 Task 2 — DNS Cache Poisoning Attack

Spoofing every individual user request is inefficient - the attacker only wins the race if
they're actively watching the network each time. A more durable attack targets the DNS
*server* instead: if the attacker's forged response reaches the server first, the server
caches it and will keep serving the poisoned answer to every future query, for the
lifetime of the cache entry.

Same sniff-and-forge approach as Task 1, but the forged response is aimed at winning the
race to the DNS server's own upstream query. After flushing the DNS server's cache
(`rndc flush`) and running the attack, the server's cache itself was inspected directly:

```
$ rndc dumpdb -cache
$ cat /var/cache/bind/dump.db
; authanswer
_.example.com.          863822   A   1.2.3.4
; authanswer
www.example.com.        863822   A   1.2.3.4
```

The poisoned record was now stored server-side - any subsequent user querying
`www.example.com`, even without the attacker present at that moment, would receive the
forged IP directly from the (now-compromised) local DNS server's cache.

---

## 🎯 Task 3 — Spoofing NS Records (Poisoning an Entire Domain)

Task 2's poisoning only affects the one hostname that was queried - a query for
`mail.example.com` would still resolve correctly and require a fresh attack. Task 3
escalates the attack to compromise the *entire domain* at once by spoofing the
**Authority section** of the DNS response rather than just the Answer section.

By including a forged NS (nameserver) record for `example.com` pointing to the attacker's
own nameserver, the local DNS server caches the attacker as the authoritative source for
*any* hostname under `example.com`:

```
;; AUTHORITY SECTION:
example.com.        259200  IN  NS  ns.attacker32.com.
```

After launching the attack and flushing the server cache, three separate lookups
(`www.example.com`, `mail.example.com`, and `ns.attacker32.com`) were issued from the
victim machine, all resolving to attacker-controlled addresses without any further
attacker intervention between queries:

```
; authanswer
ns.attacker32.com.      863876   A    10.9.0.153
; authauthority
example.com.            863876   NS   ns.attacker32.com.
; authanswer
_.example.com.          863876   A    1.2.3.4
mail.example.com.       863917   A    1.2.3.6
www.example.com.        863876   A    1.2.3.5
```

The attacker's nameserver (10.9.0.153) is now recorded as the authoritative NS for the
entire `example.com` domain - every hostname under it resolves through attacker-controlled
infrastructure, not just the one originally targeted.

---

## 🛡️ Countermeasures (Reference)

None of these defenses were enabled in this lab; they're the standard real-world
mitigations for this class of attack:

- **DNSSEC** - cryptographically signs DNS records with a chain of trust rooted at the DNS
  root zone. A resolver validating DNSSEC signatures rejects any forged answer or NS
  record outright, since the attacker cannot produce a valid signature without the
  domain's private signing key.
- **Source port and query ID randomization** - modern resolvers randomize both the UDP
  source port and the 16-bit query transaction ID for every outgoing query, instead of
  using predictable values. This forces a would-be spoofer to guess both correctly within
  the race window, making blind response-spoofing far less practical than it was in this
  lab's simplified sniff-then-forge setup.
- **0x20 encoding** - randomizing the case of letters in the query name (e.g.
  `wWw.ExAmPlE.cOm`) and requiring the response to echo the exact same casing back. This
  adds extra unpredictable bits an attacker must also guess correctly, at negligible cost
  since DNS names are case-insensitive for resolution purposes.

---

## 🛠️ Requirements

- VirtualBox or equivalent VM software
- SEED Ubuntu 20.04 VM image
- Docker and Docker Compose (pre-configured in the SEED VM)
- Scapy (pre-installed in the SEED VM)

## ⚙️ Setup

1. Download and unzip the lab setup package into the SEED VM's shared folder.
2. From the `Labsetup` folder, run `docker-compose build` (one-time), then
   `docker-compose up` to start the DNS server, user, and attacker containers.
3. In the `volumes` folder (shared between host and containers), update the network
   interface name in each attack script to match the actual interface for IP `10.9.0.1`
   (find it with `ifconfig`).
4. Before each attack, flush the local DNS server's cache (`rndc flush` from inside that
   container) so stale cached answers don't mask the attack's effect.
5. Launch the relevant attack script (`sudo ./dns_sniff_spoof.py`, `dns_sniff_poison.py`,
   or `dns_sniff_authority.py`) from the `volumes` folder, then issue `dig` queries from
   the user container to observe the result.

### ⚠️ Gotcha
Stopping and restarting the containers (`docker-compose down` / `up`) can reassign new
container IDs and a new network interface name - re-verify both before re-running an
attack after a restart, or the script will silently fail to bind to the right interface.

---

## 📝 Notes

This lab uses course-provided infrastructure (Docker container configuration, the BIND 9
DNS server setup, and the SEED Labs environment) - the attack execution and analysis above
are original work, built from instructor-provided Scapy skeleton scripts.

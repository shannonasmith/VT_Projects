<div align="center">

# 🎭 ECE 5480 – P4: Cross-Site Request Forgery (CSRF) Attack Lab

![Focus](https://img.shields.io/badge/Focus-Web%20Exploitation-red?style=for-the-badge)

</div>

---

## 🧠 What It Does

A hands-on demonstration of a Cross-Site Request Forgery attack against Elgg, an
open-source social networking web application, using the SEED Labs CSRF lab environment
(Docker containers running a vulnerable Elgg instance and an attacker-controlled site).

## 🎯 Why It Matters

Shows how much trust a web app implicitly places in "a request came from a logged-in
browser," and why state-changing actions need their own unforgeable proof of intent - not
just a valid session cookie.

> **Ethical use:** conducted entirely within an isolated, provided lab environment against
> test accounts; not directed at any real system or user.

---

## 🕵️ What Is CSRF?

Cross-Site Request Forgery exploits the trust a web application places in a logged-in
user's browser. If a victim is authenticated to a trusted site (holding an active session
cookie) and then visits a malicious site, that malicious site can silently trigger requests
to the trusted site on the victim's behalf - the browser automatically attaches the
victim's session cookie to the request, and the trusted site has no way to tell the
request wasn't intentionally made by the user.

---

## 🎯 Attack Scenario

**Goal:** Boby wants to be added to Alice's friends list on Elgg, but Alice has refused.
Boby uses CSRF to force the addition without Alice's consent or knowledge - she only has
to visit a webpage.

### Step 1 — Observing a Legitimate Request
Using the "HTTP Header Live" Firefox extension, captured the legitimate "Add Friend" HTTP
GET request that Elgg generates, revealing the exact request format including the target
user's GUID and two security parameters (`__elgg_ts` and `__elgg_token`).

### Step 2 — Identifying the GUIDs
Captured Alice's GUID (56) from the Add Friend request header, and found Boby's GUID (57)
by inspecting the `elgg.session.user` object embedded in Elgg's page source.

### Step 3 — Building the Attack Payload
Constructed a malicious webpage (`addfriend.html`) that forges the Add Friend request
using an `<img>` tag. Because browsers automatically send an HTTP GET request to fetch any
image source - even a 1x1 invisible one - simply loading the page triggers the forged
request with no click or user action required:

```html
<html>
<body>
<h1>This page forges an HTTP GET request</h1>
<img src="http://www.seed-server.com/action/friends/add?friend=57&__elgg_ts=1690229168&__elgg_token=b4c6OkGFfsr3vpRxyODFXQ"
     alt="image" width="1" height="1" />
</body>
</html>
```

The `friend` parameter is set to Boby's GUID (57) - this determines *who* gets added to
the victim's friends list once the forged request succeeds.

<div align="center">
     <img src="attack-payload-addfriend-html.png" width="700">
</div>

### Step 4 — Delivering the Payload
As Boby, sent Alice an in-Elgg message containing a link to the malicious page
(`www.attacker32.com`) - the social engineering component, since Alice has no reason to
suspect a link from a known contact.

### Step 5 — Executing the Attack
When Alice clicked the link and visited the attacker's page while still logged into Elgg,
her browser automatically fired the forged GET request to the real Elgg server, carrying
her valid session cookie along with it.

<div align="center">
     <img src="attack-traffic-capture.png" width="700">
</div>

### Step 6 — Confirming Success
Before the attack, Alice's friends list was empty. After she visited the malicious page,
Boby appeared in her friends list - despite her never clicking "Add Friend" or taking any
explicit action to add him.

<div align="center">
     <img src="attack-success-friends-list.png" width="700">
</div>

---

## 🛡️ Countermeasure: Secret Tokens

Elgg's built-in defense against CSRF embeds two values in every state-changing request:
`__elgg_ts` (a timestamp) and `__elgg_token` (an HMAC-based secret token derived from the
timestamp, the user's session ID, and a site-wide secret key). The server validates both
before processing any action.

**Why the attacker can't forge these:** the token is generated server-side from data the
attacker has no access to - specifically the victim's session token and the site secret.
An attacker crafting a CSRF payload in advance can guess the *parameter names*
(`__elgg_token`, `__elgg_ts`) but cannot predict or compute the *correct value* for a given
victim's session. With the countermeasure enabled, the forged request in this lab still
carried token and timestamp parameters, but their values didn't match the victim's actual
session - so Elgg rejected the request and the friend-add failed.

---

## 🛠️ Requirements

- VirtualBox or equivalent VM software
- SEED Ubuntu 20.04 VM image
- Docker and Docker Compose (pre-configured in the SEED VM)
- Firefox with the "HTTP Header Live" extension

## ⚙️ Setup

1. Download and unzip the lab setup package into the SEED VM.
2. From the `Labsetup` folder, run `docker-compose build` (one-time), then
   `docker-compose up` to start the Elgg and attacker containers.
3. Verify setup by browsing to `www.seed-server.com` (Elgg login) and `www.attacker32.com`
   (should show "CSRF Attacker's Page").
4. Log in to Elgg using the provided test accounts (e.g. `alice` / `seedalice`,
   `boby` / `seedboby`) to reproduce the attack.

---

## 📝 Notes

This lab uses course-provided infrastructure (Docker container configuration, the Elgg
web application, and the SEED Labs environment) - the attack methodology, payload
construction, and analysis above are original work.

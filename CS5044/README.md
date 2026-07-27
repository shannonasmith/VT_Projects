<div align="center">

# ☕ CS 5044 – Advanced Programming (Virginia Tech)
## 🧩 OOP Design • Algorithmic AI • Data Structures • GUI Integration

![Focus](https://img.shields.io/badge/Focus-Java%20%7C%20OOP%20%7C%20Testing-blue?style=for-the-badge)
![Approach](https://img.shields.io/badge/Approach-Fundamentals%20→%20Design%20→%20Integration-success?style=for-the-badge)
![Tech](https://img.shields.io/badge/Tech-Java%20%7C%20JUnit%20%7C%20Swing-orange?style=for-the-badge)

</div>

---

## 🧠 What This Is

Six Java projects tracing a progression from basic syntax through object-oriented design,
algorithmic AI, data structure delegation, and GUI integration. Each project builds on
skills from the last, moving from informal `println`-based testing to full JUnit coverage,
and from single-class programs to a multi-class engine wired into a Swing GUI.

---

## 🧬 Project Progression

---

### 👋 P0 — [Hello 5044](./P0) 🔗

![Focus](https://img.shields.io/badge/Focus-Java%20Basics-blue)

| Category | Details |
|---|---|
| Focus | Syntax, project/package setup |
| Type | Runnable Java program |
| Output | ASCII art course banner |

**What it does:** Prints a small ASCII art block spelling out the course number, instead of
a traditional one-line "Hello, World!" greeting.

**Why it matters:** Establishes the Java project/package structure and toolchain
(IntelliJ, JDK) used across every project that follows.

---

### 🔋 P1 — [Shuttle Battery Monitor](./P1) 🔗

![Focus](https://img.shields.io/badge/Focus-State%20%7C%20Informal%20Testing-blue)

| Category | Details |
|---|---|
| Focus | Instance state, accessor/mutator design |
| Type | Runnable Java program + informal tester |
| Output | Battery usage, charge remaining, trip statistics |

**What it does:** Tracks an autonomous shuttle's location, passenger count, and energy
usage as it travels, computing charge remaining and average usage per trip with
truncated-decimal precision.

**Why it matters:** First project with real internal state and multiple interacting
methods, verified with an informal `println`-based tester rather than a formal test
framework.

---

### 🚐 P2 — [Minivan Sliding Door](./P2) 🔗

![Focus](https://img.shields.io/badge/Focus-State%20Machine%20%7C%20Precedence%20Logic-blue)

| Category | Details |
|---|---|
| Focus | Enumerated state machine, precedence-ordered conditionals |
| Type | Runnable Java program + informal tester |
| Output | Door open/closed/locked state with prioritized refusal reasons |

**What it does:** Models a minivan's sliding door - open, closed, locked, childsafe, and
gear state - enforcing safety rules with a defined precedence order when multiple refusal
conditions could apply to the same request.

**Why it matters:** Built under a deliberately restrictive constraint (no nested branches,
no `&&`/`||`), forcing precedence logic into a sequence of independent, individually
readable conditionals - closer to how detection/alert-priority rules often need to be
both correct and auditable.

---

### 🎮 P3 — [Tetris AI](./P3) 🔗

![Focus](https://img.shields.io/badge/Focus-Heuristics%20%7C%20JUnit-blue)

| Category | Details |
|---|---|
| Focus | Interface implementation, heuristic cost functions |
| Type | Runnable Java program (playable game) + JUnit tests |
| Output | AI-selected piece placements minimizing a weighted board-cost function |

**What it does:** Implements a Tetris-playing AI against a provided game engine interface,
scoring every possible piece placement on four weighted cost factors (average column
height, height range, height variance, gap count) and selecting the lowest-cost option.

**Why it matters:** First project with formal JUnit tests and tuning against a numeric
benchmark (average pieces placed across test sequences) rather than a single fixed
expected output - closer to how a detection threshold gets tuned against real data.

---

### 🎲 P4 — [Dots and Boxes Game Engine](./P4) 🔗

![Focus](https://img.shields.io/badge/Focus-Delegation%20%7C%20Full%20Coverage-blue)

| Category | Details |
|---|---|
| Focus | Functional decomposition, exception-based defensive programming |
| Type | Runnable Java library + JUnit tests |
| Output | Complete Dots and Boxes game engine, no UI |

**What it does:** Implements the Dots and Boxes game engine - box ownership, turn
management, extra-turn-on-completion logic - delegating a substantial amount of logic to a
separate `Box` class rather than keeping it all in the main engine class.

**Why it matters:** Full JUnit branch coverage was a hard requirement, including every
exception path (invalid grid size, invalid coordinates, actions before initialization) -
not just the happy path.

---

### 🖥️ P5 — [Dots and Boxes GUI](./P5) 🔗

![Focus](https://img.shields.io/badge/Focus-Swing%20%7C%20Event-Driven-blue)

| Category | Details |
|---|---|
| Focus | GUI integration, MVC-style separation from the P4 engine |
| Type | Runnable Swing application + provided JUnit tests |
| Output | Interactive Dots and Boxes game window |

**What it does:** Builds a Swing front-end (menu bar, status indicators, coordinate/
direction inputs, and an interactive grid component) on top of the P4 engine, supporting
2x2/3x3/4x4 games and both click-to-draw and menu-driven interaction.

**Why it matters:** Validated by a course-provided JUnit suite that drives the interface
through real Swing component interaction (simulated clicks, combo box selection) rather
than calling engine methods directly - closer to true end-to-end verification.

---

## 🧠 How the Six Projects Connect

| Project | What It Adds | Key Technology |
|---|---|---|
| P0 | Java basics, project structure | Core Java |
| P1 | Instance state, informal testing | Core Java |
| P2 | State machines, precedence logic under constraints | Enumerated types |
| P3 | Interfaces, heuristics, formal JUnit testing | JUnit 4 |
| P4 | Delegation, exceptions, full branch coverage | JUnit 4 |
| P5 | GUI integration on top of a tested engine | Swing, JUnit 4 |

The sequence is deliberately cumulative: P0-P1 establish syntax and state. P2 introduces
constrained control-flow design. P3 introduces formal testing and algorithmic tuning. P4
introduces decomposition and defensive programming as first-class requirements. P5 wires a
fully-tested engine into a real, interactive GUI.

---

## 🛠️ Tech Stack

| Component | Detail |
|---|---|
| Language | Java 17+ |
| IDE | IntelliJ IDEA |
| Testing | JUnit 4 (P3-P5) |
| GUI | Java Swing (P5) |
| Frameworks | Course-provided interfaces/libraries (Tetris engine, Dots and Boxes API, GUI component) |

---

<div align="center">

## 👤 Shannon Smith

Cybersecurity | SOC Operations • Detection Engineering • Incident Response

</div>

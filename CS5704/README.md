<div align="center">

# 🧱 CS 5704 – Software Engineering (Virginia Tech)
## 🏗️ Data Structures • Refactoring • Design Patterns

![Focus](https://img.shields.io/badge/Focus-Java%20%7C%20Kotlin%20%7C%20Design%20Patterns-blue?style=for-the-badge)
![Approach](https://img.shields.io/badge/Approach-Structure%20→%20Refactor%20→%20Design-success?style=for-the-badge)
![Tech](https://img.shields.io/badge/Tech-JUnit%20%7C%20Swing%20%7C%20Kotlin-black?style=for-the-badge)

</div>

---

## 🧠 What This Is

Four grad-level projects spanning bounded data structure design, legacy code refactoring
into a generalized multi-variant engine, and a full Kotlin/Swing implementation of six
classic design patterns. Where CS 5044 built individual programs from scratch, this
coursework focuses on structural quality: interchangeable implementations behind shared
interfaces, generalizing one working system into several, and applying named design
patterns deliberately rather than incidentally.

---

## 🧬 Project Progression

---

### 📚 P0 — boundedstack — [Bounded Stack](./boundedstack) 🔗

![Focus](https://img.shields.io/badge/Focus-Data%20Structures%20%7C%20Shared%20Interface-blue)

| Category | Details |
|---|---|
| Focus | Interchangeable implementations behind one interface |
| Type | Runnable Java library + JUnit tests |
| Output | Two working stack implementations, verified interchangeable |

**What it does:** A bounded, generic stack interface with two implementations - array-backed
and `LinkedList`-backed - sharing common `toString`/`equals`/`hashCode`/`copy`/`reverse`
logic through an abstract base class, so neither implementation duplicates that logic.

**Why it matters:** Both implementations are verified `.equals()` to each other when holding
identical contents - proof that the shared interface genuinely abstracts away the storage
mechanism, not just in theory but in tested behavior.

---

### 🔁 P1 — boundedqueue — [Bounded Queue](./boundedqueue) 🔗

![Focus](https://img.shields.io/badge/Focus-Data%20Structures%20%7C%20Shared%20Interface-blue)

| Category | Details |
|---|---|
| Focus | Three interchangeable FIFO implementations |
| Type | Runnable Java library + JUnit tests |
| Output | List-backed, linked-node, and circular-array queues, verified interchangeable |

**What it does:** A bounded, generic queue interface with three implementations -
`LinkedList`-backed, custom doubly-linked-node, and circular-array - sharing common logic
through an abstract base class, same pattern as boundedstack but with three
implementations instead of two.

**Why it matters:** The circular-array implementation reuses freed slots via modulo
arithmetic instead of shifting elements on every dequeue - a meaningfully different
internal strategy from the other two, and all three still verify as interchangeable
through the shared interface.

---

### 🌊 P2 — rivercrossing — [River Crossing](./rivercrossing) 🔗

![Focus](https://img.shields.io/badge/Focus-Refactoring%20%7C%20Generalization-green)

| Category | Details |
|---|---|
| Focus | Refactoring a single-variant puzzle into a generalized multi-variant engine |
| Type | Runnable Java Swing application + JUnit tests |
| Output | Three interchangeable river-crossing puzzle variants sharing one engine |

**What it does:** Refactors a farmer/wolf/goose/beans puzzle implementation into a
`GameEngine` interface with a shared `AbstractGameEngine` base, then generalizes it to
support a monster/munchkin variant and a third scout variant - all driven by the same
Swing GUI, which renders purely from whatever engine is currently active.

**Why it matters:** The GUI has zero hardcoded knowledge of which puzzle variant is
running - it only knows the `GameEngine` interface. Adding the second and third puzzle
variants required no GUI changes at all, which is the actual point of the refactor.

---

### 🦆 P3 — DuckSim — [Design Patterns](./DuckSim) 🔗

![Focus](https://img.shields.io/badge/Focus-Six%20Design%20Patterns-purple)

| Category | Details |
|---|---|
| Focus | Strategy, Decorator, Factory/Singleton, Adapter, Observer, Composite |
| Type | Runnable Kotlin/Swing application |
| Output | Interactive duck pond simulator |

**What it does:** A Kotlin/Swing duck pond simulator demonstrating six classic design
patterns in one working application - swappable fly/quack behaviors (Strategy),
stackable duck decorations (Decorator), a singleton duck-assembly factory
(Factory/Singleton), an adapter wrapping a non-duck class into the duck interface
(Adapter), a welcoming-committee notification system (Observer), and a duck flock that is
itself a duck (Composite).

**Why it matters:** Every pattern is demonstrated through real interactive behavior, not
just class structure - selecting ducks, grouping them into a flock, and watching
capture/release/notification propagate through that flock live in the GUI.

---

## 🧠 How the Four Projects Connect

| Project | What It Adds | Key Technology |
|---|---|---|
| boundedstack | Shared base class, verified interchangeability | Java, JUnit 4 |
| boundedqueue | Same pattern, three implementations instead of two | Java, JUnit 4 |
| rivercrossing | Interface-driven refactor, GUI decoupled from variant logic | Java, Swing |
| DuckSim | Six named design patterns in one working system | Kotlin, Swing |

The progression moves from *interface-driven data structures* (bounded stack/queue) to
*interface-driven refactoring of an existing application* (river crossing) to *deliberate,
named application of a full pattern catalog* (DuckSim) - each stage building on the same
core idea: design around an interface, not a concrete implementation.

---

## 🛠️ Tech Stack

| Component | Detail |
|---|---|
| Languages | Java 17+, Kotlin |
| IDE | IntelliJ IDEA |
| Testing | JUnit 4 |
| GUI | Java Swing |
| Build | Gradle |

---

<div align="center">

## 👤 Shannon Smith

Cybersecurity | SOC Operations • Detection Engineering • Incident Response

</div>

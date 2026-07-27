<div align="center">

# 🔁 CS 5704 – Bounded Queue

![Focus](https://img.shields.io/badge/Focus-Data%20Structures%20%7C%20Shared%20Interface-blue?style=for-the-badge)

</div>

---

## 🧠 What It Does

A bounded, generic queue data structure (first-in-first-out) with three interchangeable
implementations sharing a common base class.

## 🎯 Why It Matters

All three implementations are interchangeable: queues from different implementations with
the same elements and capacity are `.equals()` to each other and produce the same
`hashCode()` - verified across genuinely different internal storage strategies, not just
similar ones.

---

## ✨ Features

- `Queue<E>` interface defining enqueue, dequeue, length, capacity, and full value
  semantics (`equals`, `hashCode`, `toString`), plus `copy()`, `clear()`, `newInstance()`,
  `first()`, `last()`, `isEmpty()`, `isFull()`, and `appendAll()` (recursively drains one
  queue into another)
- `AbstractQueue<E>` implements the shared logic once, on top of each subclass's core
  `enqueue`/`dequeue`/`length`/`iterator` operations
- `ListQueue<E>` - `LinkedList`-backed implementation
- `LinkedQueue<E>` - custom doubly-linked-node implementation
- `CircArrayQueue<E>` - fixed-size circular array implementation, reusing freed slots via
  modulo arithmetic rather than shifting elements

---

## 🛠️ Requirements

- JDK 17+ (developed/tested with Java 17)
- IntelliJ IDEA (Community Edition works fine)
- JUnit 4

## ▶️ How to Run

1. Open IntelliJ IDEA
2. **File → Open**, select this project's folder (the one containing `src/` and `test/`)
3. Click **Trust Project** if prompted
4. Let IntelliJ finish indexing (progress bar at the bottom)
5. If prompted with "Cannot resolve symbol 'junit'", click **Add 'JUnit4' to classpath**
   (Alt+Shift+Enter)
6. If the `test` folder shows a "located outside of the module source root" warning,
   right-click the `test` folder → **Mark Directory as → Test Sources Root**
7. In the Project panel: `test → boundedqueue`
8. Right-click each of `ListQueueTest`, `LinkedQueueTest`, and `CircArrayQueueTest` →
   **Run** individually

---

## 🧪 Testing Approach

Each implementation is tested against the same set of behaviors: initial state,
enqueue/dequeue order (FIFO), capacity limits (`IllegalStateException` when full,
`IllegalArgumentException` on a null element), `first()`/`last()`, `toString` formatting,
`equals`/`hashCode` consistency across queues with matching and differing
contents/capacities, `copy()`, `clear()`, and `appendAll()`. Cross-implementation
`equals`/`hashCode` tests confirm that different implementations holding identical data
are treated as equal through the shared `Queue<E>` interface, rather than by reference or
implementation type.

---

## ✅ Expected Output

All test methods in `ListQueueTest`, `LinkedQueueTest`, and `CircArrayQueueTest` should
pass (green checkmarks in IntelliJ's test runner panel, process exits with code 0).

---

## 💻 Setting This Up on Another PC

**Option 1 — Download ZIP**
1. On GitHub, go to this repo → green **Code** button → **Download ZIP**
2. Unzip it wherever you want
3. Open the folder in IntelliJ (`File → Open`) and follow "How to Run" above

**Option 2 — Git Clone**
1. Install Git: https://git-scm.com/downloads
2. `git clone https://github.com/yourusername/your-repo.git`
3. Open the cloned folder in IntelliJ and follow "How to Run" above

---

## 📝 Notes

- `.idea/` and `out/` folders are intentionally not included - these regenerate
  automatically when you open the project fresh in IntelliJ.

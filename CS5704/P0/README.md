<div align="center">

# 📚 CS 5704 – P0: Bounded Stack

![Focus](https://img.shields.io/badge/Focus-Data%20Structures%20%7C%20Shared%20Interface-blue?style=for-the-badge)

</div>

---

## 🧠 What It Does

A bounded, generic stack data structure (first-in-last-out) with two interchangeable
implementations sharing a common base class.

## 🎯 Why It Matters

Both implementations are verified `.equals()` to each other when holding identical
contents - proof that the shared interface genuinely abstracts away the storage mechanism,
not just in theory but in tested behavior.

---

## ✨ Features

- `Stack<E>` interface defining push, pop, depth, capacity, and full value semantics
  (`equals`, `hashCode`, `toString`), plus `copy()`, `reverse()`, `clear()`, and
  `newInstance()`
- `AbstractStack<E>` implements the shared logic (`toString`, `equals`, `hashCode`,
  `copy`, `reverse`) once, on top of each subclass's core `push`/`pop`/`depth`/`iterator`
  operations - avoiding duplicating that logic between implementations
- `ArrayStack<E>` - fixed-size array-backed implementation
- `ListStack<E>` - `LinkedList`-backed implementation
- Both implementations are interchangeable: an `ArrayStack` and a `ListStack` with the
  same elements and capacity are `.equals()` to each other

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
7. In the Project panel: `test → boundedstack`
8. Right-click `ListStackTest` → **Run 'ListStackTest'**, then repeat for `ArrayStackTest`

---

## 🧪 Testing Approach

Both implementations are tested against the same set of behaviors: initial state,
push/pop, capacity limits (`IllegalStateException` when full, `IllegalArgumentException`
on a null element), `toString` formatting, `equals`/`hashCode` consistency across stacks
with matching and differing contents/capacities, `copy()`, `reverse()`, and `clear()`.
`ArrayStackTest` additionally confirms that an `ArrayStack` and a `ListStack` with
identical contents are `.equals()` to each other, verifying the two implementations are
truly interchangeable through the shared `Stack<E>` interface.

---

## ✅ Expected Output

All test methods in both `ListStackTest` and `ArrayStackTest` should pass (green
checkmarks in IntelliJ's test runner panel, process exits with code 0).

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

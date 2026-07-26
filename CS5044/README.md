# CS 5044 - Object-Oriented Programming w Java (Virginia Tech)

Six Java projects tracing a progression from basic syntax through
object-oriented design, algorithmic AI, data structure delegation, and
GUI integration.

## Projects

| Project | Topic | Skills Demonstrated |
|---|---|---|
| [Project0](./P0) | Hello 5044 | Java basics, ASCII output, project/package setup |
| [Project1](./P1) | Shuttle Battery Monitor | Instance state, accessor/mutator design, informal (println-based) testing, truncation/rounding arithmetic |
| [Project2](./P2) | Minivan Sliding Door | State-machine logic, enumerated types, precedence-ordered conditionals under a no-nested-branch/no-&&-\|\| constraint |
| [Project3](./P3) | Tetris AI | Interface implementation, heuristic cost functions, JUnit test-driven development, weight tuning against a scoring benchmark |
| [Project4](./P4) | Dots and Boxes Game Engine | Delegation to a helper class, exception-based defensive programming, full JUnit branch coverage |
| [Project5](./P5) | Dots and Boxes GUI | Swing GUI integration (MVC-style separation from P4's engine), event-driven programming, provided-test-suite validation |

## Progression

The sequence builds deliberately:
- **P0-P1** establish basic Java syntax, state, and informal testing
- **P2** introduces enumerated state machines and precedence-based control
  flow under deliberately restrictive constraints (no nested branches, no
  `&&`/`||`)
- **P3** moves to formal JUnit testing and interface-driven implementation
  against a provided framework, including tuning a heuristic algorithm
  against a numeric benchmark rather than a fixed expected output
- **P4** introduces functional decomposition (delegating to a separate
  class) and exception handling as first-class design tools, with full
  test coverage as a hard requirement
- **P5** integrates a from-scratch engine (P4) with a GUI, using a
  provided test suite that exercises the interface through real Swing
  component interaction rather than direct method calls

## How to Run Each Project

Each project folder has its own README with exact setup and run steps for
IntelliJ (including any project-specific library dependencies). General
requirements across all projects:

- JDK 17+
- IntelliJ IDEA (Community Edition works fine)
- JUnit 4 (P3-P5 only; P0-P2 use informal `main()`-based testers)

P4 and P5 additionally require course-provided framework jars, which are
included in each of those project folders - see their individual READMEs
for setup steps.

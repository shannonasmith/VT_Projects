## DuckSim (CS 5704 - Fall 2023 - P4 Design Patterns)

### a DuckSim project with a Model-View-Controller (MVC) architecture

Summary of the Project: Duck Simulator

The Duck Simulator project is an object-oriented application implemented in Kotlin, focusing on design patterns and graphical user interface (GUI) development. It simulates a virtual environment where various types of ducks can interact with each other and the user through a graphical interface.

Key Features:

    Duck Abstraction: The project employs an abstract class Duck that serves as the base for different types of ducks. It encapsulates behaviors such as flying, quacking, and display characteristics. Subclasses of Duck represent specific types of ducks, each with its own behaviors and properties.
    
    Design Patterns: The project demonstrates the use of several design patterns:
    
    Strategy Pattern: Implemented through FlyBehavior and QuackBehavior interfaces, allowing ducks to exhibit different flying and quacking behaviors dynamically.
    
    Observer Pattern: Utilized to notify ducks about changes in the environment, such as joining or leaving a duck society (DSWC).
    
    Composite Pattern: Used to represent a flock of ducks as a single entity, enabling collective operations on multiple ducks.
    
    Adapter Pattern: Employed to adapt a Goose class into the duck hierarchy, enabling seamless integration of geese into the simulator.
    
    Graphical User Interface (GUI): The project features a GUI developed using Java Swing, providing an interactive interface for users to create, manage, and observe ducks in the simulated environment. Users can add new ducks, apply decorations (bling), and observe duck behaviors through the GUI.
    
    Dynamic Behavior Modification: Ducks in the simulator can dynamically change their behaviors, such as flying and quacking, based on user interactions and environmental changes. This dynamic behavior modification adds realism and interactivity to the simulation.
    
    Project Structure:
    
    Classes: The project consists of various classes representing ducks, behaviors, bling decorations, and GUI components.
    
    Interfaces: Interfaces such as FlyBehavior, QuackBehavior, and Observer define contracts for implementing specific behaviors and interactions.
    
    Dialogs: Dialogs like MakeDuckDialog facilitate user interaction for creating new ducks and applying decorations.
    
    Enums: The State enum defines different states ducks can be in, including swimming, flying, quacking, and welcoming.

Overall, the Duck Simulator project showcases effective use of design patterns, object-oriented principles, and GUI development techniques to create an engaging and interactive simulation environment.
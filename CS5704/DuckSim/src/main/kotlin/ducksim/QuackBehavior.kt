package ducksim

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * Interface for defining quacking behavior as part of the Strategy Pattern.
 * Classes implementing this interface represent different quacking behaviors.
 */
interface QuackBehavior {
    /**
     * State representing the behavior's state, e.g., FLYING, SWIMMING, etc.
     */
    val state: State

    /**
     * Text representing the sound of the quack.
     */
    val quackText: String
}
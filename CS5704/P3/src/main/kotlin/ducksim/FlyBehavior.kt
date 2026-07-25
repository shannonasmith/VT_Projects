package ducksim

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * Defines the behavior for flying, following the Strategy Pattern.
 */
interface FlyBehavior {
    /**
     * Represents the current state of flying behavior.
     */
    val state: State

}
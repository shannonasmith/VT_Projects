package ducksim

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * Implementation of the QuackBehavior interface representing no quack behavior.
 */
class QuackNoWay : QuackBehavior {
    /**
     * State representing the behavior's state, which is set to swimming.
     */
    override val state: State = State.SWIMMING
    /**
     * Text representing the sound of the quack, which is an empty string for this behavior.
     */
    override val quackText: String = ""

}
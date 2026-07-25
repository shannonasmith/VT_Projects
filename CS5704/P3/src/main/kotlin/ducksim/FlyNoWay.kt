package ducksim

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * Represents a flying behavior where the duck does not fly, implementing the FlyBehavior interface.
 */
class FlyNoWay : FlyBehavior {
    /**
     * The state of the duck when it's not flying, set to SWIMMING.
     */
    override val state: State = State.SWIMMING
}
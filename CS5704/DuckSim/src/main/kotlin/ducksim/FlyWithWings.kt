package ducksim

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * Represents a flying behavior where the duck flies with wings, implementing the FlyBehavior interface.
 */
class FlyWithWings : FlyBehavior {
    /**
     * The state of the duck when it's flying with wings, set to FLYING.
     */
    override val state: State = State.FLYING

}
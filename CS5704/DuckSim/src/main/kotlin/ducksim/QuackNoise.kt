package ducksim

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */
/**
 * Implementation of the QuackBehavior interface representing a noisy quack.
 */
class QuackNoise : QuackBehavior {
    /**
     * State representing the behavior's state, which is set to quacking.
     */
    override val state: State = State.QUACKING
    /**
     * Text representing the sound of the quack, which is "Noise!" for this behavior.
     */
    override val quackText: String = "Noise!"

}
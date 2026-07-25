package ducksim

import java.awt.Color

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * Represents a Rubber Duck, a type of Duck that cannot fly and squeaks instead of quacking.
 */
class RubberDuck : Duck(FlyNoWay(), QuackSqueak()) {

    /**
     * The color of the Rubber Duck, set to YELLOW.
     */
    override val color: Color = Color.YELLOW

    /**
     * Displays the name of the Rubber Duck.
     *
     * @return The name of the Rubber Duck.
     */
    override fun display() = "Rubber"

    /**
     * Overrides the fly behavior of the Rubber Duck to FlyNoWay.
     */
    override fun fly() {
        flyBehavior = FlyNoWay()
    }

}
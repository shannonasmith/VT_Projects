package ducksim

import java.awt.Color

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * Represents a Mallard Duck, a concrete subclass of the Duck class.
 * Mallard Ducks have the ability to fly with wings and quack normally.
 *
 * @constructor Creates a MallardDuck object with default fly and quack behaviors.
 */
class MallardDuck : Duck(FlyWithWings(), QuackNormal()) {

    /**
     * The color of the Mallard Duck, set to GREEN.
     */
    override val color: Color = Color.GREEN

    /**
     * Displays the name of the Mallard Duck.
     *
     * @return The name "Mallard".
     */
    override fun display() = "Mallard"

}

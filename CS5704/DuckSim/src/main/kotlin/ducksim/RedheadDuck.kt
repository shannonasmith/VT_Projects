package ducksim

import java.awt.Color

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * Represents a Redhead Duck, a type of Duck that can fly with wings and quack normally.
 */
class RedheadDuck : Duck(FlyWithWings(), QuackNormal()) {
    /**
     * The color of the Redhead Duck, set to RED.
     */
    override val color: Color = Color.RED

    /**
     * Displays the name of the Redhead Duck.
     *
     * @return The name of the Redhead Duck.
     */
    override fun display() = "Redhead"

}
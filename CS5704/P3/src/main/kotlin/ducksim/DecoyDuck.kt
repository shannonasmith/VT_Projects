package ducksim

import java.awt.Color

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.11.10
 */

/**
 * Represents a Decoy Duck, a type of duck that cannot fly or quack.
 * Extends the Duck class and provides specific behavior for a decoy duck.
 *
 * Properties:
 *      color: The color of the decoy duck, which is set to orange.
 *
 * Methods:
 *      display(): Displays the name of the decoy duck, which is "Decoy".
 *      fly(): Overrides the fly method to set the fly behavior to FlyNoWay, indicating that decoy ducks cannot fly.
 */
class DecoyDuck : Duck(FlyNoWay(), QuackNoWay()) {

    /**
     * The color of the decoy duck, which is orange.
     */
    override val color: Color = Color.ORANGE

    /**
     * Displays the name of the duck, which is "Decoy".
     *
     * @return A string representing the name of the decoy duck.
     */
    override fun display() = "Decoy"

    /**
     * Overrides the fly method to set the fly behavior to FlyNoWay, as decoy ducks cannot fly.
     */
    override fun fly() {
        flyBehavior = FlyNoWay()
    }

}

package ducksim

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * An abstract class representing Bling, a decorator for ducks.
 * It extends the Duck class, implementing the Decorator Pattern to enhance the behavior of ducks dynamically.
 *
 * Properties:
 *      duck: The duck instance to decorate.
 *
 * Methods:
 *      display(): Displays the appearance of the duck, inherited from the decorated duck.
 *      quack(): Overrides the quack behavior of the decorated duck.
 *      init block: Initializes the Bling with the fly and quack behavior of the decorated duck.
 *      release: Represents the release action for the Bling. When invoked, it sets the duck free with its original behaviors.
 *
 * @property duck The duck instance to decorate.
 */
abstract class Bling(val duck: Duck) : Duck(duck.defaultFlyBehavior, duck.defaultQuackBehavior) {

    /**
     * The color of the duck, inherited from the decorated duck.
     */
    override val color = duck.color

    /**
     * Displays the duck's appearance, inherited from the decorated duck.
     *
     * @return A string representing the duck's appearance.
     */
    override fun display(): String {
        return duck.display()
    }

    /**
     * Overrides the quack behavior of the decorated duck.
     * Retrieves the quack behavior from the current quack behavior.
     */
    override fun quack() {
        state = quackBehavior.state
        quackText = quackBehavior.quackText
    }

    /**
     * Initializes the Bling with the fly and quack behavior of the decorated duck.
     */
    init {
        flyBehavior = duck.flyBehavior
        quackBehavior = duck.quackBehavior
    }

    /**
     * Represents the release action for the Bling.
     * When invoked, it sets the duck free with its original behaviors.
     */
    override val release = object : DuckMenuItem {
        override fun invoke() {
            isFree = true
            quackText = duck.quackText
            flyBehavior = duck.flyBehavior
            quackBehavior = duck.quackBehavior
        }
    }
}
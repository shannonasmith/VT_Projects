package ducksim

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * Represents a Cross Bling, a specific type of decoration for ducks.
 * Extends the Bling abstract class to decorate ducks with a cross symbol.
 *
 *
 * Properties:
 *       wrappedDuck: The duck instance to decorate with a cross symbol.
 * Methods:
 *      display(): Overrides the display method to append a cross symbol to the appearance of the decorated duck.
 *
 * @param wrappedDuck The duck instance to decorate with a cross symbol.
 */
class CrossBling(private val wrappedDuck: Duck) : Bling(wrappedDuck) {

    /**
     * Displays the appearance of the duck decorated with a cross symbol.
     *
     * @return A string representing the duck's appearance with a cross symbol.
     */
    override fun display(): String = wrappedDuck.display() + ":+"
}
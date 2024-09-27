package ducksim

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * Represents a Moon Bling, a type of decoration for ducks, implemented as a decorator pattern.
 * Moon Bling is added to a duck and enhances its display by appending ":)" to the original display.
 *
 * @property wrappedDuck The duck to which the Moon Bling is added.
 * @constructor Creates a MoonBling object wrapping the specified duck.
 */
class MoonBling(private val wrappedDuck: Duck) : Bling(wrappedDuck) {

    /**
     * Returns the display string of the decorated duck with ":)" appended to it.
     *
     * @return The decorated display string.
     */
    override fun display(): String = wrappedDuck.display() + ":)"

}
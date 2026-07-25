package ducksim

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * Represents a Duck adorned with star decorations, adding a star symbol to its display.
 *
 * @param wrappedDuck The Duck object to which star decorations are added.
 */
class StarBling(private val wrappedDuck: Duck) : Bling(wrappedDuck) {

    /**
     * Adds star decoration to the display of the wrapped duck.
     *
     * @return The display string of the wrapped duck with an added star symbol.
     */
    override fun display(): String = wrappedDuck.display() + ":*"

}
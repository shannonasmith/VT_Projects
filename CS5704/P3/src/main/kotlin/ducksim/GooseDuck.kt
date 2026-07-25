package ducksim

import java.awt.Color

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * Represents an adapter that adapts a Goose into a Duck, implementing the Adapter Pattern.
 *
 * @property goose The instance of Goose being adapted.
 */
class GooseDuck(private val goose: Goose) : Duck(FlyWithWings(), QuackHonk()) {

    /**
     * The color of the GooseDuck, set to MAGENTA.
     */
    override val color: Color = Color.MAGENTA

    /**
     * Displays the name of the adapted goose.
     *
     * @return The name of the goose.
     */
    override fun display() = getName()

    /**
     * Displays the name of the adapted goose.
     *
     * @return The name of the goose.
     */
    private fun getName(): String {
        return goose.name
    }

}

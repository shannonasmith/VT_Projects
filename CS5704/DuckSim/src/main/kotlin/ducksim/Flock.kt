package ducksim

import java.awt.Color

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * Represents a flock of ducks, implementing the Composite Pattern.
 *
 * @property ducks The list of ducks in the flock.
 */
class Flock(val ducks: List<Duck>) : Duck(FlyWithWings(), QuackNoise()) {

    /**
     * Displays the flock composition, representing ducks by their colors.
     *
     * @return A string representing the display of the flock.
     */
    override fun display(): String {
        var result = "Flock"
        ducks.forEach {
            result += when (it.color) {
                Color.GREEN -> ":M"
                Color.RED -> ":R"
                Color.YELLOW -> ":R"
                Color.ORANGE -> ":D"
                Color.MAGENTA -> ":G"
                else -> ":R"
            }
        }
        return result
    }

    /**
     * Action to capture all ducks in the flock.
     */
    override val capture = object : DuckMenuItem {
        override fun invoke() {
            ducks.forEach { it.doCapture() }
            doCapture()
        }
    }

    /**
     * Action to release all ducks in the flock.
     */
    override val release = object : DuckMenuItem {
        override fun invoke() {
            ducks.forEach { it.doRelease() }
            doRelease()
        }
    }

    /**
     * Action for all ducks in the flock to join the DSCW.
     */
    override val joinDSCW = object : DuckMenuItem {
        override fun invoke() {
            ducks.forEach { it.doJoin() }
            doJoin()
        }
    }

    /**
     * Action for all ducks in the flock to quit the DSCW.
     */
    override val quitDSCW = object : DuckMenuItem {
        override fun invoke() {
            ducks.forEach { it.doQuit() }
            doQuit()
        }
    }

}
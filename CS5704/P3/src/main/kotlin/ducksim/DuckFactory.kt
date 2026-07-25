package ducksim

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * Represents the DuckFactory, responsible for creating ducks with various decorations.
 * Implements the Singleton pattern using the object keyword.
 * Extends the Subject class to notify observers when ducks are created.
 */
object DuckFactory : Subject() { // Factory Pattern: object makes DuckFactory a Singleton

    /**
     * Creates a duck with the specified base duck and decorations.
     *
     * @param baseDuck The base duck to decorate.
     * @param starCount The number of star decorations to add to the duck.
     * @param moonCount The number of moon decorations to add to the duck.
     * @param crossCount The number of cross decorations to add to the duck.
     * @return The decorated duck.
     */
    fun createDuck(baseDuck: Duck, starCount: Int, moonCount: Int, crossCount: Int): Duck {

        var duck: Duck = baseDuck

        // Add star decorations to the duck
        repeat(starCount) {
            duck = StarBling(duck)
        }

        // Add moon decorations to the duck
        repeat(moonCount) {
            duck = MoonBling(duck)
        }

        // Add cross decorations to the duck
        repeat(crossCount) {
            duck = CrossBling(duck)
        }

        // Notify observers after creating the decorated duck
        notifyObserver()
        return duck
    }

}
package ducksim

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * Observer interface for implementing the Observer Pattern.
 * Classes that need to observe changes in subjects should implement this interface.
 */
interface Observer {
    /**
     * Method called by subjects to notify observers about updates.
     */
    fun update()

}
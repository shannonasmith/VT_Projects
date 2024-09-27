package ducksim

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * Represents a subject in the Observer pattern.
 */
abstract class Subject {
    private var observers: ArrayList<Observer> = arrayListOf()

    /**
     * Registers an observer to receive updates from this subject.
     *
     * @param obs The observer to register.
     */
    fun registerObserver(obs: Observer) {
        observers.add(obs)
    }
    /**
     * Removes an observer from the list of registered observers.
     *
     * @param obs The observer to remove.
     */
    fun removeObserver(obs: Observer) {
        observers.remove(obs)
    }
    /**
     * Notifies all registered observers about changes in the subject.
     */
    fun notifyObserver() {
        for (duckState in observers) {
            duckState.update()
        }
    }

}
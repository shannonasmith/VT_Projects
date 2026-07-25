package ducksim

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * Represents a DuckPond, which manages ducks within a pond environment.
 */
class DuckPond {

    /**
     * The list of ducks currently residing in the pond.
     */
    val duckList = mutableListOf<Duck>()

    /**
     * The set of ducks currently selected in the pond.
     */
    val selectedDucks = mutableSetOf<Duck>()

    /**
     * The currently selected duck in the pond.
     */
    var currentDuck: Duck? = null

    /**
     * Indicates whether there is a currently selected duck in the pond.
     */
    val hasCurrentDuck
        get() = (currentDuck != null)

    /**
     * Adds a new duck to the pond.
     *
     * @param duck The duck to add to the pond.
     */
    fun addNewDuck(duck: Duck) {
        assert(duckList.size < MAX_DUCKS)
        duckList.add(duck)
    }

    /**
     * Deletes a duck from the pond.
     *
     * @param duck The duck to delete from the pond.
     */
    fun deleteDuck(duck: Duck) {
        if (duck === currentDuck) {
            currentDuck = null
        }
        duckList.remove(duck)
    }

    /**
     * Toggles the selection state of a duck in the pond.
     *
     * @param duck The duck whose selection state to toggle.
     */
    fun toggleSelection(duck: Duck) {
        if (selectedDucks.contains(duck)) {
            selectedDucks.remove(duck)
        } else {
            selectedDucks.add(duck)
        }
    }

    /**
     * Checks if there are no ducks currently selected in the pond.
     *
     * @return True if there are no selected ducks, false otherwise.
     */
    fun noSelectedDucks() = selectedDucks.isEmpty()

    /**
     * A companion object containing constant values related to the DuckPond.
     */
    companion object {
        /**
         * The maximum number of ducks allowed in the pond.
         */
        private const val MAX_DUCKS = 8
    }

}


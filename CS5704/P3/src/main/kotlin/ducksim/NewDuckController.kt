package ducksim

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * Controller responsible for creating new ducks in the Duck Pond.
 * It utilizes the Composite Pattern to handle the creation of individual ducks and duck flocks.
 *
 * @property duckPond The DuckPond instance where new ducks are added.
 * @property view The DuckSimView instance for updating the view after adding new ducks.
 * @constructor Creates a NewDuckController with the specified DuckPond and DuckSimView.
 */
class NewDuckController(private val duckPond: DuckPond, private val view: DuckSimView) { // Composite Pattern

    /**
     * Creates a new duck based on user input or a flock of selected ducks.
     * Shows a dialog to create a new duck if no duck is selected, otherwise, creates a flock of selected ducks.
     */
    fun createNewDuck() {
        if (duckPond.noSelectedDucks()) {
            val makeDuckDialog = MakeDuckDialog(duckPond, view)
            makeDuckDialog.setSize(300, 200)
            makeDuckDialog.isVisible = true
        } else {
            val ducks = duckPond.selectedDucks.filterNot { it is Flock }
            if (ducks.isEmpty()) return
            val flock = Flock(ducks)
            duckPond.addNewDuck(flock)
            view.repaint()
        }
    }
}
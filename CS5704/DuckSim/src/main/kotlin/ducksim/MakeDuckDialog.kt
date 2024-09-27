package ducksim

import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.util.*
import javax.swing.*

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * Represents a dialog for creating new ducks with optional decorations.
 *
 * This dialog allows users to select the type of duck and add decorations to it. Users can choose from various
 * duck types such as Mallard, Redhead, Rubber, Decoy, and Goose. Additionally, users can add decorations like stars,
 * crosses, and moons to the duck.
 *
 * @property model The instance of DuckPond model to add new ducks.
 * @property view The DuckSimView instance to repaint the view after adding ducks.
 */
class MakeDuckDialog(

    private val model: DuckPond,
    private val view: DuckSimView
) : JDialog() {
    // Internal enumeration defining the types of decorations available for ducks.
    enum class Decoration {
        STAR,
        CROSS,
        MOON
    }
    // Map to store the count of each type of decoration.
    private val blingCount = mutableMapOf(
        Decoration.STAR to 0,
        Decoration.CROSS to 0,
        Decoration.MOON to 0
    )
    // Map to store the JLabels displaying the count of each type of decoration.
    private val blingCountLabel = mapOf(
        Decoration.STAR to JLabel("0"),
        Decoration.CROSS to JLabel("0"),
        Decoration.MOON to JLabel("0"),
    )

    // Duck panel components
    private val duckPanel = JPanel()
    private val duckLabel = JLabel("Duck")
    private val duckStrings = listOf("Mallard", "Redhead", "Rubber", "Decoy", "Goose")
    private val duckOptions: JComboBox<*> = JComboBox<Any?>(duckStrings.toTypedArray())
    private var duckType = "Mallard"

    // Bling panel component
    private val blingPanel = JPanel(GridLayout(3, 4, 5, 5))

    // Button panel components
    private val buttonPanel = JPanel()
    private val okayButton = JButton("Okay")
    private val cancelButton = JButton("Cancel")

    //// Public Methods ////

    //// Constructor ////
    init {
        this.contentPane.layout = BoxLayout(this.contentPane, BoxLayout.Y_AXIS)

        // Initialize duck panel
        duckPanel.add(duckLabel)
        duckOptions.addActionListener { e: ActionEvent ->
            val cb = e.source as JComboBox<*>
            duckType = cb.selectedItem as String
        }
        duckPanel.add(duckOptions)
        this.add(duckPanel)

        // Initialize Bling Panel
        addBlingRow(Decoration.STAR, blingPanel)
        addBlingRow(Decoration.CROSS, blingPanel)
        addBlingRow(Decoration.MOON, blingPanel)
        blingPanel.border = BorderFactory.createEmptyBorder(0, 10, 0, 0)
        this.add(blingPanel)

        // Initialize button panel
        cancelButton.addActionListener { dispose() }
        buttonPanel.add(cancelButton)
        okayButton.addActionListener {

            // Create the base duck based on the selected duck type
            val baseDuck: Duck? = when (duckType) {
                "Mallard" -> MallardDuck()
                "Redhead" -> RedheadDuck()
                "Rubber" -> RubberDuck()
                "Decoy" -> DecoyDuck()
                "Goose" -> GooseDuck(Goose())
                else -> null
            }
            // Add the new duck to the model with the selected decorations
            if (baseDuck != null) {
                model.addNewDuck(
                    DuckFactory.createDuck(
                        baseDuck,
                        blingCount[Decoration.STAR] ?: 0,
                        blingCount[Decoration.MOON] ?: 0,
                        blingCount[Decoration.CROSS] ?: 0
                    )
                )
            }
            // Repaint the view and close the dialog
            view.repaint()
            dispose()
        }
        buttonPanel.add(okayButton)
        this.add(buttonPanel)
        this.pack()
        this.setLocationRelativeTo(null)
        this.isVisible = true
    }

    /**
     * Adds a row of decoration options to the bling panel.
     *
     * @param decoration The type of decoration for the row.
     * @param blingPanel The panel to which the row of decoration options is added.
     */
    private fun addBlingRow(decoration: Decoration, blingPanel: JPanel) {
        // Add a JLabel for the decoration name
        blingPanel.add(
            JLabel(
                // Format the decoration name to be title case
                decoration.name.lowercase(Locale.getDefault())
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
        )
        // Add a JLabel to display the count of the decoration
        blingPanel.add(blingCountLabel[decoration])

        // Add an increment button for increasing the decoration count
        val incrementButton = JButton("+")
        incrementButton.addActionListener {
            val count = blingCount[decoration] ?: 0
            val totalBlingCount = blingCount.values.sum()
            // Check if the total number of decorations is less than the maximum allowed (3)
            if (totalBlingCount < 3) {
                // Increment the count if it's less than the maximum allowed (3)
                if (count < 3) {
                    blingCount[decoration] = count + 1
                }
            }
            // Refresh the view to reflect the updated count
            blingCountLabel[decoration]!!.text = blingCount[decoration].toString()
            blingCountLabel[decoration]!!.repaint()
        }
        blingPanel.add(incrementButton)

        // Add a decrement button for decreasing the decoration count
        val decrementButton = JButton("-")
        decrementButton.addActionListener {
            val count = blingCount[decoration] ?: 0
            // Decrement the count if it's greater than 0
            if (count > 0) {
                blingCount[decoration] = count - 1
            }
            // Refresh the view to reflect the updated count
            blingCountLabel[decoration]!!.text = blingCount[decoration].toString()
            blingCountLabel[decoration]!!.repaint()
        }
        blingPanel.add(decrementButton)
    }

}

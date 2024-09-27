import ducksim.DuckPond
import ducksim.DuckSimController
import ducksim.DuckSimView
import java.awt.EventQueue
import javax.swing.JComponent
import javax.swing.JFrame

/*
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * The main function to initialize and show the DuckSim GUI.
 * This function is responsible for setting up the GUI components of the DuckSim application.
 * It creates instances of the model, view, and controller (MVC pattern), separating concerns between the
 * model, view, and controller for better organization and maintainability.
 * It sets up event listeners, configures the JFrame, and makes it visible.
 */
private fun createAndShowGUI() {

    // Create the model for the Duck Pond simulation.
    val model = DuckPond()

    // Create the view for the DuckSim using the provided model.
    val view = DuckSimView(model)

    // Create the controller for the DuckSim, connecting the view and the model.
    val controller = DuckSimController(view, model)

    // Set up the escape action in the view's interface, managed by the controller.
    view.setEscapeAction(controller.escapeAction)

    // Add mouse listeners to the view, which are managed by the controller.
    view.addMouseListener(controller)
    view.addMouseListener(controller.popupListener)

    // Create the JFrame to hold the DuckSim GUI.
    val frame = JFrame("DuckSim")
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

    // Set the content of the frame to be the DuckSim view.
    val content: JComponent = view
    content.isOpaque = true
    frame.contentPane = content

    // Set the size of the frame.
    frame.setSize(800, 600)

    // Center the frame on the screen.
    frame.setLocationRelativeTo(null)

    // Make the frame visible.
    frame.isVisible = true
}

/**
 * The entry point of the DuckSim application.
 * It invokes the createAndShowGUI() function on the event dispatch thread to ensure proper Swing concurrency.
 */
fun main() {

    EventQueue.invokeLater(::createAndShowGUI)
}

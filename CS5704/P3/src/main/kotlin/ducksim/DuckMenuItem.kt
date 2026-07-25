package ducksim

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * Represents a DuckMenuItem, which defines an action that can be performed on a duck.
 * This interface extends the () -> Unit function type, allowing it to be invoked like a function.
 */
interface DuckMenuItem : () -> Unit

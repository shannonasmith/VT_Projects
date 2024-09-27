package ducksim

import java.awt.Color

/**
 * CS 5704 - P4 Design Patterns
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.12.06
 */

/**
 * Represents a Duck, the base class for various types of ducks in the simulation.
 * Implements the Observer interface to observe changes in the simulation.
 *
 * @param defaultFlyBehavior The default fly behavior for the duck.
 * @param defaultQuackBehavior The default quack behavior for the duck.
 */
abstract class Duck(
    val defaultFlyBehavior: FlyBehavior = FlyWithWings(),
    val defaultQuackBehavior: QuackBehavior = QuackNormal()
) : Observer {

    /**
     * The color of the duck, which can be overridden by subclasses.
     */
    open val color: Color = Color.BLACK

    /**
     * The current fly behavior of the duck.
     */
    var flyBehavior: FlyBehavior = defaultFlyBehavior
        protected set

    /**
     * The current quack behavior of the duck.
     */
    var quackBehavior: QuackBehavior = defaultQuackBehavior
        protected set

    /**
     * The current state of the duck.
     */
    var state = State.SWIMMING
        protected set

    /**
     * Indicates whether the duck is free or captured.
     */
    var isFree = true
        protected set

    /**
     * Indicates whether the duck is on the DuckSim Welcoming Committee.
     */
    var isOnDSWC = false
        private set

    /**
     * The text associated with the duck's quack behavior.
     */
    var quackText = quackBehavior.quackText

    /**
     * Resets the duck's state back to its default (swimming)swimming.
     */
    open fun swim() {
        state = State.SWIMMING
    }

    /**
     * Initiates the flying behavior of the duck.
     */
    open fun fly() {
        state = flyBehavior.state
    }

    /**
     * Initiates the quacking behavior of the duck.
     */
    open fun quack() {
        state = quackBehavior.state
    }

    /**
     * Causes the duck to join the DuckSim Welcoming Committee.
     */
    fun doJoin() {
        isOnDSWC = true
        DuckFactory.registerObserver(this@Duck)

    }

    /**
     * Menu item for joining the DuckSim Welcoming Committee.
     */
    open val joinDSCW = object : DuckMenuItem {
        override fun invoke() {
            doJoin()
        }
    }

    /**
     * Causes the duck to quit the DuckSim Welcoming Committee.
     */
    fun doQuit() {
        DuckFactory.removeObserver(this@Duck)
        isOnDSWC = false
    }

    /**
     * Menu item for quitting the DuckSim Welcoming Committee.
     */
    open val quitDSCW = object : DuckMenuItem {
        override fun invoke() {
            doQuit()
        }
    }

    /**
     * Causes the duck to be captured.
     */
    fun doCapture() {
        isFree = false
        flyBehavior = FlyNoWay()
        quackBehavior = QuackNoWay()
    }

    /**
     * Menu item for capturing the duck.
     */
    open val capture = object : DuckMenuItem {
        override fun invoke() {
            doCapture()
        }
    }

    /**
     * Causes the duck to be released.
     */
    fun doRelease() {
        isFree = true
        flyBehavior = defaultFlyBehavior
        quackBehavior = defaultQuackBehavior
    }

    /**
     * Menu item for releasing the duck.
     */
    open val release = object : DuckMenuItem {
        override fun invoke() {
            doRelease()
        }
    }

    /**
     * Displays the appearance of the duck, to be implemented by concrete classes.
     *
     * @return A string representing the appearance of the duck.
     */
    abstract fun display(): String

    /**
     * Updates the state of the duck, implementing the Observer interface.
     */
    override fun update() {
        state = State.WELCOMING
    }

}

package edu.vt.cs5044;

/*
 * CS 5044 Project 2
 *
 * @author Shannon Smith (shae1223)
 * @version 2022.06.13
 */

/**
 * One brief paragraph of operational rules defines the system requirements
 * The following requests always succeed (even if no state is changed):
 *      Lock, unlock, or close the door
 *      Change the gear
 * The childsafe feature can only be accessed when the door is open
 * A request to open the door – via dashboard button or outside handle – succeeds only if:
 *      The gear is in park AND the door is unlocked
 * A request to open the door – via the inside handle – succeeds only if:
 *      The childsafe feature is disengaged
 *      AND the gear is in park AND the door is unlocked
 * <p>
 * Methods:
 * The initial conditions to be set by the constructor are given in the assignment page
 * The accessors are all just simple one-line getter methods
 * Each mutator returns a single Result value to describe the result of the requested action
 * Result is one of several enumerated types used in this assignment; see below for details
 * Before you start: Please be sure to very carefully review all of the Result file comments
 * You must honor the precedence listed in the comments, whenever multiple results apply
 * Some of these may seem counter-intuitive; the priorities are based on safety concerns
 * Java has a special enum type to represent a custom set of unique fixed values
 * An enum is declared as (and mostly acts as) a special kind of class:
 * public enum Gear { PARK, REVERSE, NEUTRAL, DRIVE; }
 * Values are arbitrary and human-readable identifiers, in all caps by convention
 * Specify values in code by type-dot-value: Gear.PARK or Gear.DRIVE
 * An enum reference may be null just like any other object reference
 * However, several features are handled like primitives instead:
 * Declaration and assignment is similar to a primitive: Gear myGear = Gear.NEUTRAL;
 * Comparison is also handled like a primitive, using == or !=
 * Type-safety is strictly enforced by the compiler, eliminating several common kinds of errors
 * In Project 2 there are three enumerated types: Gear, Direction, and Result
 *
 */
public class MinivanSlidingDoor {

    private boolean open;
    private boolean locked;
    private boolean childSafe;
    private Gear gear;

    /**
     *
     * Your constructor must set an initial state with
     * the door closed and unlocked, the childsafe disengaged,
     * and the gear shift lever in Park.
     *
     */
    public MinivanSlidingDoor() {
        open = false;
        locked = false;
        childSafe = false;
        gear = Gear.PARK;
    }

    /**
     *
     * Returns true if isOpen is Open, or false if Closed
     *
     * @return open
     */
    public boolean isOpen() {
        return open;
    }

    /**
     *
     * Returns true if isLocked is Locked, or false if Unlocked
     *
     * @return locked
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     *
     * Returns true if ChildSafe is Engaged, or false if ChildSafe is not Engaged
     *
     * @return childSafe
     */
    public boolean isChildSafe() {
        return childSafe;
    }

    /**
     *
     * Gear can be Park, Reverse, Neutral or Drive.
     *
     * @return gear
     */
    public Gear getGear() {
        return gear;
    }

    /**
     * GEAR_PARKED = Gear was any gear other than park; is now in park.
     * GEAR_RELEASED = Gear was in park; is now in any other gear.
     * GEAR_CHANGED = Gear was any gear other than park; is now in a different non-park gear.
     * For example, this applies to shifting from neutral to drive.
     * <p>
     * When the door is closed, the door cannot be opened
     * if the GEAR shift is in any position but park, regardless
     * of any other conditions. The GEAR can be successfully changed
     * at any time.Even if the GEAR shift is in park, the door cannot
     * be opened if it is locked.
     * <p>
     * Open refused; gear is not in park.
     * OPEN_REFUSED_GEAR
     *
     * @param requestedGear gear requested, different from oldGear
     * @return Result
     */
    public Result setGear(Gear requestedGear) {
        if (requestedGear == null) {
            return Result.INVALID_PARAMETER;
        }

        Gear oldGear = gear;
        gear = requestedGear;

        if (oldGear == requestedGear) {
            return Result.NO_ACTION;
        }
        if (oldGear == Gear.PARK) {
            return Result.GEAR_RELEASED;
        }
        if (requestedGear == Gear.PARK) {
            return Result.GEAR_PARKED;
        }
        return Result.GEAR_CHANGED;
    }

    /**
     *
     * The childsafe engage/disengage switch can only be
     * accessed while the door is open.
     * <p>
     * Open refused; inner handle activated while child-safety engaged.
     * OPEN_REFUSED_CHILDSAFE
     * <p>
     * Child-safe setting is inaccessible, because the door is currently closed.
     * CHILDSAFE_INACCESSIBLE
     *
     * @param requestedEngage engage switch requested when door is open
     * @return Result
     */
    public Result setChildSafe(boolean requestedEngage) {
        if (!open) {
            return Result.CHILDSAFE_INACCESSIBLE;
        }
        if (requestedEngage == childSafe) {
            return Result.NO_ACTION;
        }
        childSafe = requestedEngage;

        if (childSafe) {
            return Result.CHILDSAFE_ENGAGED;
        }
        return Result.CHILDSAFE_DISENGAGED;
    }

    /**
     *
     * The door can be successfully locked or unlocked at any
     * time, by using lock and unlock buttons.
     * <p>
     * Open refused; door is locked.
     * OPEN_REFUSED_LOCK
     *
     * @return Result
     */
    public Result pushLockButton() {
        if (locked) {
            return Result.NO_ACTION;
        }
        locked = true;
        return Result.DOOR_LOCKED;
    }

    /**
     *
     * The door can be successfully locked or unlocked at any
     * time, by using lock and unlock buttons.
     *
     * @return Result
     */
    public Result pushUnlockButton() {
        if (!locked) {
            return Result.NO_ACTION;
        }
        locked = false;
        return Result.DOOR_UNLOCKED;
    }

    /**
     *
     * Private helper
     * <p>
     * Open refused; gear is not in park.
     * OPEN_REFUSED_GEAR
     * <p>
     * Open refused; door is locked.
     * OPEN_REFUSED_LOCK
     *
     * @param direction is either open or closed
     * @return can can return either Result, handleOpen() or handleClose()
     */
    private Result handleOpenClose(Direction direction) {
        if (direction == null) {
            return Result.INVALID_PARAMETER;
        }
        if (direction == Direction.OPEN) {
            return handleOpen();
        }
        return handleClose();
    }

    /**
     *
     * Private helper for handleOpenClose method above
     *
     * @return Result
     */
    private Result handleOpen() {
        if (gear != Gear.PARK) {
            return Result.OPEN_REFUSED_GEAR;
        }
        if (locked) {
            return Result.OPEN_REFUSED_LOCK ;
        }
        if (open) {
            return Result.NO_ACTION;
        }
        open = true;
        return Result.DOOR_OPENED;
    }

    /**
     *
     * Private helper for handleOpenClose method above
     *
     * @return Result
     */
    private Result handleClose() {
        if (!open) {
            return Result.NO_ACTION;
        }
        open = false;
        return Result.DOOR_CLOSED;
    }

    /**
     * There are three mechanisms that can open the door:
     * an open button on the dashboard...
     * <p>
     * The door can be successfully closed at any time,
     * using the same mechanisms used for opening.
     *
     * @param direction indicates if the handles direction is open or closed
     * @return handleOpenClose(direction)
     */
    public Result pushDashboardButton(Direction direction) {
        return handleOpenClose(direction);
    }

    /**
     * There are three mechanisms that can open the door:
     * a handle on the inside of the door...
     * <p>
     * The childsafe engage/disengage switch can only be accessed while
     * the door is open. If childsafe is enabled, the inside handle open
     * request would not work.
     *
     * @param direction indicates if the handles direction is open or closed
     * @return can return either handleOpen() or handleClose()
     */
    public Result pushInsideHandle(Direction direction) {
        // if anything but open, we can delegate
        // this includes close and null
        if (direction != Direction.OPEN) {
            return handleOpenClose(direction);
        }
        // it's an open request, so refuse now if childsafe:
        if (childSafe) {
            return Result.OPEN_REFUSED_CHILDSAFE;
        }
        // otherwise treat this as a normal open request
        return handleOpen();
    }

    /**
     * There are three mechanisms that can open the door:
     * 2) a handle on the outside of the door...
     *
     * @param direction indicates if the handles direction is open or closed
     * @return handleOpenClose(direction)
     */
    public Result pushOutsideHandle(Direction direction) {
        return handleOpenClose(direction);
    }
}

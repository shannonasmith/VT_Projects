package river;

/**
 * CS 5704 Project 2
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.10.08
 */
public enum Location {
    START, FINISH, BOAT;

    public boolean isOnBoat() {
        return this == BOAT;
    }

    public boolean isAtFinish() {
        return this == FINISH;
    }

    public boolean isAtStart() {
        return this == START;
    }
}

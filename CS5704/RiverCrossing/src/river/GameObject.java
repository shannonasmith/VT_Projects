package river;

import java.awt.*;

/**
 * CS 5704 Project 2
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.10.08
 */
public class GameObject {
    private String label;
    private Location location;
    private Color color; // Changed from String to Color
    protected boolean isDriver;

    public GameObject(String name, Location location, Color color, boolean isDriver) {
        this.label = name;
        this.location = location;
        this.isDriver = isDriver;
        this.color = color;
    }

    public String getLabel() {
        return label;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location loc) {
        this.location = loc;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isDriver() {
        return isDriver;
    }

    public void setDriver(boolean isDriver) {
        this.isDriver = isDriver;
    }

}

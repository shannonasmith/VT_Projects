package river;

import java.awt.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * CS 5704 Project 2
 * AbstractGame Engine
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.10.08
 */
public class AbstractGameEngine implements GameEngine{

    Map<Item, GameObject> gameObjects;
    List<Item> itemsOnBoat;

    Location boatLocation;
    public AbstractGameEngine(){
        gameObjects = new EnumMap<>(Item.class);
        itemsOnBoat = new ArrayList<>(2);
    }

    @Override
    public String getItemLabel(Item item) {
        return gameObjects.get(item).getLabel();
    }

    @Override
    public Color getItemColor(Item item) {
        return gameObjects.get(item).getColor();
    }

    @Override
    public boolean getItemIsDriver(Item item) {
        return gameObjects.get(item).isDriver();
    }

    @Override
    public Location getItemLocation(Item item) {
        return gameObjects.get(item).getLocation();
    }

    @Override
    public void setItemLocation(Item item, Location location) {
        gameObjects.get(item).setLocation(location);
    }

    @Override
    public Location getBoatLocation() {
        return boatLocation;
    }

    @Override
    public void loadBoat(Item item) {
        GameObject gameObject = gameObjects.get(item);
        if (gameObject.getLocation() == boatLocation
                && gameObjects.values().stream().filter(o -> o.getLocation() == Location.BOAT).count() < 2) {
            gameObject.setLocation(Location.BOAT);
            itemsOnBoat.add(item);
        }
    }

    @Override
    public void unloadBoat(Item item) {
        GameObject gameObject = gameObjects.get(item);
        if (gameObject.getLocation() == Location.BOAT) {
            gameObject.setLocation(boatLocation);
            itemsOnBoat.remove(item);
        }
    }

    @Override
    public void rowBoat() {
    }

    @Override
    public boolean gameIsWon() {
        return gameObjects.values().stream().allMatch(o -> o.getLocation().isAtFinish());
    }

    @Override
    public boolean gameIsLost() {
        return false;
    }

    @Override
    public void resetGame() {
        gameObjects.forEach((k, v) -> v.setLocation(Location.START));
        boatLocation = Location.START;
    }

    @Override
    public List<Item> getItems() {
        List<Item> list = new ArrayList<>();
        gameObjects.forEach((key, value) -> list.add(key));
        return list;
    }
    @Override
    public List<Item> getItemsOnBoat(){
        return itemsOnBoat;
    }
}

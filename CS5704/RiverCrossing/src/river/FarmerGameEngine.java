package river;

import java.awt.*;

/**
 * CS 5704 Project 2
 * FarmerGame Engine
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.10.08
 */
public class FarmerGameEngine extends AbstractGameEngine {

    public static final Item BEANS = Item.ITEM_0;
    public static final Item GOOSE = Item.ITEM_1;
    public static final Item WOLF = Item.ITEM_2;
    public static final Item FARMER = Item.ITEM_3;


//    private Map<Item, GameObject> gameObjects;
//    private List<Item> itemsOnBoat;
//    private Location boatLocation;


    public FarmerGameEngine() {
//        gameObjects = new EnumMap<>(Item.class);
        super();
        gameObjects.put(Item.ITEM_0, new GameObject("B", Location.START, Color.CYAN, false));
        gameObjects.put(Item.ITEM_1, new GameObject("G", Location.START, Color.CYAN, false));
        gameObjects.put(Item.ITEM_2, new GameObject("W", Location.START, Color.CYAN, false));
        gameObjects.put(Item.ITEM_3, new GameObject("F", Location.START, Color.MAGENTA, true));

        boatLocation = Location.START;
//        itemsOnBoat = new ArrayList<>(2);
    }

//    public String getItemLabel(Item id) {
//        return gameObjects.get(id).getLabel();
//    }

//    public Location getItemLocation(Item id) {
//        return gameObjects.get(id).getLocation();
//    }

//    public Color getItemColor(Item id) {
//        return gameObjects.get(id).getColor();
//    }

//    public Location getBoatLocation() {
//        return boatLocation;
//    }

//    public void loadBoat(Item id) {
//        GameObject gameObject = gameObjects.get(id);
//        if (gameObject.getLocation() == boatLocation
//                /*&& (id == Item.ITEM_3 || gameObjects.get(Item.ITEM_3).getLocation() == Location.BOAT)*/
//                && gameObjects.values().stream().filter(o -> o.getLocation() == Location.BOAT).count() < 2) {
//            gameObject.setLocation(Location.BOAT);
//            itemsOnBoat.add(id);
//        }
//    }


//    public void unloadBoat(Item id) {
//        GameObject gameObject = gameObjects.get(id);
//        if (gameObject.getLocation() == Location.BOAT) {
//            gameObject.setLocation(boatLocation);
//            itemsOnBoat.remove(id);
//        }
//
//    }

    public void rowBoat() {
        assert (boatLocation != Location.BOAT);
        //boatLocation = (boatLocation == Location.START) ? Location.FINISH : Location.START;
        if (getItemLocation(Item.ITEM_3) == Location.BOAT) {
            if (boatLocation == Location.START) {
                boatLocation = Location.FINISH;
            } else {
                boatLocation = Location.START;
            }
        }


    }


    //    public boolean gameIsWon() {
//        return gameObjects.values().stream().allMatch(o -> o.getLocation().isAtFinish());
//    }
    public boolean gameIsLost() {
        if (getItemLocation(Item.ITEM_1) == Location.BOAT) {
            return false;
        }
        if (getItemLocation(Item.ITEM_1) == getItemLocation(Item.ITEM_3)) {
            return false;
        }
        if (getItemLocation(Item.ITEM_1) == boatLocation) {
            return false;
        }
        if (getItemLocation(Item.ITEM_1) == getItemLocation(Item.ITEM_2)) {
            return true;
        }
        if (getItemLocation(Item.ITEM_1) == getItemLocation(Item.ITEM_0)) {
            return true;
        }
        return false;
    }

//    public void resetGame() {
//        gameObjects.forEach((k, v) -> v.setLocation(Location.START));
//        boatLocation = Location.START;
//    }

//    public List<Item> getItems(){
//        List<Item> list = new ArrayList<>();
//        gameObjects.forEach((key, value) -> list.add(key));
//        return list;
//    }
//    @Override
//    public List<Item> getItemsOnBoat(){
//        return itemsOnBoat;
//    }

}

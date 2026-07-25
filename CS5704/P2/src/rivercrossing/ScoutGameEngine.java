package river;

import java.awt.*;

/**
 * CS 5704 Project 2
 * Scout Game Engine
 *
 * @author Shannon Smith (shae1223@vt.edu)
 * @version 2023.10.08
 */
public class ScoutGameEngine extends AbstractGameEngine {
    public static final Item EXPLORER_1 = Item.ITEM_0;
    public static final Item EXPLORER_2 = Item.ITEM_1;
    public static final Item BEAVER_1 = Item.ITEM_2;
    public static final Item BEAVER_2 = Item.ITEM_3;
    public static final Item BEAVER_3 = Item.ITEM_4;

    public ScoutGameEngine() {
        gameObjects.put(Item.ITEM_0, new GameObject("E1", Location.START, Color.CYAN, true));
        gameObjects.put(Item.ITEM_1, new GameObject("E2", Location.START, Color.CYAN, true));
        gameObjects.put(Item.ITEM_2, new GameObject("B2", Location.START, Color.CYAN, true));
        gameObjects.put(Item.ITEM_3, new GameObject("B3", Location.START, Color.CYAN, true));
        gameObjects.put(Item.ITEM_4, new GameObject("B1", Location.START, Color.CYAN, true));

        boatLocation = Location.START;
    }

    public void rowBoat() {
        assert (boatLocation != Location.BOAT);
        if (!itemsOnBoat.isEmpty()) {
            if (boatLocation == Location.START) {
                boatLocation = Location.FINISH;
            } else {
                boatLocation = Location.START;
            }
        }
    }

}
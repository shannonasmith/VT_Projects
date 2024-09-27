package river;

import java.awt.*;

/**
 * CS 5704 Project 2
 * MonsterGame Engine
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.10.08
 */
public class MonsterGameEngine extends AbstractGameEngine {
    public static final Item MONSTER_1 = Item.ITEM_0;
    public static final Item MUNCHKIN_1 = Item.ITEM_1;
    public static final Item MONSTER_2 = Item.ITEM_2;
    public static final Item MUNCHKIN_2 = Item.ITEM_3;
    public static final Item MONSTER_3 = Item.ITEM_4;
    public static final Item MUNCHKIN_3 = Item.ITEM_5;

    public MonsterGameEngine() {
        gameObjects.put(Item.ITEM_0, new GameObject("M1", Location.START, Color.CYAN, true));
        gameObjects.put(Item.ITEM_2, new GameObject("M2", Location.START, Color.CYAN, true));
        gameObjects.put(Item.ITEM_4, new GameObject("M3", Location.START, Color.CYAN, true));
        gameObjects.put(Item.ITEM_1, new GameObject("K1", Location.START, Color.CYAN, true));
        gameObjects.put(Item.ITEM_3, new GameObject("K2", Location.START, Color.CYAN, true));
        gameObjects.put(Item.ITEM_5, new GameObject("K3", Location.START, Color.CYAN, true));

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

    /* Game is lost if the number of monsters ona side is more than number of munchkins.
       When calculating number of monsters and munchkins, should include whatever is currently
       on the boat in whichever side the boat is currently located.
     */
    public boolean gameIsLost() {
        int num_Monsters_start = 0;
        int num_Munchkins_start = 0;
        int num_Monsters_finish = 0;
        int num_Munchkins_finish = 0;

        for (GameObject obj : gameObjects.values()) {
            if (obj.getLabel().contains("M")) {
                if (obj.getLocation() == Location.START) {
                    num_Monsters_start++;
                } else if (obj.getLocation() == Location.FINISH) {
                    num_Monsters_finish++;
                } else {
                    if (getBoatLocation() == Location.START) {
                        num_Monsters_start++;
                    } else {
                        num_Monsters_finish++;
                    }
                }
            } else {
                if (obj.getLocation() == Location.START) {
                    num_Munchkins_start++;
                } else if (obj.getLocation() == Location.FINISH) {
                    num_Munchkins_finish++;
                } else {
                    if (getBoatLocation() == Location.START) {
                        num_Munchkins_start++;
                    } else {
                        num_Munchkins_finish++;
                    }
                }
            }
        }
        if (num_Monsters_start > num_Munchkins_start && num_Munchkins_start != 0) {
            return true;
        } else if (num_Monsters_finish > num_Munchkins_finish && num_Munchkins_finish != 0) {
            return true;
        } else {
            return false;
        }
    }

}

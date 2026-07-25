package river;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class MonsterGameEngineTest {
    public static final Item MONSTER_0 = Item.ITEM_0;
    public static final Item MUNCHKIN_1 = Item.ITEM_1;
    public static final Item MONSTER_2 = Item.ITEM_2;
    public static final Item MUNCHKIN_3 = Item.ITEM_3;
    public static final Item MONSTER_4 = Item.ITEM_4;
    public static final Item MUNCHKIN_5 = Item.ITEM_5;
    private MonsterGameEngine engine;

    @Before
    public void setUp() throws Exception { // Exception 'java.lang.Exception' is never thrown in the method
        engine = new MonsterGameEngine();
    }

    /*
    Winning the Monsters Munchkin Game
        1. Row Munchkin + Monster
        2. Row back Munchkin
        3. Row 2 Monsters
        4. Row back 1 Monster
        5. Row 2 Munchkins
        6. Row back 1 Monster and 1 Munchkin
        7. Row 2 Munchkins
        8. Row 1 Monster backs and row all the monsters
        BOOM!
     */

    @Test
    public void testObjectCallThroughs() {
        Assert.assertEquals("M1", engine.getItemLabel(MONSTER_0));
        Assert.assertEquals(Location.START, engine.getItemLocation(MONSTER_0));
        Assert.assertEquals(Color.GREEN, engine.getItemColor(MONSTER_0));
        Assert.assertEquals(Boolean.TRUE, engine.getItemIsDriver(MONSTER_0));

        Assert.assertEquals("K1", engine.getItemLabel(MUNCHKIN_1));
        Assert.assertEquals(Location.START, engine.getItemLocation(MUNCHKIN_1));
        Assert.assertEquals(Color.BLUE, engine.getItemColor(MUNCHKIN_1));
        Assert.assertEquals(Boolean.TRUE, engine.getItemIsDriver(MUNCHKIN_1));

        Assert.assertEquals("M2", engine.getItemLabel(MONSTER_2));
        Assert.assertEquals(Location.START, engine.getItemLocation(MONSTER_2));
        Assert.assertEquals(Color.GREEN, engine.getItemColor(MONSTER_2));
        Assert.assertEquals(Boolean.TRUE, engine.getItemIsDriver(MONSTER_2));

        Assert.assertEquals("K2", engine.getItemLabel(MUNCHKIN_3));
        Assert.assertEquals(Location.START, engine.getItemLocation(MUNCHKIN_3));
        Assert.assertEquals(Color.BLUE, engine.getItemColor(MUNCHKIN_3));
        Assert.assertEquals(Boolean.TRUE, engine.getItemIsDriver(MUNCHKIN_3));

        Assert.assertEquals("M3", engine.getItemLabel(MONSTER_4));
        Assert.assertEquals(Location.START, engine.getItemLocation(MONSTER_4));
        Assert.assertEquals(Color.GREEN, engine.getItemColor(MONSTER_4));
        Assert.assertEquals(Boolean.TRUE, engine.getItemIsDriver(MONSTER_4));

        Assert.assertEquals("K3", engine.getItemLabel(MUNCHKIN_5));
        Assert.assertEquals(Location.START, engine.getItemLocation(MUNCHKIN_5));
        Assert.assertEquals(Color.BLUE, engine.getItemColor(MUNCHKIN_5));
        Assert.assertEquals(Boolean.TRUE, engine.getItemIsDriver(MUNCHKIN_5));
    }

    @Test
    public void testMidTransport() {
        MonsterGameEngine engine = new MonsterGameEngine();
        Assert.assertEquals(Location.START, engine.getItemLocation(MUNCHKIN_1));

        // transport the Goose
        engine.loadBoat(MUNCHKIN_1);
        engine.loadBoat(MONSTER_4);
        engine.rowBoat();
        engine.unloadBoat(MUNCHKIN_1);
        Assert.assertEquals(Location.FINISH, engine.getItemLocation(MUNCHKIN_1));
    }

    @Test
    public void testWinningGame() {
        MonsterGameEngine engine = new MonsterGameEngine();

        // Move 1: two monsters cross
        engine.loadBoat(MONSTER_0);
        engine.loadBoat(MONSTER_2);
        engine.rowBoat();
        engine.unloadBoat(MONSTER_0);
        engine.unloadBoat(MONSTER_2);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // Move 2: one monster returns
        engine.loadBoat(MONSTER_0);
        engine.rowBoat();
        engine.unloadBoat(MONSTER_0);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // Move 3: two monsters cross
        engine.loadBoat(MONSTER_0);
        engine.loadBoat(MONSTER_4);
        engine.rowBoat();
        engine.unloadBoat(MONSTER_0);
        engine.unloadBoat(MONSTER_4);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // Move 4: one monster returns
        engine.loadBoat(MONSTER_2);
        engine.rowBoat();
        engine.unloadBoat(MONSTER_2);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // Move 5: two munchkins cross
        engine.loadBoat(MUNCHKIN_1);
        engine.loadBoat(MUNCHKIN_3);
        engine.rowBoat();
        engine.unloadBoat(MUNCHKIN_1);
        engine.unloadBoat(MUNCHKIN_3);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // Move 6: one monster and one munchkin return
        engine.loadBoat(MONSTER_4);
        engine.loadBoat(MUNCHKIN_1);
        engine.rowBoat();
        engine.unloadBoat(MONSTER_4);
        engine.unloadBoat(MUNCHKIN_1);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // Move 7: two munchkins cross
        engine.loadBoat(MUNCHKIN_1);
        engine.loadBoat(MUNCHKIN_5);
        engine.rowBoat();
        engine.unloadBoat(MUNCHKIN_1);
        engine.unloadBoat(MUNCHKIN_5);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // Move 8: one monster returns
        engine.loadBoat(MONSTER_0);
        engine.rowBoat();
        engine.unloadBoat(MONSTER_0);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // Move 9: two monsters cross
        engine.loadBoat(MONSTER_0);
        engine.loadBoat(MONSTER_2);
        engine.rowBoat();
        engine.unloadBoat(MONSTER_0);
        engine.unloadBoat(MONSTER_2);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // Move 10: one monster returns
        engine.loadBoat(MONSTER_0);
        engine.rowBoat();
        engine.unloadBoat(MONSTER_0);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // Move 11: final two monsters cross - game won
        engine.loadBoat(MONSTER_0);
        engine.loadBoat(MONSTER_4);
        engine.rowBoat();
        engine.unloadBoat(MONSTER_0);
        engine.unloadBoat(MONSTER_4);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertTrue(engine.gameIsWon());
    }

    @Test
    public void testLosingGame() {
        MonsterGameEngine engine = new MonsterGameEngine();

        // Move 1: two monsters cross (valid)
        engine.loadBoat(MONSTER_0);
        engine.loadBoat(MONSTER_2);
        engine.rowBoat();
        engine.unloadBoat(MONSTER_0);
        engine.unloadBoat(MONSTER_2);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // Move 2: one monster returns (valid)
        engine.loadBoat(MONSTER_0);
        engine.rowBoat();
        engine.unloadBoat(MONSTER_0);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // Move 3: mistake - sending both remaining munchkins across at once,
        // leaving 2 monsters and only 1 munchkin at START
        engine.loadBoat(MUNCHKIN_1);
        engine.loadBoat(MUNCHKIN_3);
        engine.rowBoat();
        engine.unloadBoat(MUNCHKIN_1);
        engine.unloadBoat(MUNCHKIN_3);
        Assert.assertTrue(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());
    }

    // *************************** helper method *************************** //
    /**
     Write a helper method called transport that takes an item.
     The method should transport the item from one side of the river to the other.
     Use it to simplify some of the test cases in which an item is transported.
     */

    private void transport(Item item) {

        // transport the wolf
        engine.loadBoat(MONSTER_2);
        engine.rowBoat();
        engine.unloadBoat(MONSTER_2);

        // transport the goose
        engine.loadBoat(MUNCHKIN_1);
        engine.rowBoat();
        engine.unloadBoat(MUNCHKIN_1);

        // transport the Beans
        engine.loadBoat(MONSTER_0);
        engine.rowBoat();
        engine.unloadBoat(MONSTER_0);

        // go back alone
        engine.rowBoat();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());
    }

    @Test
    public void testError() {

        MonsterGameEngine engine = new MonsterGameEngine();

        // transport the munchkin and a monster together (valid opening move)
        engine.loadBoat(MUNCHKIN_1);
        engine.loadBoat(MONSTER_4);
        engine.rowBoat();
        engine.unloadBoat(MUNCHKIN_1);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // save the state
        Location bottomLoc = engine.getItemLocation(MONSTER_0);
        Location midLoc = engine.getItemLocation(MUNCHKIN_1);
        Location topLoc = engine.getItemLocation(MONSTER_2);
        Location playerLoc = engine.getItemLocation(MONSTER_4);

        // This action should do nothing since the wolf is not on the same shore 
        // as the boat
        engine.loadBoat(MONSTER_2);

        // check that the state has not changed
        Assert.assertEquals(bottomLoc, engine.getItemLocation(MONSTER_0));
        Assert.assertEquals(midLoc, engine.getItemLocation(MUNCHKIN_1));
        Assert.assertEquals(topLoc, engine.getItemLocation(MONSTER_2));
        Assert.assertEquals(playerLoc, engine.getItemLocation(MONSTER_4));
    }

}

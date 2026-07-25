package river;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static river.FarmerGameEngine.*;

/*
 * CS 5704 Project 2
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.10.08
 */
public class FarmerGameEngineTest {
    private FarmerGameEngine engine;
    private GameObject gameObject;
    private Location location;
    private Color color;

    @Before
    public void setUp() throws Exception {
        engine = new FarmerGameEngine();
        color = Color.RED;
        gameObject = new GameObject("Test", location, color, true);
    }

    private void transport(Item item) {
        engine.loadBoat(item);   // Then load the item
        engine.rowBoat();        // Row the boat
        engine.unloadBoat(item); // Unload the item
    }


    @Test
    public void testObjectCallThroughs() {
        Assert.assertEquals("F", engine.getItemLabel(FARMER));
        Assert.assertEquals(Location.START, engine.getItemLocation(FARMER));
        Assert.assertEquals(Color.MAGENTA, engine.getItemColor(FARMER));
        Assert.assertTrue(engine.getItemIsDriver(FARMER));

        // Check getters for wolf
        Assert.assertEquals("W", engine.getItemLabel(WOLF));
        Assert.assertEquals(Location.START, engine.getItemLocation(WOLF));
        Assert.assertEquals(Color.CYAN, engine.getItemColor(WOLF));
        Assert.assertFalse(engine.getItemIsDriver(WOLF));


        // Check getters for goose
        Assert.assertEquals("G", engine.getItemLabel(GOOSE));
        Assert.assertEquals(Location.START, engine.getItemLocation(GOOSE));
        Assert.assertEquals(Color.CYAN, engine.getItemColor(GOOSE));

        // Check getters for beans
        Assert.assertEquals("B", engine.getItemLabel(BEANS));
        Assert.assertEquals(Location.START, engine.getItemLocation(BEANS));
        Assert.assertEquals(Color.CYAN, engine.getItemColor(BEANS));
    }

    @Test
    public void testGooseTransport() {
        engine.loadBoat(FARMER);
        transport(WOLF);
        Assert.assertEquals(Location.FINISH, engine.getItemLocation(WOLF));
    }

    @Test
    public void testWinningGame() {

        // transport the goose
        engine.loadBoat(FARMER);
        transport(GOOSE);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // go back alone
        engine.rowBoat();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());
        // transport the BEANS
        transport(BEANS);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // go back with the GOOSE
        transport(GOOSE);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());
        // transport the WOLF
        transport(WOLF);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // go back alone
        engine.rowBoat();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // transport the GOOSE

        transport(GOOSE);
        engine.unloadBoat(FARMER);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertTrue(engine.gameIsWon());

    }


    @Test
    public void testLosingGame() {
        engine.loadBoat(FARMER);
        transport(GOOSE);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());
        engine.rowBoat();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());
        transport(WOLF);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());
        engine.rowBoat();
        Assert.assertTrue(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());
    }


    @Test
    public void testError() {
        engine.loadBoat(FARMER);
        transport(GOOSE);

        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        Location wolfLoc = engine.getItemLocation(WOLF);
        Location gooseLoc = engine.getItemLocation(GOOSE);
        Location beansLoc = engine.getItemLocation(BEANS);
        Location farmerLoc = engine.getItemLocation(FARMER);

        engine.loadBoat(WOLF);

        Assert.assertEquals(wolfLoc, engine.getItemLocation(WOLF));
        Assert.assertEquals(gooseLoc, engine.getItemLocation(GOOSE));
        Assert.assertEquals(beansLoc, engine.getItemLocation(BEANS));
        Assert.assertEquals(farmerLoc, engine.getItemLocation(FARMER));
    }

    @Test
    public void testGetItems() {
        Assert.assertFalse(engine.getItems().isEmpty());
    }

    @Test
    public void testGetItemsOnBoat() {
        Assert.assertTrue(engine.getItemsOnBoat().isEmpty());
    }

    @Test
    public void testGetItemLocation() {
        Assert.assertEquals(Location.START, engine.getItemLocation(BEANS));
        engine.loadBoat(FARMER);
        Assert.assertEquals(Location.BOAT, engine.getItemLocation(FARMER));
        transport(BEANS);
        engine.rowBoat();
        Assert.assertFalse(engine.gameIsLost());


    }

    @Test
    public void testGameObject() {
        GameObject emptyGameObject = new GameObject("", location, color, true);
        Assert.assertEquals("", emptyGameObject.getLabel());
        Assert.assertEquals("Test", gameObject.getLabel());
        Assert.assertEquals(location, gameObject.getLocation());
        Assert.assertEquals(color, gameObject.getColor());
        Assert.assertTrue(gameObject.isDriver());
        Assert.assertEquals("Test", gameObject.getLabel());

        Assert.assertEquals(location, gameObject.getLocation());

        gameObject.setLocation(Location.BOAT);
        Assert.assertEquals(Location.BOAT, gameObject.getLocation());
        Assert.assertEquals(color, gameObject.getColor());

        Color newColor = Color.BLUE;
        gameObject.setColor(newColor);
        Assert.assertEquals(newColor, gameObject.getColor());

        Assert.assertTrue(gameObject.isDriver());

        gameObject.setDriver(false);
        Assert.assertFalse(gameObject.isDriver());

    }


}
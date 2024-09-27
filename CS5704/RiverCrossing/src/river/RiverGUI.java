package river;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * CS 5704 Project 2
 * Graphical user interface for the River application
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.10.08
 */
public class RiverGUI extends JPanel implements MouseListener {

    // ==========================================================
    // Fields (hotspots)
    // ==========================================================

    // Shae Scout Game --> updated x for farmer, so it's centered, and then changed monster restart,
    //                      so it's the same distance to the right as it was before. Hope that's ok.
    private final Rectangle restartButtonRect = new Rectangle(350, 120, 100, 30);
    private final Rectangle monsterRestartButtonRect = new Rectangle(490, 120, 100, 30);
    private final Rectangle scoutRestartButtonRect = new Rectangle(210, 120, 100, 30); // Shae Scout Game
    private final int startBaseX = 20;
    private final int finishBaseX = 670;
    private final int startBoatX = 140;
    private final int finishBoatX = 550;
    private final int baseY = 215;
    private final int boatY = 275;
    private final int itemWidth = 50;
    private final int itemHeight = 50;
    private final int boatWidth = 110;
    private final int[] dx = { 0, 60, 0, 60, 0, 60};
    private final int[] dy = { 0, 0, -60, -60, -120, -120};

    // ==========================================================
    // Private Fields
    // ==========================================================

    private GameEngine engine; // Model
    private boolean restart = false;
    private final Map<Item, Rectangle> itemRecMap;
    private Rectangle boatRectangle;
    private Map<Item, Integer> itemXOffsetMap;


    // ==========================================================
    // Constructor
    // ==========================================================

    public RiverGUI() {

        engine = new FarmerGameEngine();
        itemRecMap = new HashMap<>(); //Maybe able to use the items list which will have it the interface and
        //implemented in different game engines based on number of items they have.
        addMouseListener(this);
    }

    // ==========================================================
    // Paint Methods (View)
    // ==========================================================

    @Override
    public void paintComponent(Graphics g) {

        for (Item item : engine.getItems()){
            updateItemRectangle(item);
        }
        updateBoatRectangle();

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        itemRecMap.forEach((item, rec) -> {
            paintRectangle(g, Color.CYAN, engine.getItemLabel(item),rec);
        });
        g.setColor(Color.ORANGE);
        paintRectangle(boatRectangle, g);

        String message = "";
        if (engine.gameIsLost()) {
            message = "You Lost!";
            restart = true;
        }
        if (engine.gameIsWon()) {
            message = "You Won!";
            restart = true;
        }
        paintMessage(message, g);
        if (restart) {
            paintRestartButton(g);
        }


    }
    private void updateItemRectangle(Item item) {
        Rectangle updatedRectangle;
        switch (engine.getItemLocation(item)) {
            case START:
                updatedRectangle = getUpdatedShoreRectangle(startBaseX, item);
                break;
            case FINISH:
                updatedRectangle = getUpdatedShoreRectangle(finishBaseX, item);
                break;
            default: // item Location is Location.BOAT
                int x = (engine.getBoatLocation() == Location.START) ? startBoatX : finishBoatX;
                int seat = getAssignedSeat(item);
                x += (seat * 60);
                int y = boatY - 60;
                updatedRectangle = new Rectangle(x, y, itemWidth, itemHeight);
                break;
        }
        itemRecMap.put(item, updatedRectangle);
    }
    private Rectangle getUpdatedShoreRectangle(int baseX, Item item){
        Rectangle result = new Rectangle(
                baseX + dx[item.ordinal()],
                baseY + dy[item.ordinal()],
                itemWidth,
                itemHeight);
        return result;
    }
    private int getAssignedSeat(Item item) {
        return engine.getItemsOnBoat().indexOf(item); // Add 1 to make it 1-based indexing
    }
    private void updateBoatRectangle(){
        switch (engine.getBoatLocation()) {
            case START:
                boatRectangle = new Rectangle(startBoatX, boatY, boatWidth, itemHeight);
                break;
            case FINISH:
                boatRectangle = new Rectangle(finishBoatX, boatY, boatWidth, itemHeight);
                break;
        }
    }

    public void paintStringInRectangle(String str, int x, int y, int width, int height, Graphics g) {
        g.setColor(Color.BLACK);
        int fontSize = (height >= 40) ? 36 : 18;
        g.setFont(new Font("Verdana", Font.BOLD, fontSize));
        FontMetrics fm = g.getFontMetrics();
        int strXCoord = x + width / 2 - fm.stringWidth(str) / 2;
        int strYCoord = y + height / 2 + fontSize / 2 - 4;
        g.drawString(str, strXCoord, strYCoord);
    }

    public void paintMessage(String message, Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 32));
        FontMetrics fm = g.getFontMetrics();
        int strXCoord = 400 - fm.stringWidth(message) / 2;
        int strYCoord = 100;
        g.drawString(message, strXCoord, strYCoord);
    }

    public void paintRestartButton(Graphics g) {
        g.setColor(Color.BLACK);
        paintBorder(restartButtonRect, 3, g);
        paintBorder(monsterRestartButtonRect, 3, g);
        paintBorder(scoutRestartButtonRect, 3, g); // Shae Scout Game
        g.setColor(Color.PINK);
        paintRectangle(restartButtonRect, g);
        paintRectangle(monsterRestartButtonRect, g);
        paintRectangle(scoutRestartButtonRect, g);// Shae Scout Game
        paintStringInRectangle("Farmer", restartButtonRect.x, restartButtonRect.y, restartButtonRect.width,
                restartButtonRect.height, g);
        paintStringInRectangle("Monster", monsterRestartButtonRect.x, monsterRestartButtonRect.y,
                monsterRestartButtonRect.width, monsterRestartButtonRect.height, g);
        // Shae Scout Game
        paintStringInRectangle("Scout", scoutRestartButtonRect.x, scoutRestartButtonRect.y,
                scoutRestartButtonRect.width, scoutRestartButtonRect.height, g);
    }

    public void paintBorder(Rectangle r, int thickness, Graphics g) {
        g.fillRect(r.x - thickness, r.y - thickness, r.width + (2 * thickness), r.height + (2 * thickness));
    }
    private void paintRectangle(Graphics g, Color color, String label, Rectangle rect) {
        if(label.equals("F")){
            g.setColor(Color.MAGENTA);
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
        else {
            g.setColor(color);
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
            g.setColor(Color.BLACK);
            int fontSize = (rect.height >= 40) ? 36 : 18;
            g.setFont(new Font("Verdana", Font.BOLD, fontSize));
            FontMetrics fm = g.getFontMetrics();
            int strXCoord = rect.x + rect.width / 2 - fm.stringWidth(label) / 2;
            int strYCoord = rect.y + rect.height / 2 + fontSize / 2 - 4;
            g.drawString(label, strXCoord, strYCoord);
        }



    }
    public void paintRectangle(Rectangle r, Graphics g) {
        g.fillRect(r.x, r.y, r.width, r.height);
    }

    // ==========================================================
    // Startup Methods
    // ==========================================================

    /**
     * Create the GUI and show it. For thread safety, this method should be invoked
     * from the event-dispatching thread.
     */
    private static void createAndShowGUI() {

        // Create and set up the window
        JFrame frame = new JFrame("RiverCrossing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane
        RiverGUI newContentPane = new RiverGUI();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        // Display the window
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(RiverGUI::createAndShowGUI);
    }

    // ==========================================================
    // MouseListener Methods (Controller)
    // ==========================================================

    @Override
    public void mouseClicked (MouseEvent e) {
        if (engine.gameIsWon() || engine.gameIsLost()) {
            if (restartButtonRect.contains(e.getPoint())) {
                engine = new FarmerGameEngine();
                //clearSeatAssignments();
                engine.resetGame();
                restart = false;
                repaint();
            } else if (monsterRestartButtonRect.contains(e.getPoint())) {
                engine = new MonsterGameEngine();
                engine.resetGame();
                restart = false;
//                clearSeatAssignments ();
                repaint();
            }

            // Shae Scout Game
            else if (scoutRestartButtonRect.contains(e.getPoint())) {
                engine = new ScoutGameEngine();
                engine.resetGame();
                repaint();
            }

            else {
                return;
            }
        } else if (boatRectangle.contains(e.getPoint())) {
            engine.rowBoat();
        } else {
            itemRecMap.forEach((item, rec) -> {
                if (itemRecMap.get(item).contains(e.getPoint())){
                    if (Objects.requireNonNull(engine.getItemLocation(item)) == Location.BOAT) {
                        engine.unloadBoat(item);
                    }
                    else{
                        engine.loadBoat(item);
                    }
                }
            });
        }
        repaint();
    }

    // ----------------------------------------------------------
    // None of these methods will be used
    // ----------------------------------------------------------

    @Override
    public void mousePressed(MouseEvent e) {
        //
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }
}

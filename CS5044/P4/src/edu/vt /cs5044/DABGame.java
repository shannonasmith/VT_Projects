package edu.vt.cs5044;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
*
* CS 5044 Project 4
* 
* @author Shannon Smith (shae1223)
* @version 2022.07.14
*
*/

public class DABGame implements DotsAndBoxes {       
    private boolean initialized;
    private int gridSize;
    private Player currentPlayer;
    private Map<Coordinate, Box> boxMap; 
    
    /**
     * 
     * Construct a new DABGame object. 
     *     The constructor has no arguments 
     *     and does not initialize a new game.
     *
     */
    public DABGame() {
        initialized = false; 
        gridSize = 0;
        currentPlayer = null;
        boxMap = null;
    }
    
    /**
     * 
     * Checking if the game is initialized.
     * 
     */   
    private void checkInit() { 
        if (!initialized) {
            throw new GameException();
        } 
    }
    
    /**
     * 
     * Find Box in Box class
     *     There are three places where we receive coordinates: 
     *     drawEdge, getDrawnEdgesAt, getOwnerAt.
     *   
     * @param coord coordinate for that box (i.e. (0, 0).
     * @return boxMap 
     * 
     */
    private Box findBox(Coordinate coord) {  
        if (!boxMap.containsKey(coord)) { 
            throw new GameException(); 
        }
        return boxMap.get(coord);   
    }  
     
    @Override
    public boolean drawEdge(Coordinate coord, Direction dir) {
        checkInit();
        if (dir == null) {
            throw new GameException();  
        } 
        Box box = findBox(coord);
        if (box.hasEdge(dir)) {              
            return false; 
        }
        boolean changePlayer = true; 
        box.addEdge(dir, currentPlayer);
         
        if (box.isCompleted()) {  
            changePlayer = false; 
        }
        try { 
            Box adjBox = findBox(coord.getNeighbor(dir));     
            adjBox.addEdge(dir.getOpposite(), currentPlayer);  
            
            if (adjBox.isCompleted()) { 
                changePlayer = false; 
            }  
        } catch (GameException ge) { 
            // expected empty catch block; no action needed
        }
        if (changePlayer) { 
            currentPlayer = currentPlayer.getOpponent();  
        }
        return true; 
    }
    
    /**
     * 
     * Determine if game is over.
     *   
     * @return totalScore 
     * 
     */  
    private boolean isGameOver() { 
        Map<Player, Integer> scoreMap  = getScores(); 
        int totalScore = scoreMap.get(Player.ONE) + scoreMap.get(Player.TWO);  
        return totalScore == gridSize * gridSize;
    }
    
    @Override
    public Player getCurrentPlayer() {
        checkInit(); 
        if (isGameOver()) {
            return null;
        }
        return currentPlayer;
    }
    
     
    @Override
    public Collection<Direction> getDrawnEdgesAt(Coordinate coord) { 
        checkInit(); 
        Box box = findBox(coord) ;
        return box.getDrawnEdges();
    } 

    @Override
    public Player getOwnerAt(Coordinate coord) {
        checkInit(); 
        Box box = findBox(coord); 
        return box.getOwner();
    }

    @Override
    public Map<Player, Integer> getScores() {
        checkInit(); 
        int p1 = 0;
        int p2 = 0;       
        for (Coordinate coord : boxMap.keySet()) {
            Box box = boxMap.get(coord);
            Player owner = box.getOwner();  
            if (owner == Player.ONE) { 
                p1++; 
            }
            
            if (owner == Player.TWO) { 
                p2++; 
            }
        }

        Map<Player, Integer> scores = new HashMap<Player, Integer>();
        scores.put(Player.ONE, p1); 
        scores.put(Player.TWO, p2);
        return scores;   
    }

    @Override
    public int getSize() { 
        checkInit(); 
        return gridSize;  
    } 

    @Override
    public void init(int size) {   
        if (size < 2) {
            throw new GameException();  
        } 
        gridSize = size;
        initialized = true; 
        boxMap = new HashMap<>();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                boxMap.put(new Coordinate(x, y), new Box()); 
            } 
        }
        Map<Player, Integer> scores = new HashMap<Player, Integer>();
        scores.clear();
        currentPlayer = Player.ONE;
    }
}


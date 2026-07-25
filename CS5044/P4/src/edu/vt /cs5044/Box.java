package edu.vt.cs5044;

import java.util.Collection;
import java.util.HashSet;

/**
 *
 * CS 5044 Project 4
 * 
 * @author Shannon Smith (shae1223)
 * @version 2022.07.14
 * 
 */
public class Box {
    
    private Player owner;
    private Collection<Direction> drawnEdges;
    
    /**
     * 
     * Construct a new Box object.
     *
     */
    public Box() {
        owner  = null;
        drawnEdges = new HashSet<Direction>(); 
    }
    
    /**
     * 
     * Get the owner of the completed box object.
     *
     * @return owner
     * 
     */
    public Player getOwner() {
        return owner;
    } 
    
    /**
     * 
     * Get the Collection of edges drawn at any 
     * given time in the game.
     *
     * @return drawnEdges
     * 
     */
    public Collection<Direction> getDrawnEdges() { 
        return new HashSet<>(drawnEdges); 
    }
    
    /**
     * 
     * Determine if an edge was successfully drawn. 
     * 
     * Edges = specific (enumerated) direction:
     * 
     *     BOTTOM - Indicates the bottom edge.
     *     LEFT - Indicates the left edge.
     *     RIGHT - Indicates the right edge.
     *     TOP - Indicates the top edge.
     * 
     * @param dir direction of line drawn 
     * @return drawnEdges.contains(dir)
     * 
     */
    public boolean hasEdge(Direction dir) { 
        return drawnEdges.contains(dir); 
    }
    
    /**
     * 
     * Method to determine if drawing of a box is completed. 
     *
     * @return drawnEdges
     * 
     */
    public boolean isCompleted() {
        return drawnEdges.size() == 4; 
    } 
    
    /**
     * 
     * The drawn line represents one edge of at least one box.
     *     Note that each edge is shared between two 
     *     neighboring boxes, except for boxes located 
     *     along the outer-most grid boundaries.       
     *
     * @param dir direction of line drawn
     * @param currentPlayer is the current Player
     * 
     */
    public void addEdge(Direction dir, Player currentPlayer) {
        drawnEdges.add(dir);
        if (isCompleted()) {  
            owner = currentPlayer; 
        }
    }
}

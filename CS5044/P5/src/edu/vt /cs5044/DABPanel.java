package edu.vt.cs5044;

import static edu.vt.cs5044.DABGuiName.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

// 2022.Summer

/**
 * Dots and Boxes GUI integration
 *
 *
 * CS 5044 Project 5
 * 
 * @author Shannon Smith (shae1223)
 * @version 2022.07.25 
 * 
 */
@SuppressWarnings("serial")

public class DABPanel extends JPanel {

    private final DotsAndBoxes game;

    private final JLabel p1ScoreLabel;
    private final JLabel p2ScoreLabel;
    private final JLabel turnLabel;
    private final JComboBox<Integer> xCombo;
    private final JComboBox<Integer> yCombo;
    private final JComboBox<Direction> dirCombo;
    private final JButton drawButton;
    private final DABGrid dabGrid;

    /////////////////////////////////////////////////////////////////////

    /**
     * Construct a new DABPanel object.
     *
     * @param frame = JFrame
     */
    public DABPanel(JFrame frame) {
        
        frame.setJMenuBar(setupMenuBar()); 
        
        game = new DABGame();

        xCombo = new JComboBox<>(); 
        xCombo.setName(X_COMBO); 
        xCombo.setFont(new Font("Verdana", Font.BOLD, 12));
        
        yCombo = new JComboBox<>(); 
        yCombo.setName(Y_COMBO); 
        yCombo.setFont(new Font("Verdana", Font.BOLD, 12));
        
        dirCombo = new JComboBox<>(Direction.values()); 
        dirCombo.setName(DIR_COMBO); 
        dirCombo.setFont(new Font("Verdana", Font.BOLD, 12));
        
        drawButton = new JButton("Draw!");  
        drawButton.setName(DRAW_BUTTON); 
        drawButton.setFont(new Font("Verdana", Font.BOLD, 12));
        drawButton.addActionListener(this::handleDrawButton); 
        drawButton.setName(DRAW_BUTTON); 
        
        turnLabel = new JLabel(); 
        turnLabel.setName(TURN_LABEL); 
        turnLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        
        p1ScoreLabel = new JLabel();
        p1ScoreLabel.setName(P1_SCORE_LABEL); 
        p1ScoreLabel.setFont(new Font("Verdana", Font.BOLD, 14));
        
        p2ScoreLabel = new JLabel();
        p2ScoreLabel.setName(P2_SCORE_LABEL); 
        p2ScoreLabel.setFont(new Font("Verdana", Font.BOLD, 14));

        dabGrid = new DABGrid(game); 
        dabGrid.setName(DAB_GRID);

        setupLayout(); 
        
        startGame(3);

    }
    
    /////////////////////////////////////////////////////////////////////

    /**
     * 
     * Handles the draw button of the Dots and Boxes game.
     *
     * @param ae is for the ActionEvent
     */
    private void handleDrawButton(ActionEvent ae) {
        int x = xCombo.getItemAt(xCombo.getSelectedIndex());
        int y = yCombo.getItemAt(yCombo.getSelectedIndex());
        Direction dir = dirCombo.getItemAt(dirCombo.getSelectedIndex());
        
        Coordinate xy = new Coordinate(x, y);
        
        boolean needUpdate = game.drawEdge(xy, dir); 
        updateStatus(needUpdate); 
    }
        
    /////////////////////////////////////////////////////////////////////

    /**
     * 
     * Updates the player score and the player turn and displays result.
     *
     * @param updateRequired determines if an update is required after each edge is drawn
     */
    private void updateStatus(boolean updateRequired) {
        
        p1ScoreLabel.setText("ONE: " + game.getScores().get(Player.ONE).toString());
        p2ScoreLabel.setText("TWO: " + game.getScores().get(Player.TWO).toString());
        
        if (game.getCurrentPlayer() == null) {
            turnLabel.setText("GAME OVER!!!");
            
            drawButton.setEnabled(false); 
        } 
        else {
            turnLabel.setText("Player " + game.getCurrentPlayer().toString() + " Go!");
        }
        repaint();
    }
    
    /////////////////////////////////////////////////////////////////////
    
    /**
     * 
     * Displays the coordinate choices for X and Y based on the chosen grid size.
     * 
     */
    private void updateCombos() {
        String[] xyCombos = {"0", "1", "2", "3"};
        
        
        if (game.getSize() == 2) {
                       
            xCombo.removeAllItems(); // x Coordinate 2x2
            xCombo.addItem(0);
            xCombo.addItem(1);

            yCombo.removeAllItems(); // y Coordinate 2x2
            yCombo.addItem(0);
            yCombo.addItem(1);

        }  
        else if (game.getSize() == 3) {
            
            xCombo.removeAllItems(); // x Coordinate 3x3
            xCombo.addItem(0);
            xCombo.addItem(1);
            xCombo.addItem(2);
                   
            yCombo.removeAllItems(); // y Coordinate 3x3
            yCombo.addItem(0);
            yCombo.addItem(1);
            yCombo.addItem(2);
        } 
        else { // if (game.getSize() == 4) 

            xCombo.removeAllItems(); // x Coordinate 4x4
            xCombo.addItem(0);
            xCombo.addItem(1);
            xCombo.addItem(2);
            xCombo.addItem(3);
        
            yCombo.removeAllItems(); // y Coordinate 4x4
            yCombo.addItem(0);
            yCombo.addItem(1);
            yCombo.addItem(2);
            yCombo.addItem(3);
        }
    }

    /////////////////////////////////////////////////////////////////////
    
    /**
     * 
     * Initializes a new game based on the grid size selected
     *
     * @param size is the size of the game grid selected
     */
    private void startGame(int size) { 
        drawButton.setEnabled(true);
        game.init(size);
        updateCombos();
        updateStatus(true); 
    }

    /////////////////////////////////////////////////////////////////////
    
    /**
     * 
     * Layout setup for the GUI of Dots and Boxes game
     *
     */
    private void setupLayout() {
        JPanel dabPanel = new JPanel();
        dabPanel.setLayout(new BorderLayout());
        
        JPanel controlsStatusPanel = new JPanel();
        controlsStatusPanel.setLayout(
            new BoxLayout(controlsStatusPanel, BoxLayout.PAGE_AXIS));      

        JPanel statusPanel = new JPanel(); // status indicators 
        statusPanel.setLayout(new FlowLayout());
        statusPanel.add(turnLabel); 
        
        JPanel controlPanel = new JPanel(); // input components
        controlPanel.setLayout(
            new BoxLayout(controlPanel, BoxLayout.LINE_AXIS));
        controlPanel.add(xCombo);
        controlPanel.add(yCombo);
        controlPanel.add(dirCombo);
        controlPanel.add(drawButton);
        
        controlsStatusPanel.add(statusPanel);
        controlsStatusPanel.add(controlPanel);
        
        JPanel playerScoresPanel = new JPanel(); // player scores
        playerScoresPanel.setLayout(new BorderLayout());
        
        playerScoresPanel.add(p1ScoreLabel, BorderLayout.LINE_START);
        playerScoresPanel.add(p2ScoreLabel, BorderLayout.LINE_END);
        
        dabPanel.add(controlsStatusPanel, BorderLayout.PAGE_START);
        
        dabPanel.add(dabGrid, FlowLayout.CENTER); // grid panel
        
        dabPanel.add(playerScoresPanel, BorderLayout.PAGE_END);
          
        add(dabPanel);
    } 

    /////////////////////////////////////////////////////////////////////
  
    /**
     * 
     * Creates the menu for the game, per grid size
     *
     * @return menuBar for choosing grid size and interactivity
     */
    private JMenuBar setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();        
        JMenu gameMenu = new JMenu("Game");
        gameMenu.setFont(new Font("Verdana", Font.BOLD, 13));
        JMenuItem newGame = new JMenu("New");
        newGame.setFont(new Font("Verdana", Font.BOLD, 13));

        JMenuItem new2x2Game = new JMenuItem("Size 2x2");
        newGame.add(new2x2Game);
        new2x2Game.setFont(new Font("Verdana", Font.BOLD, 13));
        new2x2Game.addActionListener(e -> startGame(2)); 
            
        JMenuItem new3x3Game = new JMenuItem("Size 3x3");
        newGame.add(new3x3Game);
        new3x3Game.setFont(new Font("Verdana", Font.BOLD, 13));
        new3x3Game.addActionListener(e -> startGame(3)); 
        
        JMenuItem new4x4Game = new JMenuItem("Size 4x4"); 
        newGame.add(new4x4Game);
        new4x4Game.setFont(new Font("Verdana", Font.BOLD, 13));
        new4x4Game.addActionListener(e -> startGame(4)); 
        
        JCheckBoxMenuItem checkBox = new JCheckBoxMenuItem("Interactive Grid");
        checkBox.setFont(new Font("Verdana", Font.BOLD, 13));

        checkBox.addActionListener(e -> {
            if (checkBox.isSelected()) {
                dabGrid.setListener(this::updateStatus);
            } else {
                dabGrid.removeListener();
            }
        });

        gameMenu.add(newGame);
        gameMenu.add(checkBox);
        menuBar.add(gameMenu);

        return menuBar; 
    }

    /////////////////////////////////////////////////////////////////////

    /**
     * This is boilerplate code; please be sure to use this exactly as-is.
     *
     */
    private static void createAndShowGUI() { 
        JFrame frame = new JFrame("Dots And Boxes"); // added my name
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JComponent newContentPane = new DABPanel(frame);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack(); 
        frame.setVisible(true);
    }

    /**
     * This is boilerplate code; please be sure to use this exactly as-is.
     *
     * @param args arguement
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DABPanel::createAndShowGUI);
    }
    
}

package edu.vt.cs5044;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.vt.cs5044.tetris.AI;
import edu.vt.cs5044.tetris.Board;
import edu.vt.cs5044.tetris.Placement;
import edu.vt.cs5044.tetris.Rotation;
import edu.vt.cs5044.tetris.Shape;

/**
* CS 5044 Project 3
*
* @author Shannon Smith (shae1223)
* @version 2022.06.29
*
*/
public class TetrisAITest {
    private AI brain;
    
    private Board emptyBoard;
    private Board oneBlockColumn;
    private Board apiBoardACH;
    
    private Board testBoardI;
    private Board testBoardJ;
    private Board testBoardL;
    private Board testBoardO;
    private Board testBoardS;
    private Board testBoardT;
    private Board testBoardZ;
    
    private Board shaesBoard1;
    private Board shaesBoard2;
    private Board shaesBoard3;
    private Board shaesBoard4;
    private Board shaesBoard5;
    
    @Before
    public void setUp() throws Exception {
        brain = new TetrisAI();
        emptyBoard = new Board(); 
        
        oneBlockColumn = new Board(
            "     #    "
            ); // expect: ACH=0; CHR=1; CHV=2; TGC=0
        
        apiBoardACH = new Board ( 
            "## ##    #",
            "# ##### ##",
            "#### #####",
            "# ##### ##",
            "## #######",
            "######### ",
            " #########",
            " #########",
            "###  #####",
            "####### ##",
            "######## #",
            " #### ####"
            ); // expect: ACH=11; CHR=2; CHV=6; TGC=14
        
        testBoardI = new Board ( 
            "### ######",
            "### ######",
            "### ######",
            "### ######",
            "##########"
            ); // expect: ACH=4; CHR=4; CHV=8; TGC=14
            // Best placement for "I" Shape  = Rotation: NONE, Col: 3
        
        testBoardJ = new Board ( 
            "#####   ##",
            "####### ##",
            "##########"
            ); // expect: ACH=2; CHR=2; CHV=4; TGC=14
            // Best placement for "J" Shape = Rotation: CCW_90, Col: 5
        
        testBoardL = new Board ( 
            "###   ####",
            "### ######",
            "##########"
            ); // expect: ACH=11; CHR=2; CHV=6; TGC=14
            // Best placement for "L" Shape = Rotation: CCW_270, Col: 3
        
        testBoardO = new Board ( 
            "##  ######",
            "##  ######",
            "##########"
            ); // expect: ACH= CHR=2; CHV=; TGC=
            // Best placement for "O" Shape = Rotation: NONE, Col: 2
        
        testBoardS = new Board ( 
            "##  ######",
            "##  ######",
            "### ######",
            "##########"
            ); // expect: ACH=3; CHR=3; CHV=6; TGC=14  
            // Best placement for "S" Shape = Rotation: CCW_90, Col: 2
        
        testBoardT = new Board ( 
            "#####   ##",
            "###### ###",
            "##########"
            ); // expect: ACH=2; CHR=2; CHV=4; TGC=
            // Best placement for "T" Shape = Rotation: CCW_180, Col: 5
        
        testBoardZ = new Board (  
            "####   ###",            
            "#####  ###",           
            "##########"
            ); // expect: ACH=11; CHR=2; CHV=6; TGC=14  
            // Best placement for "Z" Shape = Rotation: NONE, Col: 4
        
        shaesBoard1 = new Board( 
            "         #",
            "        ##",
            "     #####",
            "      # ##",
            "     #####",
            "     #### ",
            "     #  ##",
            " ##### ###",
            "##### ####"
            ); // expect: ACH=4; CHR=8; CHV=8; TGC=7
        
        shaesBoard2 = new Board( 
            "    ##    ",
            "   ####   ",
            "     #### ",
            "   #### ##",
            "   #######",
            " #### ####",
            "   ###### ",
            " #### ### ",
            " # #######"
            ); // expect: ACH=6; CHR=9; CHV=12; TGC=10
        
        shaesBoard3 = new Board( 
            "##        ",
            " ##       ",
            " ###      ",
            " ###      ",
            "  ##      ",
            " ####     ",
            "  ########",
            " #########",
            " #########",
            " ## ######"
            ); // expect: ACH=6; CHR=6; CHV=6; TGC=12
        
        shaesBoard4 = new Board( 
            "  ##      ",
            " ##       ",
            "###       ",
            "###       ",
            "###       ",
            "# #       ",
            "### ###   ",
            "####### ##",
            "# ########"
            ); // expect: ACH=4; CHR=8; CHV=11; TGC=8

        shaesBoard5 = new Board( 
            " ###      ",
            "## ##    #",
            "# ####   #",
            "# ## #   #",
            "# #####  #",
            "# ## ##  #",
            "#### #####",
            "# ##### ##",
            "### ######"
            ); // expect: ACH=6; CHR=6; CHV=12; TGC=11
    }
  
    @Test
    public void testBP() {
        assertEquals(new Placement(Rotation.NONE, 3),    brain.findBestPlacement(testBoardI,  Shape.I));
        assertEquals(new Placement(Rotation.CCW_90, 5),  brain.findBestPlacement(testBoardJ,  Shape.J));
        assertEquals(new Placement(Rotation.CCW_270, 3), brain.findBestPlacement(testBoardL,  Shape.L));
        assertEquals(new Placement(Rotation.NONE, 2),    brain.findBestPlacement(testBoardO,  Shape.O));
        assertEquals(new Placement(Rotation.CCW_90, 2),  brain.findBestPlacement(testBoardS,  Shape.S));
        assertEquals(new Placement(Rotation.CCW_180, 5), brain.findBestPlacement(testBoardT,  Shape.T));
        assertEquals(new Placement(Rotation.NONE, 4),    brain.findBestPlacement(testBoardZ,  Shape.Z));

        assertEquals(new Placement(Rotation.CCW_90, 6),  brain.findBestPlacement(oneBlockColumn, Shape.I));
        assertEquals(new Placement(Rotation.CCW_90, 5),  brain.findBestPlacement(apiBoardACH, Shape.I));
        assertEquals(new Placement(Rotation.NONE, 4),    brain.findBestPlacement(shaesBoard1, Shape.I));
        assertEquals(new Placement(Rotation.NONE, 0),    brain.findBestPlacement(shaesBoard2, Shape.I));
        assertEquals(new Placement(Rotation.CCW_90, 5),  brain.findBestPlacement(shaesBoard3, Shape.I));        
        assertEquals(new Placement(Rotation.NONE, 4),    brain.findBestPlacement(shaesBoard4, Shape.I));
        assertEquals(new Placement(Rotation.NONE, 8),    brain.findBestPlacement(shaesBoard5, Shape.I));   

        assertEquals(new Placement(Rotation.CCW_270, 0), brain.findBestPlacement(oneBlockColumn, Shape.J));
        assertEquals(new Placement(Rotation.CCW_90, 5),  brain.findBestPlacement(apiBoardACH, Shape.J));
        assertEquals(new Placement(Rotation.NONE, 3),    brain.findBestPlacement(shaesBoard1, Shape.J));
        assertEquals(new Placement(Rotation.CCW_90, 7),  brain.findBestPlacement(shaesBoard2, Shape.J));
        assertEquals(new Placement(Rotation.CCW_90, 0),  brain.findBestPlacement(shaesBoard3, Shape.J));        
        assertEquals(new Placement(Rotation.CCW_270, 4), brain.findBestPlacement(shaesBoard4, Shape.J));
        assertEquals(new Placement(Rotation.NONE, 7),    brain.findBestPlacement(shaesBoard5, Shape.J));   

        assertEquals(new Placement(Rotation.CCW_90, 2),  brain.findBestPlacement(oneBlockColumn, Shape.L));
        assertEquals(new Placement(Rotation.CCW_270, 2), brain.findBestPlacement(apiBoardACH, Shape.L));
        assertEquals(new Placement(Rotation.CCW_90, 2),  brain.findBestPlacement(shaesBoard1, Shape.L));
        assertEquals(new Placement(Rotation.CCW_90, 0),  brain.findBestPlacement(shaesBoard2, Shape.L));
        assertEquals(new Placement(Rotation.CCW_90, 5),  brain.findBestPlacement(shaesBoard3, Shape.L));        
        assertEquals(new Placement(Rotation.CCW_270, 7), brain.findBestPlacement(shaesBoard4, Shape.L));
        assertEquals(new Placement(Rotation.NONE, 7),    brain.findBestPlacement(shaesBoard5, Shape.L));   

        assertEquals(new Placement(Rotation.NONE, 0),    brain.findBestPlacement(oneBlockColumn, Shape.O));
        assertEquals(new Placement(Rotation.NONE, 0),    brain.findBestPlacement(apiBoardACH, Shape.O));
        assertEquals(new Placement(Rotation.NONE, 3),    brain.findBestPlacement(shaesBoard1, Shape.O));
        assertEquals(new Placement(Rotation.NONE, 1),    brain.findBestPlacement(shaesBoard2, Shape.O));
        assertEquals(new Placement(Rotation.NONE, 0),    brain.findBestPlacement(shaesBoard3, Shape.O));        
        assertEquals(new Placement(Rotation.NONE, 4),    brain.findBestPlacement(shaesBoard4, Shape.O));
        assertEquals(new Placement(Rotation.NONE, 7),    brain.findBestPlacement(shaesBoard5, Shape.O));   

        assertEquals(new Placement(Rotation.NONE, 3),    brain.findBestPlacement(oneBlockColumn, Shape.S));
        assertEquals(new Placement(Rotation.NONE, 7),    brain.findBestPlacement(apiBoardACH, Shape.S));
        assertEquals(new Placement(Rotation.NONE, 6),    brain.findBestPlacement(shaesBoard1, Shape.S));
        assertEquals(new Placement(Rotation.CCW_90, 6),  brain.findBestPlacement(shaesBoard2, Shape.S));
        assertEquals(new Placement(Rotation.CCW_90, 4),  brain.findBestPlacement(shaesBoard3, Shape.S));        
        assertEquals(new Placement(Rotation.NONE, 0),    brain.findBestPlacement(shaesBoard4, Shape.S));
        assertEquals(new Placement(Rotation.CCW_90, 7),  brain.findBestPlacement(shaesBoard5, Shape.S));   

        assertEquals(new Placement(Rotation.NONE, 2),    brain.findBestPlacement(oneBlockColumn, Shape.T));
        assertEquals(new Placement(Rotation.CCW_180, 6), brain.findBestPlacement(apiBoardACH, Shape.T));
        assertEquals(new Placement(Rotation.CCW_270, 0), brain.findBestPlacement(shaesBoard1, Shape.T));
        assertEquals(new Placement(Rotation.NONE, 0),    brain.findBestPlacement(shaesBoard2, Shape.T));
        assertEquals(new Placement(Rotation.NONE, 5),    brain.findBestPlacement(shaesBoard3, Shape.T));        
        assertEquals(new Placement(Rotation.CCW_270, 0), brain.findBestPlacement(shaesBoard4, Shape.T));
        assertEquals(new Placement(Rotation.CCW_90, 7),  brain.findBestPlacement(shaesBoard5, Shape.T));   

        assertEquals(new Placement(Rotation.NONE, 5),    brain.findBestPlacement(oneBlockColumn, Shape.Z));
        assertEquals(new Placement(Rotation.CCW_90, 7),  brain.findBestPlacement(apiBoardACH, Shape.Z));
        assertEquals(new Placement(Rotation.CCW_90, 0),  brain.findBestPlacement(shaesBoard1, Shape.Z));
        assertEquals(new Placement(Rotation.NONE, 6),    brain.findBestPlacement(shaesBoard2, Shape.Z));
        assertEquals(new Placement(Rotation.NONE, 4),    brain.findBestPlacement(shaesBoard3, Shape.Z));        
        assertEquals(new Placement(Rotation.CCW_90, 7),  brain.findBestPlacement(shaesBoard4, Shape.Z));
        assertEquals(new Placement(Rotation.CCW_90, 7),  brain.findBestPlacement(shaesBoard5, Shape.Z));   
    }

    @Test
    public void testACH() { // ACH = Average Column Height
        assertEquals(0, brain.getAverageColumnHeight(emptyBoard));
        assertEquals(0, brain.getAverageColumnHeight(oneBlockColumn));
        assertEquals(11,brain.getAverageColumnHeight(apiBoardACH)); 
        
        assertEquals(4, brain.getAverageColumnHeight(shaesBoard1));  
        assertEquals(6, brain.getAverageColumnHeight(shaesBoard2)); 
        assertEquals(6, brain.getAverageColumnHeight(shaesBoard3)); 
        assertEquals(4, brain.getAverageColumnHeight(shaesBoard4));
        assertEquals(6, brain.getAverageColumnHeight(shaesBoard5)); 
        
        assertEquals(2, brain.getAverageColumnHeight(testBoardT));
        assertEquals(2, brain.getAverageColumnHeight(testBoardO));
        assertEquals(4, brain.getAverageColumnHeight(testBoardI));
        assertEquals(2, brain.getAverageColumnHeight(testBoardJ));
        assertEquals(2, brain.getAverageColumnHeight(testBoardL));
        assertEquals(3, brain.getAverageColumnHeight(testBoardS));
        assertEquals(2, brain.getAverageColumnHeight(testBoardZ));
    } 
    
    @Test
    public void testCHR() { // CHR = Column Height Range
        assertEquals(0, brain.getColumnHeightRange(emptyBoard)); 
        assertEquals(1, brain.getColumnHeightRange(oneBlockColumn));
        assertEquals(2, brain.getColumnHeightRange(apiBoardACH)); 
        
        assertEquals(8, brain.getColumnHeightRange(shaesBoard1)); 
        assertEquals(9, brain.getColumnHeightRange(shaesBoard2)); 
        assertEquals(6, brain.getColumnHeightRange(shaesBoard3)); 
        assertEquals(8, brain.getColumnHeightRange(shaesBoard4));
        assertEquals(6, brain.getColumnHeightRange(shaesBoard5)); 
        
        assertEquals(2, brain.getColumnHeightRange(testBoardT));
        assertEquals(2, brain.getColumnHeightRange(testBoardO));
        assertEquals(4, brain.getColumnHeightRange(testBoardI));
        assertEquals(2, brain.getColumnHeightRange(testBoardJ));
        assertEquals(2, brain.getColumnHeightRange(testBoardL));
        assertEquals(3, brain.getColumnHeightRange(testBoardS));
        assertEquals(2, brain.getColumnHeightRange(testBoardZ));
    } 
    
    @Test
    public void testCHV() { // CHV = Column Height Variance
        assertEquals(0, brain.getColumnHeightVariance(emptyBoard));
        assertEquals(2, brain.getColumnHeightVariance(oneBlockColumn)); 
        assertEquals(6, brain.getColumnHeightVariance(apiBoardACH)); 
        
        assertEquals(8, brain.getColumnHeightVariance(shaesBoard1)); 
        assertEquals(12,brain.getColumnHeightVariance(shaesBoard2)); 
        assertEquals(6, brain.getColumnHeightVariance(shaesBoard3)); 
        assertEquals(11,brain.getColumnHeightVariance(shaesBoard4)); 
        assertEquals(12,brain.getColumnHeightVariance(shaesBoard5)); 
        
        assertEquals(4, brain.getColumnHeightVariance(testBoardT));
        assertEquals(4, brain.getColumnHeightVariance(testBoardO));
        assertEquals(8, brain.getColumnHeightVariance(testBoardI));
        assertEquals(4, brain.getColumnHeightVariance(testBoardJ));
        assertEquals(4, brain.getColumnHeightVariance(testBoardL));
        assertEquals(6, brain.getColumnHeightVariance(testBoardS));
        assertEquals(4, brain.getColumnHeightVariance(testBoardZ));
    } 

    @Test
    public void testTGC() { // TGC = Total Gap Count
        assertEquals(0, brain.getTotalGapCount(emptyBoard)); 
        assertEquals(0, brain.getTotalGapCount(oneBlockColumn));    
        assertEquals(14,brain.getTotalGapCount(apiBoardACH)); 
        
        assertEquals(7, brain.getTotalGapCount(shaesBoard1)); 
        assertEquals(10,brain.getTotalGapCount(shaesBoard2)); 
        assertEquals(12,brain.getTotalGapCount(shaesBoard3)); 
        assertEquals(8, brain.getTotalGapCount(shaesBoard4)); 
        assertEquals(11,brain.getTotalGapCount(shaesBoard5)); 
        
        assertEquals(0, brain.getTotalGapCount(testBoardT));
        assertEquals(0, brain.getTotalGapCount(testBoardO));
        assertEquals(0, brain.getTotalGapCount(testBoardI));
        assertEquals(0, brain.getTotalGapCount(testBoardJ));
        assertEquals(0, brain.getTotalGapCount(testBoardL));
        assertEquals(0, brain.getTotalGapCount(testBoardS));
        assertEquals(0, brain.getTotalGapCount(testBoardZ));
    } 
}

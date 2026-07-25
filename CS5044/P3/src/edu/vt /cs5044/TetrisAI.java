package edu.vt.cs5044;

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
public class TetrisAI implements AI {

    @Override
    public Placement findBestPlacement(Board board, Shape shape) {
        Placement bestPlace = null;
        int minCostSoFar = Integer.MAX_VALUE;

        for (Rotation rotation : shape.getValidRotationSet()) {
            for (int col = 0; col <= Board.WIDTH - shape.getWidth(rotation); col++) {
                Placement trialPlace = new Placement(rotation, col);
                Board trialBoard = board.getResultBoard(shape, trialPlace);

                int trialCost = (4 * getAverageColumnHeight(trialBoard)) +
                        (0 * getColumnHeightRange(trialBoard)) +
                        (4 * getColumnHeightVariance(trialBoard)) +
                        (12 * getTotalGapCount(trialBoard));
                if (trialCost < minCostSoFar) {
                    minCostSoFar = trialCost;
                    bestPlace = trialPlace;
                }
            }
        }
        return bestPlace;
    }

    /**
     *
     * Private helper for Average Column Height.
     * Gets individual column height which are added together
     * under ACH.
     *
     * @param board = getFixedBlocks
     * @param col starts at 0, 10 columns total
     * @return height of each column
     */
    private int getColumnHeight(Board board, int col) {
        boolean[][] fixedBlocks = board.getFixedBlocks();
        boolean[] colBlocks = fixedBlocks[col];
        int height = 0;

        for (int i = 0; i < colBlocks.length; i++) {
            if (colBlocks[i]) {
                height = i + 1;
            }
        }
        return height;
    }

    @Override
    public int getAverageColumnHeight(Board board) {
        int totalHeight = 0;

        for (int col = 0; col < Board.WIDTH; col++) {
            int colHeight = getColumnHeight(board, col);
            totalHeight += colHeight;
        }
        return totalHeight / Board.WIDTH;
    }

    @Override
    public int getColumnHeightRange(Board board) {
        int maxHeight = Integer.MIN_VALUE;
        int minHeight = Integer.MAX_VALUE;

        for (int col = 0; col < Board.WIDTH; col++) {
            int colHeight = getColumnHeight(board, col);
            if (colHeight > maxHeight) {
                maxHeight = colHeight;
            }
            if (colHeight < minHeight) {
                minHeight = colHeight;
            }
        }
        return maxHeight - minHeight;
    }

    @Override
    public int getColumnHeightVariance(Board board) {
        int totalVar = 0;
        int varPerCol = 0;

        for (int col = 0; col < Board.WIDTH - 1; col++) {
            int colHeightA = getColumnHeight(board, col);
            int colHeightB = getColumnHeight(board, col + 1);
            varPerCol = Math.abs(colHeightA - colHeightB);
            totalVar += varPerCol;
        }
        return totalVar;
    }
    /**
     *
     * Private helper for Total Gap Count.
     * Gets gap count per column height
     *     (gaps above column height aren't counted as gaps).
     *
     * @param board = getFixedBlocks
     * @param col starts at 0, 10 columns total
     * @return # of vertical gaps in each column
     */
    private int getGapCount(Board board, int col) {
        boolean[][] fixedBlocks = board.getFixedBlocks();
        boolean[] colBlocks = fixedBlocks[col];
        int colGapCount = 0;
        int gapCount = 0;

        for (int i = 0; i < colBlocks.length ; i++) {
            int colHeight = getColumnHeight(board, col);
            if (!colBlocks[i]) {
                gapCount = gapCount + 1;
            }
            if (i == (colHeight - 1)) {
                colGapCount = gapCount;
                break;
            }
        }
        return colGapCount;
    }

    @Override
    public int getTotalGapCount(Board board) {
        int totalGapCount = 0;

        for (int col = 0; col < Board.WIDTH; col++) {
            int gapCount = getGapCount(board, col);

            totalGapCount += gapCount;
        }
        return totalGapCount;
    }

}

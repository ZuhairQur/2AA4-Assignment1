/**
 * File: Maze.java
 * Author: Zuhair Qureshi
 * Description: This class represents the maze structure, including its layout and
 * key coordinates such as start and end points. Its responsibilities include:
 * storing the maze structure as a 2D character array, identifying and setting 
 * the starting and ending coordinates of the maze dynamically, and providing methods 
 * to retrieve specific blocks and key coordinates within the maze.
 */


package ca.mcmaster.se2aa4.mazerunner;


public class Maze {
    private final char[][] maze;
    private int [] startCoords = new int [2];
    private int [] endCoords = new int[2];

    public Maze(char [][] maze) {
        this.maze = maze;
    }

    /**
     * Retrieves the block at the specified coordinates (x, y) from the maze without
     * modifying the maze at all. This method is used by the walker to plan the
     * next move.
     * 
     * @param x the row number of the block to retrieve
     * @param y the column number of the block to retrieve
     * @return the block at the specified coordinates
     */
    public char peakBlock(int x, int y) {
        return maze[x][y];
    }

    
/**
 * Sets the starting and ending coordinates of the maze based on its layout.
 * 
 * This method scans the first and last columns of the maze to identify openings
 * (represented by spaces) that serve as potential entry and exit points. It assigns
 * these coordinates to the start and end positions. The start and end coordinates
 * are randomly assigned between these identified openings to simulate different
 * entry and exit scenarios.
 */

    public void setStartEndCoords() {

        // Preparing start and end coordinates
        int [] rightOpening = new int[2];
        int [] leftOpening = new int[2];

        // Identifying the opening in the first and last columns
        for (int i = 0; i < maze.length; i++) {
            
            if (maze[i][0] == ' ') {
                leftOpening[0] = i;
                leftOpening[1] = 0;
            }

            if (maze[i][maze[0].length - 1] == ' ') {
                rightOpening[0] = i;
                rightOpening[1] = maze[0].length - 1;
            }
        }
        
        // Randomly assigning each opening to start and end
        this.startCoords = leftOpening;
        this.endCoords = rightOpening;

        if (Math.random() < 0.5) {
            this.startCoords = rightOpening;
            this.endCoords = leftOpening;
        }
    }

    /**
     * Retrieves the starting coordinates of the maze.
     * 
     * @return an array containing the row and column indices of the start position
     */

    public int[] getStartCoords() {
        return this.startCoords;
    }

    /**
     * Retrieves the ending coordinates of the maze.
     * 
     * @return an array containing the row and column indices of the end position
     */

    public int[] getEndCoords() {
        return this.endCoords;
    }
    
}

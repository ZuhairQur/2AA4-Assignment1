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
    private final MazeBlock[][] maze;
    private final Coordinates startCoords;
    private final Coordinates endCoords;

    public Maze(MazeBlock [][] maze) {
        this.maze = maze;
        this.startCoords = this.findLeftOpening();
        this.endCoords = this.findRightOpening();
        
    }

    /**
     * Retrieves the coordinates of the left opening of the maze, which is the left border.
     * @return an array containing the row and column indices of the start position
     */
    private Coordinates findLeftOpening() {
        int i;
        for (i = 0; i < maze.length; i++) {
            if (maze[i][0] == MazeBlock.PASS) {
                break;
            }
        }

        return new Coordinates(i, 0);
    }

    /**
     * Retrieves the coordinates of the right opening of the maze, which is the right border.
     * @return an array containing the row and column indices of the end position
     */
    private Coordinates findRightOpening() {
        int i;
        for (i = 0; i < maze.length; i++) {
            if (maze[i][maze[i].length - 1] == MazeBlock.PASS) {
                break;
            }   
        }

        return new Coordinates(i, maze[0].length - 1);
    }

    /**
     * Retrieves the starting coordinates of the maze.
     * @return an array containing the row and column indices of the start position
     */
    public Coordinates getStartCoords() {
        return this.startCoords;
    }

    /**
     * Retrieves the ending coordinates of the maze.
     * @return the coordinates of the end position
     */
    public Coordinates getEndCoords() {
        return this.endCoords;
    }

    /**
     * Retrieves the block type at the specified coordinates within the maze.
     * @param coords the coordinates of the block to retrieve
     * @return the MazeBlock at the given coordinates
     */
    public MazeBlock getBlock(Coordinates coords) {
        return this.maze[coords.getX()][coords.getY()];
    }
}

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
    private Coordinates startCoords;
    private Coordinates endCoords;

    public Maze(MazeBlock [][] maze) {
        this.maze = maze;
        this.startCoords = this.findWestOpening();
        this.endCoords = this.findEastOpening();
        
    }

    public Coordinates findWestOpening() {
        int i;
        for (i = 0; i < maze.length; i++) {
            if (maze[i][0] == MazeBlock.PASS) {
                break;
            }
        }

        return new Coordinates(i, 0);
    }

    public Coordinates findEastOpening() {
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
     * 
     * @return an array containing the row and column indices of the start position
     */
    public Coordinates getStartCoords() {
        return this.startCoords;
    }

    public Coordinates getEndCoords() {
        return this.endCoords;
    }

    /**
     * Swaps the start and end coordinates of the maze. This method is used
     * by the InstructedWalker when it needs to attempt to navigate the maze
     * from the other opening. It is called after the walker has failed to
     * escape the maze from the original entry.
     */
    private void swapEntryExit() {
        int [] coordStore = {this.startCoords.getX(), this.startCoords.getY()};
        // this.startCoords[0] = this.endCoords[0];
        // this.startCoords[1] = this.endCoords[1];
        // this.endCoords[0] = coordStore[0];
        // this.endCoords[1] = coordStore[1];
    }

    public MazeBlock getBlock(Coordinates coords) {
        return this.maze[coords.getX()][coords.getY()];
    }

    /**
     * Checks if the walker has escaped the maze by comparing the walker's current coordinates with
     * the maze's end coordinates. If the coordinates are equal, the walker has escaped the maze.
     * @param walker the walker object
     * @return true if the walker has escaped the maze, false otherwise
     */
    public boolean hasEscaped(Walker walker) {
        return (walker.getCoords().getY() == this.endCoords.getY()) && (walker.coords.getX() == this.endCoords.getX());
    }
    
    /**
     * Determines if the walker has hit a wall in the maze.
     * This method checks the maze block at the walker's current coordinates.
     * If the block is a wall ('#'), it indicates the walker has hit a wall.
     * @param walker the walker object whose position is being evaluated
     * @return true if the walker is at a wall, false otherwise
     */
    public boolean hitWall(Walker walker) {
        return this.getBlock(walker.getCoords()) == MazeBlock.WALL;
    }

    /**
     * Determines if there is a wall on the right of the walker.
     * This method is used by the RightHandAlgorithm to determine if the walker
     * should turn right or not.
     * @param walker the walker object whose position is being evaluated
     * @return true if there is a wall on the right of the walker, false otherwise
     */
    public boolean wallOnRight(Walker walker) {
        Direction relativeRight = walker.onRight();
        Coordinates rightDirectionVector = walker.getDirectionVector(relativeRight);//walker.directionVectorMap.get(relativeRight);

        return this.getBlock(walker.getCoords().add(rightDirectionVector)) == MazeBlock.WALL;
    }

    /**
     * When the walker fails to escape the maze from the original entry point, 
     * this method sets the walker's coordinates to the start and end points 
     * of the maze, to allow the walker to attempt to escape from the other opening. 
     * @param walker the walker object whose position is being set
     */
    // @Override
    public void enterOtherSide(Walker walker) {
        walker.coords.setX(this.endCoords.getX());
        walker.coords.setY(this.endCoords.getY());
        this.swapEntryExit();
    }


}

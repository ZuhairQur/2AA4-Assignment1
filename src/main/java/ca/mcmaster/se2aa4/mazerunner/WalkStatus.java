/**
 * File: WallDetector.java
 * Author: Zuhair Qureshi
 * Description: Provides utility functionality to detect if a walker has hit a wall in the Maze Runner Application.
 * These include checking if the walker's current position in the maze corresponds to a wall
 * and evaluating the maze structure using the walker's coordinates and maze data.
 **/

package ca.mcmaster.se2aa4.mazerunner;

public class WalkStatus {

    /**
     * Checks if the walker has escaped the maze by comparing the walker's current coordinates with
     * the maze's end coordinates. If the coordinates are equal, the walker has escaped the maze.
     * @param walker the walker object to be queried
     * @param maze the maze object to be queried
     * @return true if the walker has escaped the maze, false otherwise
     */
    public boolean hasEscaped(Walker walker, Maze maze) {
        return (walker.getCoords()[0] == maze.getEndCoords()[0]) && (walker.getCoords()[1] == maze.getEndCoords()[1]);
    }
    
    /**
     * Determines if the walker has hit a wall in the maze.
     * This method checks the maze block at the walker's current coordinates.
     * If the block is a wall ('#'), it indicates the walker has hit a wall.
     * @param walker the walker object whose position is being evaluated
     * @param maze the maze object being navigated by the walker
     * @return true if the walker is at a wall, false otherwise
     */
    public boolean hitWall(Walker walker, Maze maze) {
        return maze.peakBlock(walker.getCoords()[0], walker.getCoords()[1]).isWall();
    }

    public boolean wallOnRight(FreeWalker walker, Maze maze) {
        Direction relativeRight = walker.getRelativeRight();
        int [] rightDirectionVector = relativeRight.getDirectionVector();

        return maze.peakBlock(walker.getCoords()[0] + rightDirectionVector[0], walker.getCoords()[1] + rightDirectionVector[1]).isWall();
    }
}

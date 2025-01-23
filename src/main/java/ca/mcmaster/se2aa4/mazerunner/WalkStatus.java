/**
 * File: WallDetector.java
 * Author: Zuhair Qureshi
 * Description: Provides utility functionality to detect if a walker has hit a wall in the Maze Runner Application.
 * These include checking if the walker's current position in the maze corresponds to a wall
 * and evaluating the maze structure using the walker's coordinates and maze data.
 **/

package ca.mcmaster.se2aa4.mazerunner;

public final class WalkStatus {
    
    public static boolean hasEscaped(Walker walker, Maze maze) {
        return (walker.getCoords()[0] == maze.getEndCoords()[0]) && (walker.getCoords()[1] == maze.getEndCoords()[1]);
    }

    public static boolean hitWall(Walker walker, Maze maze) {
        return maze.peakBlock(walker.getCoords()[0], walker.getCoords()[0]) == '#';
    }
}

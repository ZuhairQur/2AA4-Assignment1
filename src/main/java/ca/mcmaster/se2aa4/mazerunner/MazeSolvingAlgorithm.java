/**
 * File: MazeSolvingAlgorithm.java
 * Author: Zuhair Qureshi
 * Description: This interface contractually requires a FreeWalker object  
 * to implement a maze solving algorithm. The chosen algorithm must guide 
 * the walker through the maze.
 */
package ca.mcmaster.se2aa4.mazerunner;

public interface MazeSolvingAlgorithm {
    public abstract String solveMaze(Maze maze);
}

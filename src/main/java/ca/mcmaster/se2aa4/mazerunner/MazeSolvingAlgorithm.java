/**
 * File: MazeSolvingAlgorithm.java
 * Author: Zuhair Qureshi
 * Description: This abstract class serves as a blueprint for all maze-solving algorithms, 
 * defining the core structure for an unguided Walker to navigate a maze. The 
 * method must guide the walker through the maze based on the chosen algorithm.
 */
package ca.mcmaster.se2aa4.mazerunner;

public abstract class MazeSolvingAlgorithm {
    public abstract String solveMaze(FreeWalker walker, Maze maze);
}

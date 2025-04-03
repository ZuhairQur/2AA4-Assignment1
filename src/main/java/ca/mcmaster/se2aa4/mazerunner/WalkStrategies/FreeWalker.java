/**
 * File: FreeWalker.java
 * Author: Zuhair Qureshi
 * Description: Implements the FreeWalker class, a subclass of Walker, for the Maze Runner Application.
 * Defines the free movement of a walker through the maze based on right-hand algorithm.
 * It tracks and records the sequence of movements using a string of instructions.
 */
package ca.mcmaster.se2aa4.mazerunner.WalkStrategies;

import ca.mcmaster.se2aa4.mazerunner.Coordinates;
import ca.mcmaster.se2aa4.mazerunner.Maze;

public class FreeWalker extends Walker {
    private MazeSolvingAlgorithm algorithm;

    public FreeWalker(Coordinates coords, Maze maze, MazeSolvingAlgorithm algorithm) {
        super(coords, maze);
        this.algorithm = algorithm;
    }


/**
 * This method moves the walker through the maze with an interface algorithm, appending
 * the instruction each time a move is made, until the walker escapes
 * the maze. It records the path taken and returns a factored 
 * instruction string representing the sequence of movements.
 *
 * @param maze the maze to be navigated
 * @return a string representing the factored path taken to exit the maze
 */
    @Override
    public String walk() {
        String walkingInstructions = algorithm.solveMaze(this);
        String cleanInstructions = this.instructionCleaner.getFactoredInstructions(walkingInstructions);
        return cleanInstructions;
    }
}

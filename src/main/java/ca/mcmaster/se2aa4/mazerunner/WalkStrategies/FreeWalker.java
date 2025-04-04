/**
 * File: FreeWalker.java
 * Author: Zuhair Qureshi
 * Description: Implements the FreeWalker class, a subclass of Walker, for the Maze Runner Application.
 * Defines the free movement of a walker through the maze based on right-hand algorithm.
 * It tracks and records the sequence of movements using a string of instructions.
 */
package ca.mcmaster.se2aa4.mazerunner.WalkStrategies;

import ca.mcmaster.se2aa4.mazerunner.Coordinates;
import ca.mcmaster.se2aa4.mazerunner.CoordinatesTracker;
import ca.mcmaster.se2aa4.mazerunner.Direction;
import ca.mcmaster.se2aa4.mazerunner.DirectionManager;
import ca.mcmaster.se2aa4.mazerunner.InstructionCleaner;
import ca.mcmaster.se2aa4.mazerunner.Maze;

public class FreeWalker implements Walker {
    private MazeSolvingAlgorithm algorithm;
    private Maze maze;

    public FreeWalker(Maze maze, MazeSolvingAlgorithm algorithm) {
        this.maze = maze;
        this.algorithm = algorithm;
    }

/**
 * This method moves the walker through the maze with an interface algorithm, appending
 * the instruction each time a move is made, until the walker escapes
 * the maze. It records the path taken.
 * @param maze the maze to be navigated
 * @return a string representing the factored path taken to exit the maze
 */
    @Override
    public String walk() {
        Coordinates walkerStartingPosition = this.maze.getLeftOpening().copy();
        CoordinatesTracker coordinatesTracker = new CoordinatesTracker(walkerStartingPosition);
        DirectionManager directionManager = new DirectionManager(Direction.RIGHT);
        
        String walkingInstructions = algorithm.solveMaze(coordinatesTracker, directionManager, this.maze);
        String cleanInstructions = InstructionCleaner.getFactoredInstructions(walkingInstructions);
        return cleanInstructions;
    }
}

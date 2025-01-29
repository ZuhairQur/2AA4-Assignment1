/**
 * File: FreeWalker.java
 * Author: Zuhair Qureshi
 * Description: Implements the FreeWalker class, a subclass of Walker, for the Maze Runner Application.
 * Defines the free movement of a walker through the maze based on right-hand algorithm.
 * It tracks and records the sequence of movements using a string of instructions.
 */

package ca.mcmaster.se2aa4.mazerunner;

public class FreeWalker extends Walker {

    private Direction onRight;
    private final MazeSolvingAlgorithm rightHandAlgorithm;
    
    public FreeWalker(int[] coords, Direction direction, WalkStatus walkStatus) {
        super(coords, direction, walkStatus);
        this.rightHandAlgorithm = new RightHandAlgorithm();
    }

    public Direction getRelativeRight() {
        this.onRight = this.direction.onRight();
        return this.onRight;
    }

/**
 * This method moves the walker through the maze, appending
 * the instruction each time a move is made, until the walker escapes
 * the maze. Right hand algorithm will be used. It records the path 
 * taken and returns a factored instruction string representing 
 * the sequence of movements.
 *
 * @param maze the maze to be navigated
 * @return a string representing the factored path taken to exit the maze
 */

    @Override
    public String walk(Maze maze) {
        String walkingInstructions = rightHandAlgorithm.solveMaze(this, maze, walkStatus);
        return InstructionCleaner.getFactoredInstructions(walkingInstructions);
    }
}

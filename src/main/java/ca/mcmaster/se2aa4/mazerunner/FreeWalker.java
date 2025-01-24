/**
 * File: FreeWalker.java
 * Author: Zuhair Qureshi
 * Description: Implements the FreeWalker class, a subclass of Walker, for the Maze Runner Application.
 * Defines the free movement of a walker through the maze based on right-hand algorithm.
 * It tracks and records the sequence of movements using a string of instructions.
 */

package ca.mcmaster.se2aa4.mazerunner;

public class FreeWalker extends Walker {

    private final StringBuilder instructions = new StringBuilder();
    
    public FreeWalker(int[] coords, int direction) {
        super(coords, direction);
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
        while (WalkStatus.hasEscaped(this, maze) == false) {
            this.move();
            this.instructions.append("F");
        }

        return "Path taken to exit maze: " + InstructionCleaner.getFactoredInstructions(this.instructions.toString());

    }
}

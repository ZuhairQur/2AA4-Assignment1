/**
 * File: FreeWalker.java
 * Author: Zuhair Qureshi
 * Description: Implements the FreeWalker class, a subclass of Walker, for the Maze Runner Application.
 * Defines the free movement of a walker through the maze based on right-hand algorithm.
 * It tracks and records the sequence of movements using a string of instructions.
 */

package ca.mcmaster.se2aa4.mazerunner;

public class FreeWalker extends Walker {

    private StringBuilder instructions = new StringBuilder("");
    
    public FreeWalker(int[] coords, int direction) {
        super(coords, direction);
    }

    @Override
    public String walk(Maze maze) {
        while (WalkStatus.hasEscaped(this, maze) == false) {
            this.move();
            this.instructions.append("F");
        }

        return "Path taken to exit maze: " + InstructionCleaner.getFactoredInstructions(this.instructions.toString());

    }
}

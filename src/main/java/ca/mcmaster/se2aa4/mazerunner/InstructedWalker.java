/**
 * File: InstructedWalker.java
 * Author: Zuhair Qureshi
 * Description: Defines a subclass of Walker that attempts to navigate the maze based on a 
 * user-provided set of instructions. Implememts the step-by-step navigation logic.
 **/

package ca.mcmaster.se2aa4.mazerunner;

public class InstructedWalker extends Walker {

    private String instructions;

    public InstructedWalker(int [] coords, int direction, String instructions) {
        super(coords, direction);
        this.instructions = instructions;
    }

    @Override
    public String walk(Maze maze) {
        // Move according to instructions ... fill in later
        this.move();
        return null;
    }

}

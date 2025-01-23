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
        
        for (int i = 0; i < this.instructions.length(); i++) {
            this.move();
            
            if (WalkStatus.hasEscaped(this, maze)) {
                return "Using the instructions you gave, you have successfully escaped the maze!";
            }
        }

        return "You were unable to escape the maze using the instructions you provided.";
    }

}

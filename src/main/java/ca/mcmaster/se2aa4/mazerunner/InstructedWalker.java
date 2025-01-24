/**
 * File: InstructedWalker.java
 * Author: Zuhair Qureshi
 * Description: Defines a subclass of Walker that attempts to navigate the maze based on a 
 * user-provided set of instructions. Implememts the step-by-step navigation logic.
 **/

package ca.mcmaster.se2aa4.mazerunner;

public class InstructedWalker extends Walker {

    private final String instructions;

    public InstructedWalker(int [] coords, int direction, String instructions) {
        super(coords, direction);
        this.instructions = instructions;
    }

    /**
     * Walks the maze according to the instructions provided when creating this object.
     * 
     * If the walker successfully escapes the maze, a message indicating this is returned.
     * If the walker is unable to escape the maze using the instructions, a message indicating
     * this is returned. MVP coded to block up and down movement for particular maze implementation.
     * 
     * @param maze the maze to be navigated
     * @return a message indicating whether the walker escaped the maze or not
     */
    @Override
    public String walk(Maze maze) {

        for (int i = 0; i < this.instructions.length(); i++) {
            char currentInstruction = this.instructions.charAt(i);

            // switch might be CODE SMELL. Find other method of reading the instructions.
            switch (currentInstruction) {
                case 'F':
                    // Disable moving up or down for this particular maze implementation
                    if (this.direction == 0 || this.direction == 2) // refactor directions with enum
                        this.move();
                    break;
                case 'L':
                    this.turnLeft();
                    break;
                case 'R':
                    this.turnRight();
                    break;
                default:
                    break;
            }
            
            
            if (WalkStatus.hasEscaped(this, maze)) {
                return "correct path";
            }
        }

        return "incorrect path";
    }

}

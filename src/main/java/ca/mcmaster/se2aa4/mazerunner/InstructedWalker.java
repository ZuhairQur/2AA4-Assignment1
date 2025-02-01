/**
 * File: InstructedWalker.java
 * Author: Zuhair Qureshi
 * Description: Defines a subclass of Walker that attempts to navigate the maze based on a 
 * user-provided set of instructions. Implememts the step-by-step navigation logic.
 **/

package ca.mcmaster.se2aa4.mazerunner;

public class InstructedWalker extends Walker {

    private String instructions;
    private boolean attemptedBothEnds;

    public InstructedWalker(int [] coords, Direction direction, String instructions) {
        super(coords, direction);
        this.instructions = instructions;
        this.attemptedBothEnds = false;
    }

    /**
     * Walks the maze according to the instructions provided when creating this object.
     * 
     * If the walker successfully escapes the maze, a message indicating this is returned.
     * If the walker is unable to escape the maze using the instructions, a message indicating
     * this is returned. MVP coded to block up and down movement for particular maze implementation.
     * 
     * @param maze the maze to navigate
     * @return a message indicating whether the walker escaped the maze or not
     */
    @Override
    public String walk(Maze maze) {

        this.instructions = this.instructionCleaner.getUnfactoredInstructions(instructions);

        for (int i = 0; i < this.instructions.length(); i++) {
            char currentInstruction = this.instructions.charAt(i);

            // switch might be CODE SMELL. Find other method of reading the instructions.
            switch (currentInstruction) {
                case 'F' -> {
                    this.moveForward();
                    
                    if (maze.hasEscaped(this)) {
                        return "correct path"; 
                    }

                    try {
                        if (maze.hitWall(this)) {
                            this.stepBack();
                        }                  
                    } catch (IndexOutOfBoundsException e) {
                        this.stepBack();
                    }
                }
                case 'L' -> this.turnLeft();
                case 'R' -> this.turnRight();
                default -> {
                }
            }
        }

        if (!this.attemptedBothEnds) {
            return this.attemptFromOtherSide(maze);
        }

        return "incorrect path";
    }

    /**
     * Attempts to walk the maze from the other opening after failing to do so from 
     * the original entry point. Sets the walker's coordinates to the end of the maze, 
     * swaps the start and end points of the maze, flips the walker's entry 
     * direction, and marks that the walker has attempted both ends.
     * @param maze the maze to navigate
     */
    private String attemptFromOtherSide(Maze maze) {
        maze.enterOtherSide(this);
        this.direction = this.flipEntryDirection(); // change direction to opposite of entry direction (e.g. East if West, West if East)
        this.attemptedBothEnds = true;
        
        return this.walk(maze);
    }
}

/**
 * File: InstructedWalker.java
 * Author: Zuhair Qureshi
 * Description: Defines a subclass of Walker that attempts to navigate the maze based on a 
 * user-provided set of instructions. Implememts the step-by-step navigation logic.
 **/

package ca.mcmaster.se2aa4.mazerunner.WalkStrategies;

import ca.mcmaster.se2aa4.mazerunner.Coordinates;
import ca.mcmaster.se2aa4.mazerunner.Direction;
import ca.mcmaster.se2aa4.mazerunner.Maze;

public class InstructedWalker extends Walker {

    private String instructions;
    private boolean attemptedBothEnds;

    public InstructedWalker(Coordinates coords, Maze maze, String instructions) {
        super(coords, maze);
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
    public String walk() {
 
        this.instructions = this.instructionCleaner.getUnfactoredInstructions(instructions);
        Coordinates leftEntrance = this.maze.getStartCoords().copy();
        Coordinates rightEntrance = this.maze.getEndCoords().copy();


        boolean foundExit = 
            findExit(this.coords, rightEntrance, Direction.RIGHT) 
            || 
            findExit(rightEntrance, leftEntrance, Direction.LEFT);

        if (!foundExit) {
            return "incorrect path";
        }

        return "correct path";
    }

    private boolean findExit(Coordinates startCoords, Coordinates endCoords, Direction startDirection) {
        this.coords = startCoords;
        this.direction = startDirection;

        for (int i = 0; i < this.instructions.length(); i++) {
            char currentInstruction = this.instructions.charAt(i);

            System.out.println();
            if (currentInstruction == 'F') {
                this.moveForward();
                    
                if (this.coords.equals(endCoords)) {
                    return true; 
                }

                try {
                    if (this.hitWall()) {
                        this.stepBack();
                    }                  
                } catch (IndexOutOfBoundsException e) {
                    this.stepBack();
                }               
            } else if (currentInstruction == 'L') {
                this.turnLeft();
            } else if (currentInstruction == 'R') {
                this.turnRight();
            }
        }

        return false;
    }
}

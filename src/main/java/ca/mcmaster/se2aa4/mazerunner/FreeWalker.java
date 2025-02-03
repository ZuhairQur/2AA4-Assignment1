/**
 * File: FreeWalker.java
 * Author: Zuhair Qureshi
 * Description: Implements the FreeWalker class, a subclass of Walker, for the Maze Runner Application.
 * Defines the free movement of a walker through the maze based on right-hand algorithm.
 * It tracks and records the sequence of movements using a string of instructions.
 */

package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;


public class FreeWalker extends Walker implements MazeSolvingAlgorithm {
    
    public FreeWalker(int[] coords, Direction direction) {
        super(coords, direction);
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
    protected String walk(Maze maze) {
        String walkingInstructions = solveMaze(maze);
        return this.instructionCleaner.getFactoredInstructions(walkingInstructions);
    }

/**
 * Solves the maze using the right-hand rule algorithm.
 * The walker attempts to navigate the maze by keeping its right hand
 * in contact with the wall at all times.
 *
 * @param walker the FreeWalker object that navigates the maze
 * @param maze the Maze object representing the maze structure
 * @return a string of instructions that represents the path taken
 */
    @Override
    public String solveMaze(Maze maze) {
        StringBuilder instructions = new StringBuilder();
        
        while (!maze.hasEscaped(this)) {
            this.moveForward();

            try {
                if (maze.hitWall(this)) {
                    this.stepBack();
                    this.turnLeft();
                    instructions.append("L");
                } else {
                    instructions.append("F");
                }                  
            } catch (IndexOutOfBoundsException e) {
                this.stepBack();
            }

            if (!maze.wallOnRight(this)) {
                this.turnRight();
                instructions.append("R");
            }
        }
        
        return instructions.toString();
    }
}

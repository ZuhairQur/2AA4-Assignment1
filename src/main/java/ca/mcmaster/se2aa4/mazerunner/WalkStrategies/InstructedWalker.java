/**
 * File: InstructedWalker.java
 * Author: Zuhair Qureshi
 * Description: Defines a subclass of Walker that attempts to navigate the maze based on a 
 * user-provided set of instructions. Implememts the step-by-step navigation logic.
 **/

package ca.mcmaster.se2aa4.mazerunner.WalkStrategies;

import ca.mcmaster.se2aa4.mazerunner.Coordinates;
import ca.mcmaster.se2aa4.mazerunner.CoordinatesTracker;
import ca.mcmaster.se2aa4.mazerunner.Direction;
import ca.mcmaster.se2aa4.mazerunner.DirectionManager;
import ca.mcmaster.se2aa4.mazerunner.InstructionCleaner;
import ca.mcmaster.se2aa4.mazerunner.Maze;

public class InstructedWalker implements Walker {

    private String instructions;
    private Maze maze;
    private boolean attemptedBothEnds;

    public InstructedWalker(Maze maze, String instructions) {
        this.maze = maze;
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
 
        this.instructions = InstructionCleaner.getUnfactoredInstructions(instructions);
        Coordinates leftEntrance = this.maze.getLeftOpening();
        Coordinates rightEntrance = this.maze.getRightOpening();

        boolean foundExit = findExit(leftEntrance, rightEntrance, Direction.RIGHT) || findExit(rightEntrance, leftEntrance, Direction.LEFT);

        if (!foundExit) {
            return "incorrect path";
        }

        return "correct path";
    }

    private boolean findExit(Coordinates startCoordinates, Coordinates endCoordinates, Direction startDirection) {
        CoordinatesTracker coordinatesTracker = new CoordinatesTracker(startCoordinates.copy());
        DirectionManager directionManager = new DirectionManager(startDirection);

        MovementList movementList = new MovementList(this.instructions);
        movementList.generateMovements(coordinatesTracker, directionManager, this.maze);

        while (!movementList.isEmpty()) {
            Movement currentMovement = movementList.nextMovement();
            currentMovement.execute();

            if (coordinatesTracker.reachedEnd(endCoordinates)) {
                return true;
            }
        }

        return false;
    }
}

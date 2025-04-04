package ca.mcmaster.se2aa4.mazerunner.WalkStrategies;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.se2aa4.mazerunner.CoordinatesTracker;
import ca.mcmaster.se2aa4.mazerunner.DirectionManager;
import ca.mcmaster.se2aa4.mazerunner.Maze;

public class MovementList {
    private String instructions;
    private List<Movement> movementList = new ArrayList<>();
    
    public MovementList(String instructions) {
        this.instructions = instructions;
    }

    /**
     * Populates the list of movements by iterating through the instructions and creating
     * appropriate movement objects based on the characters in the string, adding them to a list. 
     * @param coordinatesTracker the coordinates tracker to use when generating movements
     * @param maze the maze to use when generating movements
     */
    public void generateMovements(CoordinatesTracker coordinatesTracker, DirectionManager directionManager, Maze maze) {
        for (int i = 0; i < this.instructions.length(); i++) {
            char currentInstruction = this.instructions.charAt(i);

            if (currentInstruction == 'F') {
                movementList.add(new Forward(coordinatesTracker, directionManager, maze));
            } else if (currentInstruction == 'L') {
                movementList.add(new LeftTurn(directionManager));
            } else if (currentInstruction == 'R') {
                movementList.add(new RightTurn(directionManager));
            }
        }
    }

    /**
     * Retrieves and removes the next movement from the list of movements.
     * 
     * @return the next Movement object in the list
     */
    public Movement nextMovement() {
        return movementList.remove(0);
    }

    /**
     * Checks if the list of movements is empty.
     * 
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return movementList.isEmpty();
    }
}

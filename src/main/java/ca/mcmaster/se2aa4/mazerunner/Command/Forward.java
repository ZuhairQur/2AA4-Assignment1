/**
 * File: Forward.java
 * Author: Zuhair Qureshi
 * Description: This class represents the forward movement strategy for the walker 
 * in the maze. It implements the Movement interface and provides the functionality 
 * to move the walker one step forward in the maze. The movement is only executed 
 * if there is no wall in the walker’s path or the walker does not go out of bounds. 
 */

package ca.mcmaster.se2aa4.mazerunner.Command;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Navigation.CoordinatesTracker;
import ca.mcmaster.se2aa4.mazerunner.Navigation.Direction;
import ca.mcmaster.se2aa4.mazerunner.Navigation.DirectionManager;

public class Forward implements Movement {
    private CoordinatesTracker coordinatesTracker;
    private DirectionManager directionManager;
    private Maze maze;
    private boolean successful = true;

    public Forward(CoordinatesTracker coordinatesTracker, DirectionManager directionManager, Maze maze) {
        this.coordinatesTracker = coordinatesTracker;
        this.directionManager = directionManager;
        this.maze = maze;
    }
    
    /**
     * Moves the walker forward in the maze, if possible.
     * If the walker would hit a wall or move out of bounds, the walker is not moved.
     * The method returns true if the walker avoided a wall, false otherwise.
     * @return false if the walker hit a wall, true otherwise
     */
    @Override
    public boolean execute() {
        Direction currentDirection = this.directionManager.getDirection();
        coordinatesTracker.move(currentDirection);

        try {
            if (coordinatesTracker.hitWall(this.maze)) {
                coordinatesTracker.retreat(currentDirection);
                this.successful = false;
            }                  
        } catch (IndexOutOfBoundsException e) {
            coordinatesTracker.retreat(currentDirection);
        }
        
        return this.successful; 
    }
    
}

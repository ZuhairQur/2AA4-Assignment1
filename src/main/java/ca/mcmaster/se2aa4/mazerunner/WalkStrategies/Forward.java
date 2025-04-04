package ca.mcmaster.se2aa4.mazerunner.WalkStrategies;

import ca.mcmaster.se2aa4.mazerunner.CoordinatesTracker;
import ca.mcmaster.se2aa4.mazerunner.Direction;
import ca.mcmaster.se2aa4.mazerunner.DirectionManager;
import ca.mcmaster.se2aa4.mazerunner.Maze;

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
            coordinatesTracker.move(currentDirection);
        }
        
        return this.successful; 
    }
    
}

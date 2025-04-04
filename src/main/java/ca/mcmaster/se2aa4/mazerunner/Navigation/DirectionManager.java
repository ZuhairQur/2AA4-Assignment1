/**
 * File: DirectionManager.java
 * Author: Zuhair Qureshi
 * Description: This class manages the direction of the walker in the maze. 
 * It provides methods to get the current direction, determine the directions 
 * to the left and right of the current direction, and to turn the walker left or right.
 */

package ca.mcmaster.se2aa4.mazerunner.Navigation;

public class DirectionManager {
    private Direction direction;

    public DirectionManager(Direction direction) {
        this.direction = direction;
    }

    /**
     * Retrieves the direction that the walker is currently facing.
     * @return the Direction enum object representing the walker's current direction
     */
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * Gets the direction to the left of the walker's current direction.
     * @return the direction to the left of the walker's current direction
     */
    public Direction onLeft() {
        if (this.direction == Direction.RIGHT) {
            return Direction.UP;
        } else if (this.direction == Direction.UP) {
            return Direction.LEFT;
        } else if (this.direction == Direction.LEFT) {
            return Direction.DOWN;
        }
  
        return Direction.RIGHT;
    }

    /**
     * Gets the direction to the right of the walker's current direction.
     * @return the direction to the right of the walker's current direction
     */
    public Direction onRight() {
        if (this.direction == Direction.RIGHT) {
            return Direction.DOWN;
        } else if (this.direction == Direction.DOWN) {
            return Direction.LEFT;
        } else if (this.direction == Direction.LEFT) {
            return Direction.UP;
        }
        
        return Direction.RIGHT;
    }

    /**
     * Turns the walker to the right.
     */
    public void turnRight() {
        this.direction = this.onRight();
    }

    /**
     * Turns the walker to the left.
     */
    public void turnLeft() {
        this.direction = this.onLeft();
    }
}

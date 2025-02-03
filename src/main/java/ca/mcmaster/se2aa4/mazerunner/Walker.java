/**
 * File: Walker.java
 * Author: Zuhair Qureshi
 * Description: This abstract class acts as a base class for all walker types, providing core 
 * movement logic and structure for maze navigation. It stores the current coordinates and 
 * direction of the walker, provides methods to turn left, turn right, and move in the current direction,
 * encodes movement logic using a 2D array of direction vectors, and requires subclasses to implement 
 * the walk method, defining specific navigation behaviors.
 */

package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashMap;
import java.util.Map;

public abstract class Walker {
    
    protected int[] coords = new int[2];
    protected Direction direction;
    private final Direction entryDirection;
    protected final InstructionCleaner instructionCleaner = new InstructionCleaner();
    protected HashMap<Direction, int[]> directionVectorMap = new HashMap<>(Map.of
    (
        Direction.RIGHT, new int[]{0, 1},
        Direction.DOWN, new int[]{1, 0},
        Direction.LEFT, new int[]{0, -1},
        Direction.UP, new int[]{-1, 0})
    );


    public Walker(int [] coords, Direction direction) {
        this.coords = coords;
        this.direction = direction;
        this.entryDirection = direction;
    }

    /**
     * Gets the direction to the left of the walker's current direction.
     * @return the direction to the left of the walker's current direction
     */
    protected Direction onLeft() {
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
    protected Direction onRight() {
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
     * Turns the walker 90 degrees to the right, so that its current direction becomes
     * the direction to the right of its current direction.
     */
    protected void turnRight() {
        this.direction = this.onRight();
    }

    /**
     * Turns the walker 90 degrees to the left, so that its current direction becomes
     * the direction to the left of its current direction.
     */
    protected void turnLeft() {
        this.direction = this.onLeft();
    }

    /**
     * Gets the direction opposite to the walker's entry direction.
     * @return the opposite direction
     */
    protected Direction flipEntryDirection() {
        if (this.entryDirection == Direction.RIGHT) {
            return Direction.LEFT;
        }
        return Direction.RIGHT;
    }


    /**
     * Moves the walker one block in the opposite direction of the current direction.
     * This can be used to undo the walker's last move.
     */
    protected void stepBack() {
        this.coords[0] -= this.directionVectorMap.get(this.direction)[0];
        this.coords[1] -= this.directionVectorMap.get(this.direction)[1];
    }

    /**
     * Moves the walker one block in the current direction.
     * This method should be called after the walker has confirmed that it is not hitting a wall.
     */
     protected void moveForward() {
        this.coords[0] += this.directionVectorMap.get(this.direction)[0];
        this.coords[1] += this.directionVectorMap.get(this.direction)[1];
    }

    protected abstract String walk(Maze maze);
}

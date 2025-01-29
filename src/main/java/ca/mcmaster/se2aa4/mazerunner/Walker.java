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

public abstract class Walker {
    
    protected int[] coords = new int[2];
    protected Direction direction;
    private final Direction entryDirection;
    protected WalkStatus walkStatus;

    public Walker(int [] coords, Direction direction, WalkStatus walkStatus) {
        this.coords = coords;
        this.direction = direction;
        this.entryDirection = direction;
        this.walkStatus = walkStatus;
    }

    /**
     * Retrieves the current coordinates of the walker in the maze.
     * @return an array of two integers, with the first and second elements being the row and the
     * column numbers, respectively.
     */
    public int[] getCoords() {
        return this.coords;
    }

    /**
     * Retrieves the direction the walker was facing when it entered the maze.
     * This is useful to track when attempting to walk from the other opening.
     * @return the direction the walker faced when it started walking the maze
     */
    public Direction getEntryDirection() {
        return this.entryDirection;
    }

    /**
     * Changes the walker's direction to the one on its left
     * by rotating the direction counterclockwise according to the directions array.
     */
    public void turnLeft() {
        this.direction = this.direction.onLeft();
    }

    /**
     * Changes the walker's direction to the one on its right by
     * rotating the direction clockwise according to the directions array.
     */
    public void turnRight() {
        this.direction = this.direction.onRight();
    }

    /**
     * Moves the walker one block in the opposite direction of the current direction.
     * This can be used to undo the walker's last move.
     */
    public void stepBack() {
        this.coords[0] -= this.direction.getDirectionVector()[0];
        this.coords[1] -= this.direction.getDirectionVector()[1];
    }

    /**
     * Moves the walker one block in the current direction.
     * This method should be called after the walker has confirmed that it is not hitting a wall.
     */
    
     public void moveForward() {
        this.coords[0] += this.direction.getDirectionVector()[0];
        this.coords[1] += this.direction.getDirectionVector()[1];
    }

    public abstract String walk(Maze maze);
}

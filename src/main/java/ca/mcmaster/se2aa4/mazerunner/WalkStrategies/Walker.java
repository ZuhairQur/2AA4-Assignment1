/**
 * File: Walker.java
 * Author: Zuhair Qureshi
 * Description: This abstract class acts as a base class for all walker types, providing core 
 * movement logic and structure for maze navigation. It stores the current coordinates and 
 * direction of the walker, provides methods to turn left, turn right, and move in the current direction,
 * encodes movement logic using a 2D array of direction vectors, and requires subclasses to implement 
 * the walk method, defining specific navigation behaviors.
 */

package ca.mcmaster.se2aa4.mazerunner.WalkStrategies;

import java.util.HashMap;
import java.util.Map;

import ca.mcmaster.se2aa4.mazerunner.Coordinates;
import ca.mcmaster.se2aa4.mazerunner.Direction;
import ca.mcmaster.se2aa4.mazerunner.InstructionCleaner;
import ca.mcmaster.se2aa4.mazerunner.Maze;
import ca.mcmaster.se2aa4.mazerunner.MazeBlock;

public abstract class Walker {
    
    protected Coordinates coords;
    protected Direction direction;
    protected final InstructionCleaner instructionCleaner = new InstructionCleaner();
    protected final Maze maze;
    private HashMap<Direction, Coordinates> directionVectorMap = new HashMap<>(Map.of
    (
        Direction.RIGHT, new Coordinates(0, 1),
        Direction.DOWN, new Coordinates(1, 0),
        Direction.LEFT, new Coordinates(0, -1),
        Direction.UP, new Coordinates(-1, 0)
    ));

    public Walker(Coordinates coords, Maze maze) {
        this.coords = coords;
        this.direction = Direction.RIGHT;
        this.maze = maze;
    }

    /**
     * Gets the direction to the left of the walker's current direction.
     * @return the direction to the left of the walker's current direction
     */
    private Direction onLeft() {
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
    private Direction onRight() {
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
     * Retrieves the walker's current coordinates.
     * @return the walker's current coordinates
     */
    public Coordinates getCoords() {
        return this.coords;
    }


    /**
     * Gets the current direction the walker is facing.
     * @return the current direction of the walker
     */
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * Turns the walker 90 degrees to the right, so that its current direction becomes
     * the direction to the right of its current direction.
     */
    public void turnRight() {
        this.direction = this.onRight();
    }

    /**
     * Turns the walker 90 degrees to the left, so that its current direction becomes
     * the direction to the left of its current direction.
     */
    public void turnLeft() {
        this.direction = this.onLeft();
    }

    /**
     * Moves the walker one block in the opposite direction of the current direction.
     * This can be used to undo the walker's last move.
     */
    protected void stepBack() {
        this.coords.setX(this.coords.getX() - this.directionVectorMap.get(this.direction).getX());
        this.coords.setY(this.coords.getY() - this.directionVectorMap.get(this.direction).getY());
    }

    /**
     * Moves the walker one block in the current direction.
     * This method should be called after the walker has confirmed that it is not hitting a wall.
     */
    public void moveForward() {
        this.coords.setX(this.coords.getX() + this.directionVectorMap.get(this.direction).getX());
        this.coords.setY(this.coords.getY() + this.directionVectorMap.get(this.direction).getY());
    }

    /**
     * Checks if the walker is currently positioned adjacent to a wall block in the maze, on its right side.
     * This is used to determine if the walker should turn right when following the right-hand rule.
     * @return true if the block to the right of the walker is a wall, false otherwise
     */
    public boolean hasWallOnRight() {
        Direction relativeRight = this.onRight();
        Coordinates rightDirectionVector = this.directionVectorMap.get(relativeRight);//walker.directionVectorMap.get(relativeRight);
        return this.maze.getBlock(this.coords.add(rightDirectionVector)) == MazeBlock.WALL;
    }

    /**
     * Checks if the walker is currently positioned at a wall block in the maze.
     * @return true if the current block is a wall, false otherwise
     */
    public boolean hitWall() {
        return this.maze.getBlock(this.coords) == MazeBlock.WALL;
    }

    /**
     * Determines whether the walker has reached the end of the maze.
     * @return whether the walker has reached the end of the maze
     */
    public boolean reachedEnd() {
        return this.coords.equals(this.maze.getEndCoords());
    }

    public abstract String walk();
}

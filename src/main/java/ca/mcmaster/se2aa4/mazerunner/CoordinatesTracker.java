package ca.mcmaster.se2aa4.mazerunner;


import java.util.HashMap;
import java.util.Map;

public class CoordinatesTracker {
    private Coordinates coordinates;
    private Direction direction;

    private HashMap<Direction, Coordinates> directionVectorMap = new HashMap<>(Map.of
    (
        Direction.RIGHT, new Coordinates(0, 1),
        Direction.DOWN, new Coordinates(1, 0),
        Direction.LEFT, new Coordinates(0, -1),
        Direction.UP, new Coordinates(-1, 0)
    ));

    public CoordinatesTracker (Coordinates coordinates, Direction direction) {
        this.coordinates = coordinates;
        this.direction = direction;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void moveForward() {
        this.coordinates.setX(this.coordinates.getX() + this.directionVectorMap.get(this.direction).getX());
        this.coordinates.setY(this.coordinates.getY() + this.directionVectorMap.get(this.direction).getY());
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
     * Checks if the walker is currently positioned at a wall block in the maze.
     * @return true if the current block is a wall, false otherwise
     */
    public boolean hitWall(Maze maze) {
        return maze.getBlock(this.coordinates) == MazeBlock.WALL;
    }


    /**
     * Moves the walker one block in the opposite direction of the current direction.
     * This can be used to undo the walker's last move.
     */
    public void stepBack() {
        this.coordinates.setX(this.coordinates.getX() - this.directionVectorMap.get(this.direction).getX());
        this.coordinates.setY(this.coordinates.getY() - this.directionVectorMap.get(this.direction).getY());
    }



    /**
     * Checks if the walker is currently positioned adjacent to a wall block in the maze, on its right side.
     * This is used to determine if the walker should turn right when following the right-hand rule.
     * @return true if the block to the right of the walker is a wall, false otherwise
     */
    public boolean hasWallOnRight(Maze maze) {
        Direction relativeRight = this.onRight();
        Coordinates rightDirectionVector = this.directionVectorMap.get(relativeRight);//walker.directionVectorMap.get(relativeRight);
        return maze.getBlock(this.coordinates.add(rightDirectionVector)) == MazeBlock.WALL;
    }

    public boolean reachedEnd(Coordinates endCoordinates) {
        return this.coordinates.equals(endCoordinates);
    }
}

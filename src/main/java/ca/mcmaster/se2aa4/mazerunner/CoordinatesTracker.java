package ca.mcmaster.se2aa4.mazerunner;


import java.util.HashMap;
import java.util.Map;

public class CoordinatesTracker {
    private Coordinates coordinates;
    private HashMap<Direction, Coordinates> directionVectorMap = new HashMap<>(Map.of
    (
        Direction.RIGHT, new Coordinates(0, 1),
        Direction.DOWN, new Coordinates(1, 0),
        Direction.LEFT, new Coordinates(0, -1),
        Direction.UP, new Coordinates(-1, 0)
    ));

    public CoordinatesTracker (Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Retrieves the coordinates being tracked.
     * @return the current Coordinates object representing the walker's position.
     */
    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    /**
     * Moves the walker one block in the specified direction.
     * @param direction the direction in which to move the walker
     */
    public void move(Direction direction) {
        this.coordinates.setX(this.coordinates.getX() + this.directionVectorMap.get(direction).getX());
        this.coordinates.setY(this.coordinates.getY() + this.directionVectorMap.get(direction).getY());
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
    public void retreat(Direction direction) {
        this.coordinates.setX(this.coordinates.getX() - this.directionVectorMap.get(direction).getX());
        this.coordinates.setY(this.coordinates.getY() - this.directionVectorMap.get(direction).getY());
    }


    /**
     * Checks if the walker is currently positioned adjacent to a wall block in the maze, on its right side.
     * This is used to determine if the walker should turn right when following the right-hand rule.
     * @return true if the block to the right of the walker is a wall, false otherwise
     */
    public boolean canPass(Direction direction, Maze maze) {
        Coordinates directionVector = this.directionVectorMap.get(direction);//walker.directionVectorMap.get(relativeRight);
     
        return maze.getBlock(this.coordinates.add(directionVector)) == MazeBlock.PASS;
    }

    /**
     * Checks if the walker is currently positioned at the end coordinates of the maze.
     * @param endCoordinates the coordinates that the walker is trying to reach
     * @return true if the walker has reached the end coordinates, false otherwise
     */
    public boolean reachedEnd(Coordinates endCoordinates) {
        return this.coordinates.equals(endCoordinates);
    }
}

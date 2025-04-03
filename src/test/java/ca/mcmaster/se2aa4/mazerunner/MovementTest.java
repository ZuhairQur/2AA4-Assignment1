package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.WalkStrategies.FreeWalker;
import ca.mcmaster.se2aa4.mazerunner.WalkStrategies.RightHandAlgorithm;
import ca.mcmaster.se2aa4.mazerunner.WalkStrategies.Walker;

public class MovementTest {
    private Walker walker;

    /**
     * Sets up the walker for each test, placing it at position (1,0) and
     * facing right.
     */
    @BeforeEach
    public void setup() {
        Maze maze = new Maze(new MazeBlock[][]{{MazeBlock.PASS}, {MazeBlock.WALL}});
        walker = new FreeWalker(new Coordinates(1, 0), maze, new RightHandAlgorithm());
    }

    /**
     * Tests that the walker moves one block forward when instructed to do so.
     */
    @Test
    public void testMove() {
        Coordinates forwardPosition = new Coordinates(1, 1);
        walker.moveForward();

        boolean equivalent =forwardPosition.equals(walker.getCoords());
        assertTrue(equivalent);
    }

    /**
     * Tests that the walker correctly turns left when instructed to do so.
     */
    @Test
    public void testTurnLeft() {
        walker.turnLeft();
        boolean equivalent = walker.getDirection() == Direction.UP;
        assertTrue(equivalent);
    }

    /**
     * Tests that the walker correctly turns right when instructed to do so.
     */
    @Test
    public void testTurnRight() {
        walker.turnRight();
        boolean equivalent = walker.getDirection() == Direction.DOWN;
        assertTrue(equivalent);
    }

    /**
     * Tests that the walker correctly moves up when instructed to do so.
     */
    @Test
    public void testMoveUp() {
        walker.turnLeft();
        walker.moveForward();
        Coordinates forwardPosition = new Coordinates(0, 0);
        boolean equivalent = forwardPosition.equals(walker.getCoords());
        assertTrue(equivalent);
    }

    /**
     * Tests that the walker correctly moves down when instructed to do so.
     */
    @Test
    public void testMoveDown() {
        walker.turnRight();
        walker.moveForward();
        Coordinates forwardPosition = new Coordinates(2, 0);
        boolean equivalent = forwardPosition.equals(walker.getCoords());
        assertTrue(equivalent);
    }
}

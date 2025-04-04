package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovementTest {
    private CoordinatesTracker coordinatesTracker;

    /**
     * Sets up the walker for each test, placing it at position (1,0) and
     * facing right.
     */
    @BeforeEach
    public void setup() {
        coordinatesTracker = new CoordinatesTracker(new Coordinates(1, 0), Direction.RIGHT);
    }

    /**
     * Tests that the walker moves one block forward when instructed to do so.
     */
    @Test
    public void testMove() {
        Coordinates forwardPosition = new Coordinates(1, 1);
        coordinatesTracker.moveForward();

        boolean equivalent = forwardPosition.equals(coordinatesTracker.getCoordinates());
        assertTrue(equivalent);
    }

    /**
     * Tests that the walker correctly turns left when instructed to do so.
     */
    @Test
    public void testTurnLeft() {
        coordinatesTracker.turnLeft();
        boolean equivalent = coordinatesTracker.getDirection() == Direction.UP;
        assertTrue(equivalent);
    }

    /**
     * Tests that the walker correctly turns right when instructed to do so.
     */
    @Test
    public void testTurnRight() {
        coordinatesTracker.turnRight();
        boolean equivalent = coordinatesTracker.getDirection() == Direction.DOWN;
        assertTrue(equivalent);
    }

    /**
     * Tests that the walker correctly moves up when instructed to do so.
     */
    @Test
    public void testMoveUp() {
        coordinatesTracker.turnLeft();
        coordinatesTracker.moveForward();
        Coordinates forwardPosition = new Coordinates(0, 0);
        boolean equivalent = forwardPosition.equals(coordinatesTracker.getCoordinates());
        assertTrue(equivalent);
    }

    /**
     * Tests that the walker correctly moves down when instructed to do so.
     */
    @Test
    public void testMoveDown() {
        coordinatesTracker.turnRight();
        coordinatesTracker.moveForward();
        Coordinates forwardPosition = new Coordinates(2, 0);
        boolean equivalent = forwardPosition.equals(coordinatesTracker.getCoordinates());
        assertTrue(equivalent);
    }
}

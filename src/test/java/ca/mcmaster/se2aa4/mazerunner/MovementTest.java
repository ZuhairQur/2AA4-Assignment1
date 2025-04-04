package ca.mcmaster.se2aa4.mazerunner;

import java.awt.image.DirectColorModel;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Navigation.Coordinates;
import ca.mcmaster.se2aa4.mazerunner.Navigation.CoordinatesTracker;
import ca.mcmaster.se2aa4.mazerunner.Navigation.Direction;
import ca.mcmaster.se2aa4.mazerunner.Navigation.DirectionManager;

public class MovementTest {
    private CoordinatesTracker coordinatesTracker;
    private DirectionManager directionManager;
    private Direction startDirection;

    /**
     * Sets up the walker for each test, placing it at position (1,0) and
     * facing right.
     */
    @BeforeEach
    public void setup() {
        coordinatesTracker = new CoordinatesTracker(new Coordinates(1, 0));
        directionManager = new DirectionManager(Direction.RIGHT);
    }

    /**
     * Tests that the walker moves one block forward when instructed to do so.
     */
    @Test
    public void testMove() {
        Coordinates forwardPosition = new Coordinates(1, 1);
        coordinatesTracker.move(directionManager.getDirection());

        boolean equivalent = forwardPosition.equals(coordinatesTracker.getCoordinates());
        assertTrue(equivalent);
    }

    /**
     * Tests that the walker correctly turns left when instructed to do so.
     */
    @Test
    public void testTurnLeft() {
        directionManager.turnLeft();
        boolean equivalent = directionManager.getDirection() == Direction.UP;
        assertTrue(equivalent);
    }

    /**
     * Tests that the walker correctly turns right when instructed to do so.
     */
    @Test
    public void testTurnRight() {
        directionManager.turnRight();
        boolean equivalent = directionManager.getDirection() == Direction.DOWN;
        assertTrue(equivalent);
    }

    /**
     * Tests that the walker correctly moves up when instructed to do so.
     */
    @Test
    public void testMoveUp() {
        directionManager.turnLeft();
        coordinatesTracker.move(directionManager.getDirection());
        Coordinates forwardPosition = new Coordinates(0, 0);
        boolean equivalent = forwardPosition.equals(coordinatesTracker.getCoordinates());
        assertTrue(equivalent);
    }

    /**
     * Tests that the walker correctly moves down when instructed to do so.
     */
    @Test
    public void testMoveDown() {
        directionManager.turnRight();
        coordinatesTracker.move(directionManager.getDirection());
        Coordinates forwardPosition = new Coordinates(2, 0);
        boolean equivalent = forwardPosition.equals(coordinatesTracker.getCoordinates());
        assertTrue(equivalent);
    }
}

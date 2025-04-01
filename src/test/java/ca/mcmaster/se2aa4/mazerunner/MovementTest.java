package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovementTest {
    private Walker walker;

    /**
     * Sets up the walker for each test, placing it at position (1,0) and
     * facing right.
     */
    @BeforeEach
    public void setup() {
        walker = new FreeWalker(new int[]{1, 0}, Direction.RIGHT);
    }

    /**
     * Tests that the walker moves one block forward when instructed to do so.
     */
    @Test
    public void testMove() {
        int [] forwardPosition = new int[]{1, 1};
        walker.moveForward();

        boolean equivalent = Arrays.equals(forwardPosition, walker.coords);
        assertTrue(equivalent);
    }

    /**
     * Tests that the walker correctly turns left when instructed to do so.
     */
    @Test
    public void testTurnLeft() {
        walker.turnLeft();
        boolean equivalent = walker.direction == Direction.UP;
        assertTrue(equivalent);
    }

    /**
     * Tests that the walker correctly turns right when instructed to do so.
     */
    @Test
    public void testTurnRight() {
        walker.turnRight();
        boolean equivalent = walker.direction == Direction.DOWN;
        assertTrue(equivalent);
    }

    /**
     * Tests that the walker correctly moves up when instructed to do so.
     */
    @Test
    public void testMoveUp() {
        walker.turnLeft();
        walker.moveForward();
        int [] forwardPosition = new int[]{0, 0};
        boolean equivalent = Arrays.equals(forwardPosition, walker.coords);
        assertTrue(equivalent);
    }

    /**
     * Tests that the walker correctly moves down when instructed to do so.
     */
    @Test
    public void testMoveDown() {
        walker.turnRight();
        walker.moveForward();
        int [] forwardPosition = new int[]{2, 0};
        boolean equivalent = Arrays.equals(forwardPosition, walker.coords);
        assertTrue(equivalent);
    }
}

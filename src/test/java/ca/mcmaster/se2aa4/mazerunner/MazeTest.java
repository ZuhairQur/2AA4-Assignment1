package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.cli.ParseException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeTest {

    private Configuration config;
    private InputHandler inputHandler;
    private Maze maze;


/**
 * Initializes the input handler, configuration, and maze objects before each test for a given maze.
 */
    @BeforeEach
    public void setup() throws IOException, ParseException, IllegalArgumentException {
        inputHandler = new InputHandler();
        inputHandler.setOptions();
        String [] args = {"-i", "./examples/giant.maz.txt"};
        config = new Configuration(inputHandler.readInput(args), inputHandler.getInstructions());
        maze = config.getConfiguredMaze();
    }    

    /**
     * Tests that the start coordinates are correctly set to the original start coordinates
     * after setup. This verifies that the start coordinates are set accurately based on the
     * maze structure read from the input file.
     */
    @Test
    public void testStartCoordinates() {
        boolean equivalent = Arrays.equals(new int[]{21, 0}, maze.getStartCoords());
        assertTrue(equivalent);
    }

    /**
     * Tests that the start coordinates are correctly set to the original end coordinates
     * after the walker enters from the other side of the maze.
     * This verifies that the entry and exit points are swapped accurately.
     */
    @Test
    public void testEndCoordinates() {
        maze.enterOtherSide(new InstructedWalker(new int[]{0,0}, Direction.DOWN, "")); // use random parameters for InstructedWalker (unimportant for testing)
        boolean equivalent = Arrays.equals(new int[]{53, 150}, maze.getStartCoords());
        assertTrue(equivalent);
    }
}

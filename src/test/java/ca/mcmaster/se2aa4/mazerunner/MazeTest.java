package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeTest {

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
        MazeReader mazeReader = new  MazeReader(inputHandler.getFilename(args));
        maze = mazeReader.getMaze();
    }    

    /**
     * Tests that the start coordinates are correctly set to the original start coordinates
     * after setup. This verifies that the start coordinates are set accurately based on the
     * maze structure read from the input file.
     */
    @Test
    public void testStartCoordinates() {
        boolean equivalent = new Coordinates(21, 0).equals(maze.getLeftOpening());
        assertTrue(equivalent);
    }

    /**
     * Tests that the start coordinates are correctly set to the original end coordinates
     * after the walker enters from the other side of the maze.
     * This verifies that the entry and exit points are swapped accurately.
     */
    @Test
    public void testEndCoordinates() {
        boolean equivalent =  new Coordinates(53, 150).equals(maze.getRightOpening()); 
        assertTrue(equivalent);
    }
}

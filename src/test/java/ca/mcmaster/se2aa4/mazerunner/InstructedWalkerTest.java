package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.WalkStrategies.Walker;



public class InstructedWalkerTest {
    
    private Walker walker;
    private Configuration config;
    private InputHandler inputHandler;

    // Runs before each test to initialize the input handler
    @BeforeEach
    public void setup() {
        inputHandler = new InputHandler();
        inputHandler.setOptions();
    }


    /**
     * Tests the case where the user provides instructions to navigate a simple
     * straight maze. The instructions should successfully navigate the maze.
     */
    @Test
    public void testWalk_correct_simple() throws IOException, ParseException, IllegalArgumentException {
        String [] args = {"-i", "./examples/straight.maz.txt", "-p", "FFFF"};

        MazeReader mazeReader = new  MazeReader(inputHandler.getFilename(args));
        Maze maze = mazeReader.getMaze();
        config = new Configuration(inputHandler.getInstructions(args));
        walker = config.getConfiguredWalker(maze);

        String results = walker.walk();
        assertEquals("correct path", results);
    }

    /**
     * Tests the case where the user provides instructions to navigate a simple
     * straight maze. The instructions should fail to navigate the maze.
     */
    @Test
    public void testWalk_incorrect_simple() throws IOException, ParseException, IllegalArgumentException {
        String [] args = {"-i", "./examples/straight.maz.txt", "-p", "FFF"};

        MazeReader mazeReader = new  MazeReader(inputHandler.getFilename(args));
        Maze maze = mazeReader.getMaze();
        config = new Configuration(inputHandler.getInstructions(args));
        walker = config.getConfiguredWalker(maze);

        String results = walker.walk();
        assertEquals("incorrect path", results);
    }

    /**
     * Tests the case where the user provides instructions to navigate a complex
     * maze. The instructions should successfully navigate the maze.
     */
    @Test
    public void testWalk_correct_complex() throws IOException, ParseException, IllegalArgumentException {
        String[] args = {"-i", "./examples/small.maz.txt", "-p", "F R F 2L 2F R 2F R 2F 2L 4F R 2F R 4F 2L 2F R 4F R 2F R 2F 2L 2F L 2F L 4F R 2F R 2F 2L 4F R 2F R 2F 2L 2F R 2F R 4F R 2F L 2F R 2F L F"};

        MazeReader mazeReader = new  MazeReader(inputHandler.getFilename(args));
        Maze maze = mazeReader.getMaze();
        config = new Configuration(inputHandler.getInstructions(args));
        walker = config.getConfiguredWalker(maze);

        String results = walker.walk();
        assertEquals("correct path", results);
    }

    
    /**
     * Tests the case where the user provides instructions to navigate a complex
     * maze. The instructions should fail to navigate the maze.
     */
    @Test
    public void testWalk_incorrect_complex() throws IOException, ParseException, IllegalArgumentException {
        String[] args = {"-i", "./examples/small.maz.txt", "-p", "3F"};

        MazeReader mazeReader = new  MazeReader(inputHandler.getFilename(args));
        Maze maze = mazeReader.getMaze();
        config = new Configuration(inputHandler.getInstructions(args));
        walker = config.getConfiguredWalker(maze);

        String results = walker.walk();
        assertEquals("incorrect path", results);
    }
}

package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Maze.MazeBlock;
import ca.mcmaster.se2aa4.mazerunner.Setup.Configuration;
import ca.mcmaster.se2aa4.mazerunner.Setup.InputHandler;
import ca.mcmaster.se2aa4.mazerunner.Setup.MazeReader;
import ca.mcmaster.se2aa4.mazerunner.WalkStrategies.Walker;

public class FreeWalkerTest {
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
     * Tests if the generated path for the straight maze is equal to the expected path.
     */
    @Test
    public void testWalk_straight() throws IOException, ParseException, IllegalArgumentException {
        String [] args = {"-i", "./examples/straight.maz.txt"};

        MazeReader mazeReader = new  MazeReader(inputHandler.getFilename(args));
        MazeBlock[][] mazeContents = mazeReader.readMaze();
        Maze maze = new Maze(mazeContents);
        config = new Configuration(inputHandler.getInstructions(args));
        walker = config.getConfiguredWalker(maze);

        String results = walker.walk();

        assertEquals("4F ", results);
    }

 
    /**
     * Tests if the generated path for the direct maze is equal to the expected path.
     */
    @Test
    public void testWalk_direct() throws IOException, ParseException, IllegalArgumentException {
        String [] args = {"-i", "./examples/direct.maz.txt"};

        MazeReader mazeReader = new  MazeReader(inputHandler.getFilename(args));
        MazeBlock[][] mazeContents = mazeReader.readMaze();
        Maze maze = new Maze(mazeContents);
        config = new Configuration(inputHandler.getInstructions(args));
        walker = config.getConfiguredWalker(maze);

        String results = walker.walk();

        assertEquals("F R 2F L 3F R F L F R F L 2F ", results);
    }

    /**
     * Tests if the generated path for the tiny maze is equal to the expected path.
     */
    @Test 
    public void testWalk_tiny() throws IOException, ParseException, IllegalArgumentException {
        String [] args = {"-i", "./examples/tiny.maz.txt"};
        MazeReader mazeReader = new  MazeReader(inputHandler.getFilename(args));
        Maze maze = mazeReader.getMaze();
        config = new Configuration(inputHandler.getInstructions(args));
        walker = config.getConfiguredWalker(maze);

        String results = walker.walk();
        assertEquals("5F 2L 2F R 2F R 2F 2L 2F R 2F R 3F ", results);
    }

    
    /**
     * Tests if the generated path for the small maze is equal to the expected path.
     * Validates that the FreeWalker correctly navigates the maze using the right-hand rule.
     */

    @Test
    public void testWalk_small() throws IOException, ParseException, IllegalArgumentException {
        String [] args = {"-i", "./examples/small.maz.txt"};

        MazeReader mazeReader = new  MazeReader(inputHandler.getFilename(args));
        Maze maze = mazeReader.getMaze();
        config = new Configuration(inputHandler.getInstructions(args));
        walker = config.getConfiguredWalker(maze);

        String results = walker.walk();
        assertEquals("F R F 2L 2F R 2F R 2F 2L 4F R 2F R 4F 2L 2F R 4F R 2F R 2F 2L 2F L 2F L 4F R 2F R 2F 2L 4F R 2F R 2F 2L 2F R 2F R 4F R 2F L 2F R 2F L F ", results);
    }

}

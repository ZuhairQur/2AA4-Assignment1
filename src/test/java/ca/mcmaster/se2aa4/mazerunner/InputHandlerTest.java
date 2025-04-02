package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.cli.ParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InputHandlerTest {
    private InputHandler inputHandler;

    /**
     * Runs before each test to initialize the input handler
     */
    @BeforeEach
    public void setup() {
        inputHandler = new InputHandler();
        inputHandler.setOptions();
    }

    /**
     * Tests that the input dimensions are correctly read from the input file.
     * The test uses the direct.maz.txt file as an example, which has 7 rows
     * and 8 columns. The test verifies that the readInput method correctly
     * sets up the mazeContents array with the correct number of rows and columns.
     * */
    @Test
    public void testInputDimensions() throws IOException, ParseException, IllegalArgumentException {
        String [] args = {"-i", "./examples/direct.maz.txt"};
        MazeReader mazeReader = new  MazeReader(inputHandler.getFilename(args));

        MazeBlock[][] mazeContents = mazeReader.readMaze();
        assertEquals(7, mazeContents.length);
        assertEquals(8, mazeContents[0].length);
    }


    /**
     * Tests that the instructions are correctly read and expanded from
     * the input arguments. The test provides a short instruction sequence
     * "4F" and expects it to be expanded to "FFFF" by the InputHandler.
     */
    public void testGetInstructions() throws IOException, ParseException, IllegalArgumentException {
        String [] args = {"-i", "./examples/direct.maz.txt", "-p", "4F"};
        String instructions = inputHandler.getInstructions(args);
        assertEquals("FFFF", instructions);
    }

    /**
     * Tests that the readInput method correctly reads the maze contents from the file.
     * The test uses the direct.maz.txt file as an example, which has 7 rows and 8 columns.
     * The test verifies that the readInput method correctly reads in the maze contents
     * and sets up the mazeContents array with the correct number of rows and columns.
     * */
    @Test
    public void testMazeReading() throws IOException, ParseException, IllegalArgumentException {
        String [] args = {"-i", "./examples/direct.maz.txt"};
        MazeReader reader = new MazeReader(inputHandler.getFilename(args));
        MazeBlock[][] mazeContents = reader.readMaze();
        
        MazeBlock[][] expectedContents = new MazeBlock[][] {
            {MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL},
            {MazeBlock.PASS, MazeBlock.PASS, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL},
            {MazeBlock.WALL, MazeBlock.PASS, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL},
            {MazeBlock.WALL, MazeBlock.PASS, MazeBlock.PASS, MazeBlock.PASS, MazeBlock.PASS, MazeBlock.PASS, MazeBlock.WALL, MazeBlock.WALL},
            {MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.PASS, MazeBlock.PASS, MazeBlock.WALL, MazeBlock.WALL}, 
            {MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.PASS, MazeBlock.PASS, MazeBlock.PASS},
            {MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL, MazeBlock.WALL},
        };

        boolean equivalent = Arrays.deepEquals(mazeContents, expectedContents);
        assertTrue(equivalent);
    }

    /**
     * Tests that the readInput method correctly throws an IOException when the file does not exist.
     * The test uses a nonexistent file as an example, and verifies that the IOException is thrown
     * when the readInput method is called.
     */
    @Test
    public void testNonexistentFile() throws ParseException, IllegalArgumentException {
        
        String [] args = {"-i", "./examples/nonexistent.maz.txt"};
        MazeReader mazeReader = new  MazeReader(inputHandler.getFilename(args));
        boolean exceptionThrown = false;
        try {
            mazeReader.getMaze();
        } catch (IOException e) {
            exceptionThrown = true;
        }

        assertTrue(exceptionThrown);
    }

    /**
     * Tests that the readInput method throws an IllegalArgumentException when given
     * invalid instructions. 
     **/
    @Test
    public void testInvalidInstructions() throws IOException, ParseException {
        
        String [] args = {"-i", "./examples/direct.maz.txt", "-p", "ABCDEFGHI"};

        boolean exceptionThrown = false;
        try {
            inputHandler.getInstructions(args);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }

        assertTrue(exceptionThrown);
    }

/**
 * Tests that the readInput method throws a ParseException when given
 * invalid command-line options.
 * The test uses an invalid flag '-g' as an example.
 */
    @Test
    public void testInvalidCommands() throws IOException, IllegalArgumentException {
        
        String [] args = {"-g", "./examples/direct.maz.txt"};
        boolean exceptionThrown = false;
        try {
            inputHandler.getInstructions(args);
        } catch (ParseException e) {
            exceptionThrown = true;
        }

        assertTrue(exceptionThrown);
    }
}
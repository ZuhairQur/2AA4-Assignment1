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

    @BeforeEach
    public void setup() {
        inputHandler = new InputHandler();
        inputHandler.setOptions();
    }

    @Test
    public void testInputDimensions() throws IOException, ParseException, IllegalArgumentException {
        String [] args = {"-i", "./examples/direct.maz.txt"};
        MazeBlock[][] mazeContents = inputHandler.readInput(args);
        assertEquals(7, mazeContents.length);
        assertEquals(8, mazeContents[0].length);
    }

    @Test
    public void testGetInstructions() throws IOException, ParseException, IllegalArgumentException {
        String [] args = {"-i", "./examples/direct.maz.txt", "-p", "4F"};
        inputHandler.readInput(args);
        String instructions = inputHandler.getInstructions();
        assertEquals("FFFF", instructions);
    }

    @Test
    public void testMazeReading() throws IOException, ParseException, IllegalArgumentException {
        String [] args = {"-i", "./examples/direct.maz.txt"};
        MazeBlock[][] mazeContents = inputHandler.readInput(args);
        
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
}
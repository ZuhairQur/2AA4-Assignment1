package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class InstructionCleanerTest {
    
    /**
     * Verifies that a simple string of repeated instructions is properly factored.
     */
    @Test
    public void testSimpleStringFactoring() {
        InstructionCleaner cleaner = new InstructionCleaner();
        String cleanedInstructions = cleaner.getFactoredInstructions("FFFFF");
        assertEquals("5F ", cleanedInstructions);
    }

    /**
     * Verifies that a string of instructions with a mix of different lengths is 
     * properly factored. This test is a more comprehensive test of the
     * factoring functionality, as it tests the ability of the InstructionCleaner
     * to factor strings of instructions with different lengths.
     */
    @Test
    public void testComplexStringFactoring() {
        InstructionCleaner cleaner = new InstructionCleaner();
        String cleanedInstructions = cleaner.getFactoredInstructions("FFFRFFFFFLLLFFFFR");
        assertEquals("3F R 5F 3L 4F R ", cleanedInstructions);
    }

    /**
     * Verifies that an empty string of instructions is properly factored.
     * This test case is important, as it verifies that the InstructionCleaner
     * class handles the edge case of an empty string of instructions correctly.
     */
    @Test
    public void testEmptyStringFactoring() {
        InstructionCleaner cleaner = new InstructionCleaner();
        String cleanedInstructions = cleaner.getFactoredInstructions("");
        assertEquals("", cleanedInstructions);
    }

    /**
     * Verifies that a simple string of instructions is properly unfactored.
     */
    @Test
    public void testSimpleStringUnfactoring() {
        InstructionCleaner cleaner = new InstructionCleaner();
        String cleanedInstructions = cleaner.getUnfactoredInstructions("5F");
        assertEquals("FFFFF", cleanedInstructions);
    }

    /**
     * Verifies that a string of instructions with a mix of different lengths is 
     * properly unfactored. This test is a more comprehensive test of the
     * unfactoring functionality, as it tests the ability of the InstructionCleaner
     * to unfactor strings of instructions with different lengths.
     */
    @Test
    public void testComplexStringUnfactoring() {
        InstructionCleaner cleaner = new InstructionCleaner();
        String cleanedInstructions = cleaner.getUnfactoredInstructions("3F R 5F 3L 4F R");
        assertEquals("FFFRFFFFFLLLFFFFR", cleanedInstructions);
    }

    /**
     * Verifies that an empty string of factored instructions is properly unfactored.
     * This test case checks whether the InstructionCleaner class can handle the 
     * edge case of an empty input string and return an empty output string.
     */
    @Test
    public void testEmptyStringUnfactoring() {
        InstructionCleaner cleaner = new InstructionCleaner();
        String cleanedInstructions = cleaner.getUnfactoredInstructions("");
        assertEquals("", cleanedInstructions);
    }

}

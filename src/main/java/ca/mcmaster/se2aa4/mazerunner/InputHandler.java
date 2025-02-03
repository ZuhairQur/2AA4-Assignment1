/**
 * File: InputHandler.java
 * Author: Zuhair Qureshi
 * Description: Defines the InputHandler class for the Maze Runner Application.
 * Responsible for parsing command-line arguments, reading and processing maze data 
 * from an input file, handling command-line options for specifying maze files and optional instructions.
 * Also provides methods to retrieve parsed input data and check for instruction flags.
 **/

package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InputHandler {
    private BufferedReader reader;
    private final Options options;
    private MazeBlock[][] contents;
    private CommandLine command;
    private String instructions;
    private final CommandLineParser parser;
    private final List <Character> canonicalInstructions = Arrays.asList('F', 'L', 'R');
    private final Logger logger = LogManager.getLogger();

    public InputHandler() {
        this.options = new Options();
        this.parser = new DefaultParser();
    }

    /**
     * Configures the command-line options for the application.
     * 
     * This method sets up the available options for parsing command-line arguments.
     * It forces specifying an input file containing the maze using the '-i' flag
     * option and an optional path of instructions to follow through the maze using the '-p'
     * flag. Options take an argument specifying the file or instructions respectively.
     */
    public void setOptions() {
        Option input = new Option("i", "input", true, "Input file path");
        input.setRequired(true);
        Option path = new Option("p", "path", true, "Path to follow through maze");
        path.setRequired(false);
        options.addOption(input);
        options.addOption(path);
    }

    /**
     * Reads the input from the command line arguments and prepares the maze contents.
     * 
     * This method reads the command line arguments and prepares the maze contents based on the input
     * file and instructions provided. The input file must be specified, after which this method reads
     * the file and sets up the maze contents accordingly.
     * 
     * @param args the command line arguments
     * @return the configured maze file contents in a character by character array
     */
    public MazeBlock[][] readInput(String[] args) throws IOException, IllegalArgumentException, ParseException {
   
        this.command = parser.parse(options, args);

        String filename = this.command.getOptionValue("i");

        Path path = Path.of(filename);
        int totalLineCount = (int) Files.lines(path).count();     
        this.contents = new MazeBlock[totalLineCount][];

        this.readMaze(filename);

        // Read in instructions (if present) and verify that they are valid
        if (this.command.hasOption("p")) {
            InstructionCleaner instructionCleaner = new InstructionCleaner();
            this.instructions = instructionCleaner.getUnfactoredInstructions(this.command.getOptionValue("p"));

            if (!this.hasValidInstructions()) {
                throw new IllegalArgumentException("Path contains instructions that are not canonical ('F', 'L', 'R').");
            }
        }
        

        return this.contents;
    }
    
    /**
     * Reads the maze data from the specified file and configures the maze contents array
     * accordingly.
     * 
     * This method reads the maze data from the specified file and sets up the contents array
     * with the maze layout. The maze data is read line by line and the contents array is populated
     * with the characters contained in the file.
     * 
     * @param filename the name of the file containing the maze data
     * @throws FileNotFoundException if the specified file does not exist
     * @throws IOException if an error occurs while reading the file
     */
    private void readMaze(String filename) throws IOException  {
        this.reader = new BufferedReader(new FileReader(filename));
        String line;
        StringBuilder mazeText = new StringBuilder();
        int lineNumber = 0;
        int maxLineLength = 0;

        while ((line = this.reader.readLine()) != null) {

            // Identifying the longest line (corresponds to the width of the maze)
            if (line.length() > maxLineLength) {
                maxLineLength = line.length();
            }

            for (int idx = 0; idx < line.length(); idx++) {
                char currentChar = line.charAt(idx);
                mazeText.append(currentChar);
                if (currentChar != ' ' && currentChar != '#') {
                    throw new IOException("Maze contains invalid characters.");
                }
            }

            // Handling the edge case where line contains null space at the end
            int emptyCharCount = maxLineLength - line.length();
            if (emptyCharCount > 0) {
                for (int i = 0; i < emptyCharCount; i++) {
                    line += " ";
                }
            }
            this.contents[lineNumber] = this.toMazeBlockArray(line);
            lineNumber++;
            mazeText.append("\n");
        }

        this.logger.info("\nMaze:\n"+mazeText.toString());

        this.reader.close();
    }

    /**
     * Verifies that the unraveled instructions provided by the user contain only canonical
     * characters ('F', 'L', 'R'). If the instructions contain any other characters,
     * this method returns false. Otherwise, it returns true.
     * @return true if the instructions are canonically valid, false otherwise
     */
    private boolean hasValidInstructions() {
        for (int i = 0; i < this.instructions.length(); i++) {
            char currentInstruction = this.instructions.charAt(i);
            if (!canonicalInstructions.contains(currentInstruction)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Converts a string of maze symbols into an array of MazeBlock enums.
     * 
     * This method takes a string where each character represents a maze element, 
     * with '#' indicating a wall and any other character indicating a passable path. 
     * It returns an array of MazeBlock enums corresponding to these symbols.
     *
     * @param mazeSymbols the string containing the symbols of the maze
     * @return an array of MazeBlock enums representing the maze layout on that line
     */

    private MazeBlock [] toMazeBlockArray(String mazeSymbols) {
        char [] mazeChars = mazeSymbols.toCharArray();
        MazeBlock [] mazeBlocks = new MazeBlock[mazeChars.length];
        MazeBlock block;

        for (int i = 0; i < mazeChars.length; i++) {
            block = MazeBlock.PASS;
            
            if (mazeChars[i] == '#') {
                block = MazeBlock.WALL;
            }
            mazeBlocks[i] = block;
        }

        return mazeBlocks;
    }

    /**
     * Retrieves the instructions provided by the user when running the program.
     * These instructions are used by the InstructedWalker class to navigate the maze.
     * @return the instructions to navigate the maze
     */
    public String getInstructions() {
        return this.instructions;
    }

}

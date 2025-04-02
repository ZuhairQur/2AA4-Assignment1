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

    public String getFilename(String [] args) throws ParseException {
        this.command = parser.parse(options, args);
        return this.command.getOptionValue("i");
    }

    public String getInstructions(String [] args) throws ParseException {
        this.command = parser.parse(options, args);
        if (this.command.hasOption("p")) {
            InstructionCleaner instructionCleaner = new InstructionCleaner();
            this.instructions = instructionCleaner.getUnfactoredInstructions(this.command.getOptionValue("p"));

            if (!this.hasValidInstructions()) {
                throw new IllegalArgumentException("Path contains instructions that are not canonical ('F', 'L', 'R').");
            }
        }
        
        return this.instructions;
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

}

/**
 * File: InputHandler.java
 * Author: Zuhair Qureshi
 * Description: Defines the InputHandler class for the Maze Runner Application.
 * Responsible for parsing command-line arguments, reading and processing maze data 
 * from an input file, handling command-line options for specifying maze files and optional instructions.
 * Also provides methods to retrieve parsed input data and check for instruction flags.
 **/

package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class InputHandler {
    private final Options options;
    private CommandLine command;
    private String instructions;
    private final CommandLineParser parser;
    private final List <Character> canonicalInstructions = Arrays.asList('F', 'L', 'R');

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
     * Parses the command-line arguments to retrieve the input filename specified by the user.
     * @param args the command-line arguments containing the options and their respective values
     * @return the input filename specified by the '-i' option
     * @throws ParseException if the command-line arguments cannot be parsed
     */
    public String getFilename(String [] args) throws ParseException {
        this.command = parser.parse(options, args);
        return this.command.getOptionValue("i");
    }

    /**
     * Parses the command-line arguments to retrieve the instructions specified by the user.
     * @param args the command-line arguments containing the options and their respective values
     * @return the instructions specified by the '-p' option. If the instructions contain any
     * invalid characters, an IllegalArgumentException is thrown.
     * @throws ParseException if the command-line arguments cannot be parsed
     */
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
     * characters ('F', 'L', 'R').
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

}

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

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InputHandler {
    private BufferedReader reader;
    private final Options options;
    private char[][] contents;
    private CommandLine command;
    private String instructions;
    private final CommandLineParser parser;
    private static final Logger logger = LogManager.getLogger();

    public InputHandler() {
        this.options = new Options();
        this.parser = new DefaultParser();
    }

    /**
     * Configures the command-line options for the application.
     * 
     * This method sets up the available options for parsing command-line arguments.
     * It allows specifying an input file containing the maze using the '-i' or '--input'
     * option and a path of instructions to follow through the maze using the '-p' or '--instructions'
     * option. Options require an argument specifying the file or instructions respectively.
     */

    public void setOptions() {
        this.options.addOption("i", "input", true, "File containing maze");
        this.options.addOption("p", "instructions", true, "Path to follow through maze");
    }

    /**
     * Reads the input from the command line arguments and sets up the maze accordingly.
     * 
     * This method reads the command line arguments and sets up the maze based on the input
     * file and instructions provided. The input file must be specified, after which this method reads
     * the file and sets up the maze contents accordingly. If instructions are specified,
     * this method sets up the instructions for the walker to follow.
     * 
     * @param args the command line arguments
     * @return the configured maze file contents in a character by character array
     */
    public char[][] readInput(String[] args) throws Exception {
   
        this.command = parser.parse(options, args);

        if (this.command.hasOption("i")) {
            String filename = this.command.getOptionValue("i");

            logger.info("**** Reading the maze from file " + filename);

            Path path = Path.of(filename);
            int totalLineCount = (int) Files.lines(path).count();     
            this.contents = new char[totalLineCount][];

            this.readMaze(filename);

            // Read in instructions (if present) and verify that they are valid
            if (this.command.hasOption("p")) {
                this.instructions = InstructionCleaner.getUnfactoredInstructions(this.command.getOptionValue("p"));

                if (!this.hasValidInstructions()) {
                    throw new Exception("Instructions are not canonical.");
                }
            }
        }

        else {
            throw new Exception("No commands entered.");
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
    private void readMaze(String filename) throws FileNotFoundException, IOException  {
        this.reader = new BufferedReader(new FileReader(filename));
        String line;
        int lineNumber = 0;
        StringBuilder mazeText = new StringBuilder("");

        while ((line = this.reader.readLine()) != null) {
            this.contents[lineNumber] = line.toCharArray();

            for (int idx = 0; idx < line.length(); idx++) {
                mazeText.append(line.charAt(idx));
            }

            // Handling the edge case where a line is empty (i.e. all pass)
            if (line.length() == 0) {
                String emptyLine = "";
                for (int i = 0; i < contents[0].length; i++) {
                    mazeText.append("  ");
                    emptyLine += " ";
                }
                this.contents[lineNumber] = emptyLine.toCharArray();
            }

            lineNumber++;
            mazeText.append("\n");
        }

        this.reader.close();
        logger.info(mazeText.insert(0, "\n\n").toString());
    }

    /**
     * Verifies that the unraveled instructions provided by the user contain only canonical
     * characters ('F', 'L', 'R'). If the instructions contain any other characters,
     * this method returns false. Otherwise, it returns true.
     * @return true if the instructions are canonically valid, false otherwise
     */
    public boolean hasValidInstructions() {
        for (int i = 0; i < this.instructions.length(); i++) {
            if (this.instructions.charAt(i) != 'F' && this.instructions.charAt(i) != 'L' && this.instructions.charAt(i) != 'R') {
                return false;
            }
        }
        return true;
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

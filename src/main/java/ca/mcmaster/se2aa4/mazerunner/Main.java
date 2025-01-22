/**
 * File: Main.java
 * Author: Zuhair Qureshi
 * Description: Reads in maze from file and displays
 * to standard output via Apache logger.
 **/
package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        // Check for command line arguments
        Options options = new Options();
        options.addOption("i", "input", true, "Input file");
        CommandLineParser parser = new DefaultParser();

        logger.info("** Starting Maze Runner");
        
        try {

            CommandLine command = parser.parse(options, args);
            
            logger.info("**** Computing path");

            // Output maze from file
            if (command.hasOption("i")) {
                logger.info("**** Reading the maze from file " + args[1]);
                BufferedReader reader = new BufferedReader(new FileReader(args[1]));
                String line;
                StringBuilder maze = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    for (int idx = 0; idx < line.length(); idx++) {
                        if (line.charAt(idx) == '#') {
                            maze.append("WALL ");
                        } else if (line.charAt(idx) == ' ') {
                            maze.append("PASS ");
                        }
                    }
                    maze.append("\n");
                }

                logger.info("\n" + maze.toString());
                reader.close();
            }

            else {
                logger.info("Please enter a valid command.");
            }
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }

        // Fill in with implementation details soon
        logger.warn("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");


    }
}
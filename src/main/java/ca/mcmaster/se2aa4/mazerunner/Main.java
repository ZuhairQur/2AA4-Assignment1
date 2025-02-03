/**
 * File: Main.java
 * Author: Zuhair Qureshi
 * Description: Main file that controls Maze Runner Application.
 * Holds the main method with the main logic and flow of the program.
 **/
package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

/**
 * The method that drives the Maze Runner Application.
 *
 * Centralizes the application by directing: processing of command-line arguments,
 * reading of maze data, configuration of the maze and walker, and walker
 * navigation through the maze. The results of the navigation are logged.
 *
 * @param args command line arguments, expected to include options for input
 *             maze file and optional instructions for the walker.
 */
    public static void main(String[] args) {

        // Collecting user input and command line arguments
        InputHandler inputHandler = new InputHandler();
        inputHandler.setOptions();
        logger.info("** Starting Maze Runner");

        // Setting up walking skeleton and executing
        try {
            MazeBlock[][] contents = inputHandler.readInput(args);
            Configuration configuration = new Configuration(contents, inputHandler.getInstructions());
            Maze maze = configuration.getConfiguredMaze();
            Walker walker = configuration.getConfiguredWalker(maze); // walker can enter either entry or exit
            String walkingResults = walker.walk(maze);
            System.out.println(walkingResults);
        } catch (IOException e ) {
            System.err.println("The specified maze file " + e.getMessage() + " could not be found.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.err.println("Provided path sequence argument is not valid.");
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            System.err.println("Must specify a maze file through the '-i' option.");
        } catch (Exception e) {
            System.err.println("/!\\ An error has occured /!\\");
            System.err.println(e.getMessage());
        }

        logger.info("** End of Maze Runner");
    }
}
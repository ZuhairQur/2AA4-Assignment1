/**
 * File: Main.java
 * Author: Zuhair Qureshi
 * Description: Main file that controls Maze Runner Application.
 * Holds the main method with the main logic and flow of the program.
 **/
package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

/**
 * The main method that drives the Maze Runner Application.
 *
 * This method centralizes the application by directing: processing of command-line arguments,
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

        try {
            char[][] contents = inputHandler.readInput(args);
            Configuration configuration = new Configuration(contents, inputHandler.getInstructions());
            Walker walker = configuration.getConfiguredWalker();
            Maze maze = configuration.getConfiguredMaze();
            
            String walkingResults = walker.walk(maze);
            
            logger.info(walkingResults);
            logger.info("** End of MazeRunner");
        } catch (Exception e) {
            logger.error("Did not receive valid input. Exiting program.");
        }



    }
}
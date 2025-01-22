/**
 * File: Main.java
 * Author: Zuhair Qureshi
 * Description: Reads in maze from file and displays
 * to standard output via Apache logger.
 **/
package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        InputHandler inputHandler = new InputHandler();
        inputHandler.setOptions();
        logger.info("** Starting Maze Runner");
        char[][] contents = inputHandler.readInput(args);
        logger.info("Shortest path: FFFF");
        logger.info("** End of MazeRunner");

        System.out.println(Arrays.deepToString(contents));
        Maze maze = new Maze(contents);
        maze.setStartEndCoords();

    }
}
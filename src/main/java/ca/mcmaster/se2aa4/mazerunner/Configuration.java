/**
 * File: Configuration.java
 * Author: Zuhair Qureshi
 * Description: Defines the Configuration class for the Maze Runner Application.
 * Responsible for setting up the maze and walker based on input data, ensuring the
 * maze and walker are properly initialized for the application given user input.
 * It also provides access to the configured maze and walker objects.
 */

package ca.mcmaster.se2aa4.mazerunner;

public class Configuration {
    private Maze maze;
    private Walker walker;
    private char [][] contents;
    private String userInstructions;

    public Configuration(char [][] contents, String userInstructions) {
        this.contents = contents;
        this.userInstructions = userInstructions;
        this.configureMaze();
        this.configureWalker();
    }

    private void configureMaze() {
        this.maze = new Maze(contents);
        this.maze.setStartEndCoords();
    }

    public Maze getConfiguredMaze() {
        return this.maze;
    }

    private void configureWalker() {

        int startColumn = maze.getStartCoords()[1];
        int startDirection;

        if (startColumn == 0) {
            startDirection = 0;
        } else {
            startDirection = 2;
        }

        if (this.userInstructions != null) {
            this.walker = new InstructedWalker(maze.getStartCoords(), startDirection, InstructionCleaner.getUnfactoredInstructions(this.userInstructions));
        } else {
            this.walker = new FreeWalker(maze.getStartCoords(), startDirection);
        }
    }

    public Walker getConfiguredWalker() {
        return this.walker;
    }
}

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
    private final char [][] contents;
    private String userInstructions;

    public Configuration(char [][] contents, String userInstructions) {
        this.contents = contents;
        this.userInstructions = userInstructions;
        this.configureMaze();
        this.configureWalker();
    }

    /**
     * Sets up the maze from the input data. Instantiates a new Maze object with the
     * input data and dynamically sets the start and end coordinates of the maze 
     * according to its layout.
     */
    private void configureMaze() {
        this.maze = new Maze(contents);
        this.maze.setStartEndCoords();
    }

    /**
     * Returns the configured maze object. 
     * @return the configured maze object
     */
    public Maze getConfiguredMaze() {
        return this.maze;
    }

    
    /**
     * Sets up the walker from the input data. Instantiates a new walker object based on whether
     * instructions were provided. If instructions were provided, a new InstructedWalker object is
     * created with the unfactored instruction string and the start coordinates of the maze.
     * Otherwise, a new FreeWalker object is created with the start coordinates of the maze.
     */
    private void configureWalker() {

        int startColumn = maze.getStartCoords()[1];
        int startDirection;

        // Start direction is 0 for facing right, 2 for left
        // If the start column is 0, start facing right. Otherwise, start facing left
        // CODE SMELL. This should be refactored with Enum (see lecture January 23).

        if (startColumn == 0) {
            startDirection = 0;
        } else {
            startDirection = 2;
        }

        if (this.userInstructions != null) {
            this.userInstructions = InstructionCleaner.getUnfactoredInstructions(this.userInstructions);
            this.walker = new InstructedWalker(maze.getStartCoords(), startDirection, this.userInstructions);
        } else {
            this.walker = new FreeWalker(maze.getStartCoords(), startDirection);
        }
    }

    /**
     * Returns the configured walker object.
     * 
     * @return the configured Walker object, either an InstructedWalker or FreeWalker
     *         depending on whether instructions were provided.
     */

    public Walker getConfiguredWalker() {
        return this.walker;
    }
}

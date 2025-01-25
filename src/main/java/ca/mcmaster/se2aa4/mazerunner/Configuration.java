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
    private final Maze maze;
    private Walker walker;
    private final WalkStatus walkingStatus;
    private final String userInstructions;

    public Configuration(char [][] contents, String userInstructions) {
        this.userInstructions = userInstructions;
        this.maze = new Maze(contents);
        this.walkingStatus = new WalkStatus();
        
    }

    /**
     * Returns the configured maze object. 
     * @return the configured maze object
     */
    public Maze getConfiguredMaze() {
        this.maze.initializeStartEndCoords();
        return this.maze;
    }

    /**
     * Returns the configured walker object. If instructions were provided, the walker is configured
     * to follow the instructions. Otherwise, the walker is configured to navigate the maze freely.
     * The walker's direction is determined by the start column of the maze: if the column is 0, the
     * walker faces right; if the column is non-zero, the walker faces left.
     * @return the configured walker object
     */
    public Walker getConfiguredWalker() {

        int [] walkerStartCoords = {maze.getStartCoords()[0], maze.getStartCoords()[1]};
        int startColumn = walkerStartCoords[1];
        int startDirection;


        // Start direction is 0 for facing right, 2 for left
        // If the start column is 0, start facing right. Otherwise, start facing left
        // CODE SMELL. This should be refactored with Enum (see lecture January 23).

        if (startColumn == 0) {
            startDirection = 0;
        } else {
            startDirection = 2;
        }

        this.walker = new FreeWalker(walkerStartCoords, startDirection, this.walkingStatus);

        if (this.userInstructions != null) {
            this.walker = new InstructedWalker(walkerStartCoords, startDirection,  this.walkingStatus, this.userInstructions);
        }

        return this.walker;
         
    }
}

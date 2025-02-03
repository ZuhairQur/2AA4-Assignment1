/**
 * File: Configuration.java
 * Author: Zuhair Qureshi
 * Description: Defines the Configuration class for the Maze Runner Application.
 * Responsible for setting up the maze and walker based on input data, ensuring the
 * maze and walker are properly initialized for the application given user input.
 * It also returns back the configured maze and walker objects.
 */

package ca.mcmaster.se2aa4.mazerunner;

public class Configuration {
    private final String userInstructions;
    private final MazeBlock[][] contents;

    public Configuration(MazeBlock [][] contents, String userInstructions) {
        this.userInstructions = userInstructions;
        this.contents = contents;
    }

    /**
     * Returns the configured maze object. 
     * @return the configured maze object
     */
    public Maze getConfiguredMaze() {
        Maze maze = new Maze(contents);
        maze.initializeStartEndCoords();
        return maze;
    }

    /**
     * Returns the configured walker object. If instructions were provided, the walker is configured
     * to follow the instructions. Otherwise, the walker is configured to navigate the maze freely.
     * The walker's direction is determined by the start column of the maze: if the column is 0, the
     * walker faces right; if the column is non-zero, the walker faces left.
     * @return the configured walker object
     */
    public Walker getConfiguredWalker(Maze maze) {
        Direction walkerStartDirection = Direction.LEFT;
        int [] walkerStartCoords = {maze.getStartCoords()[0], maze.getStartCoords()[1]};
        int walkerStartingColumn = walkerStartCoords[1];

        if (walkerStartingColumn == 0) {
            walkerStartDirection = Direction.RIGHT;
        } 

        if (this.userInstructions != null) {
            return new InstructedWalker(walkerStartCoords, walkerStartDirection, this.userInstructions);
        }

        return new FreeWalker(walkerStartCoords, walkerStartDirection);
         
    }
}

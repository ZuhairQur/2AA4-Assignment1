/**
 * File: Configuration.java
 * Author: Zuhair Qureshi
 * Description: Defines the Configuration class for the Maze Runner Application.
 * Responsible for setting up the walker based on input data, ensuring the
 * walker is properly initialized for the application
 */

package ca.mcmaster.se2aa4.mazerunner.Setup;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.WalkStrategies.FreeWalker;
import ca.mcmaster.se2aa4.mazerunner.WalkStrategies.InstructedWalker;
import ca.mcmaster.se2aa4.mazerunner.WalkStrategies.MazeSolvingAlgorithm;
import ca.mcmaster.se2aa4.mazerunner.WalkStrategies.RightHandAlgorithm;
import ca.mcmaster.se2aa4.mazerunner.WalkStrategies.Walker;

public class Configuration {
    private final String userInstructions;

    public Configuration(String userInstructions) {
        this.userInstructions = userInstructions;
    }

    /**
     * Returns the configured walker object. If instructions were provided, the walker is configured
     * to follow the instructions. Otherwise, the walker is configured to navigate the maze freely.
     * @return the configured walker object
     */
    public Walker getConfiguredWalker(Maze maze) {
        if (this.userInstructions != null) {
            return new InstructedWalker(maze, this.userInstructions);
        }

        MazeSolvingAlgorithm algorithm = new RightHandAlgorithm();
        return new FreeWalker(maze, algorithm);
    }
}

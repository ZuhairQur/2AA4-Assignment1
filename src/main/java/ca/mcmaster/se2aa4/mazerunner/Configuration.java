/**
 * File: Configuration.java
 * Author: Zuhair Qureshi
 * Description: Defines the Configuration class for the Maze Runner Application.
 * Responsible for setting up the maze and walker based on input data, ensuring the
 * maze and walker are properly initialized for the application given user input.
 * It also returns back the configured maze and walker objects.
 */

package ca.mcmaster.se2aa4.mazerunner;

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

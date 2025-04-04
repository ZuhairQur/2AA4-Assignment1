/**
 * File: RightHandAlgorithm.java
 * Author: Zuhair Qureshi
 * Description: This class implements the MazeSolvingAlgorithm interface and provides 
 * the right-hand rule for solving the maze. 
 */


package ca.mcmaster.se2aa4.mazerunner.WalkStrategies;

import ca.mcmaster.se2aa4.mazerunner.Command.Forward;
import ca.mcmaster.se2aa4.mazerunner.Command.LeftTurn;
import ca.mcmaster.se2aa4.mazerunner.Command.RightTurn;
import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Navigation.Coordinates;
import ca.mcmaster.se2aa4.mazerunner.Navigation.CoordinatesTracker;
import ca.mcmaster.se2aa4.mazerunner.Navigation.DirectionManager;

public class RightHandAlgorithm  implements MazeSolvingAlgorithm {
    
    /**
     * Solves the maze by using the right-hand rule, which moves the walker forward until it hits a wall.
     * When it hits a wall, it turns left and backtracks until it can move forward again. If it is not at a wall, it moves forward.
     * The algorithm is run until the walker reaches the end of the maze. The instructions are stored in a StringBuilder, which is then converted to a string and returned.
     * @param walker the walker object, which is moved according to the right-hand rule
     * @return the instructions for the walker to follow to reach the end of the maze
     */
    @Override
    public String solveMaze(CoordinatesTracker coordinatesTracker, DirectionManager directionManager, Maze maze) {
        StringBuilder instructions = new StringBuilder();


        Coordinates exitCoordinates = maze.getRightOpening();

        while (!coordinatesTracker.reachedEnd(exitCoordinates)) {
            boolean successful = new Forward(coordinatesTracker, directionManager, maze).execute();

            // Did not hit wall
            if (successful) {
                instructions.append("F");

            } else {
                new LeftTurn(directionManager).execute();
                instructions.append("L");
            }                  

            if (coordinatesTracker.canPass(directionManager.onRight(), maze)) {
                new RightTurn(directionManager).execute();
                instructions.append("R");
            }
        }
        
        return instructions.toString();
    }
}

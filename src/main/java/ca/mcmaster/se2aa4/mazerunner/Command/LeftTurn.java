/**
 * File: LeftTurn.java
 * Author: Zuhair Qureshi
 * Description: This class represents the left turn command for the walker 
 * in the maze. It implements the Movement interface and provides the functionality 
 * to turn the walker to the left.
 */

package ca.mcmaster.se2aa4.mazerunner.Command;

import ca.mcmaster.se2aa4.mazerunner.Navigation.DirectionManager;

public class LeftTurn implements Movement {

    private DirectionManager directionManager;
    private boolean successful = true;

    public LeftTurn(DirectionManager directionManager) {
        this.directionManager = directionManager;
    }

/**
 * Executes the left turn movement for the walker.
 * @return true indicating the turn was successful
 */
    @Override
    public boolean execute() {
        directionManager.turnLeft();
        return this.successful;
    }
}

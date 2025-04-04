/**
 * File: RightTurn.java
 * Author: Zuhair Qureshi
 * Description: This class represents the right turn command for the walker 
 * in the maze. It implements the Movement interface and provides the functionality 
 * to turn the walker to the right. 
 */

package ca.mcmaster.se2aa4.mazerunner.Command;

import ca.mcmaster.se2aa4.mazerunner.Navigation.DirectionManager;

public class RightTurn implements Movement {
    private DirectionManager directionManager;
    private boolean successful = true;

    public RightTurn(DirectionManager directionManager) {
        this.directionManager = directionManager;
    }

    /**
     * Executes the right turn movement for the walker.
     * @return true indicating the turn was successful
     */
    @Override
    public boolean execute() {
        directionManager.turnRight();
        return this.successful;
    }
}

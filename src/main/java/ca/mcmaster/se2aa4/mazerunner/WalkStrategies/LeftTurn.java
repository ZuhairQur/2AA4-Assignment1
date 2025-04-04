package ca.mcmaster.se2aa4.mazerunner.WalkStrategies;

import ca.mcmaster.se2aa4.mazerunner.DirectionManager;

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
        System.out.println("PRANTING DIRECTION: "+ directionManager.getDirection());
        directionManager.turnLeft();
        return this.successful;
    }
}

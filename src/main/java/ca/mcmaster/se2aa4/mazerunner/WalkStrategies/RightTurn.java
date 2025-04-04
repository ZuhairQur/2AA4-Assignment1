package ca.mcmaster.se2aa4.mazerunner.WalkStrategies;

import ca.mcmaster.se2aa4.mazerunner.DirectionManager;

public class RightTurn implements Movement {
    private DirectionManager directionManager;
    private boolean successful = true;

    public RightTurn(DirectionManager directionManager) {
        this.directionManager = directionManager;
    }

    @Override
    public boolean execute() {
        System.out.println("PRANTING DIRECTION: "+ directionManager.getDirection());
        directionManager.turnRight();
        return this.successful;
    }
}

package ca.mcmaster.se2aa4.mazerunner.WalkStrategies;

public class RightHandAlgorithm  implements MazeSolvingAlgorithm{
    
    /**
     * Solves the maze by using the right-hand rule, which moves the walker forward until it hits a wall.
     * When it hits a wall, it turns left and backtracks until it can move forward again. If it is not at a wall, it moves forward.
     * The algorithm is run until the walker reaches the end of the maze. The instructions are stored in a StringBuilder, which is then converted to a string and returned.
     * @param walker the walker object, which is moved according to the right-hand rule
     * @return the instructions for the walker to follow to reach the end of the maze
     */
    @Override
    public String solveMaze(Walker walker) {
        StringBuilder instructions = new StringBuilder();

        while (!walker.reachedEnd()) {
            walker.moveForward();

            try {
                if (walker.hitWall()) {
                    walker.stepBack();
                    walker.turnLeft();
                    instructions.append("L");
                } else {
                    instructions.append("F");
                }                  
            } catch (IndexOutOfBoundsException e) {
                walker.stepBack();
            }

            if (!walker.hasWallOnRight()) {
                walker.turnRight();
                instructions.append("R");
            }
        }
        
        return instructions.toString();
    }
}

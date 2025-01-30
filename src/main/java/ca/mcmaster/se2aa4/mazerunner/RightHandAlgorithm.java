package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

public class RightHandAlgorithm extends MazeSolvingAlgorithm {
    
/**
 * Solves the maze using the right-hand rule algorithm.
 * The walker attempts to navigate the maze by keeping its right hand
 * in contact with the wall at all times.
 *
 * @param walker the FreeWalker object that navigates the maze
 * @param maze the Maze object representing the maze structure
 * @return a string of instructions that represents the path taken
 */

    @Override
    public String solveMaze(FreeWalker walker, Maze maze, WalkStatus walkStatus) {
        StringBuilder instructions = new StringBuilder();
        System.out.println(Arrays.toString(walker.getCoords()));

        while (!walkStatus.hasEscaped(walker, maze)) {
            walker.moveForward();
            
            try {
                if (walkStatus.hitWall(walker, maze)) {
                    walker.stepBack();
                    walker.turnLeft();
                    instructions.append("L");
                } else {
                    instructions.append("F");
                }                  
            } catch (IndexOutOfBoundsException e) {
                walker.stepBack();
            }

            if (!walkStatus.wallOnRight(walker, maze)) {
                walker.turnRight();
                instructions.append("R");
            }
        }

        System.out.println(Arrays.toString(walker.getCoords()));

        return instructions.toString();
    }
}

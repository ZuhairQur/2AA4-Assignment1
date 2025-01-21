package ca.mcmaster.se2aa4.mazerunner;

public class WalkStatus {
    
    public static boolean hasEscaped(Walker walker, Maze maze) {
        return walker.getCoords()[0] == maze.getEndCoords()[0] && walker.getCoords()[1] == maze.getEndCoords()[1];
    }

    public static boolean hasLost(Walker walker, Maze maze) {
        return maze.peakBlock(walker.getCoords()[0], walker.getCoords()[0]) == '#';
    }
}

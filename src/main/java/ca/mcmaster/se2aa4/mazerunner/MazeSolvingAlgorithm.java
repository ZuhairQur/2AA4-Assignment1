package ca.mcmaster.se2aa4.mazerunner;

public abstract class MazeSolvingAlgorithm {
    public abstract String solveMaze(FreeWalker walker, Maze maze, WalkStatus walkStatus);
}

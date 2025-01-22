package ca.mcmaster.se2aa4.mazerunner;

public class FreeWalker extends Walker {

    public FreeWalker(int[] coords, int direction) {
        super(coords, direction);
    }

    @Override
    public void walk(Maze maze) {
        this.move();
    }
}

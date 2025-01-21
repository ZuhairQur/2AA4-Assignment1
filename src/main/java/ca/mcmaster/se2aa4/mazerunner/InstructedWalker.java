package ca.mcmaster.se2aa4.mazerunner;

public class InstructedWalker extends Walker {

    public InstructedWalker(int [] coords, int direction) {
        super(coords, direction);
    }

    @Override
    public void walk(Maze maze) {
        this.move();
    }

}

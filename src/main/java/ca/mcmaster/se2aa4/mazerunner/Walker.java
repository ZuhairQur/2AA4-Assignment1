package ca.mcmaster.se2aa4.mazerunner;

public abstract class Walker {
    

    protected int[] coords = new int[2];
    protected int direction;
    protected final int [][] directions = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public Walker(int [] coords, int direction) {
        this.coords = coords;
        this.direction = direction;
    }

    public int[] getCoords() {
        return this.coords;
    }

    public void turnLeft() {
        this.direction = (this.direction + 3) % 4;
    }

    public void turnRight() {
        this.direction = (this.direction + 1) % 4;
    }

    public void move() {
        this.coords[0] += this.directions[this.direction][0];
        this.coords[1] += this.directions[this.direction][1];
    }

    public abstract void walk(Maze maze);
}

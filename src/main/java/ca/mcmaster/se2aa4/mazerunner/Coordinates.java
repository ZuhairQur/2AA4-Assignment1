package ca.mcmaster.se2aa4.mazerunner;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Coordinates add(Coordinates coords) {
        return new Coordinates(this.x + coords.getX(), this.y + coords.getY());
    }

    public Coordinates subtract(Coordinates coords) {
        return new Coordinates(this.x - coords.getX(), this.y - coords.getY());
    }

    public Coordinates copy() {
        return new Coordinates(this.x, this.y);
    }

    public boolean equals(Coordinates coords) {
        return this.x == coords.getX() && this.y == coords.getY();
    }
}

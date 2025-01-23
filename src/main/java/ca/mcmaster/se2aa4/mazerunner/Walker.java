/**
 * File: Walker.java
 * Author: Zuhair Qureshi
 * Description: This abstract class acts as a base class for all walker types, providing core 
 * movement logic and structure for maze navigation. It stores the current coordinates and 
 * direction of the walker, provides methods to turn left, turn right, and move in the current direction,
 * encodes movement logic using a 2D array of direction vectors, and requires subclasses to implement 
 * the walk method, defining specific navigation behaviors.
 */

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

    public abstract String walk(Maze maze);
}

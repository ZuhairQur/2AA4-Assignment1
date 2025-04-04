/**
 * File: Coordinates.java
 * Author: Zuhair Qureshi
 * Description: This class represents a point in a 2D coordinate system with x and y values. 
 * It provides methods to get and set the x and y coordinates, perform vector addition and subtraction, 
 * create copies of coordinate objects, and check for equality between two coordinates.
 */

package ca.mcmaster.se2aa4.mazerunner.Navigation;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of the Coordinates object.
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the Coordinates object.
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x-coordinate of the Coordinates object to the specified value.
     * @param x the new x-coordinate value
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the Coordinates object to the specified value.
     * @param y the new y-coordinate value
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Returns a new Coordinates object which is the vector sum of this object and
     * the specified Coordinates object.
     * @param coordinates the Coordinates object to add
     * @return a new Coordinates object which is the sum of this object and the specified object
     */
    public Coordinates add(Coordinates coordinates) {
        return new Coordinates(this.x + coordinates.getX(), this.y + coordinates.getY());
    }
    
    /**
     * Returns a new Coordinates object which is the vector difference of this object and
     * the specified Coordinates object.
     * @param coordinates the Coordinates object to subtract
     * @return a new Coordinates object which is the difference of this object and the specified object
     */
    public Coordinates subtract(Coordinates coordinates) {
        return new Coordinates(this.x - coordinates.getX(), this.y - coordinates.getY());
    }

    /**
     * Returns a new Coordinates object which is a copy of this object.
     * @return a new Coordinates object which is a copy of this object
     */
    public Coordinates copy() {
        return new Coordinates(this.x, this.y);
    }

    /**
     * Checks if this Coordinates object is equal to the specified Coordinates object.
     * Two Coordinates objects are equal if their x and y coordinates are equal.
     * @param coordinates the Coordinates object to compare
     * @return true if this object is equal to the specified Coordinates object, false otherwise
     */
    public boolean equals(Coordinates coordinates) {
        return this.x == coordinates.getX() && this.y == coordinates.getY();
    }
}

package ca.mcmaster.se2aa4.mazerunner;

public enum Direction {
    UP, RIGHT, DOWN, LEFT;

    /**
     * Returns the direction that results from turning left from the current direction.
     * @return the direction after turning left
     */
    public Direction onLeft() {
        
        if (this == RIGHT) {
            return UP;
        } else if (this == UP) {
            return LEFT;
        } else if (this == LEFT) {
            return DOWN;
        } 

        return RIGHT;
    }

    /**
     * Returns the direction that results from turning right from the current direction.
     * @return the direction after turning right
     */
    public Direction onRight() {
        if (this == RIGHT) {
            return DOWN;
        } else if (this == DOWN) {
            return LEFT;
        } else if (this == LEFT) {
            return UP;
        }
        
        return RIGHT;
    }

    /**
     * Flips the direction between the two entry directions, i.e. RIGHT becomes LEFT and vice versa.
     * Orients the walker to walk from the other opening to verify if the  user-provided path is valid.
     * @return the flipped direction
     */
    public Direction flipEntryDirection() {
        if (this == RIGHT) {
            return LEFT;
        } 

        return RIGHT;
    }

    /**
     * Retrieves the direction vector corresponding to the current direction.
     * The direction vector is an array of two integers, with the first element
     * being the row increment and the second element being the column increment. 
     * The direction vector is used to move the walker in its current direction.
     * @return the direction vector as an array of two integers
     */
    public int[] getDirectionVector() {

        if (this == UP) {
            return new int[] {-1, 0};
        } else if (this == RIGHT) {
            return new int[] {0, 1};
        } else if (this == DOWN) {
            return new int[] {1, 0};
        } 

        return new int[] {0, -1};
    }

    /**
     * Returns the starting direction based on the start column of the walker.
     * If the start column is 0 (i.e. west end), the walker starts by facing right.
     * Otherwise, the walker starts on the east end, facing left.
     * @param startColumn the column index of the start position
     * @return the starting direction
     */
    public Direction getStartDirection(int startColumn) {
        if (startColumn == 0) {
            return RIGHT;
        }

        return LEFT;
    }
}

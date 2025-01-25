package ca.mcmaster.se2aa4.mazerunner;

public enum Direction {
    UP, RIGHT, DOWN, LEFT;

    /**
     * Returns the direction that results from turning left from the current direction.
     * @return the direction after turning left
     */
    public Direction turnLeft() {
        return switch (this) {
            case RIGHT -> UP;
            case UP -> LEFT;
            case LEFT -> DOWN;
            default -> RIGHT;
        };
    }

    /**
     * Returns the direction that results from turning right from the current direction.
     * @return the direction after turning right
     */
    public Direction turnRight() {
        return switch (this) {
            case RIGHT -> DOWN;
            case DOWN -> LEFT;
            case LEFT -> UP;
            default -> RIGHT;
        };
    }

    /**
     * Flips the direction between the two entry directions, i.e. RIGHT becomes LEFT and vice versa.
     * Orients the walker to walk from the other opening to verify if the  user-provided path is valid.
     * @return the flipped direction
     */
    public Direction flipEntryDirection() {
        return switch (this) {
            case RIGHT -> LEFT;
            default -> RIGHT;
        };
    }

    /**
     * Retrieves the direction vector corresponding to the current direction.
     * The direction vector is an array of two integers, with the first element
     * being the row increment and the second element being the column increment. 
     * The direction vector is used to move the walker in its current direction.
     * @return the direction vector as an array of two integers
     */
    public int[] getDirectionVector() {
        return switch (this) {
            case UP -> new int[] {-1, 0};
            case RIGHT -> new int[] {0, 1};
            case DOWN -> new int[] {1, 0};
            default -> new int[] {0, -1};
        };
    }

    public Direction getStartDirection(int startColumn) {
        return switch (startColumn) {
            case 0 -> RIGHT;
            default -> LEFT;
        };
    }
}

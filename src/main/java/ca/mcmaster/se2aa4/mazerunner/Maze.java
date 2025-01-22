package ca.mcmaster.se2aa4.mazerunner;


public class Maze {
    private final char[][] maze;
    private int [] startCoords = new int [2];
    private int [] endCoords = new int[2];

    public Maze(char [][] maze) {
        this.maze = maze;
    }

    public char peakBlock(int x, int y) {
        return (char) maze[x][y];
    }

    public void setStartEndCoords() {

        // BETTER TO HARD CODE FOR THE 
        // MINIMAL VIABLE PRODUCT 
        // I.E. JUST SET START COORD TO RIGHT END
        // AND END COORD TO LEFT END

        int [] rightOpening = new int[2];
        int [] leftOpening = new int[2];

        for (int i = 0; i < maze.length; i++) {
            if (maze[i][0] == ' ') {
                rightOpening[0] = i;
                rightOpening[1] = 0;
            }

            if (maze[i][maze[0].length - 1] == ' ') {
                leftOpening[0] = i;
                leftOpening[1] = maze[0].length - 1;
            }
        }
        
        this.startCoords = leftOpening;
        this.endCoords = rightOpening;

        if (Math.random() < 0.5) {
            this.startCoords = rightOpening;
            this.endCoords = leftOpening;
        }
    }

    public int[] getStartCoords() {
        return this.startCoords;
    }

    public int[] getEndCoords() {
        return this.endCoords;
    }
    
}

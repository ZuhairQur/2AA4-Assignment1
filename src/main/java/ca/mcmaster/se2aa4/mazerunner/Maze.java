/**
 * File: Maze.java
 * Author: Zuhair Qureshi
 * Description: This class represents the maze structure, including its layout and
 * key coordinates such as start and end points. Its responsibilities include:
 * storing the maze structure as a 2D character array, identifying and setting 
 * the starting and ending coordinates of the maze dynamically, and providing methods 
 * to retrieve specific blocks and key coordinates within the maze.
 */


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

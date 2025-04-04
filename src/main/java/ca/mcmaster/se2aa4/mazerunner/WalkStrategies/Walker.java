/**
 * File: Walker.java
 * Author: Zuhair Qureshi
 * Description: This interface defines the Walker strategy for a walker in the maze. 
 * The strategy is contained in the walk() method which is implemented by classes to define 
 * how the walker navigates through the maze.
 */


package ca.mcmaster.se2aa4.mazerunner.WalkStrategies;

public interface Walker {
    public String walk();
}

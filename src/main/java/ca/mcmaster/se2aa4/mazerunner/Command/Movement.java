/**
 * File: Movement.java
 * Author: Zuhair Qureshi
 * Description: This interface defines a general movement command for a walker in the maze. 
 * It includes a single method, execute(), which is implemented by specific movement 
 * classes to perform different actions (e.g., moving forward, turning left or right).
 */

package ca.mcmaster.se2aa4.mazerunner.Command;

public interface Movement {
    public boolean execute();
}

package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MazeReader {
    private String filename;

    public MazeReader(String filename) {
        this.filename = filename;
    }

    /**
     * Reads the maze file and returns a Maze object based on the contents. 
     * @return a Maze object representing the contents of the file
     * @throws IOException if the file is not found or does not contain a valid maze
     */
    public Maze getMaze() throws IOException {
        MazeBlock[][] contents = this.readMaze();
        Maze maze = new Maze(contents);
        return maze;
    }

    /**
     * Reads the maze file and returns a 2D array of MazeBlock enums
     * representing the maze structure. Throws an IOException if the file
     * is not found or contains invalid characters.
     * @return a 2D array of MazeBlock enums representing the maze structure
     * @throws IOException if the file is not found or does not contain a valid maze
     */
    protected MazeBlock[][] readMaze() throws IOException {
        Path path = Path.of(filename);
        int totalLineCount = (int) Files.lines(path).count();     
        MazeBlock[][] contents = new MazeBlock[totalLineCount][];

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        StringBuilder mazeText = new StringBuilder();
        int lineNumber = 0;
        int maxLineLength = 0;

        while ((line = reader.readLine()) != null) {

            // Identifying the longest line (corresponds to the width of the maze)
            if (line.length() > maxLineLength) {
                maxLineLength = line.length();
            }

            for (int idx = 0; idx < line.length(); idx++) {
                char currentChar = line.charAt(idx);
                mazeText.append(currentChar);
                if (currentChar != ' ' && currentChar != '#') {
                    throw new IOException("Maze contains invalid characters.");
                }
            }

            // Handling the edge case where line contains null space at the end
            int emptyCharCount = maxLineLength - line.length();
            if (emptyCharCount > 0) {
                for (int i = 0; i < emptyCharCount; i++) {
                    line += " ";
                }
            }
            contents[lineNumber] = this.toMazeBlockArray(line);
            lineNumber++;
            mazeText.append("\n");
        }

        reader.close();

        return contents;
    }

/**
 * Converts a string of maze symbols into an array of MazeBlock enums.
 * @param mazeSymbols the string containing the maze symbols to convert
 * @return an array of MazeBlock enums representing the maze structure
 */
    private MazeBlock [] toMazeBlockArray(String mazeSymbols) {
        char [] mazeChars = mazeSymbols.toCharArray();
        MazeBlock [] mazeBlocks = new MazeBlock[mazeChars.length];
        MazeBlock block;

        for (int i = 0; i < mazeChars.length; i++) {
            block = MazeBlock.PASS;
            
            if (mazeChars[i] == '#') {
                block = MazeBlock.WALL;
            }
            mazeBlocks[i] = block;
        }

        return mazeBlocks;
    }
}

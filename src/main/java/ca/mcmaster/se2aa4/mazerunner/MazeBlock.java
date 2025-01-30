package ca.mcmaster.se2aa4.mazerunner;

public enum MazeBlock {
    PASS, WALL;

    public boolean isPass() {
        return this == PASS;
    }

    public boolean isWall() {
        return this == WALL; 
    }

    private static MazeBlock fromChar(char c) {
        return c == '#' ? WALL : PASS;
    }

    public static MazeBlock [] toMazeBlockArray(String mazeText) {
        char [] mazeChars = mazeText.toCharArray();
        MazeBlock [] mazeBlocks = new MazeBlock[mazeChars.length];
        for (int i = 0; i < mazeChars.length; i++) {
            mazeBlocks[i] = MazeBlock.fromChar(mazeChars[i]);
        }
        return mazeBlocks;
    }
}

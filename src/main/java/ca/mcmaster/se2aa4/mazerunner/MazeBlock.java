package ca.mcmaster.se2aa4.mazerunner;

public enum MazeBlock {
    PASS, WALL;


    /**
     * Indicates whether this maze block is a wall ('#') or not.
     * @return true if this block is a wall, false if it is passable
     */
    public boolean isWall() {
        return this == WALL; 
    }

    /**
     * Converts a character to a MazeBlock.
     * 
     * Given a character, this method returns the corresponding MazeBlock. If the
     * character is '#', this method returns MazeBlock.WALL. Otherwise, it returns
     * MazeBlock.PASS.
     * @param c the character to convert
     * @return the corresponding MazeBlock
     */
    private static MazeBlock convertCharToBlock(char mazeChar) {
        if (mazeChar == '#') {
            return WALL;
        }
        
        return PASS;
    }

    public static MazeBlock [] toMazeBlockArray(String mazeText) {
        char [] mazeChars = mazeText.toCharArray();
        MazeBlock [] mazeBlocks = new MazeBlock[mazeChars.length];
        for (int i = 0; i < mazeChars.length; i++) {
            mazeBlocks[i] = MazeBlock.convertCharToBlock(mazeChars[i]);
        }
        return mazeBlocks;
    }
}

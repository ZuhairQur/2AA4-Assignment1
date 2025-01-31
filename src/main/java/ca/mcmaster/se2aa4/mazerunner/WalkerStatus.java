package ca.mcmaster.se2aa4.mazerunner;

public interface WalkerStatus {

    public boolean hasEscaped(Walker walker);
    public boolean hitWall(Walker walker);
    public boolean wallOnRight(FreeWalker walker);
    public void enterOtherSide(InstructedWalker walker);
    
}

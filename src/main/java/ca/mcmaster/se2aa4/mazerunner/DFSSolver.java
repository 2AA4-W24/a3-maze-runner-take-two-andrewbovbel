package ca.mcmaster.se2aa4.mazerunner;

public class DFSSolver extends Solver{
    @Override
    public Algorithm createAlgorithm() {
        return new DFS();
    }
}

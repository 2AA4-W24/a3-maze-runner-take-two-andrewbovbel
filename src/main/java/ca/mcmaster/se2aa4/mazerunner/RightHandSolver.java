package ca.mcmaster.se2aa4.mazerunner;

public class RightHandSolver extends Solver {
    @Override
    public Algorithm createAlgorithm() {
        return new RightHand();
    }
}

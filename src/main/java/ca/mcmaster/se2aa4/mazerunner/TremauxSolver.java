package ca.mcmaster.se2aa4.mazerunner;

public class TremauxSolver extends Solver{
    @Override
    public Algorithm createAlgorithm() {
        return new Tremaux();
    }
}

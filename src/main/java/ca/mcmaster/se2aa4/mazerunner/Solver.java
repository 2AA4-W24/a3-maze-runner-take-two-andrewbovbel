package ca.mcmaster.se2aa4.mazerunner;

public abstract class Solver {
    public abstract Algorithm createAlgorithm();
    public Maze.Path solveMaze(Maze maze) {
        Algorithm algorithm = createAlgorithm();
        return algorithm.solve(maze);
    }
}

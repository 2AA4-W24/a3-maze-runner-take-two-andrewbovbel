package ca.mcmaster.se2aa4.mazerunner;

public interface Algorithm {
    /**
     * Solve maze and return path through maze.
     *
     * @param maze Maze to solve
     * @return Path that solves the provided maze
     */
    Maze.Path solve(Maze maze);
}

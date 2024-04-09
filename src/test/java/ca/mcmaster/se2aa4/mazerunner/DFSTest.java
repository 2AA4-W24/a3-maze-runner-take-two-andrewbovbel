package ca.mcmaster.se2aa4.mazerunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DFSTest {
    Maze straight;
    Maze direct;
    Maze giant;
    Maze regular;
    Maze small;

    Algorithm dfs = new DFS();

    @BeforeEach
    public void setUp() throws Exception {
        straight = new Maze("examples/straight.maz.txt");
        direct = new Maze("examples/direct.maz.txt");
        giant = new Maze("examples/giant.maz.txt");
        regular = new Maze("examples/regular.maz.txt");
        small = new Maze("examples/small.maz.txt");
    }

    @Test
    void solvesStraightShortestPath() {
        Maze.Path path = dfs.solve(straight);
        assertEquals("4F", path.getFactorizedForm());
    }
    @Test
    void solvesDirectShortestPath() {
        Maze.Path path = dfs.solve(direct);
        assertEquals("F R 2F L 4F R 2F L 2F", path.getFactorizedForm());
    }
    @Test
    void solvesGiantShortestPath() {
        Maze.Path path = dfs.solve(giant);
        assertEquals("F L 2F R 2F L 6F R 2F L 6F R 2F R 2F L 2F R 2F L 2F R 8F L 4F R 4F L 6F R 2F L 4F R 2F L 2F R 4F L 4F R 2F L 18F R 4F L 4F R 2F L 2F R 2F L 4F R 4F L 2F R 2F L 2F L 2F R 4F L 2F R 4F L 2F R 10F L 6F R 2F L 2F R 6F L 2F R 2F R 4F L 2F R 2F L 14F R 4F L 4F R 2F L 2F R 8F L 10F R 2F L 4F R 2F L 6F R 2F L 4F R 2F L 6F L 2F R 2F L 4F R 5F", path.getFactorizedForm());
    }
    @Test
    void solvesRegularShortestPath() {
        Maze.Path path = dfs.solve(regular);
        assertEquals("3F L 2F L 2F R 30F R 16F R 2F L 4F R 4F L 2F R 2F L 2F R 6F L 2F R 4F L 2F R 6F L 4F R 2F L 2F R 2F L 4F L 2F R F", path.getFactorizedForm());
    }
    @Test
    void solvesSmallShortestPath() {
        Maze.Path path = dfs.solve(small);
        assertEquals("F L F R 2F L 6F R 4F R 2F L 2F R 2F L F", path.getFactorizedForm());
    }

}





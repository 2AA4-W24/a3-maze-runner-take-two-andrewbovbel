package ca.mcmaster.se2aa4.mazerunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {
    Maze maze;

    @BeforeEach
    public void setUp() throws Exception {
        maze = new Maze("examples/straight.maz.txt");
    }

    @Test
    void findsPaths() {
        assertFalse(maze.isWall(new Position(0, 2)));
        assertFalse(maze.isWall(new Position(1, 2)));
        assertFalse(maze.isWall(new Position(2, 2)));
        assertFalse(maze.isWall(new Position(3, 2)));
        assertFalse(maze.isWall(new Position(4, 2)));
    }

    @Test
    void findsDimension() {
        assertEquals(maze.getSizeX(), 5);
        assertEquals(maze.getSizeY(), 5);
    }

    @Test
    void validatesPath() {
        Maze.Path path = new Maze.Path();
        for (int i = 0; i < 4; i++) {
            path.addStep('F');
        }
        assertTrue(maze.validatePath(path));
    }

}





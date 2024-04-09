package ca.mcmaster.se2aa4.mazerunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {
    Maze maze;
    Tile path;
    Tile wall;

    @BeforeEach
    public void setUp() throws Exception {
        maze = new Maze("examples/straight.maz.txt");
        path = maze.get(2,1).get();
        wall = maze.get(0,0).get();
    }

    @Test
    void get() {
        assertInstanceOf(PathTile.class, path);
        assertInstanceOf(WallTile.class, wall);
    }

    @Test
    void pathTileAdj() {
        assertEquals(Direction.LEFT , path.adj().getFirst().direction());
        assertEquals(Direction.RIGHT , path.adj().getLast().direction());
    }
    @Test
    void wallTileAdj() {
        assertNull(wall.adj());
    }

}





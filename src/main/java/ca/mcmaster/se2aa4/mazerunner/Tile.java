package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public interface Tile {
    String getType();
    public void setAdj(List<Direction.DirectionTilePair> neighbours);
    public List<Direction.DirectionTilePair> adj();
}

package ca.mcmaster.se2aa4.mazerunner.Yes;

import java.util.List;

public interface Tile {
    String getType();
    public void setAdj(List<DirectionTilePair> neighbours);
    public List<DirectionTilePair> adj();
}

package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public class WallTile implements Tile {
    @Override
    public String getType() { return "#"; }

    @Override
    public void setAdj(List<Direction.DirectionTilePair> lol) {

    }

    @Override
    public List<Direction.DirectionTilePair> adj() {
        return null;
    }
}

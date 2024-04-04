package ca.mcmaster.se2aa4.mazerunner.Yes;

import java.util.List;

public class WallTile implements Tile {
    @Override
    public String getType() { return "#"; }

    @Override
    public void setAdj(List<DirectionTilePair> lol) {

    }

    @Override
    public List<DirectionTilePair> adj() {
        return null;
    }
}

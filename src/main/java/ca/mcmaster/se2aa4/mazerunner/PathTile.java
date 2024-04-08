package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public class PathTile implements Tile {

    private List<Direction.DirectionTilePair> neighbours;
    @Override
    public String getType() { return "_"; }

    @Override
    public void setAdj(List<Direction.DirectionTilePair> neighbours) {
        this.neighbours = neighbours;
    }
    @Override
    public List<Direction.DirectionTilePair> adj() {
        return neighbours;
    }

}

package ca.mcmaster.se2aa4.mazerunner.Yes;

import ca.mcmaster.se2aa4.mazerunner.Position;

import java.util.ArrayList;
import java.util.List;

public class PathTile implements Tile{

    private List<DirectionTilePair> neighbours;
    @Override
    public String getType() { return "_"; }

    @Override
    public void setAdj(List<DirectionTilePair> neighbours) {
        this.neighbours = neighbours;
    }
    @Override
    public List<DirectionTilePair> adj() {
        return neighbours;
    }

}

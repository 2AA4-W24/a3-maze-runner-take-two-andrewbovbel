package ca.mcmaster.se2aa4.mazerunner.Yes;

import ca.mcmaster.se2aa4.mazerunner.Direction;
import ca.mcmaster.se2aa4.mazerunner.Position;

import java.util.*;

public class NewMaze {

    private final int row;
    private final int col;

    private final Tile[][] contents;

    private int currentLine = 0;

    private Position start;
    private Position end;

    public NewMaze(int row, int col) {
        this.row = row;
        this.col = col;
        this.contents = new Tile[row][col];
    }

    public Optional<Tile> get(int line, int column) {
        try {
            return Optional.of(contents[line][column]);
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    public void addLine(List<Tile> line){
        for(int i = 0; i < col; i++) {
            this.contents[currentLine][i] = line.get(i);
        }
        currentLine++;
    }





    public void setStart(Position pos) {
        this.start = pos;
    }

    public void setEnd(Position pos) {
        this.end = pos;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }
}

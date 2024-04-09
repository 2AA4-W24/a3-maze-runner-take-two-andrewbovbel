package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Maze {
    private static final Logger logger = LogManager.getLogger();
    private final List<List<Tile>> maze = new ArrayList<>();
    private final Position start;
    private final Position end;

    /**
     * Initialize a Maze from a file path.
     *
     * @param filePath File path of the maze file
     * @throws Exception If maze cannot be read, or maze has no start or end
     */
    public Maze(String filePath) throws Exception {
        logger.debug("Reading the maze from file " + filePath);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int idy = 0;
        List<Position> paths = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            List<Tile> newLine = new ArrayList<>();
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    newLine.add(new WallTile());
                } else if (line.charAt(idx) == ' ') {
                    paths.add(new Position(idx,idy));
                    newLine.add(new PathTile());
                }
            }
            idy++;
            maze.add(newLine);
        }
        start = findStart();
        end = findEnd();
        for (Position path : paths) {
                setAdjacent(path.getLine(), path.getCol());
        }
    }

    /**
     * Find start position of the maze.
     *
     * @return The start position
     * @throws Exception If no valid start position exists
     */
    private Position findStart() throws Exception {
        for (int i = 0; i < maze.size(); i++) {
            Position pos = new Position(0, i);
            if (!isWall(pos)) {
                return pos;
            }
        }
        throw new Exception("Invalid maze (no start position available)");
    }

    /**
     * Find start end of the maze.
     *
     * @return The end position
     * @throws Exception If no valid end position exists
     */
    private Position findEnd() throws Exception {
        for (int i = 0; i < maze.size(); i++) {
            Position pos = new Position(maze.getFirst().size() - 1, i);
            if (!isWall(pos)) {
                return pos;
            }
        }
        throw new Exception("Invalid maze (no end position available)");
    }

    /**
     * Check if position of Maze is a wall.
     *
     * @param pos The position to check
     * @return If position is a wall
     */
    public Boolean isWall(Position pos) {
        return maze.get(pos.y()).get(pos.x()) instanceof WallTile;
    }

    /**
     * Get start position.
     *
     * @return Start position
     */
    public Position getStart() {
        return start;
    }

    /**
     * Get end position.
     *
     * @return End position
     */
    public Position getEnd() {
        return end;
    }

    /**
     * Get horizontal (X) size of Maze.
     *
     * @return Horizontal size
     */
    public int getSizeX() {
        return this.maze.getFirst().size();
    }

    /**
     * Get vertical (Y) size of Maze.
     *
     * @return Vertical size
     */
    public int getSizeY() {
        return this.maze.size();
    }

    /**
     * Check if path is valid for Maze.
     *
     * @param path The path to valid
     * @return If path is valid
     */
    public Boolean validatePath(Path path) {
        return validatePathDir(path, getStart(), Direction.RIGHT, getEnd()) || validatePathDir(path, getEnd(), Direction.LEFT, getStart());
    }

    /**
     * Check if path is valid from starting to end position.
     *
     * @param path Path
     * @param startPos Starting position
     * @param startDir Starting direction
     * @param endPos Ending position
     * @return If path is valid
     */
    private Boolean validatePathDir(Path path, Position startPos, Direction startDir, Position endPos) {
        Position pos = startPos;
        Direction dir = startDir;
        for (char c : path.getPathSteps()) {
            switch (c) {
                case 'F' -> {
                    pos = pos.move(dir);

                    if (pos.x() >= getSizeX() || pos.y() >= getSizeY() || pos.x() < 0 || pos.y() < 0) {
                        return false;
                    }
                    if (isWall(pos)) {
                        return false;
                    }
                }
                case 'R' -> dir = dir.turnRight();
                case 'L' -> dir = dir.turnLeft();
            }
            logger.debug("Current Position: " + pos);
        }

        return pos.equals(endPos);
    }

    public static class Path {
        private final List<Character> path = new ArrayList<>();

        /**
         * Initialize an empty Path.
         */
        public Path() {
        }

        /**
         * Initialize path from a Path String.
         *
         * @param pathStr The Path String
         */
        public Path(String pathStr) {
            String expanded = expandFactorizedStringPath(pathStr);
            for (Character c : expanded.toCharArray()) {
                if (c != ' ') {
                    if (c != 'F' && c != 'L' && c != 'R') {
                        throw new IllegalArgumentException("Instruction '" + c + "' is invalid. Must be 'F', 'L', or 'R'.");
                    }
                    addStep(c);
                }
            }
        }

        /**
         * Expand a factorized string path into a canonical one.
         *
         * @param path String path
         * @return Expanded string path
         */
        public String expandFactorizedStringPath(String path) {
            StringBuilder expanded = new StringBuilder();

            for (int i = 0; i < path.length(); i++) {
                if (!Character.isDigit(path.charAt(i))) {
                    expanded.append(path.charAt(i));
                } else {
                    int count = 0;
                    int digit = 0;
                    do {
                        count *= (int) Math.pow(10, digit++);
                        count += Character.getNumericValue(path.charAt(i++));
                    } while (Character.isDigit(path.charAt(i)));

                    String step = String.valueOf(path.charAt(i)).repeat(count);
                    expanded.append(step);
                }
            }

            return expanded.toString();
        }

        /**
         * Get steps of Path.
         *
         * @return Chars of Path
         */
        public List<Character> getPathSteps() {
            return new ArrayList<>(this.path);
        }

        /**
         * Adds a step to the path.
         *
         * @param step The step that needs to be added.
         */
        public void addStep(Character step) {
            path.add(step);
        }

        /**
         * Generates the canonical form of the maze path.
         *
         * @return A string of the canonical form of a path.
         */
        public String getCanonicalForm() {
            StringBuilder sb = new StringBuilder();

            for (Character c : path) {
                if (sb.isEmpty() || sb.charAt(sb.length() - 1) == c) {
                    sb.append(c);
                } else {
                    sb.append(' ');
                    sb.append(c);
                }
            }

            return sb.toString();
        }

        /**
         * Generates the factorized form of the maze path.
         *
         * @return A string of the factorized form of a path.
         */
        public String getFactorizedForm() {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < path.size(); i++) {
                Character current = path.get(i);
                int count = 0;
                while (i < path.size() && current == path.get(i)) {
                    count++;
                    i++;
                }

                if (count == 1) {
                    sb.append(current);
                } else {
                    sb.append(count);
                    sb.append(current);
                }

                if (i != path.size()) {
                    sb.append(' ');
                }
                i--;
            }

            return sb.toString();
        }
    }

    private void setAdjacent(int line, int column) {

        if (this.get(line,column).isPresent() && this.get(line,column).get().getType().equals("_")) {

            Tile tile = this.get(line,column).get();

            List<Direction.DirectionTilePair> raw = new ArrayList<>();

            if (this.get(line, column - 1).isPresent() && this.get(line, column - 1).get().getType().equals("_")){
                raw.add(new Direction.DirectionTilePair(Direction.LEFT, this.get(line, column - 1).get()));
            }

            if (this.get(line, column + 1).isPresent() && this.get(line, column + 1).get().getType().equals("_")){
                raw.add(new Direction.DirectionTilePair(Direction.RIGHT, this.get(line, column + 1).get()));
            }

            if (this.get(line + 1, column).isPresent() && this.get(line + 1, column).get().getType().equals("_")){
                raw.add(new Direction.DirectionTilePair(Direction.DOWN, this.get(line + 1, column).get()));
            }

            if (this.get(line - 1, column).isPresent() && this.get(line - 1, column).get().getType().equals("_")){
                raw.add(new Direction.DirectionTilePair(Direction.UP, this.get(line - 1, column).get()));
            }

            tile.setAdj(raw);
        }
    }

    public Optional<Tile> get(int line, int column) {
        try {
            return Optional.of(maze.get(line).get(column));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }
}

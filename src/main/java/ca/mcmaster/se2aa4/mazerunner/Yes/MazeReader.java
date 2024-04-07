//package ca.mcmaster.se2aa4.mazerunner.Yes;
//
//import ca.mcmaster.se2aa4.mazerunner.Position;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.LinkedList;
//import java.util.List;
//
//public class MazeReader {
//
//    public NewMaze build(String filename) {
//        try {
//            BufferedReader r = new BufferedReader(new FileReader(filename));
//            String fileLine = r.readLine();
//            int row = getRows(filename);
//            int col = fileLine.length();
//
//            System.out.println(row);
//            System.out.println(col);
//            NewMaze maze = new NewMaze(row, col);
//            int index = 1;
//            List<Tile> line = buildLine(fileLine);
//            maze.addLine(line);
//            do {
//                fileLine = r.readLine();
//                line = buildLine(fileLine);
//                maze.addLine(line);
//                index ++;
//
//            }
//            while (index < row);
//
//
//            for (int i = 0; i < row; i++) {
//                for (int j = 0; j < col; j++){
//
//                        maze.setNeighbours(i,j);
//                    }
//                }
//            findStart(maze, row);
//            findEnd(maze, row);
//            return maze;
//        } catch(IOException e) {
//            throw new IllegalArgumentException(filename);
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage() + " hi");
//        }
//    }
//
//    private List<Tile> buildLine(String data) {
//        List<Tile> line = new LinkedList<>();
//        if (data != null) {
//            for (String s : data.split("")) {
//                line.add(Symbol.buildFrom(s));
//            }
//        }
//        return line;
//    }
//
//    private void findStart(NewMaze maze, int dimension) throws Exception {
//        for (int i = 0; i < dimension - 1; i++) {
//            Position pos = new Position(0, i);
//            if (maze.get(i,0).get().getType().equals("_")) {
//                maze.setStart(pos);
//                return;
//            }
//        }
//        throw new Exception("Invalid maze (no start position available)");
//    }
//
//    /**
//     * Find start end of the maze.
//     *
//     * @return The end position
//     * @throws Exception If no valid end position exists
//     */
//    private void findEnd(NewMaze maze, int dimension) throws Exception {
//        for (int i = 0; i < dimension - 1; i++) {
//            Position pos = new Position(dimension-1, i);
//            if (maze.get(i,dimension-1).get().getType().equals("_")) {
//                maze.setEnd(pos);
//                return;
//            }
//        }
//        throw new Exception("Invalid maze (no end position available)");
//    }
//
//    private int getRows(String inputFile) {
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
//            String line;
//            int row = 0;
//
//            while ((line = reader.readLine()) != null) {
//                row++;
//            }
//            reader.close();
//            return row;
//
//        } catch (IOException e) {
////            logger.error(e.getMessage());
////            return new Point(0, 0);
//            return 0;
//        }
//
//    }
//
//
//}
//

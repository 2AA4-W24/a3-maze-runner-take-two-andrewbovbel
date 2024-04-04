package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Yes.DirectionTilePair;
import ca.mcmaster.se2aa4.mazerunner.Yes.NewMaze;
import ca.mcmaster.se2aa4.mazerunner.Yes.PathTile;
import ca.mcmaster.se2aa4.mazerunner.Yes.Tile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DFS {

    private Tile e;
    private Maze.Path toReturn;


    private final Set<Tile> marked = new HashSet<>();
    public Maze.Path solve(NewMaze maze) {
        Position start = maze.getStart();
        Position end = maze.getEnd();

        System.out.println(start);
        System.out.println(end);


        Tile t = maze.get(start.y(), start.x()).get();
        e = maze.get(end.y(), end.x()).get();
        Direction facing = Direction.RIGHT;

        dfs(t, facing, new Maze.Path());

        return toReturn;




    }


        // depth first search from t
        private void dfs(Tile t, Direction facing, Maze.Path path) {

            marked.add(t);

            for (DirectionTilePair w : t.adj()) {
                if (w.tile().equals(e)) {
                    Direction to = w.direction();
                    delta(facing, to, path);
                    toReturn = path;
                    return;
                }
                if (!marked.contains(w.tile())) {
                    Direction to = w.direction();
                    facing = delta(facing, to, path);
                    dfs(w.tile(), facing, path);
                }
            }

        }

    private Direction delta (Direction facing, Direction to, Maze.Path path) {
        switch(facing) {
            case UP -> {
                switch(to) {
                    case UP -> {
                        path.addStep('F');
                        return facing;
                    }
                    case DOWN -> {
                        facing = facing.turnRight().turnRight();
                        path.addStep('R');
                        path.addStep('R');
                        path.addStep('F');
                        return facing;
                    }
                    case LEFT -> {
                        facing = facing.turnLeft();
                        path.addStep('L');
                        path.addStep('F');
                        return facing;
                    }
                    case RIGHT -> {
                        facing = facing.turnRight();
                        path.addStep('R');
                        path.addStep('F');
                        return facing;
                    }
                }
            }

            case DOWN -> {
                switch(to) {
                    case UP -> {
                        facing = facing.turnRight().turnRight();
                        path.addStep('R');
                        path.addStep('R');
                        path.addStep('F');
                        return facing;
                    }
                    case DOWN -> {
                        path.addStep('F');
                        return facing;
                    }
                    case LEFT -> {
                        facing = facing.turnRight();
                        path.addStep('R');
                        path.addStep('F');
                        return facing;

                    }
                    case RIGHT -> {
                        facing = facing.turnLeft();
                        path.addStep('L');
                        path.addStep('F');
                        return facing;

                    }
                }
            }

            case LEFT -> {
                switch(to) {
                    case UP -> {
                        facing = facing.turnRight();
                        path.addStep('R');
                        path.addStep('F');
                        return facing;

                    }
                    case DOWN -> {
                        facing = facing.turnLeft();
                        path.addStep('L');
                        path.addStep('F');
                        return facing;

                    }
                    case LEFT -> {
                        path.addStep('F');
                        return facing;

                    }
                    case RIGHT -> {
                        facing = facing.turnRight().turnRight();
                        path.addStep('R');
                        path.addStep('R');
                        path.addStep('F');
                        return facing;

                    }
                }
            }



            case RIGHT -> {
                switch(to) {
                    case UP -> {
                        facing = facing.turnLeft();
                        path.addStep('L');
                        path.addStep('F');
                        return facing;

                    }
                    case DOWN -> {
                        facing = facing.turnRight();
                        path.addStep('R');
                        path.addStep('F');
                        return facing;

                    }
                    case LEFT -> {
                        facing = facing.turnRight().turnRight();
                        path.addStep('R');
                        path.addStep('R');
                        path.addStep('F');
                        return facing;

                    }
                    case RIGHT -> {
                        path.addStep('F');
                        return facing;
                    }

                    }
            }
        }

        return facing;
    }




}

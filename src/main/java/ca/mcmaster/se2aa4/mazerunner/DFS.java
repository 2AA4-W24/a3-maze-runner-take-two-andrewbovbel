package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Yes.DirectionTilePair;
import ca.mcmaster.se2aa4.mazerunner.Yes.Tile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DFS implements MazeSolver{

    private Tile e;
    private Maze.Path toReturn;

    private ArrayList<DirectionTilePair> path = new ArrayList<>();

    private final Set<Tile> marked = new HashSet<>();
    public Maze.Path solve(Maze maze)  {
        Position start = maze.getStart();
        Position end = maze.getEnd();

//        System.out.println(start);
//        System.out.println(end);


        Tile t = maze.get(start.y(), start.x()).get();
        e = maze.get(end.y(), end.x()).get();
        Direction facing = Direction.RIGHT;

        newdfs(new DirectionTilePair(Direction.RIGHT, t));
        path.removeFirst();
        System.out.println(path);
        return getpath(path);

//        try {
//            return dfs(t, facing, new Maze.Path());
//        } catch (CloneNotSupportedException ex) {
//            throw new RuntimeException(ex);
//        }
    }

    private Maze.Path getpath(ArrayList<DirectionTilePair> path) {
        Maze.Path lol = new Maze.Path();
        Direction facing = Direction.RIGHT;
        for (DirectionTilePair to : path) {
            facing = delta(facing, to.direction(),lol);
        }

        return lol;
    }

    private void newdfs(DirectionTilePair t) {

        marked.add(t.tile());
        path.add(t);

        if (t.tile().equals(e)) {
            return;
        }

        Boolean lol = false;
        for (DirectionTilePair w : t.tile().adj()) {
            if (!marked.contains(w.tile())) {
                lol = true;
            }
        }
        if (!lol) {
            DirectionTilePair last = path.getLast();
//            System.out.println(last.tile().adj().stream());
            while (last.tile().adj().stream().allMatch(x -> marked.contains(x.tile()))) {
                path.removeLast();

                System.out.println(last.direction());
                last = path.getLast();
//                System.out.println("hjo");
//                System.out.println(last.direction());
//                last = path.removeLast();
            }

            return;
        }

        for (DirectionTilePair w : t.tile().adj()) {
            if (!marked.contains(w.tile())) {
                newdfs(w);
//                return;
                           }
        }



    }


    // depth first search from t
        private Maze.Path dfs(Tile t, Direction facing, Maze.Path path) throws CloneNotSupportedException {

            marked.add(t);
            System.out.println(t.getType());

            for (DirectionTilePair w : t.adj()) {
                if (w.tile().equals(e)) {
                    Direction to = w.direction();
                    System.out.println(to);
                    delta(facing, to, path);
                    return path;
                }
                if (!marked.contains(w.tile())) {
                    Direction to = w.direction();
                    System.out.println(to);
                    facing = delta(facing, to, path);
                    Maze.Path result = dfs(w.tile(), facing, path);
                    if (result != null) {
                        Maze.Path cloned = result.clone();
                        System.out.println(cloned);
                        return result; // Return the result if found
                    }
                }
            }

            return null;

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

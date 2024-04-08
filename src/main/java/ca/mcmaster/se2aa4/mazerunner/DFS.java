package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DFS implements MazeSolver{
    private Tile e;
    private ArrayList<Direction.DirectionTilePair> path = new ArrayList<>();
    private final Set<Tile> marked = new HashSet<>();
    public Maze.Path solve(Maze maze)  {
        Position start = maze.getStart();
        Position end = maze.getEnd();
        Tile t = maze.get(start.y(), start.x()).get();
        e = maze.get(end.y(), end.x()).get();
        Direction.DirectionTilePair first = new Direction.DirectionTilePair(Direction.RIGHT, t);
        try {
            dfs(first);
        } catch (TerminateRecursionException e) {
            path = e.getCurrentState();
        }
        path.removeFirst();
        return getpath(path);
    }

    private Maze.Path getpath(ArrayList<Direction.DirectionTilePair> nodeCollection) {
        Maze.Path path = new Maze.Path();
        Direction facing = Direction.RIGHT;
        for (Direction.DirectionTilePair to : nodeCollection) {
            facing = delta(facing, to.direction(), path);
        }
        return path;
    }

    private void dfs(Direction.DirectionTilePair t) {

        marked.add(t.tile());
        path.add(t);

        if (t.tile().equals(e)) {
            //if end node is found, terminate recursion and return path
            throw new TerminateRecursionException(path);
        }

        //backtracking algorithm to remove unnecessary dead ends
        //first check if current node has unchecked neighbours
        boolean hasNeighboursUnmarked = false;
        for (Direction.DirectionTilePair w : t.tile().adj()) {
            if (!marked.contains(w.tile())) {
                hasNeighboursUnmarked = true;
                break;
            }
        }
        //if all neighbours have been visited, i.e. is a dead end, backtrack until we hit the turning point
        if (!hasNeighboursUnmarked) {
            Direction.DirectionTilePair last = path.getLast();
            while (last.tile().adj().stream().allMatch(x -> marked.contains(x.tile()))) {
                path.removeLast();
                last = path.getLast();
            }
            return;
        }

        //continue dfs
        for (Direction.DirectionTilePair w : t.tile().adj()) {
            if (!marked.contains(w.tile())) {
                dfs(w);
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

    static class TerminateRecursionException extends RuntimeException {
        private final ArrayList<Direction.DirectionTilePair> currentState;

        public TerminateRecursionException(ArrayList<Direction.DirectionTilePair> currentState) {
            this.currentState = currentState;
        }

        public ArrayList<Direction.DirectionTilePair> getCurrentState() {
            return currentState;
        }
    }
}

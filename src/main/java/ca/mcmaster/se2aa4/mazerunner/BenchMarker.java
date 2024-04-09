package ca.mcmaster.se2aa4.mazerunner;

public class BenchMarker {
    public static void benchmarkOperation(String filePath, Solver baselineSolver, Solver solver) throws Exception {
        long mazeStart = System.nanoTime();
        Maze maze = new Maze(filePath);
        long mazeEnd = System.nanoTime();
        double durationMaze = (mazeEnd - mazeStart) / 1_000_000.0;

        long startTimeBaseline = System.nanoTime();
        baselineSolver.solveMaze(maze);
        long endTimeBaseline = System.nanoTime();
        double durationBaseline = (endTimeBaseline - startTimeBaseline) / 1_000_000.0;

        long startTime = System.nanoTime();
        solver.solveMaze(maze);
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000.0;
        System.out.println("Maze read time = " + Math.round(durationMaze * 100.0) / 100.0 + "ms");
        System.out.println("Baseline milliseconds = " + Math.round(durationBaseline * 100.0) / 100.0 + "ms");
        System.out.println("Method milliseconds = " + Math.round(duration * 100.0) / 100.0 + "ms");
        System.out.println("Speedup = " + Math.round((durationBaseline / duration) * 100.0) / 100.0 + " times") ;
    }
}

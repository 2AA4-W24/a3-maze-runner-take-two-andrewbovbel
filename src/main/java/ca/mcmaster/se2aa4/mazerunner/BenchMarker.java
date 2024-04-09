package ca.mcmaster.se2aa4.mazerunner;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

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


//        BigDecimal roundedDurationBaseline = (new BigDecimal(durationBaseline)).round(new MathContext(4, RoundingMode.HALF_UP));
//        BigDecimal roundedDuration =         (new BigDecimal(duration)).round(new MathContext(4, RoundingMode.HALF_UP));
//        BigDecimal roundedDurationMaze = (new BigDecimal(durationMaze)).round(new MathContext(4, RoundingMode.HALF_UP));
//        BigDecimal speedup = new BigDecimal(durationBaseline).divide(new BigDecimal(duration), 2, RoundingMode.HALF_UP);
//        BigDecimal divisor = BigDecimal.valueOf(1_000_000);

//        System.out.println("Maze read time = " + roundedDurationMaze.divide(divisor));
//        System.out.println("Baseline milliseconds = " + roundedDurationBaseline.divide(divisor));
//        System.out.println("Method milliseconds = " + roundedDuration.divide(divisor));
//        System.out.println("Speedup = " + speedup);
    }
}

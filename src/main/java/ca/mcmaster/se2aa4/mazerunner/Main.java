package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(getParserOptions(), args);
            process(cmd);
        } catch (Exception e) {
            System.err.println("MazeRunner failed. Reason: " + e.getMessage());
            logger.error("MazeRunner failed. Reason: " + e.getMessage());
        }
        logger.info("End of MazeRunner");
    }

    private static void process(CommandLine cmd) throws Exception {
        String filePath = cmd.getOptionValue('i');
        if (cmd.hasOption("p")) {
            Maze maze = new Maze(filePath);
            validatePath(cmd, maze);
        } else {
            String method = cmd.getOptionValue("method", "righthand");
            Solver solver = getSolver(method);
            if (cmd.hasOption("baseline")) {
                String baselineMethod = cmd.getOptionValue("baseline");
                Solver baselineSolver = getSolver(baselineMethod);
                BenchMarker.benchmarkOperation(filePath, baselineSolver, solver);
            } else {
                Maze maze = new Maze(filePath);
                Maze.Path path = solver.solveMaze(maze);
                System.out.println(path.getFactorizedForm());
            }
        }
    }

    private static void validatePath(CommandLine cmd, Maze maze) {
        String pathString = cmd.getOptionValue("p");
        Maze.Path path = new Maze.Path(pathString);
        if (maze.validatePath(path)) {
            System.out.println("correct path");
        } else {
            System.out.println("incorrect path");
        }
    }

    private static Solver getSolver(String method) throws Exception {
        return switch (method) {
            case "righthand" -> {
                logger.debug("RightHand algorithm chosen.");
                yield new RightHandSolver();
            }
            case "tremaux" -> {
                logger.debug("Tremaux algorithm chosen.");
                yield new TremauxSolver();
            }
            case "dfs" -> {
                logger.debug("DFS algorithm chosen.");
                yield new DFSSolver();
            }
            default -> throw new Exception("Maze solving method '" + method + "' not supported.");
        };
    }

    private static Options getParserOptions() {
        Options options = new Options();
        options.addOption(Option.builder("i").argName("file").hasArg().desc("File that contains maze").required().build());
        options.addOption(Option.builder("p").argName("path").hasArg().desc("Path to be verified in maze").build());
        options.addOption(Option.builder("method").argName("method").hasArg().desc("Specify which path computation algorithm will be used").build());
        options.addOption(Option.builder("baseline").argName("baseline").hasArg().desc("Specify which path computation algorithm will be used as your baseline").build());
        return options;
    }
}

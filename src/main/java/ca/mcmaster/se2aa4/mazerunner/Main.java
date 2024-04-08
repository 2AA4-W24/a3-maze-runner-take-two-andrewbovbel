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
            String filePath = cmd.getOptionValue('i');
            Maze maze = new Maze(filePath);
            processMaze(cmd, maze);
        } catch (Exception e) {
            System.err.println("MazeRunner failed. Reason: " + e.getMessage());
            logger.error("MazeRunner failed. Reason: " + e.getMessage());
        }
        logger.info("End of MazeRunner");
    }

    private static void processMaze(CommandLine cmd, Maze maze) throws Exception {
        if (cmd.hasOption("p")) {
            validatePath(cmd, maze);
        } else {
            String method = cmd.getOptionValue("method", "righthand");
            if (cmd.hasOption("baseline")) {
                String baselineMethod = cmd.getOptionValue("baseline");
                Maze.Path pathMethod = solveMaze(method, maze);
                Maze.Path pathBaseline = solveMaze(baselineMethod, maze);
            } else {
                Maze.Path path = solveMaze(method, maze);
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

    private static Maze.Path solveMaze(String method, Maze maze) throws Exception {
        Solver solver;
        switch (method) {
            case "righthand" -> {
                logger.debug("RightHand algorithm chosen.");
                solver = new RightHandSolver();
            }
            case "tremaux" -> {
                logger.debug("Tremaux algorithm chosen.");
                solver = new TremauxSolver();
            }
            case "dfs" -> {
                logger.debug("DFS algorithm chosen.");
                solver = new DFSSolver();
            }
            default -> throw new Exception("Maze solving method '" + method + "' not supported.");
        };
        return solver.solveMaze(maze);
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

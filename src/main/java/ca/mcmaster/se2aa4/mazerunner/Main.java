package ca.mcmaster.se2aa4.mazerunner;


import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;


public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("i", true, "inputfile");
        options.addOption("p", true, "path to verify");
        options.addOption("method", true, "Algorithm method (dfs or rightHand)");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            logger.error("Parsing error " + e.getMessage());
            return;
        }

        String filePath = cmd.getOptionValue("i");
        String pathToVerify = cmd.getOptionValue("p");
        String method = cmd.getOptionValue("method", "rightHand");

        if (filePath == null) {
            logger.error("Input file not specified.");
            return;
        }

        Configuration config = Configuration.readMaze(new String[]{"-i", filePath});
        if (config == null) {
            logger.error("Could not read the maze configuration.");
            return;
        }

        Maze maze = new Maze(config.getMazeConfig());
        logger.info("Chosen method: " + method);

        if ("dfs".equalsIgnoreCase(method)) {
            maze.setMazeExplorer(new DFSAlgorithm(maze.getMazeConfig()));
            logger.info("DFS Algorithm has been selected.");
        } else if ("righthand".equalsIgnoreCase(method)) {
            maze.setMazeExplorer(new RightHandAlg(maze.getMazeConfig()));
            logger.info("Right Hand Algorithm has been selected.");
        } else {
            logger.error("No valid maze exploration method specified.");
            return;
        }

        if (pathToVerify != null) {
            logger.info("Verifying provided path: " + pathToVerify);
            boolean isValid = maze.verifyPath(pathToVerify);
            System.out.println("Provided path is " + (isValid ? "valid" : "invalid"));
        }else {
            if ("dfs".equalsIgnoreCase(method)) {
                List<String> dfsPath = maze.solveMazeWithDFS();
                if (dfsPath.isEmpty()) {
                    System.out.println("DFS Path is empty.");
                } else {
                    System.out.println("DFS Path: " + dfsPath);
                    String directionPath = DFSPathConverter.convertPathToDirections(dfsPath);
                    System.out.println("Directions: " + directionPath);
                    String factorizedDFSPath = maze.factorizePath(directionPath);
                    System.out.println("Factorized DFS Path: " + factorizedDFSPath);
                }
            } else if ("rightHand".equalsIgnoreCase(method)) {
                String exploredPath = maze.exploreMaze();
                System.out.println("Explored Path: " + exploredPath);
                System.out.println("Factorized Maze Path: " + maze.factorizePath(exploredPath));
            }
        }
    }
}
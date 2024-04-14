package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        // The walking skeleton
        /*
        String pathfile = "path/to/maze.txt"
        Maze maze = readConfiguration(pathfile);
        String solution = explrmaze(Maze);
        String path = input_path;
        maze = readConfiguration(args[i + 1]);
        return factoredPath.toString();
        System.out.println("Canonical Maze Path: " + exploredPath);
        boolean isValid = maze.verifyPath(pathToVerify);
        System.out.println("Provided path is " + (isValid ? "valid" : "invalid"));
        */
        Options options = new Options();
        options.addOption("i", true, "inputfile");
        options.addOption("p", true, "path to verify");

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
        maze.setMazeExplorer(new RightHandAlg(maze.getMazeConfig()));

        if (pathToVerify != null) {
            logger.info("Verifying provided path: " + pathToVerify);
            boolean isValid = maze.verifyPath(pathToVerify);
            System.out.println("Provided path is " + (isValid ? "valid" : "invalid"));
        } else {
            String exploredPath = maze.explrmaze();
            System.out.println("Factorized Maze Path: " + maze.factorizePath(exploredPath));
        }

        logger.info("** End of MazeRunner");
    }
}

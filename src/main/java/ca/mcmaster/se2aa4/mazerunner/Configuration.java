package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.FileReader;

public class Configuration {

    private static final Logger logger = LogManager.getLogger(Configuration.class);
    private char[][] maze;

    public Configuration(char[][] maze) {
        this.maze = maze;
    }

    public char[][] getMazeConfig() {
        for(int i=0; i<maze.length;i++) {
            for (int j=0; j<maze[0].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
        return this.maze;
    }

    public static Configuration readMaze(String[] args) {
        Options options = new Options();
        options.addOption("i", true, "inputfile");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            logger.error("Parsing error " + e.getMessage());
            return null;
        }
        String file = cmd.getOptionValue("i");
        if (file == null) {
            logger.error("Input file not specified.");
            return null;
        }
        try {
            logger.info("**** Reading the maze from file " + file);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder mazeBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                mazeBuilder.append(line).append(System.lineSeparator());
            }
            reader.close();
            String mazeString = mazeBuilder.toString().trim();
            String[] mazeRows = mazeString.split(System.lineSeparator());
            char[][] maze = new char[mazeRows.length][];
            for (int i = 0; i < mazeRows.length; i++) {
                maze[i] = mazeRows[i].toCharArray();
            }
            return new Configuration(maze);
        } catch (Exception e) {
            logger.error("An error has occurred while reading the maze", e);
        }
        return null;
    }
}

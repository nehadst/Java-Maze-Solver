package ca.mcmaster.se2aa4.mazerunner;
import java.util.List;

public class DFSAlgorithm implements MazeExplorer {
    private char[][] maze;
    
    public DFSAlgorithm(char[][] maze) {
        this.maze = maze;
    }

    public String explrmaze(Maze maze) {
        List<String> path = maze.solveMazeWithDFS();
        return String.join(", ", path);
    }
}
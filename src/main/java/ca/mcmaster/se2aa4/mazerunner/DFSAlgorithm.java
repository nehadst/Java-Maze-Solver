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

    public void dfs(Node node, List<String> path) {
        if (node == null || node.isVisited()) {
            return;
        }
        node.visit();
        path.add("(" + node.getX() + ", " + node.getY() + ")");
    
        for (Edge edge : node.getEdges()) {
            Node nextNode = edge.getEnd();
            if (!nextNode.isVisited()) {
                dfs(nextNode, path);
            }
        }
    }
}
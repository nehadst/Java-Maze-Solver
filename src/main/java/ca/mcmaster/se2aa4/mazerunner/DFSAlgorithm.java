package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;
import java.util.ArrayList;

public class DFSAlgorithm implements MazeExplorer {
    private char[][] maze;
    private boolean[][] visited;

    public DFSAlgorithm(char[][] maze) {
        this.maze = maze;
        this.visited = new boolean[maze.length][maze[0].length];
    }

    @Override
    public String exploreMaze(Maze maze) {
        List<String> path = new ArrayList<>();
        for (int i = 0; i < visited.length; i++) {
            java.util.Arrays.fill(visited[i], false);
        }
        dfs(maze.getX(), maze.getY(), path);
        return String.join(", ", path);
    }

    public void dfs(int x, int y, List<String> path) {
        if (x < 0 || y < 0 || x >= maze.length || y >= maze[0].length || visited[x][y] || maze[x][y] == '#') {
            return;
        }
        visited[x][y] = true;
        path.add("(" + x + ", " + y + ")");
        dfs(x + 1, y, path);
        dfs(x - 1, y, path);
        dfs(x, y + 1, path);
        dfs(x, y - 1, path);
    }
}

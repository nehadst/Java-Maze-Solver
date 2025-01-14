package ca.mcmaster.se2aa4.mazerunner;
import java.util.List;
import java.util.ArrayList;


public class Maze {


    private static final char PATH = ' ';
    private char[][] maze;
    private int x, y;
    private Direction dir;
    private MazeExplorer maze_explorer;
    private Node[][] nodes;
    private MazeGraphBuilder builder;
    private MazeExplorer explorer;

    public void setExplorationStrategy(MazeExplorer explorer) {
        this.explorer = explorer;
    }
    public Node[][] getNodes() {
        return nodes;
    }
    
    public Maze(char[][] maze) {
        this.maze = maze;
        this.nodes = new Node[maze.length][maze[0].length];
        this.builder = new MazeGraphBuilder(this, nodes, maze);
        findStartingPosition();
        builder.initializeGraph();
        this.dir = new Direction(Direction.DirectionEnum.RIGHT);
        if (nodes[this.x][this.y] == null) {
            System.out.println("No start node at the supposed start position.");
            return;
        }
    }
    private void findStartingPosition() {
        for (int i = 0; i < getLength(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                if (maze[i][j] == PATH) {
                    this.x = i;
                    this.y = j;
                    return;
                }
            }
        }
    }
    
    public char[][] getMazeConfig() {
        return this.maze;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setMazeExplorer(MazeExplorer maze_explorer) {
        this.maze_explorer = maze_explorer;
    }

    public String exploreMaze() {
        return this.maze_explorer.exploreMaze(this);
    }

    public int getLength(){
        return maze.length;
    }

    public char getCell(int r, int c){
        return maze[r][c];
    }

    public int getWidth(){
        return maze[0].length;
    }

    public String factorizePath(String pathString) {
        Path path = new Path(pathString, dir.getDirection(), this);
        return path.factorizePath(pathString);
    }

    public boolean verifyPath(String pathString) {
    Path path = new Path(pathString, dir.getDirection(), this);
    return path.verifyPath();
    }

    
    
    public boolean canMove(int localX, int localY, Direction.DirectionEnum localDir) {
        switch (localDir) {
            case UP: return localX > 0 && maze[localX - 1][localY] == PATH;
            case RIGHT: return localY < maze[0].length - 1 && maze[localX][localY + 1] == PATH;
            case DOWN: return localX < maze.length - 1 && maze[localX + 1][localY] == PATH;
            case LEFT: return localY > 0 && maze[localX][localY - 1] == PATH;
            default: return false;
        }
    }
    public List<String> solveMazeWithDFS() {
        Node startNode = nodes[x][y];
        if (startNode == null) {
            System.out.println("Start node is null.");
            return new ArrayList<>();
        }
        List<String> path = new ArrayList<>();
        DFSAlgorithm dfsAlgorithm = new DFSAlgorithm(maze);
        dfsAlgorithm.dfs(startNode.getX(), startNode.getY(), path);
        return path;
    }
}


package ca.mcmaster.se2aa4.mazerunner;
import java.util.List;
import java.util.ArrayList;


public class Maze {

    private static final char WALL = '#';
    private static final char PATH = ' ';
    private char[][] maze;
    private int x, y;
    private Direction dir;
    private MazeExplorer maze_explorer;
    private Node[][] nodes;
    private Node endNode;

    private void initializeGraph() {
        nodes = new Node[getLength()][getWidth()];
        for (int i = 0; i < getLength(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                if (maze[i][j] == PATH) {
                    if (nodes[i][j] == null) {
                        nodes[i][j] = new Node(i, j);
                    }
                    linkNodes(i, j);
                }
            }
        }
    }

    private void linkNodes(int i, int j) {
        if (i > 0 && maze[i - 1][j] == PATH) {
            if (nodes[i-1][j] == null) nodes[i-1][j] = new Node(i-1, j);
            nodes[i][j].addEdge(new Edge(nodes[i][j], nodes[i-1][j]));
        }
        if (i < getLength() - 1 && maze[i + 1][j] == PATH) {
            if (nodes[i+1][j] == null) nodes[i+1][j] = new Node(i+1, j);
            nodes[i][j].addEdge(new Edge(nodes[i][j], nodes[i+1][j]));
        }
        if (j > 0 && maze[i][j - 1] == PATH) {
            if (nodes[i][j-1] == null) nodes[i][j-1] = new Node(i, j-1);
            nodes[i][j].addEdge(new Edge(nodes[i][j], nodes[i][j-1]));
        }
        if (j < getWidth() - 1 && maze[i][j + 1] == PATH) {
            if (nodes[i][j+1] == null) nodes[i][j+1] = new Node(i, j+1);
            nodes[i][j].addEdge(new Edge(nodes[i][j], nodes[i][j+1]));
        }
    }

    public Maze(char[][] maze) {
        this.maze = maze;
        initializeGraph();  // Ensures all nodes and edges are created
        this.x = 1; // Manually set to a known open path if auto-detection fails
        this.y = 1;
        this.dir = new Direction(Direction.DirectionEnum.RIGHT);
        findStartingPosition();
        initializeGraph();
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

    public String explrmaze() {
        return maze_explorer.explrmaze(this);
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
        List<String> path = new ArrayList<>();
        dfs(startNode, path);
        return path;
    }
    
    private void dfs(Node node, List<String> path) {
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


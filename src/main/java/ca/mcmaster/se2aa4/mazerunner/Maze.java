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




    public Maze(char[][] maze) {
        this.maze = maze;
        this.nodes = new Node[maze.length][maze[0].length];
        this.builder = new MazeGraphBuilder(this, nodes, maze);
        this.x = 1;
        this.y = 1;
        this.dir = new Direction(Direction.DirectionEnum.RIGHT);
        findStartingPosition();
        builder.initializeGraph();
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
        DFSAlgorithm dfsAlgorithm = new DFSAlgorithm(maze);
        dfsAlgorithm.dfs(startNode, path);
        return path;
    }

}


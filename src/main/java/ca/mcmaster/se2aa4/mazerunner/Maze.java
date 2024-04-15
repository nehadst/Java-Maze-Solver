package ca.mcmaster.se2aa4.mazerunner;

public class Maze {

    private static final char WALL = '#';
    private static final char PATH = ' ';
    private char[][] maze;
    private int x, y;
    private Direction dir;
    private MazeExplorer maze_explorer;


    public Maze(char[][] maze) {
        this.maze = maze;
        for (int i = 0; i < maze.length; i++) {
            if (maze[i][0] == PATH) {
                x = i;
                y = 0;
                break;
            }
        }
        this.dir = new Direction(Direction.DirectionEnum.RIGHT);
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
    }

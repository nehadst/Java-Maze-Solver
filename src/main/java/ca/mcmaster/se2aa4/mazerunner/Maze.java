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

    public String factorizePath(String path) {
        StringBuilder factoredPath = new StringBuilder();
        int i = 0;
        while (i < path.length()) {
            char action = path.charAt(i);
            int count = 1;
            i++;
  
            if (i < path.length() && (path.charAt(i) == 'R' || path.charAt(i) == 'L')) {
                char rotation = path.charAt(i);
                i++;
                while (i + 1 < path.length() && path.charAt(i) == action && path.charAt(i + 1) == rotation) {
                    count++;
                    i += 2;
                }
                if (count > 1) factoredPath.append(count);
                factoredPath.append(action).append(rotation);
            } else {
                factoredPath.append(action);
            }
        }
        return factoredPath.toString();
    }

    

        public boolean verifyPath(String pathString) {
            int localX = x;
            int localY = y;
            Direction.DirectionEnum localDir =dir.getDirection();
    
            for (char step : pathString.toCharArray()) {
                switch (step) {
                    case 'F':
                        if (!canMove(localX, localY, localDir)) {
                            return false;
                        }
                        if (localDir == Direction.DirectionEnum.UP) localX--;
                        if (localDir == Direction.DirectionEnum.RIGHT) localY++;
                        if (localDir == Direction.DirectionEnum.DOWN) localX++;
                        if (localDir == Direction.DirectionEnum.LEFT) localY--;
                        break;
                    case 'R':
                        localDir = dir.turnRight();
                        break;
                    case 'L':
                        localDir = dir.turnLeft();
                        break;
                    default:
                        return false;
                }
                if (localX < 0 || localX >= maze.length || localY < 0 || localY >= maze[0].length || maze[localX][localY] == WALL) {
                    return false;
                }
            }
            return localY == maze[0].length - 1;
        }
    
        private boolean canMove(int localX, int localY, Direction.DirectionEnum localDir) {
            switch (localDir) {
                case UP: return localX > 0 && maze[localX - 1][localY] == PATH;
                case RIGHT: return localY < maze[0].length - 1 && maze[localX][localY + 1] == PATH;
                case DOWN: return localX < maze.length - 1 && maze[localX + 1][localY] == PATH;
                case LEFT: return localY > 0 && maze[localX][localY - 1] == PATH;
                default: return false;
            }
        }
    }

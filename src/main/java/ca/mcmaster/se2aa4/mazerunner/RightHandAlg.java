package ca.mcmaster.se2aa4.mazerunner;

public class RightHandAlg implements MazeExplorer {
    private int x, y;
    private static final char PATH = ' ';
    private Direction dir;
    private char[][] maze;

   
    private enum Direction {
        UP, RIGHT, DOWN, LEFT
    }

    public RightHandAlg(char[][] maze) {
        this.maze = maze;
        this.dir = Direction.RIGHT;
        for (int i = 0; i < maze.length; i++) {
            if (maze[i][0] == PATH) {
                x = i;
                y = 0;
                break;
            }
        }
    }
    


    public String exploreMaze(Maze maze) {
        StringBuilder path = new StringBuilder();
        int forwardCount = 0;

        while (y < maze.getWidth() - 1) {
            if (canMove(turnRight(dir))) {
                if (forwardCount > 0) {
                    path.append("F".repeat(forwardCount));
                    forwardCount = 0;
                }
                turnRight();
                path.append('R');
                if (canMove(dir)) { 
                    moveForward();
                    forwardCount = 1;
                }
            } else if (canMove(dir)) {
                forwardCount++;
                moveForward();
            } else {

                if (forwardCount > 0) {
                    path.append("F".repeat(forwardCount));
                    forwardCount = 0;
                }
                turnLeft();
                path.append('L');
                while (!canMove(dir)) {
                    turnLeft();
                    path.append('L');
                }
                moveForward();
                forwardCount = 1;
            }
        }
        if (forwardCount > 0) {
            path.append("F".repeat(forwardCount));
        }
    
        return path.toString();
    }
    
    private boolean canMove() {
        return canMove(dir);
    }

    private boolean canMove(Direction direction) {
        switch (direction) {
            case UP: return x > 0 && maze[x - 1][y] == PATH;
            case RIGHT: return y < maze[0].length - 1 && maze[x][y + 1] == PATH;
            case DOWN: return x < maze.length - 1 && maze[x + 1][y] == PATH;
            case LEFT: return y > 0 && maze[x][y - 1] == PATH;
            default: return false;
        }
    }

    private void moveForward() {
        switch (dir) {
            case UP: x--; break;
            case RIGHT: y++; break;
            case DOWN: x++; break;
            case LEFT: y--; break;
        }
    }

    private void turnRight() {
        dir = Direction.values()[(dir.ordinal() + 1) % 4];
    }

    private void turnLeft() {
        dir = Direction.values()[(dir.ordinal() + 3) % 4];
    }

    private Direction turnRight(Direction direction) {
        return Direction.values()[(direction.ordinal() + 1) % 4];
    }

    private Direction turnLeft(Direction direction) {
        return Direction.values()[(direction.ordinal() + 3) % 4];
    }

}
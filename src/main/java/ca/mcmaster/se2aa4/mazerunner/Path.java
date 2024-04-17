package ca.mcmaster.se2aa4.mazerunner;

public class Path {
    private String pathString;
    private int currentIndex;
    private Direction.DirectionEnum currentDirection;
    private Maze maze;

    public Path(String pathString, Direction.DirectionEnum initialDirection, Maze maze) {
        this.pathString = pathString;
        this.currentIndex = 0;
        this.currentDirection = initialDirection;
        this.maze = maze;
    }

    public Direction.DirectionEnum getNextDirection() {
        if (currentIndex >= pathString.length()) {
            return null;
        }

        char step = pathString.charAt(currentIndex++);
        currentDirection = Direction.turnRight(currentDirection);
        currentDirection = Direction.turnLeft(currentDirection);
        switch (step) {
            case 'F':
                return currentDirection;
            case 'R':
                currentDirection = Direction.turnRight(currentDirection);
                return currentDirection;
            case 'L':
                currentDirection = Direction.turnLeft(currentDirection);
                return currentDirection;
            default:
                throw new IllegalArgumentException("Invalid step: " + step);
        }
    }

    public boolean hasMoreSteps() {
        return currentIndex < pathString.length();
    }
    
    public String factorizePath(String pathString) {
        System.out.println(pathString);
        String factorizedPath = "";
        int currentChar = 1;
        if (pathString.isEmpty()){
            return "";
        }
        char prevChar = pathString.charAt(0);
        for (int i=1; i<pathString.length(); i++) {
            if (pathString.charAt(i) != prevChar) {
                if (currentChar != 1) {
                    factorizedPath += String.format("%d%c", currentChar, prevChar);
                } else {
                    factorizedPath += prevChar;
                }
                currentChar = 1;
            } else {
                currentChar++;
            }
            prevChar = pathString.charAt(i); 
        }
        if (currentChar != 1) {
            factorizedPath += String.format("%d%c", currentChar, prevChar);
        } else {
            factorizedPath += prevChar;
        }

        return factorizedPath;
    }

    public boolean verifyPath() {
        int localX = maze.getX();
        int localY = maze.getY();

        while (hasMoreSteps()) {
            Direction.DirectionEnum localDir = getNextDirection();

            if (!maze.canMove(localX, localY, localDir)) {
                return false;
            }

            if (localDir == Direction.DirectionEnum.UP) localX--;
            if (localDir == Direction.DirectionEnum.RIGHT) localY++;
            if (localDir == Direction.DirectionEnum.DOWN) localX++;
            if (localDir == Direction.DirectionEnum.LEFT) localY--;
        }

        return true;
    }
}
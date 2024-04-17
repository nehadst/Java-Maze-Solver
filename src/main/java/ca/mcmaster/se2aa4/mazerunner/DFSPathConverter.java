package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public class DFSPathConverter {
    enum Direction {
        NORTH, SOUTH, EAST, WEST
    }

    public static String convertPathToDirections(List<String> path) {
        StringBuilder directions = new StringBuilder();
        Direction currentDirection = Direction.EAST;
        
        for (int i = 1; i < path.size(); i++) {
            String[] currentPos = path.get(i - 1).replaceAll("[()]", "").split(", ");
            String[] nextPos = path.get(i).replaceAll("[()]", "").split(", ");
            int x1 = Integer.parseInt(currentPos[0]);
            int y1 = Integer.parseInt(currentPos[1]);
            int x2 = Integer.parseInt(nextPos[0]);
            int y2 = Integer.parseInt(nextPos[1]);

            if (y2 == y1 + 1) { // Moving east
                directions.append(adjustDirection(currentDirection, Direction.EAST));
            } else if (y2 == y1 - 1) { // Moving west
                directions.append(adjustDirection(currentDirection, Direction.WEST));
            } else if (x2 == x1 + 1) { // Moving south
                directions.append(adjustDirection(currentDirection, Direction.SOUTH));
            } else if (x2 == x1 - 1) { // Moving north
                directions.append(adjustDirection(currentDirection, Direction.NORTH));
            }
            currentDirection = updateDirection(currentDirection, directions.charAt(directions.length() - 1));
        }
        return directions.toString();
    }

    private static String adjustDirection(Direction current, Direction target) {
        if (current == target) {
            return "F"; // Forward
        } else if ((current == Direction.NORTH && target == Direction.EAST) ||
                   (current == Direction.EAST && target == Direction.SOUTH) ||
                   (current == Direction.SOUTH && target == Direction.WEST) ||
                   (current == Direction.WEST && target == Direction.NORTH)) {
            return "R";
        } else {
            return "L";
        }
    }

    private static Direction updateDirection(Direction current, char turn) {
        if (turn == 'F') return current;
        switch (current) {
            case NORTH:
                return (turn == 'R') ? Direction.EAST : Direction.WEST;
            case EAST:
                return (turn == 'R') ? Direction.SOUTH : Direction.NORTH;
            case SOUTH:
                return (turn == 'R') ? Direction.WEST : Direction.EAST;
            case WEST:
                return (turn == 'R') ? Direction.NORTH : Direction.SOUTH;
        }
        return current;
    }
}

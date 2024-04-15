package ca.mcmaster.se2aa4.mazerunner;

public class Direction {
    public enum DirectionEnum {
        UP, RIGHT, DOWN, LEFT
    }

    private DirectionEnum direction;

    public Direction(DirectionEnum direction) {
        this.direction = direction;
    }

    public DirectionEnum getDirection() {
        return this.direction;
    }

    public static Direction.DirectionEnum turnRight(DirectionEnum direction) {
        return DirectionEnum.values()[(direction.ordinal() + 1) % 4];
    }

    public static Direction.DirectionEnum turnLeft(DirectionEnum direction) {
        return DirectionEnum.values()[(direction.ordinal() + 3) % 4];
    }
}
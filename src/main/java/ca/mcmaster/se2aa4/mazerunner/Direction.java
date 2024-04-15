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

    public Direction.DirectionEnum turnRight() {
        return DirectionEnum.values()[(this.direction.ordinal() + 1) % 4];
    }

    public Direction.DirectionEnum turnLeft() {
        return DirectionEnum.values()[(this.direction.ordinal() + 3) % 4];
    }
}
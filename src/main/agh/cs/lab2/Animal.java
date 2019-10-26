package agh.cs.lab2;

public class Animal {
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);
    private IWorldMap map;

    Animal(IWorldMap map) {
        this.map = map;
    }

    Animal(IWorldMap map, Vector2d initialPosition) {
        this.position = initialPosition;
        this.map = map;
    }

    public String toString() {
        switch (this.direction) {
            case NORTH:
                return ("N");
            case SOUTH:
                return ("S");
            case EAST:
                return ("E");
            case WEST:
                return ("W");
            default:
                return ("");
        }
    }

    Vector2d getPosition() {
        return (this.position);
    }

    MapDirection getDirection() {
        return (this.direction);
    }

    void move(MoveDirection direction) {
        Vector2d check;
        switch (direction) {
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case LEFT:
                this.direction = this.direction.previous();
                break;
            case BACKWARD:
                check = this.position.subtract(this.direction.toUnitVector());
                if (this.map.canMoveTo(check)) {
                    this.position = check;
                }
                break;
            case FORWARD:
                check = this.position.add(this.direction.toUnitVector());
                if (this.map.canMoveTo(check)) {
                    this.position = check;
                }
                break;
            default:
                break;
        }
    }
}

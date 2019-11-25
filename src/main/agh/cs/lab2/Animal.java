package agh.cs.lab2;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractMapElement implements IMapElement {
    private MapDirection direction = MapDirection.NORTH;
    private IWorldMap map;

    Animal(IWorldMap map) {
        this(map, new Vector2d(2,2));
    }

    Animal(IWorldMap map, Vector2d initialPosition) {
        super(initialPosition);
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

    MapDirection getDirection() {
        return (this.direction);
    }

    void move(MoveDirection direction) {
        Vector2d newPosition;
        switch (direction) {
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case LEFT:
                this.direction = this.direction.previous();
                break;
            case BACKWARD:
                newPosition = this.getPosition().subtract(this.direction.toUnitVector());
                if (this.map.canMoveTo(newPosition)) {
                    Vector2d oldPosition = this.position;
                    this.position = newPosition;
                    positionChanged(oldPosition);
                }
                break;
            case FORWARD:
                newPosition = this.getPosition().add(this.direction.toUnitVector());
                if (this.map.canMoveTo(newPosition)) {
                    Vector2d oldPosition = this.position;
                    this.position = newPosition;
                    positionChanged(oldPosition);
                }
                break;
            default:
                break;
        }
    }
}

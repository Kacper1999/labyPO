package agh.cs.lab2;

import java.util.Comparator;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {
    private TreeSet<Vector2d> orderedByX;
    private TreeSet<Vector2d> orderedByY;

    MapBoundary() {
        Comparator<Vector2d> orderByX = (pos1, pos2) -> {
            if (pos1.x > pos2.x) {
                return 1;
            } else if (pos1.x == pos2.x) {
                if (pos1.y > pos2.y) {
                    return -1;
                } else if (pos1.y < pos2.y) {
                    return 1;
                }
                return 0;
            }
            return -1;
        };
        Comparator<Vector2d> orderByY = (pos1, pos2) -> {
            if (pos1.y > pos2.y) {
                return 1;
            } else if (pos1.y == pos2.y) {
                if (pos1.x > pos2.x) {
                    return -1;
                } else if (pos1.x < pos2.x) {
                    return 1;
                }
                return 0;
            }
            return -1;
        };
        this.orderedByX = new TreeSet<>(orderByX);
        this.orderedByY = new TreeSet<>(orderByY);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        orderedByY.remove(oldPosition);
        orderedByX.remove(oldPosition);
        orderedByY.add(newPosition);
        orderedByX.add(newPosition);
    }

    void add(Vector2d v) {
        orderedByX.add(v);
        orderedByY.add(v);
    }

    Vector2d getUpperRight() {
        return new Vector2d(orderedByX.last().x, orderedByY.last().y);
    }

    Vector2d getLowerLeft() {
        return new Vector2d(orderedByX.first().x, orderedByY.first().y);
    }
}

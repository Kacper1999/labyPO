package agh.cs.lab2;

public class JungleRolledMap extends AbstractWorldMap implements IWorldMap {
    final private Vector2d lowerLeft;
    final private Vector2d upperRight;
    final private int width;
    final private int height;
    final private int jungleWidth;
    final private int jungleHeight;

    JungleRolledMap(int width, int height, int jungleWidth, int jungleHeight) {
        if (width < jungleWidth || height < jungleHeight) {
            throw new IllegalArgumentException("invalid jungle width or jungle height (bigger than maps width/height)");
        }
        this.width = width;
        this.height = height;
        this.jungleWidth = jungleWidth;
        this.jungleHeight = jungleHeight;
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width - 1, height - 1);
    }

    @Override
    protected Vector2d calcUpperRightBoundary() {
        return this.upperRight;
    }

    @Override
    protected Vector2d calcLowerLeftBoundary() {
        return this.lowerLeft;
    }

    @Override
    public Vector2d newPosition(Vector2d oldPosition, Vector2d move, MoveDirection moveDirection) {
        Vector2d newPosition;
        if (moveDirection == MoveDirection.FORWARD) {
            Vector2d tmp = oldPosition.add(move);
            newPosition = new Vector2d(tmp.x % this.width, tmp.y % this.height);
        } else {
            Vector2d tmp = oldPosition.subtract(move);
            newPosition = new Vector2d(tmp.x % this.width, tmp.y % this.height);
        }
        if (this.canMoveTo(newPosition)) {
            return newPosition;
        }
        return oldPosition;
    }

}

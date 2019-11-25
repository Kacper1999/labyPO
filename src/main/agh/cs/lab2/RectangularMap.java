package agh.cs.lab2;

class RectangularMap extends AbstractWorldMap implements IWorldMap {
    private final Vector2d lowerLeftBoundary = new Vector2d(0, 0);
    private final Vector2d upperRightBoundary;
    private final int width;
    private final int height;

    RectangularMap(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        this.upperRightBoundary = new Vector2d(width - 1, height -1);
    }

    @Override
    protected Vector2d calcUpperRightBoundary() {
        return new Vector2d(this.width - 1, this.height - 1);
    }

    @Override
    protected Vector2d calcLowerLeftBoundary() {
        return new Vector2d(0, 0);
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.follows(lowerLeftBoundary) && position.precedes(upperRightBoundary)) {
            return (!isOccupied(position));
        }
        return false;
    }
}

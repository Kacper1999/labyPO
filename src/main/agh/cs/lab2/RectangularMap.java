package agh.cs.lab2;

class RectangularMap extends AbstractWorldMap implements IWorldMap {
    private final Vector2d lowerLeftBoundary;
    private final Vector2d upperRightBoundary;

    RectangularMap(int width, int height) {
        super();
        this.lowerLeftBoundary = new Vector2d(0, 0);
        this.upperRightBoundary = new Vector2d(width - 1, height -1);
    }

    @Override
    protected Vector2d calcUpperRightBoundary() {
        return this.upperRightBoundary;
    }

    @Override
    protected Vector2d calcLowerLeftBoundary() {
        return this.lowerLeftBoundary;
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.follows(lowerLeftBoundary) && position.precedes(upperRightBoundary)) {
            return (!isOccupied(position));
        }
        return false;
    }
}

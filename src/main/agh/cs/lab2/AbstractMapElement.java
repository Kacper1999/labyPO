package agh.cs.lab2;

public abstract class AbstractMapElement implements IMapElement{
    Vector2d position;

    AbstractMapElement(Vector2d position) {
        this.position = position;
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }
}

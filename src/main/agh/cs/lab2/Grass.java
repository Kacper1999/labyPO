package agh.cs.lab2;

public class Grass extends AbstractMapElement implements IMapElement {
    Grass(Vector2d position) {
        super(position);
    }

    public String toString() {
        return "*";
    }
}

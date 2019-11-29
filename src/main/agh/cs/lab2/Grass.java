package agh.cs.lab2;

public class Grass extends AbstractMapElement implements IMapElement {
    Grass(IWorldMap map, Vector2d position) {
        super(map, position);
    }

    public String toString() {
        return "*";
    }
}

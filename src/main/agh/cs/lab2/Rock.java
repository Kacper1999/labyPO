package agh.cs.lab2;

public class Rock extends AbstractMapElement implements IMapElement{
    Rock(IWorldMap map, Vector2d position) {
        super(map, position);
    }

    public String toString() {
        return "R";
    }
}

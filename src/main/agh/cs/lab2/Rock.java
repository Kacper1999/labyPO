package agh.cs.lab2;

public class Rock extends AbstractMapElement implements IMapElement{
    Rock(Vector2d position) {
        super(position);
    }

    public String toString() {
        return "R";
    }
}

package agh.cs.lab2;

import java.util.Random;

public class EvolvingAnimal extends Animal implements IMapElement {
    private int[] genes;

    EvolvingAnimal(IWorldMap map, Vector2d position, int[] genes) {
        super(map, position);
        this.genes = genes;
    }

    void move(Vector2d move) {
        this.position = this.getMap().newPosition(this.getPosition(), move, MoveDirection.FORWARD);
    }
}

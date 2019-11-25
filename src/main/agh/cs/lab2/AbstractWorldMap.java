package agh.cs.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public abstract class AbstractWorldMap implements IPositionChangeObserver, IWorldMap {
    private List<Animal> animals;
    private HashMap<Vector2d, IMapElement> map;

    AbstractWorldMap() {
        this(new ArrayList<>());
    }

    AbstractWorldMap(List<IMapElement> mapElements) {
        this.animals = new ArrayList<>();
        this.map = new HashMap<>();

        for (IMapElement mapElement : mapElements) {
            map.put(mapElement.getPosition(), mapElement);
        }
    }

    protected abstract Vector2d calcUpperRightBoundary();

    protected abstract Vector2d calcLowerLeftBoundary();


    public String toString() {
        Vector2d lowerLeftBoundary = calcLowerLeftBoundary();
        Vector2d upperRightBoundary = calcUpperRightBoundary();
        MapVisualizer temp = new MapVisualizer(this);
        return temp.draw(lowerLeftBoundary, upperRightBoundary);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        IMapElement tmp = map.get(oldPosition);
        map.remove(oldPosition);
        map.put(newPosition, tmp);
    }


    @Override
    public boolean place(IMapElement mapElement) {
        if (canMoveTo(mapElement.getPosition())) {
            if (mapElement.getClass().equals(Animal.class)) {
                animals.add((Animal) mapElement);
                ((Animal) mapElement).addObserver(this);
            }

            map.put(mapElement.getPosition(), mapElement);
            return true;
        }
        throw new IllegalArgumentException("Invalid place coordinates");
    }

    @Override
    public void run(MoveDirection[] directions) {
        for (int i = 0; i < directions.length; i++) {
            executeOrder(directions[i], i);
        }
    }

    void executeOrder(MoveDirection direction, int which_move) {
        int size = animals.size();
        Animal animal = animals.get(which_move % size);

        animal.move(direction);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return map.get(position);
    }
}

package agh.cs.lab2;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap {
    private List<Animal> animals;
    private List<IMapElement> mapElements;
    private int width;
    private int height;
    private Vector2d lowerLeftBoundary;
    private Vector2d upperRightBoundary;
    private boolean bounded;


    AbstractWorldMap() {
        this(new ArrayList<>());
    }

    AbstractWorldMap(List<IMapElement> mapElements) {
        this(mapElements, false, 0, 0);
    }

    AbstractWorldMap(int width, int height) {
        this(new ArrayList<>(), true, width, height);
    }

    private AbstractWorldMap(List<IMapElement> mapElements, boolean bounded, int width, int height) {
        this.width = width;
        this.height = height;
        this.bounded = bounded;
        this.mapElements = mapElements;
        this.animals = new ArrayList<>();
        this.lowerLeftBoundary = calcLowerLeftBoundary();
        this.upperRightBoundary = calcUpperRightBoundary();
    }

    private Vector2d calcUpperRightBoundary() {
        if (this.bounded) {
            return new Vector2d(width - 1, height - 1);
        }

        int max_x = Integer.MIN_VALUE;
        int max_y = Integer.MIN_VALUE;

        for (Animal animal : animals) {
            if (animal.getPosition().x > max_x) {
                max_x = animal.getPosition().x;
            }
            if (animal.getPosition().y > max_y) {
                max_y = animal.getPosition().y;
            }
        }

        for (IMapElement mapElement : mapElements) {
            if (mapElement.getPosition().x > max_x) {
                max_x = mapElement.getPosition().x;
            }
            if (mapElement.getPosition().y > max_y) {
                max_y = mapElement.getPosition().y;
            }
        }
        return new Vector2d(max_x, max_y);
    }

    private Vector2d calcLowerLeftBoundary() {
        if (this.bounded) {
            return new Vector2d(0, 0);
        }

        int min_x = Integer.MAX_VALUE;
        int min_y = Integer.MAX_VALUE;

        for (Animal animal : animals) {
            if (animal.getPosition().x < min_x) {
                min_x = animal.getPosition().x;
            }
            if (animal.getPosition().y < min_y) {
                min_y = animal.getPosition().y;
            }
        }

        for (IMapElement mapElement : mapElements) {
            if (mapElement.getPosition().x < min_x) {
                min_x = mapElement.getPosition().x;
            }
            if (mapElement.getPosition().y < min_y) {
                min_y = mapElement.getPosition().y;
            }
        }
        return new Vector2d(min_x, min_y);
    }


    public String toString() {
        this.lowerLeftBoundary = calcLowerLeftBoundary();
        this.upperRightBoundary = calcUpperRightBoundary();
        MapVisualizer temp = new MapVisualizer(this);
        return temp.draw(this.lowerLeftBoundary, this.upperRightBoundary);
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        if (this.bounded) {
            if (position.follows(lowerLeftBoundary) && position.precedes(upperRightBoundary)) {
                return (!isOccupied(position));
            }
            return false;
        }
        return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public void run(MoveDirection[] directions) {
        for (int i = 0; i < directions.length; i++) {
            execute_order(directions[i], i);
        }
    }

    void execute_order(MoveDirection direction, int which_move) {
        int size = animals.size();
        Animal temp = animals.get(which_move % size);

        temp.move(direction);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.getPosition().equals(position)) {
                return true;
            }
        }
        for (IMapElement mapElement : mapElements) {
            if (mapElement.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.getPosition().equals(position)) {
                return animal;
            }
        }
        for (IMapElement mapElement : mapElements) {
            if (mapElement.getPosition().equals(position)) {
                return mapElement;
            }
        }
        return null;
    }
}

package agh.cs.lab2;


import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap {
    int width;
    int height;
    Animal[][] map;
    List<Animal> animals = new ArrayList<>();


    RectangularMap(int width, int height) {
        this.width = width;
        this. height = height;
         map = new Animal[width + 1][height + 1];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = null;
            }
        }
    }

    public String toString() {
        MapVisualizer temp = new MapVisualizer(this);
        return temp.draw(new Vector2d(0, 0), new Vector2d(width - 1, height - 1));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Vector2d origin = new Vector2d(0, 0);
        Vector2d boundary = new Vector2d(this.width - 1, this.height - 1);

        return (position.follows(origin) && position.precedes(boundary));
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animals.add(animal);
            this.map[animal.getPosition().x][animal.getPosition().y] = animal;
            return true;
        }
        return false;
    }

    @Override
    public void run(MoveDirection[] directions) {
        for (int i = 0; i < directions.length; i++) {
            int size  = animals.size();
            Animal temp = animals.get(i % size);

            map[temp.getPosition().x][temp.getPosition().x] = null;
            animals.get(i % size).move(directions[i]);
            map[temp.getPosition().x][temp.getPosition().x] = temp;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return (map[position.x][position.y] != null);
    }

    @Override
    public Object objectAt(Vector2d position) {
        return map[position.x][position.y];
    }
}

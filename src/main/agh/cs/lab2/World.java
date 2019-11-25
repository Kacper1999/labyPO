package agh.cs.lab2;

import java.util.ArrayList;
import java.util.List;

public class World {
    public static void main(String[] args) {
        try {
            /*int width = 5;
            int height = 5;
            Vector2d initialPosition = new Vector2d(2, 2);

            RectangularMap map = new RectangularMap(width, height);

            Animal testAnimal = new Animal(map, initialPosition);
            map.place(testAnimal);*/

            List<IMapElement> rocks = new ArrayList<>();
            rocks.add(new Rock(new Vector2d(-4, -4)));
            rocks.add(new Rock(new Vector2d(7, 7)));
            rocks.add(new Rock(new Vector2d(3, 6)));
            rocks.add(new Rock(new Vector2d(2, 0)));


            MoveDirection[] directions = new OptionsParser().parse(args);
            UnboundedMap map = new UnboundedMap(rocks);
            
            map.place(new Animal(map));
            map.place(new Animal(map, new Vector2d(3, 4)));

            new AnimateAnimalMoves(map, directions);


        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

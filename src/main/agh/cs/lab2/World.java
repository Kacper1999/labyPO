package agh.cs.lab2;

public class World {
    public static void main(String[] args) {
        /*int width = 5;
        int height = 5;
        Vector2d initialPosition = new Vector2d(2, 2);

        RectangularMap map = new RectangularMap(width, height);

        Animal testAnimal = new Animal(map, initialPosition);
        map.place(testAnimal);*/


        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        map.place(new Animal(map));
        map.place(new Animal(map, new Vector2d(3,4)));
        map.run(directions);

        System.out.println(map);

    }
}

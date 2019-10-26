package agh.cs.lab2;

public class World {
    public static void main(String[] args) {
        int width = 5;
        int height = 5;
        Vector2d initialPosition = new Vector2d(2, 2);

        RectangularMap map = new RectangularMap(width, height);

        Animal testAnimal = new Animal(map, initialPosition);
        map.place(testAnimal);

        System.out.println(map);

        String testString = "rfff";
        int len = testString.length();
        OptionParser op = new OptionParser();
        MoveDirection[] test = op.parse(testString);

        map.run(test);
    }
}

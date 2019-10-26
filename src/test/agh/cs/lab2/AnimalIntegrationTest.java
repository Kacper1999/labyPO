package agh.cs.lab2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnimalIntegrationTest {
    // Default parameters for testing (def stands for default not define in this case)
    private int defWidth = 5;
    private int defHeight = 5;
    private Vector2d defInitialPosition = new Vector2d(2, 2);
    private RectangularMap defMap = new RectangularMap(defWidth, defHeight);
    private Animal defTestAnimal = new Animal(defMap, defInitialPosition);

    private OptionsParser op = new OptionsParser();


    @Test
    public void directionTest() {

        defTestAnimal.move(MoveDirection.RIGHT);
        defTestAnimal.move(MoveDirection.RIGHT);

        assertEquals(MapDirection.SOUTH, defTestAnimal.getDirection());

        defTestAnimal.move(MoveDirection.LEFT);
        defTestAnimal.move(MoveDirection.RIGHT);

        assertEquals(MapDirection.SOUTH, defTestAnimal.getDirection());

        defTestAnimal.move(MoveDirection.LEFT);
        defTestAnimal.move(MoveDirection.LEFT);
        defTestAnimal.move(MoveDirection.LEFT);

        assertEquals(MapDirection.WEST, defTestAnimal.getDirection());
    }

    @Test
    public void positionTest() {
        defTestAnimal.move(MoveDirection.FORWARD);
        defTestAnimal.move(MoveDirection.FORWARD);
        defTestAnimal.move(MoveDirection.FORWARD);

        assertEquals(new Vector2d(2, 4), defTestAnimal.getPosition());

        defTestAnimal.move(MoveDirection.LEFT);
        defTestAnimal.move(MoveDirection.BACKWARD);
        defTestAnimal.move(MoveDirection.BACKWARD);
        defTestAnimal.move(MoveDirection.BACKWARD);

        assertEquals(new Vector2d(4, 4), defTestAnimal.getPosition());

        defTestAnimal.move(MoveDirection.LEFT);
        defTestAnimal.move(MoveDirection.FORWARD);
        defTestAnimal.move(MoveDirection.FORWARD);
        defTestAnimal.move(MoveDirection.RIGHT);
        defTestAnimal.move(MoveDirection.FORWARD);

        assertEquals(new Vector2d(3, 2), defTestAnimal.getPosition());

        defTestAnimal.move(MoveDirection.FORWARD);
        defTestAnimal.move(MoveDirection.FORWARD);
        defTestAnimal.move(MoveDirection.FORWARD);
        defTestAnimal.move(MoveDirection.FORWARD);
        defTestAnimal.move(MoveDirection.FORWARD);

        assertEquals(new Vector2d(0, 2), defTestAnimal.getPosition());

        defTestAnimal.move(MoveDirection.RIGHT);
        defTestAnimal.move(MoveDirection.RIGHT);
        defTestAnimal.move(MoveDirection.LEFT);
        defTestAnimal.move(MoveDirection.BACKWARD);
        defTestAnimal.move(MoveDirection.BACKWARD);
        defTestAnimal.move(MoveDirection.BACKWARD);
        defTestAnimal.move(MoveDirection.BACKWARD);

        assertEquals(new Vector2d(0, 0), defTestAnimal.getPosition());
    }

    @Test
    public void optionsParserTest() {
        defMap.place(defTestAnimal);

        String[] testString1 = {"f", "f", "f"};
        defMap.run(op.parse(testString1));
        assertEquals(new Vector2d(2, 4), defTestAnimal.getPosition());

        String[] testString2 = {"l", "b", "b", "b"};
        defMap.run(op.parse(testString2));

        assertEquals(new Vector2d(4, 4), defTestAnimal.getPosition());

        String[] testString3 = {"l", "f", "f", "r", "f"};
        defMap.run(op.parse(testString3));

        assertEquals(new Vector2d(3, 2), defTestAnimal.getPosition());

        String[] testString4 = {"f", "f", "f", "f", "f"};
        defMap.run(op.parse(testString4));

        assertEquals(new Vector2d(0, 2), defTestAnimal.getPosition());

        String[] testString5 = {"r", "r", "l", "b", "b", "b", "b"};
        defMap.run(op.parse(testString5));

        assertEquals(new Vector2d(0, 0), defTestAnimal.getPosition());
    }

    @Test
    public void rectangularMapTest() {
        defMap.place(defTestAnimal);

        // Defining new animal that will be on a default map simultaneously with default animal
        Vector2d defMapInitialPosition_2 = new Vector2d(3, 4);
        Animal defMapAnimal_2 = new Animal(defMap, defMapInitialPosition_2);
        defMap.place(defMapAnimal_2);

        // Two tests on a default map
        String[] defMapTestString_1 = {"f", "l", "f", "f", "f", "f"};
        defMap.run(op.parse(defMapTestString_1));
        assertEquals(new Vector2d(2, 4), defTestAnimal.getPosition());
        assertEquals(MapDirection.NORTH, defTestAnimal.getDirection());
        assertEquals(new Vector2d(3, 4), defMapAnimal_2.getPosition());
        assertEquals(MapDirection.WEST, defMapAnimal_2.getDirection());


        String[] defMapTestString_2 = {"b", "l", "b", "f", "b", "f", "b", "f", "b", "r", "b", "f", "f", "f"};
        defMap.run(op.parse((defMapTestString_2)));
        assertEquals(new Vector2d(2, 0), defTestAnimal.getPosition());
        assertEquals(MapDirection.NORTH, defTestAnimal.getDirection());
        assertEquals(new Vector2d(1, 1), defMapAnimal_2.getPosition());
        assertEquals(MapDirection.WEST, defMapAnimal_2.getDirection());


        // Two tests on a new map
        int width_1 = 10;
        int height_1 = 5;
        RectangularMap map = new RectangularMap(width_1, height_1);

        Vector2d initialPosition_1 = new Vector2d(3, 4);
        Animal testAnimal_1 = new Animal(map, initialPosition_1);
        Animal testAnimal_2 = new Animal(map); // initial position = [2, 2]
        map.place(testAnimal_1);
        map.place(testAnimal_2);


        String[] testString_1 = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        map.run(op.parse(testString_1));
        assertEquals(new Vector2d(4, 0), testAnimal_1.getPosition());
        assertEquals(MapDirection.SOUTH, testAnimal_1.getDirection());
        assertEquals(new Vector2d(1, 4), testAnimal_2.getPosition());
        assertEquals(MapDirection.NORTH, testAnimal_2.getDirection());

        String[] testString_2 = {"b", "b", "b", "b", "r", "l", "f", "b", "f", "f"};
        map.run(op.parse(testString_2));
        assertEquals(new Vector2d(3, 2), testAnimal_1.getPosition());
        assertEquals(MapDirection.WEST, testAnimal_1.getDirection());
        assertEquals(new Vector2d(1, 2), testAnimal_2.getPosition());
        assertEquals(MapDirection.WEST, testAnimal_2.getDirection());
    }
}

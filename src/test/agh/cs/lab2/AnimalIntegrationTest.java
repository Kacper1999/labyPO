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
        defTestAnimal.rotate(Rotation.ROTATE0);
        assertEquals(MapDirection.NORTH, defTestAnimal.getDirection());

        defTestAnimal.rotate(Rotation.ROTATE45);
        assertEquals(MapDirection.NORTHEAST, defTestAnimal.getDirection());

        defTestAnimal.rotate(Rotation.ROTATE90);
        assertEquals(MapDirection.SOUTHEAST, defTestAnimal.getDirection());

        defTestAnimal.rotate(Rotation.ROTATE135);
        assertEquals(MapDirection.WEST, defTestAnimal.getDirection());

        defTestAnimal.rotate(Rotation.ROTATE180);
        assertEquals(MapDirection.EAST, defTestAnimal.getDirection());

        defTestAnimal.rotate(Rotation.ROTATE225);
        assertEquals(MapDirection.NORTHWEST, defTestAnimal.getDirection());

        defTestAnimal.rotate(Rotation.ROTATE270);
        assertEquals(MapDirection.SOUTHWEST, defTestAnimal.getDirection());

        defTestAnimal.rotate(Rotation.ROTATE315);
        assertEquals(MapDirection.SOUTH, defTestAnimal.getDirection());
    }

    @Test
    public void positionTest() {
        assertEquals(new Vector2d(2, 2), defTestAnimal.getPosition());

        defTestAnimal.move(MoveDirection.FORWARD);
        defTestAnimal.move(MoveDirection.FORWARD);
        defTestAnimal.move(MoveDirection.FORWARD);

        assertEquals(new Vector2d(2, 4), defTestAnimal.getPosition());

        defTestAnimal.move(MoveDirection.LEFT);
        defTestAnimal.move(MoveDirection.RIGHT);
        defTestAnimal.move(MoveDirection.RIGHT);
        defTestAnimal.move(MoveDirection.RIGHT);
        defTestAnimal.move(MoveDirection.RIGHT);

        assertEquals(new Vector2d(4, 4), defTestAnimal.getPosition());

        defTestAnimal.move(MoveDirection.LEFT);
        defTestAnimal.move(MoveDirection.RIGHT);
        defTestAnimal.move(MoveDirection.LEFT);
        defTestAnimal.move(MoveDirection.RIGHT);

        assertEquals(new Vector2d(4, 4), defTestAnimal.getPosition());

        defTestAnimal.move(MoveDirection.LEFT);
        defTestAnimal.move(MoveDirection.LEFT);
        defTestAnimal.move(MoveDirection.LEFT);
        defTestAnimal.move(MoveDirection.LEFT);
        defTestAnimal.move(MoveDirection.LEFT);
        defTestAnimal.move(MoveDirection.LEFT);

        assertEquals(new Vector2d(0, 4), defTestAnimal.getPosition());

        defTestAnimal.move(MoveDirection.BACKWARD);
        defTestAnimal.move(MoveDirection.BACKWARD);
        defTestAnimal.move(MoveDirection.BACKWARD);
        defTestAnimal.move(MoveDirection.BACKWARD);
        defTestAnimal.move(MoveDirection.BACKWARD);

        assertEquals(new Vector2d(0, 0), defTestAnimal.getPosition());
    }

    @Test
    public void optionsParserTest() {
        defMap.place(defTestAnimal);
        assertEquals(new Vector2d(2, 2), defTestAnimal.getPosition());

        String[] testString1 = {"f", "f", "f"};
        defMap.run(op.parse(testString1));
        assertEquals(new Vector2d(2, 4), defTestAnimal.getPosition());

        String[] testString2 = {"l", "r", "r", "r", "r"};
        defMap.run(op.parse(testString2));
        assertEquals(new Vector2d(4, 4), defTestAnimal.getPosition());

        String[] testString3 = {"l", "r", "l", "r"};
        defMap.run(op.parse(testString3));
        assertEquals(new Vector2d(4, 4), defTestAnimal.getPosition());

        String[] testString4 = {"l", "l", "l", "l", "l"};
        defMap.run(op.parse(testString4));
        assertEquals(new Vector2d(0, 4), defTestAnimal.getPosition());

        String[] testString5 = {"b", "b", "b", "b", "b"};
        defMap.run(op.parse(testString5));
        assertEquals(new Vector2d(0, 0), defTestAnimal.getPosition());
    }
}

package agh.cs.lab2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnimalIntegrationTest {
    private int width = 4;
    private int height = 4;
    private Vector2d initialPosition = new Vector2d(2, 2);

    private RectangularMap map = new RectangularMap(width, height);

    private Animal testAnimal = new Animal(map, initialPosition);

    @Test
    public void testDirection() {

        testAnimal.move(MoveDirection.RIGHT);
        testAnimal.move(MoveDirection.RIGHT);

        assertEquals(MapDirection.SOUTH, testAnimal.getDirection());

        testAnimal.move(MoveDirection.LEFT);
        testAnimal.move(MoveDirection.RIGHT);

        assertEquals(MapDirection.SOUTH, testAnimal.getDirection());

        testAnimal.move(MoveDirection.LEFT);
        testAnimal.move(MoveDirection.LEFT);
        testAnimal.move(MoveDirection.LEFT);

        assertEquals(MapDirection.WEST, testAnimal.getDirection());
    }

    @Test
    public void testPosition() {
        testAnimal.move(MoveDirection.FORWARD);
        testAnimal.move(MoveDirection.FORWARD);
        testAnimal.move(MoveDirection.FORWARD);

        assertEquals(new Vector2d(2, 4), testAnimal.getPosition());

        testAnimal.move(MoveDirection.LEFT);
        testAnimal.move(MoveDirection.BACKWARD);
        testAnimal.move(MoveDirection.BACKWARD);
        testAnimal.move(MoveDirection.BACKWARD);

        assertEquals(new Vector2d(4, 4), testAnimal.getPosition());

        testAnimal.move(MoveDirection.LEFT);
        testAnimal.move(MoveDirection.FORWARD);
        testAnimal.move(MoveDirection.FORWARD);
        testAnimal.move(MoveDirection.RIGHT);
        testAnimal.move(MoveDirection.FORWARD);

        assertEquals(new Vector2d(3, 2), testAnimal.getPosition());

        testAnimal.move(MoveDirection.FORWARD);
        testAnimal.move(MoveDirection.FORWARD);
        testAnimal.move(MoveDirection.FORWARD);
        testAnimal.move(MoveDirection.FORWARD);
        testAnimal.move(MoveDirection.FORWARD);

        assertEquals(new Vector2d(0, 2), testAnimal.getPosition());

        testAnimal.move(MoveDirection.RIGHT);
        testAnimal.move(MoveDirection.RIGHT);
        testAnimal.move(MoveDirection.LEFT);
        testAnimal.move(MoveDirection.BACKWARD);
        testAnimal.move(MoveDirection.BACKWARD);
        testAnimal.move(MoveDirection.BACKWARD);
        testAnimal.move(MoveDirection.BACKWARD);

        assertEquals(new Vector2d(0, 0), testAnimal.getPosition());
    }

    @Test
    public void testOptionParser() {
        OptionParser op = new OptionParser();

        String testString1 = "fff";
        testAnimal.executeOrders(op.parse(testString1));

        assertEquals(new Vector2d(2, 4), testAnimal.getPosition());

        String testString2 = "lbbb";
        testAnimal.executeOrders(op.parse(testString2));

        assertEquals(new Vector2d(4, 4), testAnimal.getPosition());

        String testString3 = "lffrf";
        testAnimal.executeOrders(op.parse(testString3));

        assertEquals(new Vector2d(3, 2), testAnimal.getPosition());

        String testString4 = "fffff";
        testAnimal.executeOrders(op.parse(testString4));

        assertEquals(new Vector2d(0, 2), testAnimal.getPosition());

        String testString5 = "rrlbbbb";
        testAnimal.executeOrders(op.parse(testString5));

        assertEquals(new Vector2d(0, 0), testAnimal.getPosition());

    }
}

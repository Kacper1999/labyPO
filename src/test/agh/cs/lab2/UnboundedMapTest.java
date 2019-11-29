package agh.cs.lab2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UnboundedMapTest {
    private List<IMapElement> rocks = new ArrayList<>();
    private Vector2d initialPosition = new Vector2d(2, 2);
    private UnboundedMap map = new UnboundedMap(rocks);
    private Animal testAnimal = new Animal(map, initialPosition);

    private OptionsParser op = new OptionsParser();



    @Test
    public void canMoveToTest() {
        map.place(new Rock(map, new Vector2d(1, 1)));
        map.place(testAnimal);
        map.place(new Animal(map, new Vector2d(3, 4)));

        assertFalse(map.canMoveTo(new Vector2d(3, 4)));
        assertFalse(map.canMoveTo(initialPosition));
        assertFalse(map.canMoveTo(new Vector2d(1, 1)));

        assertTrue(map.canMoveTo(new Vector2d(0, 0)));
    }

    @Test
    public void placeTest() {
        assertTrue(map.place(testAnimal));
        assertTrue(map.place(new Animal(map, new Vector2d(3, 4))));
    }

    @Test (expected = java.lang.IllegalArgumentException.class)
    public void placeExceptionTest() {
        map.place(new Rock(map, new Vector2d(1, 1)));

        map.place(new Animal(map, new Vector2d(1, 1)));
    }

    @Test
    public void runTest() {
        map.place(testAnimal);
        map.place(new Rock(map, new Vector2d(1, 1)));
        map.place(new Rock(map, new Vector2d(-3, -3)));

        // Defining new animal that will be on a default map simultaneously with default animal
        Vector2d defMapInitialPosition_2 = new Vector2d(3, 4);
        Animal defMapAnimal_2 = new Animal(map, defMapInitialPosition_2);
        map.place(defMapAnimal_2);

        String[] defMapTestString_1 = {"f", "l", "f", "f", "f", "f"};
        map.run(op.parse(defMapTestString_1));
        assertEquals(new Vector2d(2, 5), testAnimal.getPosition());
        assertEquals(MapDirection.NORTH, testAnimal.getDirection());
        assertEquals(new Vector2d(2, 4), defMapAnimal_2.getPosition());
        assertEquals(MapDirection.WEST, defMapAnimal_2.getDirection());


        String[] defMapTestString_2 = {"f", "f", "b", "l", "l", "f", "l", "f", "l", "f"};
        map.run(op.parse((defMapTestString_2)));
        assertEquals(new Vector2d(2, 5), testAnimal.getPosition());
        assertEquals(MapDirection.EAST, testAnimal.getDirection());
        assertEquals(new Vector2d(1, 2), defMapAnimal_2.getPosition());
        assertEquals(MapDirection.SOUTH, defMapAnimal_2.getDirection());
    }

    @Test
    public void objectAtTest() {
        Rock rock1 = new Rock(map, new Vector2d(1, 1));
        map.place(rock1);

        map.place(testAnimal);
        Animal secondAnimal = new Animal(map, new Vector2d(3, 4));
        map.place(secondAnimal);

        assertEquals(testAnimal, map.objectAt(initialPosition));
        assertEquals(secondAnimal, map.objectAt(new Vector2d(3, 4)));
        assertEquals(rock1, map.objectAt(new Vector2d(1, 1)));

        assertNull(map.objectAt(new Vector2d(1, 0)));
    }


    @Test
    public void isOccupiedTest() {
        map.place(testAnimal);
        map.place(new Rock(map, new Vector2d(1, 1)));
        map.place(new Animal(map, new Vector2d(3, 4)));

        assertTrue(map.isOccupied(initialPosition));
        assertTrue(map.isOccupied(new Vector2d(3, 4)));
        assertTrue(map.isOccupied(new Vector2d(1, 1)));

        assertFalse(map.isOccupied(new Vector2d(1, 3)));
    }


    @Test
    public void calcBoundariesTest() {
        map.place(testAnimal);
        map.place(new Rock(map, new Vector2d(1, 1)));
        assertEquals(new Vector2d(2, 2), map.calcUpperRightBoundary());
        assertEquals(new Vector2d(1, 1), map.calcLowerLeftBoundary());

        String[] TestString1 = {"b", "b"};
        map.run(op.parse(TestString1));

        assertEquals(new Vector2d(2, 1), map.calcUpperRightBoundary());
        assertEquals(new Vector2d(1, 0), map.calcLowerLeftBoundary());

        String[] TestString2 = {"l", "f", "f"};
        map.run(op.parse(TestString2));

        assertEquals(new Vector2d(1, 1), map.calcUpperRightBoundary());
        assertEquals(new Vector2d(0, 0), map.calcLowerLeftBoundary());
    }
}

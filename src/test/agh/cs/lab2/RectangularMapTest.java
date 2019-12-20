package agh.cs.lab2;

import org.junit.Test;

import static org.junit.Assert.*;

public class RectangularMapTest {
    private int defWidth = 5;
    private int defHeight = 5;
    private Vector2d defInitialPosition = new Vector2d(2, 2);
    private RectangularMap defMap = new RectangularMap(defWidth, defHeight);
    private Animal defTestAnimal = new Animal(defMap, defInitialPosition);

    private OptionsParser op = new OptionsParser();


    @Test
    public void placeTest() {
        assertTrue(defMap.place(defTestAnimal));
        assertTrue(defMap.place(new Animal(defMap, new Vector2d(3, 4))));
        // putting animal on sth throws exception and is tested below
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void placeExceptionTest() {
        defMap.place(defTestAnimal);
        defMap.place(defTestAnimal);
    }

    @Test
    public void canMoveToTest() {
        defMap.place(defTestAnimal);
        defMap.place(new Animal(defMap, new Vector2d(3, 4)));

        assertFalse(defMap.canMoveTo(new Vector2d(3, 4)));
        assertFalse(defMap.canMoveTo(defInitialPosition));
        assertTrue(defMap.canMoveTo(new Vector2d(0, 0)));
        assertTrue(defMap.canMoveTo(new Vector2d(1, 1)));
        assertFalse(defMap.canMoveTo(new Vector2d(8, 0)));
    }


    @Test
    public void runTest() {
        defMap.place(defTestAnimal);

        // Defining new animal that will be on a default map simultaneously with default animal
        Vector2d defMapInitialPosition_2 = new Vector2d(3, 4);
        Animal defMapAnimal_2 = new Animal(defMap, defMapInitialPosition_2);
        defMap.place(defMapAnimal_2);

        // Two tests on a default map
        String[] defMapTestString_1 = {"f", "f", "f", "l", "f", "l"};
        defMap.run(op.parse(defMapTestString_1));
        assertEquals(new Vector2d(2, 4), defTestAnimal.getPosition());
        assertEquals(new Vector2d(3, 4), defMapAnimal_2.getPosition());


        String[] defMapTestString_2 = {"b", "l", "b", "f", "b", "f", "b", "f", "b", "r", "b", "f", "f", "f"};
        defMap.run(op.parse((defMapTestString_2)));
        assertEquals(new Vector2d(2, 1), defTestAnimal.getPosition());
        assertEquals(new Vector2d(3, 4), defMapAnimal_2.getPosition());


        // Two tests on a new map
        int width_1 = 10;
        int height_1 = 5;
        RectangularMap map = new RectangularMap(width_1, height_1);

        Vector2d initialPosition_2 = new Vector2d(3, 4);
        Animal testAnimal_1 = new Animal(map); // initial position = [2, 2]
        Animal testAnimal_2 = new Animal(map, initialPosition_2);
        map.place(testAnimal_1);
        map.place(testAnimal_2);


        String[] testString_1 = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        map.run(op.parse(testString_1));
        assertEquals(new Vector2d(2, 4), testAnimal_1.getPosition());
        assertEquals(new Vector2d(4, 4), testAnimal_2.getPosition());

        String[] testString_2 = {"b", "b", "b", "b", "r", "l", "f", "b", "f", "f"};
        map.run(op.parse(testString_2));
        assertEquals(new Vector2d(3, 4), testAnimal_1.getPosition());
        assertEquals(new Vector2d(4, 2), testAnimal_2.getPosition());
    }

    @Test
    public void objectAtTest() {
        defMap.place(defTestAnimal);
        Animal secondAnimal = new Animal(defMap, new Vector2d(3, 4));
        defMap.place(secondAnimal);

        assertEquals(defTestAnimal, defMap.objectAt(defInitialPosition));
        assertEquals(secondAnimal, defMap.objectAt(new Vector2d(3, 4)));

        assertNull(defMap.objectAt(new Vector2d(1, 1)));
    }


    @Test
    public void isOccupiedTest() {
        defMap.place(defTestAnimal);

        defMap.place(new Animal(defMap, new Vector2d(3, 4)));

        assertTrue(defMap.isOccupied(defInitialPosition));
        assertTrue(defMap.isOccupied(new Vector2d(3, 4)));
        assertFalse(defMap.isOccupied(new Vector2d(1, 1)));
    }
}

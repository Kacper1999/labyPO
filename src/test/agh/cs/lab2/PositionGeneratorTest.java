package agh.cs.lab2;

import org.junit.Test;

import static org.junit.Assert.*;

public class PositionGeneratorTest {
    private RolledMapWithJungle smallMap = new RolledMapWithJungle(3, 3, 1, 1, 3);
    private positionGenerator jungleRPG = new positionGenerator(smallMap, smallMap.getJungleLowerLeft(),
            smallMap.getJungleUpperRight());
    private positionGenerator steppeRPG = new positionGenerator(smallMap, smallMap.calcLowerLeftBoundary(),
            smallMap.calcUpperRightBoundary(), smallMap.getJungleLowerLeft(), smallMap.getJungleUpperRight());

    @Test
    public void getPositionsTest() {
        assertEquals(new Vector2d(1, 1), jungleRPG.getPosition(0));
        smallMap.place(new Animal(smallMap, new Vector2d(1, 1)));
        assertNull(jungleRPG.getPosition(0));

        smallMap.place(new Animal(smallMap, new Vector2d(2, 2)));
        smallMap.place(new Animal(smallMap, new Vector2d(0, 0)));
        assertEquals(new Vector2d(1, 0), steppeRPG.getPosition(8));

        smallMap.place(new Animal(smallMap, new Vector2d(0, 1)));
        smallMap.place(new Animal(smallMap, new Vector2d(0, 2)));
        smallMap.place(new Animal(smallMap, new Vector2d(1, 0)));
        smallMap.place(new Animal(smallMap, new Vector2d(1, 2)));
        smallMap.place(new Animal(smallMap, new Vector2d(2, 0)));
        smallMap.place(new Animal(smallMap, new Vector2d(2, 1)));
        assertNull(steppeRPG.getPosition(2));
    }

}

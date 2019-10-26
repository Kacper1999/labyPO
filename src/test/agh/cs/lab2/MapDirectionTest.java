package agh.cs.lab2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapDirectionTest {
    @Test
    public void testNext() {
        assertEquals(MapDirection.NORTH.next(), MapDirection.EAST);
        assertEquals(MapDirection.SOUTH.next(), MapDirection.WEST);
        assertEquals(MapDirection.WEST.next(), MapDirection.NORTH);
        assertEquals(MapDirection.EAST.next(), MapDirection.SOUTH);
    }

    @Test
    public void testPrevious() {
        assertEquals(MapDirection.NORTH.previous(), MapDirection.WEST);
        assertEquals(MapDirection.SOUTH.previous(), MapDirection.EAST);
        assertEquals(MapDirection.WEST.previous(), MapDirection.SOUTH);
        assertEquals(MapDirection.EAST.previous(), MapDirection.NORTH);
    }
}

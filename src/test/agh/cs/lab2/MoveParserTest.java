package agh.cs.lab2;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MoveParserTest {
    private int[] moves1 = new int[]{1, 4, 6, 0};
    private int[] moves2 = new int[]{2, 7, 3, 2};
    private int[] moves3 = new int[]{5, 8};
    private int[] moves4 = new int[]{0, 3, -1};
    private MoveParser mp = new MoveParser();

    @Test
    public void parseMovesTest() {
        Vector2d[] myResult1 = mp.parseMoves(moves1);
        Vector2d[] myResult2 = mp.parseMoves(moves2);

        Vector2d[] result1 = new Vector2d[]{new Vector2d(1, 1), new Vector2d(0, -1),
                new Vector2d(-1, 0), new Vector2d(0, 1)};
        Vector2d[] result2 = new Vector2d[]{new Vector2d(1, 0), new Vector2d(-1, 1),
                new Vector2d(1, -1), new Vector2d(1, 0)};

        for (int i = 0; i < result1.length; i++) {
            assertEquals(myResult1[i], result1[i]);
        }
        for (int i = 0; i < result2.length; i++) {
            assertEquals(myResult2[i], result2[i]);
        }
    }

    @Test (expected = java.lang.IllegalArgumentException.class)
    public void parseMoveOverExceptionTest() {
        mp.parseMoves(moves3);
    }
    @Test (expected = java.lang.IllegalArgumentException.class)
    public void parseMoveUnderExceptionTest() {
        mp.parseMoves(moves4);
    }
}

package agh.cs.lab2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OptionParserTest {
    private OptionsParser op = new OptionsParser();

    @Test
    public void optionParserTest() {
        String[] testString = {"f", "r", "l", "b"};
        MoveDirection[] moves = op.parse(testString);
        MoveDirection[] result = {MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.BACKWARD};
        for (int i = 0; i < moves.length; i++) {
            assertEquals(result[i], moves[i]);
        }
    }
}

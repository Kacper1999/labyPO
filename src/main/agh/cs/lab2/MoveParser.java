package agh.cs.lab2;

class MoveParser {
    Vector2d[] parseMoves(int[] moves) {
        int len = moves.length;
        Vector2d[] parsedMoves = new Vector2d[len];
        for (int i = 0; i < len; i++) {
            int move = moves[i];
            if (move < 0 || move > 7) {
                throw new IllegalArgumentException("argument at position " + i + " with value " + move + " is invalid");
            }
            parsedMoves[i] = MapDirection.values()[move].toUnitVector();
            // values of "MapDirection" are stored starting from north and continues with clockwise fashion
            // that's why we can use "move" as an index
        }
        return parsedMoves;
    }
}

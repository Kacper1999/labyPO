package agh.cs.lab2;

import java.util.Arrays;

class OptionsParser {
    OptionsParser() {

    }

    MoveDirection[] parse(String[] args) {
        int len = args.length;
        MoveDirection[] result = new MoveDirection[len];
        int j = 0;
        for (String arg : args) {
            switch (arg) {
                case "f":
                    result[j++] = MoveDirection.FORWARD;
                    break;
                case "b":
                    result[j++] = MoveDirection.BACKWARD;
                    break;
                case "r":
                    result[j++] = MoveDirection.RIGHT;
                    break;
                case "l":
                    result[j++] = MoveDirection.LEFT;
                    break;
                default:
                    break;
            }
        }
        return(Arrays.copyOfRange(result, 0, j));
    }
}

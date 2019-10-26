package agh.cs.lab2;

import java.util.Arrays;

public class OptionParser {
    OptionParser() {

    }

    MoveDirection[] parse(String args) {
        MoveDirection result[] = new MoveDirection[args.length()];
        int j = 0;
        for (int i = 0; i < args.length(); i++) {
            switch (args.charAt(i)) {
                case 'f':
                    result[j++] = MoveDirection.FORWARD;
                    break;
                case 'b':
                    result[j++] = MoveDirection.BACKWARD;
                    break;
                case 'r':
                    result[j++] = MoveDirection.RIGHT;
                    break;
                case 'l':
                    result[j++] = MoveDirection.LEFT;
                    break;
                default:
                    break;
            }
        }
        return(Arrays.copyOfRange(result, 0, j));
    }
}

public class GameMethods {
    public static boolean point(int board) {
        // Check for point in | direction
        if ((board & board>>1 & board<<1) > 0)
            return true;
        // Check for points in - direction
        if ((board & board>>4 & board<<4) > 0)
            return true;
        // Check for points in / direction
        if ((board & board>>5 & board<<5) > 0)
            return true;
        // Check for points in \ direction
        return (board & board >> 3 & board << 3) > 0;
    }

    public static boolean spaceOccupied(int board, int space) {
        return (board>>space & 1) == 1;
    }

    public static int makeMove(int board, int move) {
        return board | (1<<move);
    }

    public static int spacesOnBoard(int board) {
        int spaces = 9;
        for (int shift = 0; shift < 12; shift++) {
            if (shift % 4 != 3 && (board>>shift & 1) == 1) {
                spaces--;
            }
        }
        return spaces;
    }
}

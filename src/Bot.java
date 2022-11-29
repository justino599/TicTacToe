import java.util.Arrays;

public class Bot {
    int bestMove(int board, int playerBoard) {
        // Get minimax scores for all valid moves
        int[] spaceScores = new int[12];
        boolean[] validMove = new boolean[12];
        Arrays.fill(validMove, true);
        for (int move = 0; move < 12; move++) {
            if (move % 4 != 3 && !GameMethods.spaceOccupied(board, move)) {
                spaceScores[move] = minimax(GameMethods.makeMove(board, move), playerBoard);
            }
            else {
                validMove[move] = false;
            }
        }

        // Find max score
        int max = -9, maxIdx = 0;
        for (int move = 0; move < 12; move++) {
            if (validMove[move] && spaceScores[move] > max) {
                max = spaceScores[move];
                maxIdx = move;
            }
        }
        return maxIdx;
    }

    private int minimax(int board, int playerBoard) {
        if (GameMethods.spacesOnBoard(board) % 2 == 0) { // If the player just made a move
            if (GameMethods.point(playerBoard)) { // If the player just scored
                return -1 * (GameMethods.spacesOnBoard(board) + 1);
            }
            else if (GameMethods.spacesOnBoard(board) == 0) { // If the board is full and there are no points
                return 0;
            }
            else { // Bot makes a move
                // Get minimax scores for all valid moves
                int[] spaceScores = new int[12];
                boolean[] validMove = new boolean[12];
                Arrays.fill(validMove, true);
                for (int move = 0; move < 12; move++) {
                    if (move % 4 != 3 && !GameMethods.spaceOccupied(board, move)) {
                        spaceScores[move] = minimax(GameMethods.makeMove(board, move), playerBoard);
                    }
                    else {
                        validMove[move] = false;
                    }
                }

                // Find max score
                int max = -9;
                for (int move = 0; move < 12; move++) {
                    if (validMove[move] && spaceScores[move] > max) {
                        max = spaceScores[move];
                    }
                }

                return max;
            }
        }
        else { // If the bot just made a move
            if (GameMethods.point(board ^ playerBoard)) { // If the bot just scored
                return GameMethods.spacesOnBoard(board);
            }
            else { // Player makes move
                // Get minimax scores for all valid moves
                int[] spaceScores = new int[12];
                boolean[] validMove = new boolean[12];
                Arrays.fill(validMove, true);
                for (int move = 0; move < 12; move++) {
                    if (move % 4 != 3 && !GameMethods.spaceOccupied(board, move)) {
                        spaceScores[move] = minimax(GameMethods.makeMove(board, move), GameMethods.makeMove(playerBoard, move));
                    }
                    else {
                        validMove[move] = false;

                    }
                }

                // Find minimum score
                int min = 9;
                for (int move = 0; move < 12; move++) {
                    if (validMove[move] && spaceScores[move] < min) {
                        min = spaceScores[move];
                    }
                }

                return min;
            }
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

public class Game {
    static final int WIDTH = 500;
    static final int HEIGHT = 500;
    static boolean allowedClick = false;
    static int playerBoard;
    static int board;
    static boolean newGame = true;
    /*
    3  7  11
    2  6  10
    1  5  9
    0  4  8
     */

    public static void main(String[] args) throws InterruptedException {
        JFrame f = new JFrame("Tic Tac Toe");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        TicTacToe t = new TicTacToe(board, playerBoard, WIDTH, HEIGHT);
        t.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        t.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }
            public void mousePressed(MouseEvent e) {

            }
            public void mouseReleased(MouseEvent e) {
                if (allowedClick) {
                    int move = e.getX() / (WIDTH / 3) * 4 + 2 - e.getY() / (HEIGHT / 3);
                    if (!GameMethods.spaceOccupied(board, move)) {
                        board = GameMethods.makeMove(board, move);
                        playerBoard = GameMethods.makeMove(playerBoard, move);
                    }
                }
                else if (!newGame) {
                    newGame = true;
                }
            }
            public void mouseEntered(MouseEvent e) {

            }
            public void mouseExited(MouseEvent e) {

            }
        });
        f.add(t);
        f.pack();
        f.setResizable(false);
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation(screenDim.width/2 - WIDTH/2, screenDim.height/2 - HEIGHT*5/8);

        f.setVisible(true);

        // Main Game Loop
        Bot bobTheGreat = new Bot(); // A legend is born!
        while (true) {
            board = 0;
            playerBoard = 0;
            t.winner = 0;
            t.board = board;
            t.playerBoard = playerBoard;
            t.repaint();
            while (true) {
                // Wait for player to make a move
                int old_board = board;
                allowedClick = true;
                while (board == old_board) {
                    TimeUnit.MILLISECONDS.sleep(1);
                }
                allowedClick = false;
                t.board = board;
                t.playerBoard = playerBoard;
                t.repaint();
                if (GameMethods.point(playerBoard)) { // You won!
                    TimeUnit.MILLISECONDS.sleep(1500);
                    t.winner = 1;
                    t.repaint();
                    break;
                }
                if (GameMethods.spacesOnBoard(board) == 0) { // Tie!
                    TimeUnit.MILLISECONDS.sleep(1500);
                    t.winner = 3;
                    t.repaint();
                    break;
                }

                // Bot makes a move
                board = GameMethods.makeMove(board, bobTheGreat.bestMove(board,playerBoard));
                t.board = board;
                t.repaint();
                if (GameMethods.point(board ^ playerBoard)) { // You Lost
                    TimeUnit.MILLISECONDS.sleep(1500);
                    t.winner = 2;
                    t.repaint();
                    break;
                }
            }
            // Wait for new click to start new game
            newGame = false;
            while (!newGame) {
                TimeUnit.MILLISECONDS.sleep(1);
            }
        }
    }
}

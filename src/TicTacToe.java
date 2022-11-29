import java.awt.*;

public class TicTacToe extends Canvas {
    int board, playerBoard, width, height, winner = 0;

    TicTacToe(int board, int playerBoard, int width, int height) {
        this.board = board;
        this.width = width;
        this.height = height;
        this.playerBoard = playerBoard;
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        if (winner == 1) {
            g.setFont(Font.decode("Arial-42"));
            g.drawString("You won!", width/2 - g.getFontMetrics().stringWidth("You Won")/2, height*3/8);
            g.setFont(Font.decode("Arial-26"));
            g.drawString("Click anywhere to start a new game", width/2 - g.getFontMetrics().stringWidth("Click anywhere to start a new game")/2, height/2);
        }
        else if (winner == 2) {
            g.setFont(Font.decode("Arial-42"));
            g.drawString("You Lost", width/2 - g.getFontMetrics().stringWidth("You Lost")/2, height*3/8);
            g.setFont(Font.decode("Arial-26"));
            g.drawString("Click anywhere to start a new game", width/2 - g.getFontMetrics().stringWidth("Click anywhere to start a new game")/2, height/2);
        }
        else if (winner == 3) {
            g.setFont(Font.decode("Arial-42"));
            g.drawString("Tie", width/2 - g.getFontMetrics().stringWidth("Tie")/2, height*3/8);
            g.setFont(Font.decode("Arial-26"));
            g.drawString("Click anywhere to start a new game", width/2 - g.getFontMetrics().stringWidth("Click anywhere to start a new game")/2, height/2);
        }
        else {
            super.paint(g);
            // Paint Grid Lines Onto Board
            int lineWidth = 5;
            g.fillRect(width / 3 - lineWidth / 2, 0, lineWidth, height);
            g.fillRect(width * 2 / 3 - lineWidth / 2, 0, lineWidth, height);
            g.fillRect(0, height / 3, width, lineWidth);
            g.fillRect(0, height * 2 / 3, width, lineWidth);

            // Paint Xs Onto Board (Player)
            g.setFont(Font.decode("Century Gothic-230"));
            int shift;
            for (shift = 0; shift < 12; shift++) {
                if (shift % 4 != 3 && (playerBoard >> shift & 1) == 1) {
                    g.drawString("o", (width / 3) * (shift / 4) + (width / 3 - g.getFontMetrics().stringWidth("o")) / 2, height - (height / 3) * (shift % 4 + 1) + g.getFontMetrics().getHeight() / 2);
                }
            }

            // Paint Os Onto Board (Bot)
            for (shift = 0; shift < 12; shift++) {
                if (shift % 4 != 3 && ((board ^ playerBoard) >> shift & 1) == 1) {
                    g.drawString("x", (width / 3) * (shift / 4) + (width / 3 - g.getFontMetrics().stringWidth("x")) / 2, height - (height / 3) * (shift % 4 + 1) + g.getFontMetrics().getHeight() / 2);
                }
            }
        }
    }
}

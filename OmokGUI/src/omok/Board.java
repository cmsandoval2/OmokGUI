 package omok;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Displays and receives user input and outputs stones in board
 * 	@author Carlos Sandoval	*/
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class Board extends JPanel {

    private static final int ROWS = 15; // Number of rows in the Omok board
    private static final int COLS = 15; // Number of columns in the Omok board
    private static final int CELL_SIZE = 40; // Size of each cell in pixels

    private Omok omok; // Reference to the Omok class

    public Board(Omok omok) {
        this.omok = omok;
        setPreferredSize(
            new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS)
        ); // Set preferred size of the panel

        // Add a mouse listener to handle mouse events
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = e.getY() / CELL_SIZE; // Get the row index of the clicked cell
                int col = e.getX() / CELL_SIZE; // Get the column index of the clicked cell
                omok.handleMove(row, col); // Call the handleMove method in the Omok class
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the Omok board grid
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int x = col * CELL_SIZE;
                int y = row * CELL_SIZE;
                g.setColor(Color.BLACK);
                g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
            }
        }
        // Draw the Omok pieces on the board
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int x = col * CELL_SIZE;
                int y = row * CELL_SIZE;
                if (omok.getBoard()[row][col] == Omok.BLACK) {
                    g.setColor(Color.BLACK);
                  //  g.fillOval(x + 5, y + 5, CELL_SIZE - 10, CELL_SIZE - 10);
                } else if (omok.getBoard()[row][col] == Omok.WHITE) {
                    g.setColor(Color.WHITE);
                    //g.fillOval(x + 5, y + 5, CELL_SIZE - 10, CELL_SIZE - 10);
                }
            }
        }
    }
}
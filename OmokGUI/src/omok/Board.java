 package omok;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    private int[][] boardState; // 2D array to store the state of the board
    public Board(Omok omok) {
        this.omok = omok;
        setPreferredSize(
            new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS)
        ); // Set preferred size of the panel
        boardState = new int[15][15]; // Initialize the board state with empty values
        // Add a mouse listener to handle mouse events
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	 // Check if the game has ended
                if (omok.gameEnded) {
                    return;
                }
                
                // Get the mouse click coordinates
                int mouseX = e.getX();
                int mouseY = e.getY();

                // Calculate the clicked cell's row and column
                int row = mouseY / CELL_SIZE;
                int col = mouseX / CELL_SIZE;

                // Check if the clicked cell is empty
                if (omok.getBoard()[row][col] == 0) {
                    // Handle the current player's move
                    omok.handleMove(row, col); // Call handleMove() from your Omok class

                    // Repaint the game board
                    repaint();

                    // Check for win condition
                    if (omok.checkWin(row,col)) { // Call checkWin() from your Omok class
                        // Display win message
                        JOptionPane.showMessageDialog(null, "Player " + (omok.getCurrentPlayer() == 1 ? "Black" : "Red") + " wins!");
                        omok.gameEnded = true;
                        // Optionally handle game restart here
                    } else if (omok.checkDraw()) { // Call checkDraw() from your Omok class
                        // Display draw message
                        JOptionPane.showMessageDialog(null, "Draw!");
                        omok.gameEnded = true;
                        // Optionally handle game restart here
                    } else {
                        // Switch to the next player's turn
                        omok.switchTurn(); // Call switchTurn() from your Omok class
                    }
                } 
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
    	 super.paintComponent(g);
    	    // Get the size of the panel
    	    int panelWidth = getWidth();
    	    int panelHeight = getHeight();

    	    // Calculate the size of each cell based on the panel size
    	    int cellSize = Math.min(panelWidth / COLS, panelHeight / ROWS);

    	  

    	    // Iterate through the game board state array and draw the cells
    	    for (int row = 0; row < ROWS; row++) {
    	        for (int col = 0; col < COLS; col++) {
    	            // Calculate the position of the cell
    	            int x = col * cellSize;
    	            int y = row * cellSize;

    	            // Draw the cell as a rectangle with a border
    	            g.setColor(Color.WHITE);
    	            g.fillRect(x, y, cellSize, cellSize);
    	            g.setColor(Color.BLACK);
    	            g.drawRect(x, y, cellSize, cellSize);

    	            // Get the player state for the current cell
    	            int state = omok.getBoard()[row][col];

    	            // Draw the stone for the player if the state is not 0
    	            if (state == 1) {
    	                g.setColor(Color.BLACK);
    	                g.fillOval(x + cellSize / 4, y + cellSize / 4, cellSize / 2, cellSize / 2);
    	            }
    	            else if(state==2){
    	            	g.setColor(Color.RED);
    	                g.fillOval(x + cellSize / 4, y + cellSize / 4, cellSize / 2, cellSize / 2);
    	            }
    	        }
    	    }
    }
}
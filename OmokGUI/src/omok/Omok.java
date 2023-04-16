package omok;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Omok {

    public static final int EMPTY = 0; // empty cell 
    public static final int BLACK = 1; // black piece 
    public static final int WHITE = 2; // white piece
    private Board oboard;
    private int[][] board; // 2D array representing the Omok board
    private int cplayer; // Current player (BLACK or WHITE)

    public Omok() {
        board = new int[15][15]; // Create a 15x15 Omok board
        cplayer = BLACK; // Start with BLACK player
    }

    // Handle a move made by the current player
    public void handleMove(int row, int col) {
        // Check if the move is valid
        if (board[row][col] == EMPTY) {
            board[row][col] = cplayer; // Place the current player's piece on the board
            // Check for a win condition
            if (checkWin(row, col)) {
                String winner = (cplayer == BLACK) ? "BLACK" : "WHITE";
                System.out.println("Player " + winner + " wins!");
                // Perform necessary actions for winning condition
            } else {
                cplayer = (cplayer == BLACK) ? WHITE : BLACK; // Switch to the other player's turn
            }
        }
    }

 // Check if the current player has won the game
    private boolean checkWin(int row, int col) {
        int player = board[row][col]; // Get the player of the current move
        int count = 1; // Counter for consecutive pieces in a row
        int r, c; // Variables for row and column indices

        // Check horizontally to the left
        r = row;
        c = col - 1;
        while (c >= 0 && board[r][c] == player) {
            count++;
            c--;
        }

        // Check horizontally to the right
        c = col + 1;
        while (c < 15 && board[r][c] == player) {
            count++;
            c++;
        }

        if (count >= 5) {
            return true; // Win condition met horizontally
        }

        // Check vertically upwards
        count = 1;
        r = row - 1;
        c = col;
        while (r >= 0 && board[r][c] == player) {
            count++;
            r--;
        }

        // Check vertically downwards
        r = row + 1;
        while (r < 15 && board[r][c] == player) {
            count++;
            r++;
        }

        if (count >= 5) {
            return true; // Win condition met vertically
        }

        // Check diagonally upwards to the left
        count = 1;
        r = row - 1;
        c = col - 1;
        while (r >= 0 && c >= 0 && board[r][c] == player) {
            count++;
            r--;
            c--;
        }

        // Check diagonally downwards to the right
        r = row + 1;
        c = col + 1;
        while (r < 15 && c < 15 && board[r][c] == player) {
            count++;
            r++;
            c++;
        }

        if (count >= 5) {
            return true; // Win condition met diagonally (upwards to the left and downwards to the right)
        }

        // Check diagonally upwards to the right
        count = 1;
        r = row - 1;
        c = col + 1;
        while (r >= 0 && c < 15 && board[r][c] == player) {
            count++;
            r--;
            c++;
        }

        // Check diagonally downwards to the left
        r = row + 1;
        c = col - 1;
        while (r < 15 && c >= 0 && board[r][c] == player) {
            count++;
            r++;
            c--;
        }

        if (count >= 5) {
            return true; // Win condition met diagonally (upwards to the right and downwards to the left)
        }

        return false; // No win condition met
    }

    // Get the current state of the Omok board
    public int[][] getBoard() {
        return board;
    }
 // Method to set the OmokBoard instance
    public void setOmokBoard(Board oBoard) {
        this.oboard = oboard;
    }

    // Method to reset the game
    public void resetGame() {
        // Reset game logic, such as game state and board state
        // ...

        // Repaint the OmokBoard
        if (oboard != null) {
            oboard.repaint();
        }
    }
}
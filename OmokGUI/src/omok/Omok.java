package omok;

import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

import javax.swing.*;
public class Omok{
	Main main=new Main();
    public static final int EMPTY = 0; // empty cell 
    public static final int BLACK = 1; // black piece 
    public static final int WHITE = 2; // white piece
    private Board oboard;
    private int boardSize=15;
    private int[][] board; // 2D array representing the Omok board
    protected int cplayer=BLACK; // Current player (BLACK or WHITE)
    protected boolean gameEnded; // flag indicating if the game has ended
    private JFrame frame;
    public Omok(JFrame frame) {
    	this.frame = frame;
        cplayer = BLACK;
        // Create and add components to the Omok class
        oboard = new Board(this);
        board=new int[15][15];
        frame.add(oboard, BorderLayout.CENTER);
        frame.revalidate(); // Refresh the frame to reflect changes
    }
    public void handleMove(int row, int col) {
    	switchTurn();
        // Check if the game has already ended
        if (gameEnded) {
            return;
        }
        // Check if the cell is already occupied
        if (board[row][col] != EMPTY) {
            return;
        }
        // Place the current player's piece on the cell
        board[row][col] = cplayer;
        // Redraw the game board
        oboard.repaint();
        // Check if the current player has won
        if (checkWin(row, col)) {
            gameEnded = true;
            return;
        }
        // Check if the game is a draw
        if (checkDraw()) {
            JOptionPane.showMessageDialog(frame, "The game is a draw.");
            gameEnded = true;
            return;
        }
        // Switch to the next player's turn
        switchTurn();
    }
    public boolean checkDraw() {
        // Check if all cells are filled
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (board[row][col] == EMPTY) {
                    return false; // There is at least one empty cell, so the game is not a draw
                }
            }
        }
        // All cells are filled, so the game is a draw
        return true;
    }

// Check if the current player has won the game
    protected boolean checkWin(int row, int col) {
    	  // Check horizontal
        int count = 1;
        for (int i = col - 1; i >= 0; i--) {
            if (board[row][i] == cplayer) {
                count++;
            } else {
                break;
            }
        }
        for (int i = col + 1; i < boardSize; i++) {
            if (board[row][i] == cplayer) {
                count++;
            } else {
                break;
            }
        }
        if (count >= 5) {
            return true;
        }

        // Check vertical
        count = 1;
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][col] == cplayer) {
                count++;
            } else {
                break;
            }
        }
        for (int i = row + 1; i < boardSize; i++) {
            if (board[i][col] == cplayer) {
                count++;
            } else {
                break;
            }
        }
        if (count >= 5) {
            return true;
        }

        // Check diagonal (top-left to bottom-right)
        count = 1;
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == cplayer) {
                count++;
            } else {
                break;
            }
        }
        for (int i = row + 1, j = col + 1; i < boardSize && j < boardSize; i++, j++) {
            if (board[i][j] == cplayer) {
                count++;
            } else {
                break;
            }
        }
        if (count >= 5) {
            return true;
        }

        // Check diagonal (top-right to bottom-left)
        count = 1;
        for (int i = row - 1, j = col + 1; i >= 0 && j < boardSize; i--, j++) {
            if (board[i][j] == cplayer) {
                count++;
            } else {
                break;
            }
        }
        for (int i = row + 1, j = col - 1; i < boardSize && j >= 0; i++, j--) {
            if (board[i][j] == cplayer) {
                count++;
            } else {
                break;
            }
        }
        if (count >= 5) {
            return true;
        }

        return false;
    }
    public void switchTurn() {
    	if (!gameEnded) {
        if (cplayer == BLACK) {
            cplayer = WHITE;
           main.currentPlayerLabel.setText("Current player: BLACK"); 
        } else {
            cplayer = BLACK;
            main.currentPlayerLabel.setText("Current player: RED");
        }
    	}
    }
    /** Getter for game board**/
    public int[][] getBoard() {
        return board;
    }

    /** Setter for game board**/
    public void setBoard(int[][] board) {
        this.board = board;
    }

    /** Getter for current player**/
    public int getCurrentPlayer() {
        return cplayer;
    }

    /** Setter for current player**/
    public void setCurrentPlayer(int cplayer) {
        this.cplayer = cplayer;
    }
    /** Getter for game ended flag**/
    public boolean isGameEnded() {
        return gameEnded;
    }

    /** Setter for game ended flag**/
    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    /** Method to reset the game**/
    public void resetGame() {
        board = new int[15][15]; // Reset the game board
        cplayer = BLACK; // Reset player turn to Player 1 (BLACK)
        gameEnded = false; // Reset game ended flag

        // Repaint the OmokBoard to update the display
        oboard.repaint();
    }
    
   
}
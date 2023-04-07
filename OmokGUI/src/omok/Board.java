package omok;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/** Displays and receives user input and outputs stones in board
 * 	@author Carlos Sandoval	*/
public class Board extends JPanel {
	private final int cell_size=30;
	private final int board_size=15;
	private final int board_width=board_size;
	private final int board_height=board_size;
	private final char[][] board2=new char[board_size][board_size];
	public Board() {
		setPreferredSize(new Dimension(board_width,board_height));
	}
	@Override
	protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	for(int row=0;row<board_size;row++) {
		for(int col=0;col<board_size;col++) {
			int x=col*cell_size;
			int y=row*cell_size;
			g.drawRect(x, y, cell_size, cell_size);
		}
	}
	}
	public char[][] board= {
			{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'}};
	/**Will show the rows and columns while each place on board will be '.' in a char array*/
public void displayboard() {
	//**For the y coordinate display.*/
	int num=1;
	System.out.println("X  1  2  3  4  5  6  7  8  9 10  11 12 13 14 15");
	
	System.out.println("y---------------------------------------------------");
	for(char[] row:board) {
		System.out.print(num);
		for(char c: row) {
			System.out.print("  "+c);
		}
		num++;
		if(num==9) {
			num=1;
		}
		System.out.println();

	}

}
/**Checks if the board is full*/
private boolean isFull() {
	// TODO Auto-generated method stub
	for(int i=0;i<board.length;i++) {
		for(int j=0;j<board[i].length;j++) {
			if(board[i][j]=='.') {
				return false;
			}
		}
	}
	return true;
}
/**Checks for the winner of the game will take either players of the game*/
protected boolean isWinner(String player) {
	// TODO Auto-generated method stub
	if(player=="player") {
		//Horizontal
	for(int i=0;i<board.length;i++) {
		for(int j=0;j<board[i].length-3;j++) {
			if(board[i][j]=='X'&&board[i][j+1]=='X'&&board[i][j+2]=='X'&&board[i][j+3]=='X'&&board[i][j+4]=='X') {
				return true;
			}
		}
	}
			//vertical
		for(int i=0;i<board.length-3;i++) {
			for(int j=0;j<board[i].length;j++) {
		 if(board[i][j]=='X'&&board[i+1][j]=='X'&&board[i+2][j]=='X'&&board[i+3][j]=='X'&&board[i+4][j]=='X') {
				return true;
			}
			}
		}
		//Ascending diagonal
		for (int i = 4; i < board.length; i++) {
		    for (int j = 0; j < board[i].length-4; j++) {
		        if (board[i][j] == 'X' && board[i-1][j+1] == 'X' && board[i-2][j+2] == 'X' && board[i-3][j+3] == 'X' && board[i-4][j+4] == 'X') {
		            return true;
		        }
		    }
		}
		//Descending diagonal
		for(int i=4;i<board.length;i++) {
			for(int j=4;j<board[i].length;j++) {
				if(board[i][j]=='X'&&board[i-1][j-1]=='X'&&board[i-2][j-2]=='X'&&board[i-3][j-3] =='X'&&board[i-4][j-4]=='X') {
					return true;
		}
			}
		}
	}
	if(player=="cpu"||player=="player2") {
		//Horizontal
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length-3;j++) {
				if(board[i][j]=='O'&&board[i][j+1]=='O'&&board[i][j+2]=='O'&&board[i][j+3]=='O'&&board[i][j+4]=='O') {
					return true;
				}
			}
		}
				//vertical
			for(int i=0;i<board.length-3;i++) {
				for(int j=0;j<board[i].length;j++) {
			 if(board[i][j]=='O'&&board[i+1][j]=='O'&&board[i+2][j]=='O'&&board[i+3][j]=='O'&&board[i+4][j]=='O') {
					return true;
				}
				}
			}
			//Ascending diagonal
			for (int i = 4; i < board.length; i++) {
			    for (int j = 0; j < board[i].length-4; j++) {
			        if (board[i][j] == 'O' && board[i-1][j+1] == 'O' && board[i-2][j+2] == 'O' && board[i-3][j+3] == 'O' && board[i-4][j+4] == 'O') {
			            return true;
			        }
			    }
			}
			//Descending diagonal
			for(int i=4;i<board.length;i++) {
				for(int j=4;j<board[i].length;j++) {
					if(board[i][j]=='O'&&board[i-1][j-1]=='O'&&board[i-2][j-2]=='O'&&board[i-3][j-3] =='O'&&board[i-4][j-4]=='O') {
						return true;
			}
				}
			}
	}
	return false;
}
/**Will place the stone on the Board*/
public void placeStone(int row,int col,String user) {
	// TODO Auto-generated method stub
	/** Declares which players turn*/
	char symbol=' ';
	if(user.equals("player")) {
		symbol='X';
	}else if(user.equals("cpu")||user.equals("player2")){
		symbol='O';
	}
	board[row-1][col-1]=symbol;	
	
}
/**Checks for draw*/
public boolean isDraw() {
	if(isFull()) {
		return true;
	}
	return false;
}
/**Checks if the stone landed in the coordinates listed on the board
if not they will be given one more opportunity as explained on GameController method*/
	public boolean isValidPosition(int row,int col) {
		 if(row<1||row>15) {
			return false;
		} if(col<1||col>15) {
			return false;
		}
		return true;
	}
}

package omok;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client {
    private String host;
    private int port;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private boolean running = false;
    private Board board;
    private Omok omok;
    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        omok = new Omok();
        board = new Board(omok);

    }
    NetworkAdapter.MessageListener messageListener = new NetworkAdapter.MessageListener() {
        @Override
        public void messageReceived(NetworkAdapter.MessageType type, int x, int y) {
        	 // Handle the received message
            switch (type) {
                case MOVE:
                    // Update the client's board with the received move
                    omok.handleMove(x, y);

                    // Repaint the game board
                    board.repaint();

                    // Check for win condition
                    if (omok.checkWin(x, y)) {
                        // Display win message
                        JOptionPane.showMessageDialog(null, "Opponent wins!");
                        omok.gameEnded = true;
                    } else if (omok.checkDraw()) {
                        // Display draw message
                        JOptionPane.showMessageDialog(null, "Draw!");
                        omok.gameEnded = true;
                    } else {
                        // Switch to the current player's turn
                        omok.switchTurn();
                    }
                    break;
                default:
                    // Handle other message types (if any)
                    break;
            }
                    }
    };
    public void connect() {
    	try {
            socket = new Socket(host, port);
            NetworkAdapter networkAdapter = new NetworkAdapter(socket);
            networkAdapter.setMessageListener(messageListener);
            networkAdapter.receiveMessagesAsync();
        } catch (ConnectException e) {
            // Handle connection error
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
    }

    public String receiveMessage() throws IOException {
        return reader.readLine();
    }

	public boolean isRunning() {
	    return running;
	}
}

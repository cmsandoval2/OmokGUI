package omok;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class Client {
    private String host;
    private int port;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private boolean running = false;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }
    NetworkAdapter.MessageListener messageListener = new NetworkAdapter.MessageListener() {
        @Override
        public void messageReceived(NetworkAdapter.MessageType type, int x, int y) {
            // Handle the received message
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

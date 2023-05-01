package omok;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private NetworkAdapter networkAdapter;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        // create a new ServerSocket
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        // accept incoming client connections
        clientSocket = serverSocket.accept();
        System.out.println("Client connected: " + clientSocket.getInetAddress());

        // create a new NetworkAdapter for the client
        networkAdapter = new NetworkAdapter(clientSocket);

        // start receiving messages asynchronously
        networkAdapter.receiveMessagesAsync();
    }

    public void stop() throws IOException {
        // close the client socket and server socket
        clientSocket.close();
        serverSocket.close();
    }

    public void sendMessage() {
        // send a message to the client using the NetworkAdapter instance
        networkAdapter.writePlay();
    }
    public boolean isRunning() {
        return serverSocket != null && !serverSocket.isClosed();
    }
    public void close() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Error while closing server socket: " + e.getMessage());
        }
    }

}

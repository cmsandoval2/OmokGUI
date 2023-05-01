package omok;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static JLabel currentPlayerLabel=new JLabel();
	public static void main(String[] args) {
		Server server=new Server(80);
        // Create a JFrame to hold the OmokBoard
        JFrame frame = new JFrame("Omok");
        // Create an instance of Omok
        Omok omok = new Omok(frame);
     // Create an instance of OmokBoard
        Board oboard = new Board(omok);
        frame.setLayout(new BorderLayout());
     // Add OmokBoard as the main content in CENTER position
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(oboard, BorderLayout.CENTER);
        // Add the OmokBoard to the JFrame
        //frame.add(oboard);
        JPanel toolbarPanel = new JPanel();
        // Add a JToolBar to the top of the JFrame
        JToolBar toolBar = new JToolBar();
        toolbarPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolbarPanel.setPreferredSize(new Dimension(frame.getWidth(), 50)); // Set desired height
        toolbarPanel.add(toolBar);
        frame.add(toolbarPanel, BorderLayout.NORTH);
        currentPlayerLabel = new JLabel("Current player: BLACK");
        frame.add(currentPlayerLabel, BorderLayout.SOUTH);
        // Add a reset button to the toolbar
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            omok.resetGame();
            oboard.repaint();
        });
        toolBar.add(resetButton);
        JButton connectButton = new JButton("Connect");
        JButton clientButton = new JButton("Connect Client");
        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client("localhost", 80);
                if (!client.isRunning()) {
                    client.connect();
                }
            }
        });

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (!server.isRunning()) {
            		try {
            		    server.start();
            		    server.close();
            		} catch (IOException f) {
            		    f.printStackTrace();
            		}                }
            }
        });
        toolBar.add(connectButton);
        toolBar.add(clientButton);
        frame.pack();
        frame.setVisible(true);
    }
}

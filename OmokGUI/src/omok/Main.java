package omok;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;

public class Main {

	public static void main(String[] args) {
        // Create an instance of Omok
        Omok omok = new Omok();

        // Create an instance of OmokBoard
        Board oboard = new Board(omok);

        // Create a JFrame to hold the OmokBoard
        JFrame frame = new JFrame("Omok");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the OmokBoard to the JFrame
        frame.add(oboard);

        // Add a JToolBar to the top of the JFrame
        JToolBar toolBar = new JToolBar();
        frame.add(toolBar, BorderLayout.NORTH);

        // Add a reset button to the toolbar
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            omok.resetGame();
            oboard.repaint();
        });
        toolBar.add(resetButton);

        // Pack the JFrame and make it visible
        frame.pack();
        frame.setVisible(true);
    }
}

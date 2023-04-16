package omok;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Omok Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // Create an instance of the Omok class
        Omok omok = new Omok();

        // Create an instance of the OmokBoard class
        Board board = new Board(omok);
        omok.setOmokBoard(board); // Set the OmokBoard instance in the Omok class

        // Add the OmokBoard to the JFrame
        frame.add(board);

        // Set up the GUI
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(board, BorderLayout.CENTER);

        // Add a reset button to start a new game
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            omok.resetGame();
            board.repaint();
        });
        contentPane.add(resetButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}

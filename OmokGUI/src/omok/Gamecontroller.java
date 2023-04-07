package omok;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gamecontroller {
public Gamecontroller() {
	

}
public JPanel makePanel() {
	JPanel turn=new JPanel();
	JLabel playerTurn=new JLabel();
	Color panelColor=turn.getBackground();
	playerTurn.setBackground(panelColor);
	playerTurn.setForeground(Color.BLACK);
	playerTurn.setFont(new Font("Ink Free",Font.BOLD,14));
	playerTurn.setText("Player turn:");
	playerTurn.setHorizontalAlignment(JLabel.LEFT);
	playerTurn.setOpaque(true);
	turn.add(playerTurn);
	return turn;
}
}

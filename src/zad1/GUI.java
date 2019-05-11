package zad1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JPanel {

	protected JTextArea chatArea;
	private Client client;
	public GUI(Client c){
		super(new BorderLayout(20, 20));
		this.client = c;
		JPanel panel = new JPanel(new BorderLayout());
		chatArea = new JTextArea();
		chatArea.setEditable(false);
		chatArea.setLineWrap(true);
		chatArea.setBackground(Color.LIGHT_GRAY);
		JScrollPane scrollPane = new JScrollPane(chatArea);
		setPreferredSize (new Dimension (600, 600));
		add(scrollPane, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
	}

	public void sendM(String t){
		chatArea.append(" "+t + "\n");
		chatArea.setCaretPosition(chatArea.getDocument().getLength());
		chatArea.update(chatArea.getGraphics());
	}

}

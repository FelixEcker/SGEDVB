package de.felixeckert.sgedvb.grafik;

import java.awt.Canvas;

import javax.swing.JFrame;

public class GameWindow extends Canvas {
	private static final long serialVersionUID = -5697997542416676112L;
	private JFrame frame;
	
	public GameWindow(int[] res, String title) {
		this.frame = new JFrame(title);
		frame.setSize(res[0], res[1]);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//frame.setUndecorated(true);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		this.setSize(res[0], res[1]);
		
		frame.add(this);
		frame.setVisible(true);
	}

	public JFrame getFrame() { return this.frame; }
}

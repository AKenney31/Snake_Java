package main.view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 
 * This class draws the Menu Screen
 *
 */

public class GameScreen extends Window{
	private JFrame frame;
	private JButton game;
	private int width;
	private int height;
	
	public GameScreen(JFrame frame, int width, int height, JButton game) {
		this.frame = frame;
		this.game = game;
		this.height = height;
		this.width = width;
	}
	@Override
	public void draw() {
		frame.setSize(width, height);
		frame.getContentPane().removeAll();
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
		frame.setLayout(fl);
		frame.add(game);
		frame.setVisible(true);
	}

}

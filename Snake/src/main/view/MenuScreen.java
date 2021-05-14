package main.view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 
 * This class draws the Menu Screen
 *
 */

public class MenuScreen extends Window{
	private JFrame frame;
	private JButton men;
	private int width;
	private int height;
	
	public MenuScreen(JFrame frame, int width, int height, JButton men) {
		this.frame = frame;
		this.men = men;
		this.height = height;
		this.width = width;
	}
	@Override
	public void draw() {
		frame.setSize(width, height);
		frame.getContentPane().removeAll();
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
		frame.setLayout(fl);
		frame.add(men);
		frame.setVisible(true);
	}

}

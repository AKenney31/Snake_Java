package main.view;

import java.awt.FlowLayout;

import javax.swing.JFrame;

/**
 * 
 * This class draws the Menu Screen
 *
 */

public class MenuScreen extends Window{
	private JFrame frame;
	
	public MenuScreen(JFrame frame) {
		this.frame = frame;
	}
	@Override
	public void draw() {
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
		frame.setLayout(fl);
		frame.setVisible(true);
	}

}

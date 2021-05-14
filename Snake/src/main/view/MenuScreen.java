package main.view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import main.controller.Controller;

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
	private Controller cont;
	
	/**
	 * This is the constructor for the MenuScreen class
	 * 
	 * @param frame
	 * @param cont
	 * @param width
	 * @param height
	 * @param men
	 */
	public MenuScreen(JFrame frame, Controller cont, int width, int height, JButton men) {
		this.frame = frame;
		this.men = men;
		this.height = height;
		this.width = width;
		this.cont = cont;
	}
	
	/**
	 * This method updates the frame with the components of the menu screen
	 */
	@Override
	public void draw() {
		frame.setVisible(false);
		frame.setSize(width, height);
		frame.getContentPane().removeAll();
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
		frame.setLayout(fl);
		frame.add(men);
		frame.setVisible(true);
	}

}

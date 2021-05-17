package main.view;

import javax.swing.JFrame;

import main.controller.Controller;

/**
 * 
 * This class draws the Menu Screen
 *
 */

public class GameScreen extends Window{
	private JFrame frame;
	private int width;
	private int height;
	private GamePanel panel;
	private Controller cont;
	
	/**
	 * GameScreen constructor
	 * 
	 * @param frame
	 * @param width
	 * @param height
	 */
	public GameScreen(JFrame frame, Controller cont, int width, int height) {
		this.frame = frame;
		this.height = height;
		this.width = width;
		this.cont = cont;
	}
	
	/**
	 * panel getter
	 * @return
	 */
	public GamePanel getPanel() {
		return panel;
	}
	
	/**
	 * This method updates the frame with the components of the snake screen
	 */
	@Override
	public void draw() {
		frame.setVisible(false);
		frame.getContentPane().removeAll();
		panel = new GamePanel(width, height, cont);
		frame.setContentPane(panel);
		frame.setVisible(true);
	}

}

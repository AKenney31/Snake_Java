package main.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		this.panel = new GamePanel(width, height, cont);
	}
	
	/**
	 * This method updates the frame with the components of the snake screen
	 */
	@Override
	public void draw() {
		frame.setVisible(false);
		frame.setSize(width, height);
		frame.getContentPane().removeAll();
		frame.setContentPane(panel);
		frame.setVisible(true);
	}

}

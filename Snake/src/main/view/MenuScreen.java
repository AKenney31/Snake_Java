package main.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.controller.Controller;

/**
 * 
 * This class draws the Menu Screen
 *
 */

public class MenuScreen extends Window{
	private JFrame frame;
	private int width;
	private int height;
	private Controller cont;
	JPanel menuPanel;
	
	/**
	 * This is the constructor for the MenuScreen class
	 * 
	 * @param frame
	 * @param cont
	 * @param width
	 * @param height
	 * @param men
	 */
	public MenuScreen(JFrame frame, Controller cont, int width, int height) {
		this.frame = frame;
		this.height = height;
		this.width = width;
		this.cont = cont;
	}
	
	/**
	 * This method updates the frame with the components of the menu screen
	 */
	@SuppressWarnings("serial")
	@Override
	public void draw() {
		frame.setVisible(false);
		frame.getContentPane().removeAll();
		frame.setLayout(new FlowLayout());
		menuPanel = new JPanel() {
			/**
			 * Draws the background image, and the "Welcome to snake" title to the menu screen
			 */
			@Override 
			public void paint(Graphics g) {
				Image background = new ImageIcon("src/main/view/images/snake.jpg").getImage();
				g.drawImage(background, 0, 0, width, height, null);
				//Draw Welcome To Snake Text
				g.setColor(Color.ORANGE);
				g.setFont(new Font(Font.DIALOG, Font.BOLD, 50));
				g.drawString("Welcome To Snake", (int)(width / 2) - (int)(g.getFontMetrics().stringWidth("Welcome To Snake") / 2)
						, (int)(height * .3));
				paintComponents(g);
			}
		};
		menuPanel.setLayout(null);
		menuPanel.setPreferredSize(new Dimension(width, height));
		addStart();
		addColor();
		addSpeed();
		frame.add(menuPanel);
		frame.setVisible(true);
	}
	
	/**
	 * Sets Bounds, styles, and adds the start game button to the screen
	 */
	private void addStart() {
		JButton menuButton = cont.getMenuButton();
		menuButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		menuButton.setBounds((int)(width * .5) - (int)(width * .1), (int)(height * .4), (int)(width * .2), (int)(height * .1));
		menuPanel.add(menuButton);
	}
	
	/**
	 * Sets Bounds, styles, and adds the Color Choosing radio buttons to the screen
	 */
	private void addColor() {
		ButtonGroup colors = new ButtonGroup();
		colors.add(cont.getRed());
		colors.add(cont.getGreen());
		colors.add(cont.getPurple());
		colors.clearSelection();
		cont.getRed().setSelected(true);
		
		cont.getRed().setBackground(Color.LIGHT_GRAY);
		cont.getGreen().setBackground(Color.LIGHT_GRAY);
		cont.getPurple().setBackground(Color.LIGHT_GRAY);
		
		JLabel colorLabel = new JLabel("Select Color", SwingConstants.CENTER);
		colorLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		colorLabel.setOpaque(true);
		colorLabel.setBackground(Color.LIGHT_GRAY);
		
		colorLabel.setBounds((int)(width * .17), (int)(height * .57), (int)(width * .15), (int)(height * .05));
		cont.getRed().setBounds((int)(width * .17), (int)(height * .61), (int)(width * .15), (int)(height * .05));
		cont.getGreen().setBounds((int)(width * .17), (int)(height * .65), (int)(width * .15), (int)(height * .05));
		cont.getPurple().setBounds((int)(width * .17), (int)(height * .69), (int)(width * .15), (int)(height * .05));
		menuPanel.add(colorLabel);
		menuPanel.add(cont.getRed());
		menuPanel.add(cont.getGreen());
		menuPanel.add(cont.getPurple());
	}
	
	/**
	 * Sets Bounds, styles, and adds the Speed Choosing radio buttons to the screen
	 */
	private void addSpeed() {
		ButtonGroup speed = new ButtonGroup();
		speed.add(cont.getSlow());
		speed.add(cont.getMid());
		speed.add(cont.getFast());
		speed.clearSelection();
		cont.getSlow().setSelected(true);
		
		cont.getSlow().setBackground(Color.LIGHT_GRAY);
		cont.getMid().setBackground(Color.LIGHT_GRAY);
		cont.getFast().setBackground(Color.LIGHT_GRAY);
		
		JLabel speedLabel = new JLabel("Select Speed", SwingConstants.CENTER);
		speedLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		speedLabel.setOpaque(true);
		speedLabel.setBackground(Color.LIGHT_GRAY);
		
		speedLabel.setBounds((int)(width * .67), (int)(height * .57), (int)(width * .15), (int)(height * .05));
		cont.getSlow().setBounds((int)(width * .67), (int)(height * .61), (int)(width * .15), (int)(height * .05));
		cont.getMid().setBounds((int)(width * .67), (int)(height * .65), (int)(width * .15), (int)(height * .05));
		cont.getFast().setBounds((int)(width * .67), (int)(height * .69), (int)(width * .15), (int)(height * .05));
		menuPanel.add(speedLabel);
		menuPanel.add(cont.getSlow());
		menuPanel.add(cont.getMid());
		menuPanel.add(cont.getFast());
	}

}

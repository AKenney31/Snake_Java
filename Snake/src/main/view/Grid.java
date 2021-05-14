package main.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.controller.Controller;

/**
 * This class handles all the graphics for the game screen
 * @author akenney
 *
 */
@SuppressWarnings("serial")
public class Grid extends JPanel{
	private int width;
	private int height;
	private Controller cont;
	public Grid(int width, int height, Controller cont) {
		super();
		this.width = width;
		this.height = height;
		this.cont = cont;
	}
	
	@Override
	   public void paint(Graphics g) {
		System.out.println("HERE");
	      Graphics2D graphic2d = (Graphics2D) g;
	      Image background = new ImageIcon("src/main/view/images/rainforest.jpg").getImage();
	      g.drawImage(background, 0, 0, width, height, null);
	      drawGridLines(graphic2d);
	      drawSnake(graphic2d);
	      drawFood(graphic2d);
	}
	
	/**
	 * Draws the gridlines on the screen
	 * @param g
	 */
	private void drawGridLines(Graphics2D g) {
		int gridStartX = (int)(width * .1);
		int gridWidth = (int)(width * .8);
		int gridStartY = (int)(height * .1);
		int gridHeight = (int)(height * .8);
		int squareWidth = (int)(gridHeight / 15);
		
		g.setColor(Color.WHITE);
		g.fillRect(gridStartX, gridStartY, gridWidth, gridHeight);
		
		g.setColor(Color.BLACK);		
		for(int startX = gridStartX; startX < gridStartX + gridHeight; startX += squareWidth) {
			g.drawLine(startX, gridStartY, startX, gridStartY + gridHeight);
		}
		for(int startY = gridStartY; startY < gridStartY + gridHeight; startY += squareWidth) {
			g.drawLine(gridStartX, startY, gridStartX + gridHeight, startY);
		}
	}
	
	/**
	 * Draws all the snake components on the screen
	 * @param g
	 */
	private void drawSnake(Graphics2D g) {
		
	}
	
	/**
	 * Draws the current food object on the screen
	 * @param g
	 */
	private void drawFood(Graphics2D g) {
		
	}
}

package main.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.controller.Controller;
import main.enums.Direction;
import main.model.Model;
import main.model.Snake;

/**
 * This class handles all the graphics for the game screen
 * @author akenney
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	private int width;
	private int height;
	private Controller cont;
	private int gridStartX = 0;
	private int gridWidth = 0;
	private int gridStartY = 0;
	private int gridHeight = 0;
	private int squareWidth = 0;
	private int gameWidth = 0;
	private int gameWidthDiff = 0;
	
	public GamePanel(int width, int height, Controller cont) {
		super();
		this.width = width;
		this.height = height;
		this.cont = cont;
		gridStartX = (int)(width * .1);
		gridWidth = (int)(width * .8);
		gridStartY = (int)(height * .1);
		gridHeight = (int)(height * .8);
		squareWidth = (int)(gridHeight / 15);
		gameWidth = squareWidth * 15;
		gameWidthDiff = (int)((gridHeight - gameWidth) / 2);
	}
	
	@Override
	public void paint(Graphics g) {
		  Graphics2D graphic2d = (Graphics2D) g;
		  Image background = new ImageIcon("src/main/view/images/rainforest.jpg").getImage();
		  g.drawImage(background, 0, 0, width, height, null);
		  g.setColor(Color.WHITE);
		  g.fillRect(gridStartX, gridStartY, gridWidth, gridHeight);
		  drawGridLines(graphic2d);
		  drawSnake(graphic2d);
		  drawFood(graphic2d);
	}
	
	/**
	 * Draws the grid lines and game window on the screen
	 * @param g
	 */
	private void drawGridLines(Graphics2D g) {	
		g.setColor(Color.BLACK);		
		for(int startX = gridStartX; startX <= gridStartX + gameWidth; startX += squareWidth) {
			g.drawLine(startX + gameWidthDiff, gridStartY + gameWidthDiff, startX + gameWidthDiff, gridStartY + gameWidth + gameWidthDiff);
		}
		for(int startY = gridStartY; startY <= gridStartY + gameWidth; startY += squareWidth) {
			g.drawLine(gridStartX + gameWidthDiff, startY + gameWidthDiff, gridStartX + gameWidth + gameWidthDiff, startY + gameWidthDiff);
		}
	}
	
	/**
	 * Draws all the snake components on the screen
	 * @param g
	 */
	private void drawSnake(Graphics2D g) {
		Model m = cont.getModel();
		Snake tmp;
		
		for(tmp = m.getSnake(); tmp != null; tmp = tmp.getNext()) {
			int snakeX = tmp.getX();
			int snakeY = tmp.getY();
			int beginningX = gridStartX + gameWidthDiff;
			int beginningY = gridStartY + gameWidthDiff;		
			g.setColor(m.getColor());
			g.fillRect(snakeX * squareWidth + beginningX, snakeY * squareWidth + beginningY, squareWidth, squareWidth);
			//draw eyes
			if(tmp == m.getSnake()) {
				g.setColor(Color.ORANGE);
				int ex;
				int ey;
				switch(tmp.getDirection()) {
				case EAST:
					ex = snakeX * squareWidth + beginningX + (int)(squareWidth * .7);
					ey = snakeY * squareWidth + beginningY + (int)(squareWidth * .2);
					g.fillOval(ex, ey, (int)(squareWidth * .2), (int)(squareWidth * .2));
					g.fillOval(ex, ey + (int)(squareWidth * .5), (int)(squareWidth * .2), (int)(squareWidth * .2));
					break;
				case NORTH:
					ex = snakeX * squareWidth + beginningX + (int)(squareWidth * .2);
					ey = snakeY * squareWidth + beginningY + (int)(squareWidth * .2);
					g.fillOval(ex, ey, (int)(squareWidth * .2), (int)(squareWidth * .2));
					g.fillOval(ex + (int)(squareWidth * .5), ey, (int)(squareWidth * .2), (int)(squareWidth * .2));
					break;
				case WEST:
					ex = snakeX * squareWidth + beginningX + (int)(squareWidth * .2);
					ey = snakeY * squareWidth + beginningY + (int)(squareWidth * .2);
					g.fillOval(ex, ey, (int)(squareWidth * .2), (int)(squareWidth * .2));
					g.fillOval(ex, ey + (int)(squareWidth * .5), (int)(squareWidth * .2), (int)(squareWidth * .2));
					break;
				case SOUTH:
					ex = snakeX * squareWidth + beginningX + (int)(squareWidth * .2);
					ey = snakeY * squareWidth + beginningY + (int)(squareWidth * .7);
					g.fillOval(ex, ey, (int)(squareWidth * .2), (int)(squareWidth * .2));
					g.fillOval(ex + (int)(squareWidth * .5), ey, (int)(squareWidth * .2), (int)(squareWidth * .2));
					break;
				default:
					break;
				}
			}
		}
	}
	
	/**
	 * Draws the current food object on the screen
	 * @param g
	 */
	private void drawFood(Graphics2D g) {
		Model m = cont.getModel();
		Image apple = new ImageIcon("src/main/view/images/apple.png").getImage();
		int beginningX = gridStartX + gameWidthDiff + (int)(squareWidth * .05);
		int beginningY = gridStartY + gameWidthDiff + (int)(squareWidth * .05);
		g.drawImage(apple, m.getFood().getX() * squareWidth + beginningX, m.getFood().getY() * squareWidth + beginningY, squareWidth - (int)(squareWidth * .1), squareWidth - (int)(squareWidth * .1), null);
	}
}

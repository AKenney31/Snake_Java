package main.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
public class GamePanel extends JPanel implements Runnable{
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
	private int infoScreenStart = 0;
	private int infoScreenWidth = 0;
	private boolean gameStop;
	
	public GamePanel(int width, int height, Controller cont) {
		super();
		setLayout(null);
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
		infoScreenStart = (int)(gridStartX + gameWidth + gameWidthDiff);
		infoScreenWidth = (int)(gridWidth - gameWidth - gameWidthDiff);
		
		gameStop = false;
		JButton backToMenu = cont.getGameButton();
		backToMenu.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		backToMenu.setBounds(infoScreenStart + (int)(infoScreenWidth * .5) - (int)(infoScreenWidth * .5 / 2),
				gridStartY + (int)(gridHeight * .6), (int)(infoScreenWidth * .5), (int)(gridHeight * .1));
		add(backToMenu);
		Thread t = new Thread(this);
		t.start();
	}
	
	@Override
	public void paint(Graphics g) {
		Image background = new ImageIcon("src/main/view/images/rainforest.jpg").getImage();
		g.drawImage(background, 0, 0, width, height, null);
		drawGridLines(g);
		drawSnake(g);
		drawFood(g);
		drawInfoScreen(g);
		if(gameStop) {
			drawGameOver(g);
		}
		paintComponents(g);
	}
	
	/**
	 * Draws the grid lines and game window on the screen
	 * @param g
	 */
	private void drawGridLines(Graphics g) {	
		g.setColor(Color.WHITE);
		g.fillRect(gridStartX, gridStartY, gridWidth, gridHeight);
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
	private void drawSnake(Graphics g) {
		Model m = cont.getModel();
		m.getSnake().forEach(tmp -> {
			int snakeX = tmp.getX();
			int snakeY = tmp.getY();
			int beginningX = gridStartX + gameWidthDiff;
			int beginningY = gridStartY + gameWidthDiff;		
			g.setColor(m.getColor());
			if(tmp.getX() >= 0 && tmp.getX() <= 14 && tmp.getY() >= 0 && tmp.getY() <= 14) {
				g.fillRect(snakeX * squareWidth + beginningX, snakeY * squareWidth + beginningY, squareWidth, squareWidth);
				//draw eyes
				if(tmp == m.getSnake().get(0)) {
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
		});
	}
	
	/**
	 * Draws the current food object on the screen
	 * @param g
	 */
	private void drawFood(Graphics g) {
		Model m = cont.getModel();
		Image apple = new ImageIcon("src/main/view/images/apple.png").getImage();
		int beginningX = gridStartX + gameWidthDiff + (int)(squareWidth * .05);
		int beginningY = gridStartY + gameWidthDiff + (int)(squareWidth * .05);
		g.drawImage(apple, m.getFood().getX() * squareWidth + beginningX, m.getFood().getY() * squareWidth + beginningY, squareWidth - (int)(squareWidth * .1), squareWidth - (int)(squareWidth * .1), null);
	}
	
	/**
	 * Draws the score and the current high score to the info screen
	 * @param g
	 */
	private void drawInfoScreen(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
		String score = "Score: " + cont.getModel().getSnakeLength();
		g.drawString(score, (int)(infoScreenStart + (infoScreenWidth * .5) - (g.getFontMetrics().stringWidth(score) / 2)), 
				(int)(gridStartY + gridHeight * .1));
		String highScore = "High Score: " + cont.getModel().getHighScore();
		g.drawString(highScore, (int)(infoScreenStart + (infoScreenWidth * .5) - (g.getFontMetrics().stringWidth(highScore) / 2)), 
				(int)(gridStartY + gridHeight * .25));
		String sp = "";
		switch(cont.getModel().getSpeed()) {
		case SLOW:
			sp = "Slow";
			break;
		case MID:
			sp = "Medium";
			break;
		case FAST:
			sp = "Fast";
			break;
		}
		String speed = "Speed: " + sp;
		g.drawString(speed, (int)(infoScreenStart + (infoScreenWidth * .5) - (g.getFontMetrics().stringWidth(speed) / 2)), 
				(int)(gridStartY + gridHeight * .4));
	}
	
	private void drawGameOver(Graphics g) {
		g.setColor(Color.ORANGE);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
		g.drawString("Game Over", (int)(gridStartX + gameWidthDiff + (gameWidth / 2) - (g.getFontMetrics().stringWidth("Game Over") / 2)), 
				(int)(gridStartY + gridHeight * .5));
	}
	@Override
	public void run() {
		gameStop = false;
		cont.addKeyBinds();
		while(!gameStop) {
			try {
				Thread.sleep(1000/cont.getModel().getSpeed().getSpeed());
				gameStop = cont.tick();
				repaint();
			}catch(Exception e) {
				e.printStackTrace();
				gameStop = true;
			}
		}
		cont.updateHighScores();
	}
}

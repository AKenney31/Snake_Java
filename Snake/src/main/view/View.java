package main.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import main.controller.Controller;
import main.enums.CurrentScreen;

/**
 * constructor for the View class
 */	
public class View {
	private Controller cont;
	private int width;
	private int height;
	private JFrame frame;
	
	//Windows
	private MenuScreen menuScreen;
	private GameScreen gameScreen;
	
	/**
	 * View constructor
	 */
	public View() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		height = (int) size.getHeight();
		width = (int) size.getWidth();
		
		frame = new JFrame("Snake Game");
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.cont = new Controller(this);
		this.menuScreen = new MenuScreen(frame, cont, width, height);
		this.gameScreen = new GameScreen(frame, cont, width, height);
		changeScreen(CurrentScreen.MENU);
	}
	
	/**
	 * gameScreen getter
	 * @return
	 */
	public GameScreen getGameScreen() {
		return gameScreen;
	}
	
	/**
	 * changes between views
	 * 
	 * @param cs, new Current Screen 
	 */
	public void changeScreen(CurrentScreen cs) {
		switch(cs) {
		case MENU:
			menuScreen.draw();
			break;
		case GAME:
			gameScreen.draw();
			break;
		default:
			menuScreen.draw();
			break;
		}
	}
	
	/**
	 * Main Method... This method creates a new View instance which automatically opens the menu screen
	 * @param args
	 */
	public static void main(String [] args) {
		System.out.println("WELCOME TO JAVA SNAKE");
		new View();
	}
}

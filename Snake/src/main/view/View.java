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
	
	public View() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		height = (int) size.getHeight() - 65;
		width = (int) size.getWidth();
		
		frame = new JFrame("Snake Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.cont = new Controller(this);
		this.menuScreen = new MenuScreen(frame, width, height, cont.getMenu());
		this.gameScreen = new GameScreen(frame, width, height, cont.getGame());
		changeScreen(CurrentScreen.MENU);
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
	
	public static void main(String [] args) {
		System.out.println("WELCOME TO JAVA SNAKE");
		new View();
	}
}

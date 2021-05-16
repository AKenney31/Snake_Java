package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.Timer;

import main.enums.CurrentScreen;
import main.model.Model;
import main.model.Snake;
import main.view.View;

/**
 * This class defines the handlers, and buttons that control the program.
 * 
 * @author akenney
 *
 */
public class Controller {
	private View view;
	private Model model;
	private Boolean run = false;
	private Timer timer;
	private int delay = 100;
	
	//Define Buttons
	private JButton gameSc = new JButton("Game");
	private JButton menuSc = new JButton("Menu");
	
	/**
	 * Controller constructor
	 * 
	 * @param view
	 */
	public Controller(View view) {
		this.view = view;
		this.model = new Model();
		
		//Button Handlers
		gameSc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.changeScreen(CurrentScreen.MENU);
			}
		});
		menuSc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startGame();
				view.changeScreen(CurrentScreen.GAME);
			}
		});
	}
	
	public void startGame() { 
		run = true; 
		new Thread() {  
			@Override
			public void run() {
				while(run) {
					try {
						Thread.sleep(30);
						if(checkCollisions()) {
							run = false;
						}
						if(run) {
							moveSnake();
						}
						view.getGamePanel().paint(view.getFrame().getGraphics());
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						run = false;
					}
				}
			}
		}.start();
	}
	 
	public boolean checkCollisions() {
		//Check if Snake collided with sides
		if(model.getSnake().getX() == 15) {
			return true;
		}else if(model.getSnake().getY() == 15) {
			return true;
		}
		
		//Check if Snake collided with Body
		int headX = model.getSnake().getX();
		int headY = model.getSnake().getY();
		Snake tmp = model.getSnake();
		if(tmp.getNext() != null) {
			tmp = tmp.getNext();
			while(tmp != null) {
				if(tmp.getX() == headX && tmp.getY() == headY) {
					return true;
				}
			}
		}
		
		//Check if snake collided with food
		if(model.getSnake().getX() == model.getFood().getX() && 
				model.getSnake().getY() == model.getFood().getY()) {
			model.incSnakeLength();
			model.makeFood();
		}
		
		//Check if snake collided with any turns
		model.getTurns().forEach(t -> {
			Snake turnTmp = model.getSnake();
			if(turnTmp.getNext() != null) {
				turnTmp = turnTmp.getNext();
				while(turnTmp != null) {
					if(turnTmp.getX() == t.getX() && turnTmp.getY() == t.getY()) {
						t.incCount();
						turnTmp.setDirection(t.getDir());
					}
					turnTmp = turnTmp.getNext();
				}
				if(t.getCount() == model.getSnakeLength()) {
					model.getTurns().remove(t);
				}
			}
		});
		return false;
	}
	
	public void moveSnake() {
		Snake tmp = model.getSnake();
		while(tmp != null) {
			tmp.move();
			tmp = tmp.getNext();
		}
	}
	
	//Getters
	public JButton getGame() {
		return gameSc;
	}
	public JButton getMenu() {
		return menuSc;
	}
	public Model getModel() {
		return model;
	}
}

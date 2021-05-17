package main.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import main.enums.CurrentScreen;
import main.enums.Direction;
import main.enums.Speed;
import main.model.Model;
import main.model.Snake;
import main.model.Turn;
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
	private boolean collided;
	
	//Define Buttons
	private JButton menuSc = new JButton("Start Game");
	private JButton gameSc = new JButton("Return To Menu");
	
	//Define Radio Buttons
	private JRadioButton colorRed = new JRadioButton("Red", true);
	private JRadioButton colorGreen = new JRadioButton("Green", false);
	private JRadioButton colorPurple = new JRadioButton("Purple", false);
	
	private JRadioButton speedSlow = new JRadioButton("Slow", true);
	private JRadioButton speedMid = new JRadioButton("Medium", false);
	private JRadioButton speedFast = new JRadioButton("Fast", false);
	
	/**
	 * Controller constructor
	 * 
	 * @param view
	 */
	public Controller(View view) {
		this.view = view;
		this.model = new Model();
		
		//Button Handlers
		menuSc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.changeScreen(CurrentScreen.GAME);
			}
		});
		
		gameSc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model = new Model();
				unaddKeyBinds();
				view.changeScreen(CurrentScreen.MENU);
			}
		});
		
		colorRed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setColor(Color.RED);
			}	
		});
		
		colorGreen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setColor(Color.GREEN);
			}	
		});
		
		colorPurple.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setColor(Color.MAGENTA);
			}	
		});
		
		speedSlow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setSpeed(Speed.SLOW);
			}	
		});
		
		speedMid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setSpeed(Speed.MID);
			}	
		});
		
		speedFast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setSpeed(Speed.FAST);
			}	
		});
	}
	
	/**
	 * This method maps the arrow keys to the snake's direction changes
	 */
	@SuppressWarnings("serial")
	public void addKeyBinds() {
		InputMap im = view.getGameScreen().getPanel().getInputMap(JComponent.WHEN_FOCUSED);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "Turn North");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "Turn East");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "Turn South");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "Turn West");
		
		ActionMap ap =  view.getGameScreen().getPanel().getActionMap();
		ap.put("Turn North", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if(model.getSnake().get(0).getDirection() != Direction.NORTH) {
					Snake head = model.getSnake().get(0);
					head.setDirection(Direction.NORTH);
					model.addTurn(new Turn(Direction.NORTH, head.getX(), head.getY()));
				}		
			}
		});
		ap.put("Turn East", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if(model.getSnake().get(0).getDirection() != Direction.EAST) {
					Snake head = model.getSnake().get(0);
					head.setDirection(Direction.EAST);
					model.addTurn(new Turn(Direction.EAST, head.getX(), head.getY()));
				}		
			}
		});
		ap.put("Turn South", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if(model.getSnake().get(0).getDirection() != Direction.SOUTH) {
					Snake head = model.getSnake().get(0);
					head.setDirection(Direction.SOUTH);
					model.addTurn(new Turn(Direction.SOUTH, head.getX(), head.getY()));
				}		
			}
		});
		ap.put("Turn West", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if(model.getSnake().get(0).getDirection() != Direction.WEST) {
					Snake head = model.getSnake().get(0);
					head.setDirection(Direction.WEST);
					model.addTurn(new Turn(Direction.WEST, head.getX(), head.getY()));
				}		
			}
		});
	}
	
	/**
	 * This method removes the key binds. Called when user returns to the menu screen
	 */
	private void unaddKeyBinds() {
		view.getGameScreen().getPanel().getInputMap().clear();
		view.getGameScreen().getPanel().getActionMap().clear();
	}
	
	/**
	 * This method moves the snake and calls the check collisions method. It is called whenever the game thread
	 * ticks.
	 * @return whether or not the game should end
	 */
	public boolean tick() {
		moveSnake();
		return checkCollisions();
	}
	
	/**
	 * This method goes down the checklist of loss conditions, and checks if the snake has collided with food or a turn object
	 * @return true if the snake has collided, and false if the game should continue
	 */
	public boolean checkCollisions() {
		//Check if Snake collided with sides
		if(model.getSnake().get(0).getX() == 15 || model.getSnake().get(0).getX() == -1) {
			return true;
		}else if(model.getSnake().get(0).getY() == 15 || model.getSnake().get(0).getY() == -1) {
			return true;
		}
		
		//Check if Snake collided with Body
		int headX = model.getSnake().get(0).getX();
		int headY = model.getSnake().get(0).getY();
		collided = false;
		model.getSnake().forEach(tmp -> {
			if(tmp != model.getSnake().get(0)) {
				if(tmp.getX() == headX && tmp.getY() == headY) {
					collided = true;
				}
			}
		});
		if(collided) {
			return true;
		}
		
		//Check if snake collided with food
		if(model.getSnake().get(0).getX() == model.getFood().getX() && 
				model.getSnake().get(0).getY() == model.getFood().getY()) {
			model.incSnakeLength();
			model.makeFood();
		}
		
		//Check if snake collided with any turns
		ArrayList<Turn> fullfilled = new ArrayList<Turn>();
		model.getTurns().forEach(t -> {
			model.getSnake().forEach(turnTmp ->{
				if(turnTmp.getX() == t.getX() && turnTmp.getY() == t.getY()) {
					t.incCount();
					turnTmp.setDirection(t.getDir());
				}
			});
			if(t.getCount() == model.getSnakeLength()) {
				fullfilled.add(t);
			}
		});
		fullfilled.forEach(t -> {
			model.getTurns().remove(t);
		});
		return false;
	}
	
	/**
	 * This method calls the move method for every piece in the model's snake list
	 */
	public void moveSnake() {
		model.getSnake().forEach(s -> {
			s.move();
		});
	}
	
	//Getters
	public JButton getMenuButton() {
		return menuSc;
	}
	public JButton getGameButton() {
		return gameSc;
	}
	public JRadioButton getRed() {
		return colorRed;
	}
	public JRadioButton getGreen() {
		return colorGreen;
	}
	public JRadioButton getPurple() {
		return colorPurple;
	}
	public JRadioButton getSlow() {
		return speedSlow;
	}
	public JRadioButton getMid() {
		return speedMid;
	}
	public JRadioButton getFast() {
		return speedFast;
	}
	public Model getModel() {
		return model;
	}
}

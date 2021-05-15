package main.model;

import java.awt.Color;
import java.util.Random;

import main.enums.Direction;


/**
 * This class contains details about our program including the user input and the snake object, and methods
 * that control the flow of the program
 * 
 * @author akenney
 *
 */
public class Model {
	private Color color = Color.BLUE;
	private Snake snake;
	private Food food;
	private int snakeLength = 0;
	
	/**
	 * This is the constructor for model
	 */
	public Model() {
		snake = new Snake(7, 7, Direction.EAST);
		makeFood();
	}
	
	/**
	 * The makeFood() method creates a new food object at a random place on the board that doesn't overlap with 
	 * any of the snake's body pieces
	 */
	public void makeFood() {
		Random rand = new Random();
		int fx = rand.nextInt(15);
		int fy = rand.nextInt(15);
		Snake tmp;
		for(tmp = snake; tmp != null; tmp = tmp.getNext()) {
			if(fx == tmp.getX() && fy == tmp.getY()) {
				makeFood();
			}
		}
		food = new Food(fx, fy);
	}
	
	//Getters
	public Snake getSnake() {
		return snake;
	}
	
	public Food getFood() {
		return food;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getSnakeLength() {
		return snakeLength;
	}
	
	//Setters
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void incSnakeLength() {
		this.snakeLength++;
	}
}

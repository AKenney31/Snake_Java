package main.model;

import java.awt.Color;

import main.enums.Direction;

/**
 * This class contains details about our program including the user input and the snake object, and methods
 * that control the flow of the program
 * 
 * @author akenney
 *
 */
public class Model {
	private Color color = Color.WHITE;
	private Snake snake;
	private Direction direction = Direction.EAST;
	
	/**
	 * This is the constructor for model
	 */
	public Model() {
		snake = new Snake(15, 15);
	}
	
	//Getters
	public Snake getSnake() {
		return snake;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public Color getColor() {
		return color;
	}
}

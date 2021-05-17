package main.model;

import main.enums.Direction;

/**
 * This class defines the linked list that will represent the snake
 * 
 * @author akenney
 *
 */
public class Snake {
	//X and Y are integers between 0 and 14 that represent the grid space that the snake will be painted on
	private int x;
	private int y;
	private Direction direction;
	
	/**
	 * Snake Constructor
	 * 
	 * @param x
	 * @param y
	 * @param direction
	 */
	public Snake(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	//Getters
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	//Setters
	public void setDirection(Direction dir) {
		direction = dir;
	}
	
	//Move the snake piece based on its current direction
	public void move() {
		switch(direction) {
		case NORTH:
			y--;
			break;
		case EAST:
			x++;
			break;
		case SOUTH:
			y++;
			break;
		case WEST:
			x--;
			break;
		default:
			break;
		}
	}
}

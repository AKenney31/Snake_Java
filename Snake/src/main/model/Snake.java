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
	private Snake next;
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
		this.next = null;
	}
	
	/**
	 * This is a static method used to tag a new body piece to the end of the snake
	 * 
	 * @param head
	 */
	public static void addBodyPiece(Snake head) {
		Snake tmp;
		for(tmp = head; tmp.getNext() != null; tmp = tmp.getNext());
		tmp.setNext(new Snake(tmp.getX() + 1, tmp.getY() + 1, tmp.getDirection()));
	}
	//Getters
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Snake getNext() {
		return next;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	//Setters
	public void setNext(Snake next) {
		this.next = next;
	}
	
	public void setDirection(Direction dir) {
		direction = dir;
	}
}

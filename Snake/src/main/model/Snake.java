package main.model;

/**
 * This class defines the linked list that will represent the snake
 * 
 * @author akenney
 *
 */
public class Snake {
	private int x;
	private int y;
	private Snake next;
	
	public Snake(int x, int y) {
		this.x = x;
		this.y = y;
		this.next = null;
	}
	
	public void addBodyPiece() {
		
	}
	//Getters
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}

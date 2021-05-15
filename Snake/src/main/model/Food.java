package main.model;

/**
 * This class defines the food object
 * 
 * @author akenney
 *
 */

public class Food {
	private int x;
	private int y;
	
	/**
	 * Food Constructor
	 * @param x
	 * @param y
	 */
	public Food(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//getters
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}

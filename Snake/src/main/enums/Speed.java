package main.enums;

/**
 * 
 * This Enum represents the different speeds the snake can move in
 *
 */

public enum Speed {
    SLOW(5),
    MID(10),
    FAST(15);
	
	private int speed = 0;
	
	/**
	 * Direction the snake moves
	 * @param s, the direction
	 */
	
	private Speed(int s){
		speed = s;
	}
	
	/**
	 * gets the speed
	 * @return speed
	 */
	public int getSpeed() {
		return speed;
	}
}

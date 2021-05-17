package main.enums;

/**
 * 
 * This Enum represents the different directions the snake can move
 *
 */

public enum Direction {
    NORTH("NORTH"),
    SOUTH("SOUTH"),
    EAST("EAST"),
    WEST("WEST");
	
	private String name = null;
	
	/**
	 * Direction the snake moves
	 * @param s, the direction
	 */
	
	private Direction(String s){
		name = s;
	}
	
	/**
	 * gets the direction
	 * @return direction
	 */
	public String getName() {
		return name;
	}
}
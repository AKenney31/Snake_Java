package main.enums;

/**
 * 
 * This Enum represents the different screens that the program can switch between
 *
 */

public enum CurrentScreen {
    MENU("MENU"),
    GAME("GAME");
	
	private String name = null;
	
	/**
	 * CurrentScreen of the project
	 * @param s, name of screen
	 */
	
	private CurrentScreen(String s){
		name = s;
	}
	
	/**
	 * gets the enum of the screen
	 * @return name of screen
	 */
	public String getName() {
		return name;
	}
}

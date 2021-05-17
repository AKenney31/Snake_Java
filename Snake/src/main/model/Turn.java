package main.model;

import main.enums.Direction;

/**
 * This class represents the point in which a snake changes direction and it 
 * tells the other body parts where to turn
 * @author akenney
 *
 */
public class Turn {
	private Direction newDir;
	private int x;
	private int y;
	private int count = 0;
	public Turn(Direction newDir, int x, int y) {
		this.newDir = newDir;
		this.x = x;
		this.y = y;
	}
	
	public Direction getDir() {
		return newDir;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getCount() {
		return count;
	}
	
	public void incCount() {
		count++;
	}
}

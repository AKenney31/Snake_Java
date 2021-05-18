package main.model;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import main.enums.Direction;
import main.enums.Speed;


/**
 * This class contains details about our program including the user input and the snake object, and methods
 * that control the flow of the program
 * 
 * @author akenney
 *
 */
public class Model {
	private Color color;
	private ArrayList<Snake> snake = new ArrayList<Snake>();
	private Food food;
	private int snakeLength = 0;
	private ArrayList<Turn> turns = new ArrayList<Turn>();
	private Speed speed;
	private int highScore;
	
	/**
	 * This is the constructor for model
	 */
	public Model() {
		this.color = Color.RED;
		this.speed = Speed.SLOW;
		snake.add(new Snake(0, 7, Direction.EAST));
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
		snake.forEach(tmp -> {
			if(fx == tmp.getX() && fy == tmp.getY()) {
				makeFood();
			}
		});
		food = new Food(fx, fy);
	}
	
	//Getters
	public ArrayList<Snake> getSnake() {
		return snake;
	}
	
	public Food getFood() {
		return food;
	}
	
	public ArrayList<Turn> getTurns(){
		return turns;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getSnakeLength() {
		return snakeLength;
	}
	
	public int getHighScore() {
		return highScore;
	}
	
	public Speed getSpeed() {
		return speed;
	}
	
	//Setters
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setSpeed(Speed s) {
		speed = s;
	}
	
	public void setHighScore() {
		String endURL = "";
		switch(this.speed) {
		case SLOW:
			endURL = "slow.txt";
			break;
		case MID:
			endURL = "mid.txt";
			break;
		case FAST:
			endURL = "fast.txt";
			break;
		}
		try {
			File fi = new File("src/main/model/highscores/" + endURL);
			Scanner scan = new Scanner(fi);
			String num = scan.nextLine();
			highScore = Integer.parseInt(num);
			scan.close();
			System.out.println("High Score Loaded: " + highScore);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			highScore = 0;
		} catch (Exception e) {
			e.printStackTrace();
			highScore = 0;
		}
	}
	
	/**
	 * This method will add a new turn to the end of the turns array list
	 * @param t
	 */
	public void addTurn(Turn t) {
		turns.add(t);
	}
	
	/**
	 * This method will add 1 to the length variable and add a new body piece to the end of the snake
	 */
	public void incSnakeLength() {
		Snake tmp = snake.get(snakeLength);
		this.snakeLength++;
		int newSX, newSY;
		switch(tmp.getDirection()) {
		case NORTH:
			newSX = tmp.getX();
			newSY = tmp.getY() + 1;
			break;
		case EAST:
			newSX = tmp.getX() - 1;
			newSY = tmp.getY();
			break;
		case SOUTH:
			newSX = tmp.getX();
			newSY = tmp.getY() - 1;
			break;
		case WEST:
			newSX = tmp.getX() + 1;
			newSY = tmp.getY();
			break;
		default:
			newSX = 0;
			newSY = 0;
			break;
		}
		snake.add(new Snake(newSX, newSY, tmp.getDirection()));
	}
}

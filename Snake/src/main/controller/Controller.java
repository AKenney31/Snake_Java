package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.enums.CurrentScreen;
import main.model.Model;
import main.view.View;

/**
 * This class defines the handlers, and buttons that control the program.
 * 
 * @author akenney
 *
 */
public class Controller {
	View view;
	Model model;
	Boolean run = false;
	
	//Define Buttons
	JButton gameSc = new JButton("Game");
	JButton menuSc = new JButton("Menu");
	
	/**
	 * Controller constructor
	 * 
	 * @param view
	 */
	public Controller(View view) {
		this.view = view;
		this.model = new Model();
		
		//Button Handlers
		gameSc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.changeScreen(CurrentScreen.MENU);
			}
		});
		menuSc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//startGame();
				view.changeScreen(CurrentScreen.GAME);
			}
		});
	}
	
	/*
	 * public void startGame() { run = true; Thread gameThread = new Thread() {
	 * 
	 * }; }
	 */
	
	//Getters
	public JButton getGame() {
		return gameSc;
	}
	public JButton getMenu() {
		return menuSc;
	}
	public Model getModel() {
		return model;
	}
}

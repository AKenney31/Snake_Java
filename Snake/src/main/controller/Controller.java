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
				view.changeScreen(CurrentScreen.GAME);
			}
		});
	}
	
	//Getters
	public JButton getGame() {
		return gameSc;
	}
	public JButton getMenu() {
		return menuSc;
	}
}

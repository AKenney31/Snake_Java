package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.enums.CurrentScreen;
import main.view.View;

public class Controller {
	View view;
	JButton gameSc = new JButton("Game");
	JButton menuSc = new JButton("Menu");
	public Controller(View view) {
		this.view = view;
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
	public JButton getGame() {
		return gameSc;
	}
	public JButton getMenu() {
		return menuSc;
	}
}

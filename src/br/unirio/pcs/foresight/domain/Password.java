package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class Password {

	private JTextField textField;
	private String inputText = "";
	private MainMenu mainMenu;
	private Yellow player;
	private Image background;
	private double positionX, positionY;
	private boolean currentlyOnScreen = false;
	
	public Password (MainMenu mainMenu, Yellow player) {
		
		background = new ImageIcon("images/backgroundMenu/colored_land.png").getImage();
		textField = new JTextField(10);
		textField.setEnabled(true);
		inputText.concat(textField.getText());
		positionX = 0;
		positionY = -200;
		this.mainMenu = mainMenu;
		this.player = player;
		
	}

	public void update(double differenceTime) {
		if (player.key_states[KeyEvent.VK_ESCAPE]){
			deactivateScreen();
			mainMenu.activateScreen();
		}
	}
	
	public void draw(Graphics2D graphics2D) {
		graphics2D.drawImage(background, (int) positionX, (int) positionY, null);
		textField.paintComponents(graphics2D);
	}
	
	public void deactivateScreen() {
		currentlyOnScreen = false;
	}
	
	public void activateScreen() {
		currentlyOnScreen = true;
	}
	
	public boolean isActive() {
		return currentlyOnScreen;
	}
	
}

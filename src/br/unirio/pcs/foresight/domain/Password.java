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
	private Image background;
	private double positionX, positionY;
	private boolean currentlyOnScreen = false;
	
	public Password (MainMenu mainMenu) {
		this.background = new ImageIcon("images/backgroundMenu/colored_land.png").getImage();
		this.textField = new JTextField(10);
		this.inputText.concat(textField.getText());
		this.positionX = 0;
		this.positionY = -200;
		this.mainMenu = mainMenu;
	}

	public void update(double differenceTime) {
		if (InputListener.key_states[KeyEvent.VK_ESCAPE]){
			deactivateScreen();
			mainMenu.activateScreen();
		}
	}
	
	public void draw(Graphics2D graphics2D) {
		graphics2D.drawImage(background, (int) positionX, (int) positionY, null);
		textField.paint(graphics2D);
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

package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class SecondLevel {

	private MainMenu mainMenu;
	private Yellow yellow;
	private Image[] background = new Image[10];
	private double positionX;
	private double positionY;
	private boolean currentlyOnScreen;
	private AudioPlayer secondLevelBackgroundMusic;

	public SecondLevel (MainMenu mainMenu, Yellow yellow, AudioPlayer secondLevelBackgroundMusic) {

		this.mainMenu = mainMenu;
		this.yellow = yellow;
		this.secondLevelBackgroundMusic = secondLevelBackgroundMusic;
		for (int i = 0; i < 10; i++)
			background[i] = new ImageIcon("images/background/colored_desert.png").getImage();
		positionX = 0;
		positionY = -200;
	}
	
	public void update(double differenceTime) {
		if (yellow.getPositionX() > 400) {
			positionX -= yellow.getSpeedX() * differenceTime;
			yellow.setPositionX(yellow.getPositionX() - yellow.getSpeedX() * differenceTime);
		} else if (yellow.getPositionX() < 395){
			if (positionX != 0){
				positionX += yellow.getSpeedX() * differenceTime;
				yellow.setPositionX(yellow.getPositionX() + yellow.getSpeedX() * differenceTime);
			}
		}
		if (yellow.key_states[KeyEvent.VK_ESCAPE]){
			deactivateScreen();
			mainMenu.activateScreen();
		}
	}

	public void draw(Graphics2D graphics2D) {
		for (int i = 0; i < 10; i++)
			graphics2D.drawImage(background[i], (int) positionX + (i * 1024), (int) positionY + i, null);
	}
	
	public void deactivateScreen() {
		currentlyOnScreen = false;
		secondLevelBackgroundMusic.stop();
	}
	
	public void activateScreen() {
		currentlyOnScreen = true;
		secondLevelBackgroundMusic.playInLoop();
	}
	
	public boolean isActive() {
		return currentlyOnScreen;
	}

}
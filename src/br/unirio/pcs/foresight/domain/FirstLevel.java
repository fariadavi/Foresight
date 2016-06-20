package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class FirstLevel {

	private Image[] background = new Image[10];
	private double positionX;
	private double positionY;
	private Yellow player;
	public boolean currentlyOnScreen;
	private AudioPlayer firstLevelBackgroundMusic;
	private MainMenu mainMenu;

	public FirstLevel (MainMenu mainMenu, Yellow player, AudioPlayer firstLevelBackgroundMusic) {
		
		this.mainMenu = mainMenu;
		this.firstLevelBackgroundMusic = firstLevelBackgroundMusic;
		for (int i = 0; i < 10; i++)
			background[i] = new ImageIcon("images/background/colored_grass.png").getImage();
		positionX = 0;
		positionY = -200;
		this.player = player;
	}
	
	public void update(double differenceTime) {
		
		if (player.getPositionX() > 400) {
			positionX -= player.getSpeedX() * differenceTime;
			player.setPositionX(player.getPositionX() - player.getSpeedX() * differenceTime);
		} else if (player.getPositionX() < 395){
			if (positionX != 0){
				positionX += player.getSpeedX() * differenceTime;
				player.setPositionX(player.getPositionX() + player.getSpeedX() * differenceTime);
			}
		}
		if (player.key_states[KeyEvent.VK_ESCAPE]){
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
		firstLevelBackgroundMusic.stop();
	}
	
	public void activateScreen() {
		currentlyOnScreen = true;
		firstLevelBackgroundMusic.play();
	}
	
	public boolean isActive() {
		return currentlyOnScreen;
	}
}

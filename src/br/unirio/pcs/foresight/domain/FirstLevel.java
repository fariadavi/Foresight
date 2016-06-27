package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class FirstLevel {

//	private static final int INITIAL_MAP_POS = 0;
//	private static final int FINAL_MAP_POS = -1024 * 9;
	private Image background;
	private double positionX;
	private double positionY;
	private Yellow player;
	public boolean currentlyOnScreen;
	private AudioPlayer firstLevelBackgroundMusic;
	private MainMenu mainMenu;
	private TileSheet tileSheet;

	public FirstLevel(MainMenu mainMenu, Yellow player, AudioPlayer firstLevelBackgroundMusic) {
		
		this.mainMenu = mainMenu;
		this.player = player;
		this.firstLevelBackgroundMusic = firstLevelBackgroundMusic;
		this.background = new ImageIcon("images/background/colored_grass.png").getImage();
		this.positionX = 0;
		this.positionY = -80;
		this.tileSheet = TileSheet.getFromFile("firstLevelMap.txt", "images/platformer-extendedtiles-0/PNG Grass/Spritesheet/tilesheetGrass.png", player);
	}
	
	public void update(double differenceTime) {
		tileSheet.update(differenceTime);
		
		if (tileSheet.moveMap())
			positionX += ((player.getHorizontalDirection() * -1) * (player.getSpeedX()/6) * differenceTime);
			
		if (player.key_states[KeyEvent.VK_ESCAPE]){
			deactivateScreen();
			mainMenu.activateScreen();
		}
	}

	public void draw(Graphics2D graphics2D) {
		for (int i = 0; i < 2; i++)
			graphics2D.drawImage(background, (int) Math.round(positionX + (i * background.getWidth(null))), (int) Math.round(positionY), null);
		tileSheet.draw(graphics2D);
	}
	
	public void deactivateScreen() {
		currentlyOnScreen = false;
		firstLevelBackgroundMusic.stop();
	}
	
	public void activateScreen() {
		currentlyOnScreen = true;
		firstLevelBackgroundMusic.playInLoop();
	}
	
	public boolean isActive() {
		return currentlyOnScreen;
	}
}

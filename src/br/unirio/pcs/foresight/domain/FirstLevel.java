package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class FirstLevel {
	
	private Yellow player;
	private Image background;
	private double positionX;
	private double positionY;
	public boolean currentlyOnScreen;
	private AudioPlayer firstLevelBackgroundMusic;
	private MainMenu mainMenu;
	private TileSheet tileSheet;
	private List<Enemies> enemies = new ArrayList<Enemies>();
	
	
	public FirstLevel(MainMenu mainMenu) { // extender classe Levels e criar abstrações entre First e Second Level
		this.mainMenu = mainMenu;
		this.firstLevelBackgroundMusic = new AudioPlayer("soundtrack/FirstLevel.mp3");
        
		this.background = new ImageIcon("images/background/colored_grass.png").getImage();
		this.positionX = 0;
		this.positionY = -80;
		this.tileSheet = TileSheet.getFromFile("firstLevelMap.txt", "images/platformer-extendedtiles-0/PNG Grass/Spritesheet/tilesheetGrass.png", player);

		this.player = new Yellow();
		this.enemies.add(new Bee());
		this.enemies.add(new Fly());
		this.enemies.add(new SnakeLava());
		this.enemies.add(new Barnacle());
	}
	
	public void update(double differenceTime) {
		player.update(differenceTime);
		tileSheet.update(differenceTime);
		
		if (tileSheet.moveMap()) {
			positionX += ((player.getHorizontalDirection() * -1) * (player.getSpeedX()/6) * differenceTime);
			
		}
			
		if (InputListener.key_states[KeyEvent.VK_ESCAPE]){
			deactivateScreen();
			mainMenu.activateScreen();
		}
	}

	public void draw(Graphics2D graphics2D) {
		for (int i = 0; i < 2; i++)
			graphics2D.drawImage(background, (int) Math.round(positionX + (i * background.getWidth(null))), (int) Math.round(positionY), null);
		tileSheet.draw(graphics2D);
		player.draw(graphics2D);
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

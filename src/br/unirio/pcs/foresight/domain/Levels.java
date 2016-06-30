package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class Levels extends Screen {

	protected Yellow player;
//	protected Image background;
	protected double positionX;
	protected double positionY;
//	protected boolean currentlyOnScreen;
//	protected AudioPlayer backgroundMusic;
	protected MainMenu mainMenu;
	protected TileSheet tileSheet;
	protected List<Enemies> enemies = new ArrayList<Enemies>();
	
	public Levels(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}
	
	public void update(double differenceTime) {
		tileSheet.update(differenceTime);
		player.update(differenceTime);
		
		if (tileSheet.moveMap())
			positionX += ((player.getHorizontalDirection() * -1) * (player.getSpeedX()/6) * differenceTime);
			
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
		backgroundMusic.stop();
	}
	
	public void activateScreen() {
		currentlyOnScreen = true;
		backgroundMusic.playInLoop();
	}
	
	public boolean isActive() {
		return currentlyOnScreen;
	}
}

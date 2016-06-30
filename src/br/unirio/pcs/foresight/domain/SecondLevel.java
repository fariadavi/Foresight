package br.unirio.pcs.foresight.domain;

import javax.swing.ImageIcon;

public class SecondLevel extends Levels {
	
	public SecondLevel (MainMenu mainMenu) {
		super(mainMenu);
		
		this.backgroundMusic = new AudioPlayer("soundtrack/SecondLevel.mp3");
		this.background = new ImageIcon("images/background/colored_desert.png").getImage();
		this.positionX = 0;
		this.positionY = -200;

		this.player = new Yellow();
		this.enemies.add(new Bee());
		this.enemies.add(new Fly());
		this.enemies.add(new SnakeLava());
		this.enemies.add(new Barnacle());
		
		this.tileSheet = TileSheet.getFromFile("firstLevelMap.txt", "images/platformer-extendedtiles-0/PNG Desert/Spritesheet/tilesheetDesert.png", player);
	}
	
//	public void update(double differenceTime) {
//		if (player.getPositionX() > 400) {
//			positionX -= player.getSpeedX() * differenceTime;
//			player.setPositionX(player.getPositionX() - player.getSpeedX() * differenceTime);
//		} else if (player.getPositionX() < 395){
//			if (positionX != 0){
//				positionX += player.getSpeedX() * differenceTime;
//				player.setPositionX(player.getPositionX() + player.getSpeedX() * differenceTime);
//			}
//		}
//		if (InputListener.key_states[KeyEvent.VK_ESCAPE]){
//			deactivateScreen();
//			mainMenu.activateScreen();
//		}
//	}
//
//	public void draw(Graphics2D graphics2D) {
//		for (int i = 0; i < 2; i++)
//			graphics2D.drawImage(background, (int) positionX + (i * 1024), (int) positionY + i, null);
//	}
}
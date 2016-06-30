package br.unirio.pcs.foresight.domain;

import javax.swing.ImageIcon;

public class FirstLevel extends Levels {
	
	public FirstLevel(MainMenu mainMenu) {
		super(mainMenu);
		this.backgroundMusic = new AudioPlayer("soundtrack/FirstLevel.mp3");
        
		this.background = new ImageIcon("images/background/colored_grass.png").getImage();
		this.positionX = 0;
		this.positionY = -80;

		this.player = new Yellow();
		this.enemies.add(new Bee());
		this.enemies.add(new Fly());
		this.enemies.add(new SnakeLava());
		this.enemies.add(new Barnacle());
		
		this.tileSheet = TileSheet.getFromFile("firstLevelMap.txt", "images/platformer-extendedtiles-0/PNG Grass/Spritesheet/tilesheetGrass.png", player);
	}
}

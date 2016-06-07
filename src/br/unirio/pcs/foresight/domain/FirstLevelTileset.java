package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class FirstLevelTileset {

	private Image background;
	private int positionX;
	private int positionY;
	
	public FirstLevelTileset(){
		
		background = new ImageIcon("images/background/colored_grass.png").getImage();
		positionX = 0;
		positionY = -200;
	}
	
	public void update(double differenceTime) {
			positionX -= 100 * differenceTime;
	}
    
    public void draw(Graphics2D graphics2D) {
    	
    	graphics2D.drawImage(background, positionX, positionY, null);
    	graphics2D.drawString(String.valueOf(positionX), 10, 30);
	}
}

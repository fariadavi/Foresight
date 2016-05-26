package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class FirstLevelTileset {

	private Image ground;
	private int positionX;
	private int positionY;
	
	public FirstLevelTileset(){
		
		ground = new ImageIcon("images/test/terrain_test.jpg").getImage();
		positionX = 0;
		positionY = 570;
	}
	
	public void update(double differenceTime) {
		
	}
    
    public void draw(Graphics2D graphics2D) {
    	
    	graphics2D.drawImage(ground, positionX, positionY, null);
	}
}

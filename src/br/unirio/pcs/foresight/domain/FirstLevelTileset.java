package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class FirstLevelTileset {

	private Image background;
	private double positionX;
	private double positionY;
	private double frametime = 0;
	private Player player;

	public FirstLevelTileset(Player player, AudioPlayer firstLevelBackgroundMusic) {

		background = new ImageIcon("images/background/colored_grass.png").getImage();
		firstLevelBackgroundMusic.play();
		positionX = 0;
		positionY = -200;
		this.player = player;
	}

	public void update(double differenceTime) {
		frametime += differenceTime;
		
		if (player.getPositionX() > 700) {
			positionX -= player.getSpeedX() * differenceTime;
			player.setPositionX(player.getPositionX() - player.getSpeedX() * differenceTime);
		} else if (player.getPositionX() < 300){
			if (positionX != 0){
				positionX += player.getSpeedX() * differenceTime;
				player.setPositionX(player.getPositionX() + player.getSpeedX() * differenceTime);
			}
		}
	}

	public void draw(Graphics2D graphics2D) {
		graphics2D.drawImage(background, (int) positionX, (int) positionY, null);
		graphics2D.drawImage(background, (int) positionX + (1 * 1024), (int) positionY + 1, null);
		graphics2D.drawImage(background, (int) positionX + (2 * 1024), (int) positionY + 2, null);
		graphics2D.drawImage(background, (int) positionX + (3 * 1024), (int) positionY + 3, null);
		graphics2D.drawImage(background, (int) positionX + (4 * 1024), (int) positionY + 4, null);
		graphics2D.drawImage(background, (int) positionX + (5 * 1024), (int) positionY + 5, null);
		graphics2D.drawImage(background, (int) positionX + (6 * 1024), (int) positionY + 6, null);
		graphics2D.drawImage(background, (int) positionX + (7 * 1024), (int) positionY + 7, null);
		graphics2D.drawImage(background, (int) positionX + (8 * 1024), (int) positionY + 8, null);
		graphics2D.drawImage(background, (int) positionX + (9 * 1024), (int) positionY + 9, null);
		graphics2D.drawImage(background, (int) positionX + (10 * 1024), (int) positionY + 10, null);
		graphics2D.drawImage(background, (int) positionX + (11 * 1024), (int) positionY + 11, null);
		graphics2D.drawImage(background, (int) positionX + (12 * 1024), (int) positionY + 12, null);
		graphics2D.drawImage(background, (int) positionX + (13 * 1024), (int) positionY + 13, null);
		graphics2D.drawImage(background, (int) positionX + (15 * 1024), (int) positionY + 14, null);
		graphics2D.drawString(String.valueOf(positionX), 10, 30);
		graphics2D.drawString(String.valueOf(frametime), 10, 40);
		graphics2D.drawString(String.valueOf(player.getPositionX()), 10, 50);
	}

}

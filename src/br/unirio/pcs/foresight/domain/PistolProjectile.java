package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class PistolProjectile extends Projectile{

	
	private Image bulletSprite;
	
	public PistolProjectile() {
		super();
		bulletSprite = new ImageIcon("images/weapons/laserPurpleDot.png").getImage();
	}

	@Override
	public void update(double differenceTime) {
		positionX += 500 * differenceTime;
	}

	@Override
	public void draw(Graphics2D graphics2D) {
		graphics2D.drawImage(bulletSprite, (int) positionX + 53,(int) positionY + 15, null);
	}

}

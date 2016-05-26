package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class PistolProjectile extends Projectile{

	
	private Image bulletSprite;
	
	public PistolProjectile(double speed) {
		super(speed);
		bulletSprite = new ImageIcon("images/weapons/laserPurpleDot.png").getImage();
	}

	@Override
	public void update(double differenceTime) {
		
	}

	@Override
	public void draw(Graphics2D graphics2D) {
		graphics2D.drawImage(bulletSprite, (int) positionX - 44,(int) positionY + 11, null);
	}

}

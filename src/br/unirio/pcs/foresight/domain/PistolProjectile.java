package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class PistolProjectile extends Projectile{

	private static final int BULLET_SPEED = 500;
	private Yellow yellow;
	private Image bulletSprite;
	private double pistolProjectileFrametime = 0;
	
	public PistolProjectile(Yellow yellow) {
		super();
		bulletSprite = new ImageIcon("images/weapons/laserPurpleDot.png").getImage();
		this.yellow = yellow;
	}

	@Override
	public void update(double differenceTime) {
		
		pistolProjectileFrametime += differenceTime;
		if (yellow.getProjectile()){
			if (pistolProjectileFrametime > 0.5){
				if (!active()){
					activate();
					setPositionX(yellow.getPositionX());
					setPositionY(yellow.getPositionY());
					pistolProjectileFrametime = 0;
				}
			}
		}
		if (active()) {
			positionX += BULLET_SPEED * differenceTime;
		if (getPositionX() > 1000) {
				deactivate();
			}
		}
	}

	@Override
	public void draw(Graphics2D graphics2D) {
		if (active())
			graphics2D.drawImage(bulletSprite, (int) positionX + 53,(int) positionY + 15, null);
		graphics2D.drawString(String.valueOf(pistolProjectileFrametime), 100, 100);
	}

}

package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class PistolProjectile extends Projectile {

	private static final int BULLET_SPEED = 500;
	private Yellow yellow;
	private Barnacle barnacle;
	private Image bulletSprite;
	private double pistolProjectileFrametime = 0;
	
	public PistolProjectile(Yellow yellow, Barnacle barnacle) {
		super(BULLET_SPEED);
		this.yellow = yellow;
		this.barnacle = barnacle;
		bulletSprite = new ImageIcon("images/weapons/laserPurpleDot.png").getImage();
	}

	@Override
	public void update(double differenceTime) {
		
		pistolProjectileFrametime += differenceTime;
//		if (yellow.getProjectile()){
			if (pistolProjectileFrametime > 0.1){
				if (!active()){
					activate();
					setPositionX(yellow.getPositionX());
					setPositionY(yellow.getPositionY());
					pistolProjectileFrametime = 0;
//				}
			}
		}
		if (active()) {
			positionX += BULLET_SPEED * differenceTime;
			if (CheckBoxCollision(positionX, positionY, 9, 10, barnacle.getPositionX() - 72, barnacle.getPositionY() - 25, 58, 40)){
				deactivate();
				barnacle.die();
			}
		}
		if (getPositionX() > 1000) 
			deactivate();
	}
	@Override
	public void draw(Graphics2D graphics2D) {
		if (active())
			graphics2D.drawImage(bulletSprite, (int) positionX + 83,(int) positionY + 43, null);
	}
	
	private boolean CheckBoxCollision(double positionX1, double positionY1, double width1, double height1, double positionX2, double positionY2, double width2, double height2) {
        return ((positionX1 < positionX2 + width2) && (positionX2 < positionX1 + width1) && (positionY1 < positionY2 + height2) && (positionY2 < positionY1 + height1));
    }
	
}

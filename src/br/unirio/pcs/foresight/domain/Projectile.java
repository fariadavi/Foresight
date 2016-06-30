package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;

public abstract class Projectile {

	protected double speed;
	protected double positionX;
	protected double positionY;
	protected boolean active = false;
	protected Image bulletSprite;

	public Projectile() {
		
	}

	public double getPositionX() {
		return positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public boolean active() {
		return active;
	}

	public void activate() {
		active = true;
	}

	public void deactivate() {
		active = false;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public abstract void update(double differenceTime);

	public abstract void draw(Graphics2D graphics2D);
}

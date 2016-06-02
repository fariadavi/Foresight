package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;

public abstract class Sprite {

	protected boolean visible;
	protected double positionX;
	protected double positionY;
	protected double speedX;
	protected double speedY;
	protected double gravity = 0.5;
	
	public Sprite(double positionX, double positionY, double speedX, double speedY) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.speedX = speedX;
		this.speedY = speedY;
		visible = true;
	}
	
	public void die() {
		visible = false;
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	public double getPositionX() {
		return positionX;
	}
	
	public double getPositionY() {
		return positionY;
	}
	
	public void setSpeedX(double speed) {
		this.speedX = speed;
	}
	
	public double getSpeedX() {
		return speedX;
	}
	
	public void setSpeedY(double speed) {
		this.speedY = speed;
	}
	
	public double getSpeedY() {
		return speedY;
	}
	
	public abstract void update(double differenceTime);
    
    public abstract void draw(Graphics2D graphics2D);
	
}

package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;

public abstract class Sprite {

	protected boolean visible;
	protected double positionX;
	protected double positionY;
	protected double speed;
	
	public Sprite(double positionX, double positionY, double speed) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.speed = speed;
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
	
	public abstract void update(double differenceTime);
    
    public abstract void draw(Graphics2D graphics2D);
	
}

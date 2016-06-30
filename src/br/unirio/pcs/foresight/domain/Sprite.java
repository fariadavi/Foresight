package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;

public abstract class Sprite {

	protected boolean alive = false;
	protected double positionX, positionY, speedX, speedY, gravity = 0.8;
	protected int life;
	
	public Sprite(double positionX, double positionY, double speedX, double speedY, int life) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.speedX = speedX;
		this.speedY = speedY;
		this.life = life;
	}
	
	public void die() {
		alive = false;
		life = -1;
	}
	
	public void spawn() {
		alive = true;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}
	
	public void setPositionY(double positionY) {
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

package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Fly extends Sprite {

	private static final int FLY_POSITION_X = 800;
	private static final int FLY_POSITION_Y = 250;
	private static final int FLY_RUN_SPEED = 100;
	private static final int FLY_JUMP_SPEED = 0;
	private static final int FLY_LIFE = 1;
	private Image[] flySprite = new Image[2];
	private int steps = 0;
	private double frametime = 0;
		
	public Fly () {
		
		super(FLY_POSITION_X, FLY_POSITION_Y, FLY_RUN_SPEED, FLY_JUMP_SPEED, FLY_LIFE);
		flySprite[0] = new ImageIcon("images/enemys/fly.png").getImage();
		flySprite[1] = new ImageIcon("images/enemys/fly_fly.png").getImage();
	}

	@Override
	public void update(double differenceTime) {
		frametime += differenceTime;
	
		if (frametime > 0.2 && steps < 1){
			steps++;
		} else if (frametime > 0.4 && steps > 0){
			steps--;
			frametime = 0;
		}
		if (positionX > 700)
			positionX -= speedX * differenceTime;
		if (positionX < 200)
			positionX += speedX * differenceTime;
	}

	@Override
	public void draw(Graphics2D graphics2D) {
		if (alive)
			graphics2D.drawImage(flySprite[steps], (int) positionX, (int) positionY, null);
	}
}

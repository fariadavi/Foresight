package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class SnakeLava extends Sprite {
	private static final int SNAKELAVA_POSITION_X = 800;
	private static final int SNAKELAVA_POSITION_Y = 500;
	private static final int SNAKELAVA_RUN_SPEED = 100;
	private static final int SNAKELAVA_JUMP_SPEED = 0;
	private static final int SNAKELAVA_LIFE = 1;
	private Image[] snakeLavaSprite = new Image[2];
	private int steps = 0;
	private double frametime = 0;
		
	public SnakeLava () {
		
		super(SNAKELAVA_POSITION_X, SNAKELAVA_POSITION_Y, SNAKELAVA_RUN_SPEED, SNAKELAVA_JUMP_SPEED, SNAKELAVA_LIFE);
		snakeLavaSprite[0] = new ImageIcon("images/enemys/snakeLava.png").getImage();
		snakeLavaSprite[1] = new ImageIcon("images/enemys/snakeLava_ani.png").getImage();
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
			graphics2D.drawImage(snakeLavaSprite[steps], (int) positionX, (int) positionY, null);
	}
}

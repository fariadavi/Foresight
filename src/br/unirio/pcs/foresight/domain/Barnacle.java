package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Barnacle extends Sprite {

	private static final int BARNACLE_POSITION_X = 500;
	private static final int BARNACLE_POSITION_Y = 625;
	private static final int BARNACLE_RUN_SPEED = 0;
	private static final int BARNACLE_JUMP_SPEED = 0;
	private static final int BARNACLE_LIFE = 1;
	private Image[] barnacleSprite = new Image[2];
	private int steps = 0;
	private double frametime = 0;
	
	public Barnacle() {
		
		super(BARNACLE_POSITION_X, BARNACLE_POSITION_Y, BARNACLE_RUN_SPEED, BARNACLE_JUMP_SPEED, BARNACLE_LIFE);
		barnacleSprite[0] = new ImageIcon("images/enemys/barnacle.png").getImage();
		barnacleSprite[1] = new ImageIcon("images/enemys/barnacle_bite.png").getImage();
	}

	@Override
	public void update(double differenceTime) {
		frametime += differenceTime;
		if (frametime > 0.3 && steps < 1){
			steps++;
		} else if (frametime > 0.6 && steps > 0){
			steps--;
			frametime = 0;
		}
	}

	@Override
	public void draw(Graphics2D graphics2D) {
		if (alive)
			graphics2D.drawImage(barnacleSprite[steps], (int) positionX, (int) positionY, null);
	}
}

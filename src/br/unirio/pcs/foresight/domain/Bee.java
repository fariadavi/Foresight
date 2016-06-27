package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Bee extends Enemies {

	private static final int BEE_POSITION_X = 600;
	private static final int BEE_POSITION_Y = 100;
	private static final int BEE_RUN_SPEED = 0;
	private static final int BEE_JUMP_SPEED = 0;
	private static final int BEE_LIFE = 1;
	private Image[] beeSprite = new Image[2];
	private int steps = 0;
	private double frametime = 0;
		
	public Bee() {
		super(BEE_POSITION_X, BEE_POSITION_Y, BEE_RUN_SPEED, BEE_JUMP_SPEED, BEE_LIFE);
		beeSprite[0] = new ImageIcon("images/enemys/bee.png").getImage();
		beeSprite[1] = new ImageIcon("images/enemys/bee_fly.png").getImage();
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
	}

	@Override
	public void draw(Graphics2D graphics2D) {
		if (alive)
			graphics2D.drawImage(beeSprite[steps], (int) positionX, (int) positionY, null);
	}
}

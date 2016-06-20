package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Yellow extends Sprite {

	private static final int YELLOW_POSITION_X = 30;
	private static final int YELLOW_POSITION_Y = 600;
	private static final int YELLOW_RUN_SPEED = 250;
	private static final int YELLOW_JUMP_SPEED = 300;
	private static final int YELLOW_LIFE = 3;
	private Image[] yellowSprite = new Image[10];
	private Image[] yellowGunSprite = new Image[2];
	private Image[] hudYellow = new Image[15];
	public boolean[] key_states = new boolean[256];
	private double frametime = 0;
	private boolean Projectile;
	private int steps = 0;
	private double jumpVelocity = 0.0;
	private boolean onGround;

	public Yellow() {
		super(YELLOW_POSITION_X, YELLOW_POSITION_Y, YELLOW_RUN_SPEED, YELLOW_JUMP_SPEED, YELLOW_LIFE);
		yellowSprite[0] = new ImageIcon("images/characters/yellow/alienYellow_stand_right.png").getImage();
		yellowSprite[1] = new ImageIcon("images/characters/yellow/alienYellow_walk_right0.png").getImage();
		yellowSprite[2] = new ImageIcon("images/characters/yellow/alienYellow_walk_right1.png").getImage();
		yellowSprite[3] = new ImageIcon("images/characters/yellow/alienYellow_jump_right.png").getImage();
		yellowSprite[4] = new ImageIcon("images/characters/yellow/alienYellow_hurt_right.png").getImage();

		yellowSprite[5] = new ImageIcon("images/characters/yellow/alienYellow_stand_left.png").getImage();
		yellowSprite[6] = new ImageIcon("images/characters/yellow/alienYellow_walk_left0.png").getImage();
		yellowSprite[7] = new ImageIcon("images/characters/yellow/alienYellow_walk_left1.png").getImage();
		yellowSprite[8] = new ImageIcon("images/characters/yellow/alienYellow_jump_left.png").getImage();
		yellowSprite[9] = new ImageIcon("images/characters/yellow/alienYellow_hurt_left.png").getImage();

		yellowGunSprite[0] = new ImageIcon("images/weapons/raygunPurple_right.png").getImage();
		yellowGunSprite[1] = new ImageIcon("images/weapons/raygunPurple_left.png").getImage();
		
		hudYellow[0] = new ImageIcon("images/HUD/hudPlayer_yellow.png").getImage();
		
		hudYellow[1] = new ImageIcon("images/HUD/hudHeart_full.png").getImage();
		hudYellow[2] = new ImageIcon("images/HUD/hudHeart_half.png").getImage();
		hudYellow[3] = new ImageIcon("images/HUD/hudHeart_empty.png").getImage();
		
		hudYellow[4] = new ImageIcon("images/HUD/hud0.png").getImage();
		hudYellow[5] = new ImageIcon("images/HUD/hud1.png").getImage();
		hudYellow[6] = new ImageIcon("images/HUD/hud2.png").getImage();
		hudYellow[7] = new ImageIcon("images/HUD/hud3.png").getImage();
		hudYellow[8] = new ImageIcon("images/HUD/hud4.png").getImage();
		hudYellow[9] = new ImageIcon("images/HUD/hud5.png").getImage();
		hudYellow[10] = new ImageIcon("images/HUD/hud6.png").getImage();
		hudYellow[11] = new ImageIcon("images/HUD/hud7.png").getImage();
		hudYellow[12] = new ImageIcon("images/HUD/hud8.png").getImage();
		hudYellow[13] = new ImageIcon("images/HUD/hud9.png").getImage();
		hudYellow[14] = new ImageIcon("images/HUD/hudX.png").getImage();
	}

	public boolean getProjectile() {
		return Projectile;
	}

	private void walk(double differenceTime, int firstStep,	int horizontalDirection) {
		frametime += differenceTime;
		if (frametime > 0.1) {
			steps++;
			if (steps < firstStep || steps > (firstStep + 1))
				steps = firstStep;
			frametime = 0;
		}
		positionX += (horizontalDirection * speedY * differenceTime);
	}
	
	@Override
	public void update(double differenceTime) {
		Projectile = false;
		if ((key_states[KeyEvent.VK_RIGHT]) && (positionX < 956))
			walk(differenceTime, 1, 1);
		else if ((key_states[KeyEvent.VK_LEFT]) && (positionX > -4))
			walk(differenceTime, 6, -1);
		else if (steps >= 0 && steps < 3)
			steps = 0;
		else if (steps >= 5 && steps < 8)
			steps = 5;

		// Player movements controller
		if (key_states[KeyEvent.VK_UP]) {
			if (onGround) {
				jumpVelocity = -1 * speedY * differenceTime;
				onGround = false;
			}
			if (key_states[KeyEvent.VK_RIGHT] || (steps >= 0 && steps < 5))
				steps = 3;
			else if (key_states[KeyEvent.VK_LEFT] || (steps >= 5 && steps < 10))
				steps = 8;
			else if (!(onGround) && key_states[KeyEvent.VK_RIGHT])
				steps = 3;
		} 

		if (onGround) {
			if (steps == 3)
				steps = 0;
			else if (steps == 8)
				steps = 5;
			jumpVelocity = 0;
		} else {
			jumpVelocity += gravity * differenceTime;
		}

		positionY += jumpVelocity;

		onGround = positionY >= 600;

		// Player shots controller
		if ((key_states[KeyEvent.VK_CONTROL])) {
			Projectile = true;
		}
	}

	@Override
	public void draw(Graphics2D graphics2D) {

		graphics2D.drawImage(hudYellow[1], 0, 0, null);
		graphics2D.drawImage(hudYellow[1], 55, 0, null);
		graphics2D.drawImage(hudYellow[1], 110, 0, null);
		
		graphics2D.drawImage(hudYellow[14], 770, 5, null);
		graphics2D.drawImage(hudYellow[4], 798, 0, null);
		graphics2D.drawImage(hudYellow[4], 826, 0, null);
		graphics2D.drawImage(hudYellow[4], 854, 0, null);
		graphics2D.drawImage(hudYellow[4], 882, 0, null);
		graphics2D.drawImage(hudYellow[4], 910, 0, null);
		graphics2D.drawImage(hudYellow[4], 938, 0, null);
		graphics2D.drawImage(hudYellow[4], 966, 0, null);
		
		graphics2D.drawImage(yellowSprite[steps], (int) positionX,(int) positionY, null);
		if (steps < 5)
			graphics2D.drawImage(yellowGunSprite[0], (int) positionX + 30,(int) positionY + 11, null);
		else
			graphics2D.drawImage(yellowGunSprite[1], (int) positionX - 44,(int) positionY + 11, null);

	}
}

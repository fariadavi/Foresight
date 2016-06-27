package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player extends Sprite {

	private static final int PLAYER_POSITION_X = 30;
	private static final int PLAYER_POSITION_Y = 500;
	private static final int PLAYER_RUN_SPEED = 250;
	private static final int PLAYER_JUMP_SPEED = 300;
	private Image[] playerSprite = new Image[10];
	private Image[] playerGunSprite = new Image[2];
	public boolean[] key_states = new boolean[256];
	private double frametime = 0;
	private boolean Projectile;
	private int steps = 0;
	private double jumpVelocity = 0.0;
	private boolean onGround;

	public Player() {
		super(PLAYER_POSITION_X, PLAYER_POSITION_Y, PLAYER_RUN_SPEED, PLAYER_JUMP_SPEED, 1);
		playerSprite[0] = new ImageIcon("images/characters/yellow/alienYellow_stand_right.png").getImage();
		playerSprite[1] = new ImageIcon("images/characters/yellow/alienYellow_walk_right0.png").getImage();
		playerSprite[2] = new ImageIcon("images/characters/yellow/alienYellow_walk_right1.png").getImage();
		playerSprite[3] = new ImageIcon("images/characters/yellow/alienYellow_jump_right.png").getImage();
		playerSprite[4] = new ImageIcon("images/characters/yellow/alienYellow_hurt_right.png").getImage();

		playerSprite[5] = new ImageIcon("images/characters/yellow/alienYellow_stand_left.png").getImage();
		playerSprite[6] = new ImageIcon("images/characters/yellow/alienYellow_walk_left0.png").getImage();
		playerSprite[7] = new ImageIcon("images/characters/yellow/alienYellow_walk_left1.png").getImage();
		playerSprite[8] = new ImageIcon("images/characters/yellow/alienYellow_jump_left.png").getImage();
		playerSprite[9] = new ImageIcon("images/characters/yellow/alienYellow_hurt_left.png").getImage();

		playerGunSprite[0] = new ImageIcon("images/weapons/raygunPurple_right.png").getImage();
		playerGunSprite[1] = new ImageIcon("images/weapons/raygunPurple_left.png").getImage();
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
		if ((positionX < 956) && (positionX > -4)) {
			int horizontalDirection = getHorizontalDirection();
			walk(differenceTime, horizontalDirection == -1 ? 6 : 1, horizontalDirection);
		}
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

	public int getHorizontalDirection() {
		return key_states[KeyEvent.VK_RIGHT] ? 1 : (key_states[KeyEvent.VK_LEFT] ? -1 : 0);
	}
	
	@Override
	public void draw(Graphics2D graphics2D) {

		graphics2D.drawImage(playerSprite[steps], (int) positionX,(int) positionY, null);
		if (steps < 5)
			graphics2D.drawImage(playerGunSprite[0], (int) positionX + 30,(int) positionY + 11, null);
		else
			graphics2D.drawImage(playerGunSprite[1], (int) positionX - 44,(int) positionY + 11, null);

		graphics2D.drawString(String.valueOf(jumpVelocity), 10, 10);
		graphics2D.drawString(String.valueOf(positionX), 10, 20);

	}
}

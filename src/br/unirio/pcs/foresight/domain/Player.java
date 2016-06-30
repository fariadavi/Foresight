package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Set;

import javax.swing.ImageIcon;

public abstract class Player extends Sprite {

	private Set<Integer> colision;
	
//	private boolean Projectile;
	protected Image[] playerSprite = new Image[10];
	protected Image[] playerGunSprite = new Image[2];
	private Image[] hud = new Image[15];
	private double frametime = 0;
	private int steps = 0;
	private double jumpVelocity = 0.0;
	public boolean onGround, collidingUp, collidingLeft, collidingRight, collidingBottom;
	
	public Player(double positionX, double positionY, double speedX, double speedY, int life) {
		super(positionX, positionY, speedX, speedY, life);
	
		playerGunSprite[0] = new ImageIcon("images/weapons/raygunPurple_right.png").getImage();
		playerGunSprite[1] = new ImageIcon("images/weapons/raygunPurple_left.png").getImage();
		
		hud[0] = new ImageIcon("images/HUD/hudPlayer_yellow.png").getImage();
		
		hud[1] = new ImageIcon("images/HUD/hudHeart_full.png").getImage(); //Separar classe de HUD
		hud[2] = new ImageIcon("images/HUD/hudHeart_half.png").getImage();
		hud[3] = new ImageIcon("images/HUD/hudHeart_empty.png").getImage();
		
		hud[4] = new ImageIcon("images/HUD/hud0.png").getImage();
		hud[5] = new ImageIcon("images/HUD/hud1.png").getImage();
		hud[6] = new ImageIcon("images/HUD/hud2.png").getImage();
		hud[7] = new ImageIcon("images/HUD/hud3.png").getImage();
		hud[8] = new ImageIcon("images/HUD/hud4.png").getImage();
		hud[9] = new ImageIcon("images/HUD/hud5.png").getImage();
		hud[10] = new ImageIcon("images/HUD/hud6.png").getImage();
		hud[11] = new ImageIcon("images/HUD/hud7.png").getImage();
		hud[12] = new ImageIcon("images/HUD/hud8.png").getImage();
		hud[13] = new ImageIcon("images/HUD/hud9.png").getImage();
		hud[14] = new ImageIcon("images/HUD/hudX.png").getImage();
	}

//	public boolean getProjectile() {
//		return Projectile;
//	}

	private void walk(double differenceTime, int horizontalDirection) {
		frametime += differenceTime;
		if (frametime > 0.1) {
			steps++;
			frametime = 0;
		}
		int firstStep = horizontalDirection == -1 ? 6 : 1;
		if (steps < firstStep || steps > (firstStep + 1))
			steps = firstStep;
		
		if(horizontalDirection == 1 && positionX < 956 || horizontalDirection == -1 && positionX > -4)
			positionX += (horizontalDirection * speedX * differenceTime);
	}
	
	@Override
	public void update(double differenceTime) {
//		Projectile = false;
		
		int horizontalDirection = getHorizontalDirection();
		
//		setColisions(colision);
		
//		if (horizontalDirection != 0)
		walk(differenceTime, horizontalDirection);
		if (horizontalDirection == 0) {
			if (steps > 0 && steps < 3)
				steps = 0;
			else if (steps > 5 && steps < 8)
				steps = 5;
		}

		// Player movements controller
		if (onGround) {
			if (InputListener.key_states[KeyEvent.VK_UP]) {
				jumpVelocity = -1 * speedY / 10;
			} 
			if (InputListener.key_states[KeyEvent.VK_UP] || horizontalDirection != 0)
				onGround = false;
			
			
			if (horizontalDirection == 1) {
				if((steps >= 0 && steps < 5) || !(onGround))
					steps = 3;
			} else if (horizontalDirection == -1) {
				if((steps >= 5 && steps < 10) || !(onGround)) 
					steps = 8;
			}
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
		
		// Player shots controller
//		if ((InputListener.key_states[KeyEvent.VK_CONTROL])) {
//			Projectile = true;
//		}
	}
	
	public int getHorizontalDirection() {
		return InputListener.key_states[KeyEvent.VK_RIGHT] ? 1 : (InputListener.key_states[KeyEvent.VK_LEFT] ? -1 : 0);
	}

	@Override
	public void draw(Graphics2D graphics2D) {
		graphics2D.drawImage(hud[1], 0, 0, null);
		graphics2D.drawImage(hud[1], 55, 0, null);
		graphics2D.drawImage(hud[1], 110, 0, null);
		
		graphics2D.drawImage(hud[14], 770, 5, null);
		graphics2D.drawImage(hud[4], 798, 0, null);
		graphics2D.drawImage(hud[4], 826, 0, null);
		graphics2D.drawImage(hud[4], 854, 0, null);
		graphics2D.drawImage(hud[4], 882, 0, null);
		graphics2D.drawImage(hud[4], 910, 0, null);
		graphics2D.drawImage(hud[4], 938, 0, null);
		graphics2D.drawImage(hud[4], 966, 0, null);
		
		graphics2D.drawImage(getImage(), (int) positionX,(int) positionY, null);
		if (steps < 5)
			graphics2D.drawImage(playerGunSprite[0], (int) positionX + 30,(int) positionY + 11, null);
		else
			graphics2D.drawImage(playerGunSprite[1], (int) positionX - 44,(int) positionY + 11, null);

	}

	public Image getImage() {
		return playerSprite[steps];
	}

	public void setColision(Set<Integer> listColision) {
		colision = listColision;
	}

	public void setOnGround(boolean ground) {
		onGround = ground;
	}
}

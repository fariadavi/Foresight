package br.unirio.pcs.foresight.domain;

import javax.swing.ImageIcon;

public class Yellow extends Player {

	private static final int YELLOW_POSITION_X = 30;
	private static final int YELLOW_POSITION_Y = 200;
	private static final int YELLOW_RUN_SPEED = 250;
	private static final int YELLOW_JUMP_SPEED = 5;
	private static final int YELLOW_LIFE = 3;

	public Yellow() {
		super(YELLOW_POSITION_X, YELLOW_POSITION_Y, YELLOW_RUN_SPEED, YELLOW_JUMP_SPEED, YELLOW_LIFE);
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
}

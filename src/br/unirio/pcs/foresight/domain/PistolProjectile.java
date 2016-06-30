package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;

import br.unirio.pcs.foresight.domain.dto.Score;

public class PistolProjectile extends Projectile{

	private static final int BULLET_SPEED = 500;
	private Yellow yellow;
	private Barnacle barnacles;
	private Records records;
	private double pistolProjectileFrametime = 0;
	
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	
	public PistolProjectile(Yellow yellow, Barnacle barnacle, Records records) {
		super();
		this.yellow = yellow;
		this.barnacles = barnacle;
		this.records = records;
		bulletSprite = new ImageIcon("images/weapons/laserPurpleDot.png").getImage();
	}

	@Override
	public void update(double differenceTime) {
		
		pistolProjectileFrametime += differenceTime;
		if (yellow.getProjectile()){
			if (pistolProjectileFrametime > 0.5){
				
				Projectile newProjectile = new PistolProjectile(yellow, barnacles, records);
				this.projectiles.add(newProjectile);
				
				for(Projectile projetil : this.projectiles){
					if (!projetil.active()){
						projetil.activate();
						projetil.setPositionX(yellow.getPositionX());
						projetil.setPositionY(yellow.getPositionY());
						pistolProjectileFrametime = 0;
					}
				}
			}
		}
		
		for(Projectile projectile : this.projectiles){
			if (projectile.active()) {
				projectile.positionX += BULLET_SPEED * differenceTime;
				
				for(Sprite barnacle : this.barnacles.getBarnacles()){
					if (CheckBoxCollision(projectile.positionX, projectile.positionY, 9, 10, barnacle.getPositionX() - 72, barnacle.getPositionY() - 25, 58, 40)){
						projectile.deactivate();
						this.barnacles.remove(barnacle);
						Score newScore = new Score("DFT", 10);
						records.addScore(newScore); 
					}
				}
			}
			if (projectile.positionX > 1000){
				projectile.deactivate();
			}
		}
	}
	@Override
	public void draw(Graphics2D graphics2D) {
		Projectile[] tiles = this.projectiles.toArray(new Projectile[this.projectiles.size()]);
		
		List<Projectile> clone = Arrays.asList(tiles); 
		
		for(Projectile projectile :clone){
			if (projectile.active()){
				graphics2D.drawImage(projectile.bulletSprite, (int) projectile.positionX + 83,(int) projectile.positionY + 43, null);
			}
		}
	}
	
	private boolean CheckBoxCollision(double positionX1, double positionY1, double width1, double height1, double positionX2, double positionY2, double width2, double height2) {
        return ((positionX1 < positionX2 + width2) && (positionX2 < positionX1 + width1) && (positionY1 < positionY2 + height2) && (positionY2 < positionY1 + height1));
    }
	
}

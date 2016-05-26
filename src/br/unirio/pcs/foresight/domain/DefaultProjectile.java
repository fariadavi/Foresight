package br.unirio.pcs.foresight.domain;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class DefaultProjectile extends Projectile {

	private static final double DEFAULT_PROJECTILE_SPEED = 500;
	private boolean enemyShot;
    private int way;
	
	public DefaultProjectile(boolean enemyShot) {
		super(DEFAULT_PROJECTILE_SPEED);
		this.enemyShot = enemyShot;
	}
    
	@Override
    public void update(double differenceTime){
		if(enemyShot)
            way = -1;
        else
            way = 1;
        positionX += way * speed * differenceTime;
    }

	@Override
	public void draw(Graphics2D graphics2D){
		graphics2D.setColor(Color.RED);
        if(enemyShot)
        	graphics2D.fill(new Rectangle2D.Double((int)positionX, (int)positionY, 10, 3));
        else
        	graphics2D.fill(new Rectangle2D.Double((int)positionX, (int)positionY, 10, 3));
	}
}

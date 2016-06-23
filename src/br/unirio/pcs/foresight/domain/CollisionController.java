package br.unirio.pcs.foresight.domain;

import java.util.List;

import br.unirio.pcs.foresight.domain.dto.Score;

public class CollisionController {

	private Barnacle barnacle;
	private PistolProjectile pistolProjectile;
	private List<Score> scores;
	
	public void CheckCollision() {
		if (CheckBoxCollision(pistolProjectile.getPositionX(), pistolProjectile.getPositionY(), 9, 10, barnacle.getPositionX() - 58, barnacle.getPositionY() - 25, 58, 40) && barnacle.isAlive()){
			pistolProjectile.deactivate();
			barnacle.die();
			scores.add(100, null);
		}
	}
	
	private boolean CheckBoxCollision(double positionX1, double positionY1, double width1, double height1, double positionX2, double positionY2, double width2, double height2) {
        return ((positionX1 < positionX2 + width2) && (positionX2 < positionX1 + width1) && (positionY1 < positionY2 + height2) && (positionY2 < positionY1 + height1));
    }
	
}

package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	private List<Sprite> barnacles = new ArrayList<Sprite>();
	private Yellow yellow;
	
	public Barnacle(Yellow yellow) {
		super(BARNACLE_POSITION_X, BARNACLE_POSITION_Y, BARNACLE_RUN_SPEED, BARNACLE_JUMP_SPEED, BARNACLE_LIFE);
		this.yellow = yellow;
		barnacleSprite[0] = new ImageIcon("images/enemys/barnacle.png").getImage();
		barnacleSprite[1] = new ImageIcon("images/enemys/barnacle_bite.png").getImage();
	}
	
	public void init(){
		Sprite newBarnacle = new Barnacle(yellow);
		this.barnacles.add(newBarnacle);
		
		
		for (Sprite barnacle : this.barnacles){
            if(!barnacle.isAlive()){
            	barnacle.spawn();
            	break;
            }
            if(barnacle.isAlive()) {
                if(CheckBoxCollision(yellow.getPositionX(), yellow.getPositionY(), 60, 30, barnacle.positionX + 8, barnacle.positionY + 8, 48, 48)){
                	barnacle.die();
                }
            }
        }
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

		for (Sprite barnacle : this.barnacles){
			if (barnacle.alive) {
				graphics2D.drawImage(barnacleSprite[steps], (int) positionX, (int) positionY, null);
			}
		}
	}
	
	private boolean CheckBoxCollision(double positionX1, double positionY1, double width1, double height1, double positionX2, double positionY2, double width2, double height2) {
        return ((positionX1 < positionX2 + width2) && (positionX2 < positionX1 + width1) && (positionY1 < positionY2 + height2) && (positionY2 < positionY1 + height1));
    }

	public List<Sprite> getBarnacles() {
		Sprite[] array = this.barnacles.toArray(new Sprite[this.barnacles.size()]);
		return Arrays.asList(array);
	}

	public void remove(Sprite barnacle) {
		barnacle.die();
		this.barnacles.remove(barnacle);
	}
	
}

package br.unirio.pcs.foresight.domain;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

import br.unirio.pcs.foresight.domain.dto.Score;

public class Records {
	
	private Yellow yellow;
	private MainMenu mainMenu;
	private List<Score> scores;
	private double positionX = 365, positionY = 610;
	private boolean currentlyOnScreen = false;

	public Records(MainMenu mainMenu, Yellow yellow) {
		this.mainMenu = mainMenu;
		this.yellow = yellow;
		this.scores = FileController.recoversScore();
	}

	public void update(double differenceTime) {
		if (positionY > 80)
			positionY -= 180 * differenceTime;
		if (yellow.key_states[KeyEvent.VK_ESCAPE]) {
			deactivateScreen();
			mainMenu.activateScreen();
		}
	}

	public void draw(Graphics2D graphics2D) {
		if (currentlyOnScreen && this.scores!=null) {
			
			graphics2D.setPaint(new GradientPaint((float) positionX, (float) positionY, Color.black, (float) positionX, (float) positionY + 400, Color.lightGray));
			graphics2D.fill(new RoundRectangle2D.Double(positionX, positionY, 350, 420, 50, 50));
			graphics2D.setColor(Color.black);
			graphics2D.draw(new RoundRectangle2D.Double(positionX, positionY, 350, 420, 50, 50));
			graphics2D.setColor(new Color(254, 233, 0));
			graphics2D.fill(new Rectangle2D.Double(positionX + 120, positionY + 400, 110, 40));
			graphics2D.setColor(new Color(254, 233, 0, 250));
			graphics2D.setFont(new Font("Tahoma", Font.BOLD, 36));
			graphics2D.drawString("HIGH SCORES", (int) positionX + 48, (int) positionY + 70);
			graphics2D.setFont(new Font("Tahoma", Font.BOLD, 24));
			
			for(int i = 0, h = (int) positionY + 110; i < this.scores.size(); i++, h += 30){
				Score score = this.scores.get(i);
				graphics2D.drawString(score.getPlayer() + " ", (int) positionX + 80, h);
				graphics2D.drawString(" " + score.getScore(), (int) positionX + 230, h);
			}
			
			graphics2D.setColor(Color.black);
			graphics2D.draw(new Rectangle2D.Double(positionX + 120, positionY + 400, 110, 40));
			graphics2D.drawString("CLOSE", (int) positionX + 136, (int) positionY + 430);
		}
	}
	
	public void deactivateScreen() {
		currentlyOnScreen  = false;
		positionX = 365;
		positionY = 610;
	}
	
	public void activateScreen() {
		currentlyOnScreen = true;
	}
	
	public boolean isActive() {
		return currentlyOnScreen;
	}
	
	public void updateUserName(String newName){
		for(Score savedScore : this.scores){
			if(savedScore.getPlayer().equalsIgnoreCase("yellow")){
				savedScore.setPlayer(newName);
			}
		}
		
		FileController.saveScores(this.scores);
	}

	public void addScore(Score newScore) {
		for(Score savedScore : this.scores){
			if(savedScore.getPlayer().equalsIgnoreCase(newScore.getPlayer())){
				savedScore.setScore(savedScore.getScore() + newScore.getScore());
				FileController.saveScores(this.scores);
				return;
			}
		}
		
		this.scores.add(newScore);
		
		FileController.saveScores(this.scores);
	}
	
}

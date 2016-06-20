package br.unirio.pcs.foresight.domain;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class MainMenu {
	
	private Yellow yellow;
	
	private Password password;
	private Recordes recordes;
	private FirstLevel firstLevel;
	@SuppressWarnings("unused")
	private SecondLevel secondLevel;
	
	private FontAnimation fontAnimation = new FontAnimation();
	private Font fontMenuOptions = new Font("Verdana", Font.PLAIN, 50);
	
	private Image background;
	
	private float thickness = 4;
	private double positionX, positionY, buttonFrametime;
	private boolean newGameIsSelected = true, paswordIsSelected, 
			recordesIsSelected, quitIsSelected, currentlyOnScreen = true;

	private AudioPlayer mainMenuBackgroundMusic;
	

	public MainMenu (Yellow yellow, AudioPlayer mainMenuBackgroundMusic) {

		this.yellow = yellow;
		this.mainMenuBackgroundMusic = mainMenuBackgroundMusic;
		background = new ImageIcon("images/backgroundMenu/colored_land.png").getImage();
		positionX = 0;
		positionY = -200;
	}
	
	public void update(double differenceTime) {
		
		buttonFrametime += differenceTime;
		
		if (pressDown()){
			if (newGameIsSelected) {
				newGameIsSelected = false;
				paswordIsSelected = true;
				buttonFrametime = 0;
			} else if (paswordIsSelected && buttonFrametime > 0.1) {
				paswordIsSelected = false;
				recordesIsSelected = true;
				buttonFrametime = 0;
			} else if (recordesIsSelected && buttonFrametime > 0.1) {
				recordesIsSelected = false;
				quitIsSelected = true;
				buttonFrametime = 0;
			}
		}
		if (pressUp()){
			if(quitIsSelected && buttonFrametime > 0.1) {
				quitIsSelected = false;
				recordesIsSelected = true;
				buttonFrametime = 0;
			} else if (recordesIsSelected && buttonFrametime > 0.1) {
				recordesIsSelected = false;
				paswordIsSelected = true;
				buttonFrametime = 0;
			} else if (paswordIsSelected && buttonFrametime > 0.1) {
				paswordIsSelected = false;
				newGameIsSelected = true;
				buttonFrametime = 0;
			} 
		}
		if (pressEnter()) {
			if (newGameIsSelected){
				deactivateScreen();
				firstLevel.activateScreen();
				mainMenuBackgroundMusic.stop();
			} else if (paswordIsSelected){
				deactivateScreen();
				password.activateScreen();
			} else if (recordesIsSelected) {
				deactivateScreen();
				recordes.activateScreen();
			} else if(quitIsSelected)
				System.exit(0);
		}
	}

	public void draw(Graphics2D graphics2D) {
		
		graphics2D.drawImage(background, (int) positionX, (int) positionY, null);
		fontAnimation.draw(graphics2D);
		if (fontAnimation.getFontSize() == 101){
			if (newGameIsSelected) {
				Stroke oldStroke = graphics2D.getStroke();
				graphics2D.setStroke(new BasicStroke(thickness));
				graphics2D.drawRoundRect(365, 308, 277, 50, 10, 10);
				graphics2D.setStroke(oldStroke);
			} else if (paswordIsSelected) {
				Stroke oldStroke = graphics2D.getStroke();
				graphics2D.setStroke(new BasicStroke(thickness));
				graphics2D.drawRoundRect(365, 358, 277, 50, 10, 10);
				graphics2D.setStroke(oldStroke);
			} else if (recordesIsSelected) {
				Stroke oldStroke = graphics2D.getStroke();
				graphics2D.setStroke(new BasicStroke(thickness));
				graphics2D.drawRoundRect(365, 408, 277, 50, 10, 10);
				graphics2D.setStroke(oldStroke);
			} else if (quitIsSelected) {
				Stroke oldStroke = graphics2D.getStroke();
				graphics2D.setStroke(new BasicStroke(thickness));
				graphics2D.drawRoundRect(365, 458, 277, 50, 10, 10);
				graphics2D.setStroke(oldStroke);
			}
			graphics2D.setColor(Color.BLACK);
			graphics2D.setFont(fontMenuOptions);
			graphics2D.drawString("New Game", 365, 350);
			graphics2D.drawString("Password", 385, 400);
			graphics2D.drawString("Records", 405, 450);
			graphics2D.drawString("Quit", 445, 500);
		}
		
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	public void setRecordes(Recordes recordes) {
		this.recordes = recordes;
	}

	public void setFirstLevel(FirstLevel firstLevel) {
		this.firstLevel = firstLevel;
	}

	public void setSecondLevel(SecondLevel secondLevel) {
		this.secondLevel = secondLevel;
	}
	
	public void deactivateScreen() {
		currentlyOnScreen = false;
	}
	
	public void activateScreen() {
		currentlyOnScreen = true;
		mainMenuBackgroundMusic.playInLoop();
	}
	
	public boolean isActive() {
		return currentlyOnScreen;
	}
	
	private boolean pressEnter() {
		return yellow.key_states[KeyEvent.VK_ENTER];
	}

	private boolean pressUp() {
		return yellow.key_states[KeyEvent.VK_UP];
	}

	private boolean pressDown() {
		return yellow.key_states[KeyEvent.VK_DOWN];
	}
	
}

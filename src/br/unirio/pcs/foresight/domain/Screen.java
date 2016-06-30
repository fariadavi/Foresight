package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;

public abstract class Screen {
	
	protected AudioPlayer backgroundMusic;
	protected Image background;
	protected boolean currentlyOnScreen;
	
	public abstract void update(double differenceTime);
	public abstract void draw(Graphics2D graphics2D);
	
	public void deactivateScreen() {
		currentlyOnScreen = false;
	}
	
	public void activateScreen() {
		currentlyOnScreen = true;
	}
	
	public boolean isActive() {
		return currentlyOnScreen;
	}
}

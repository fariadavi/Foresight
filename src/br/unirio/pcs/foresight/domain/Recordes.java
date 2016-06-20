package br.unirio.pcs.foresight.domain;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class Recordes {
	
	private Yellow player;
	private MainMenu mainMenu;
	private double px = 365, py = 610;
	private String[] nomes;
	private int[] pts;
	private boolean currentlyOnScreen = false;

	public Recordes(MainMenu mainMenu, Yellow player) {
		this.mainMenu = mainMenu;
		this.player = player;
	}

	public void update(double dt) {
		if (py > 80)
			py -= 180 * dt;
		if (player.key_states[KeyEvent.VK_ESCAPE]){
			deactivateScreen();
			mainMenu.activateScreen();;
		}
	}

	public void draw(Graphics2D g2d) {
		if (currentlyOnScreen) {
			g2d.setPaint(new GradientPaint((float) px, (float) py, Color.black, (float) px, (float) py + 400, Color.lightGray));
			g2d.fill(new RoundRectangle2D.Double(px, py, 350, 420, 50, 50));
			g2d.setColor(Color.black);
			g2d.draw(new RoundRectangle2D.Double(px, py, 350, 420, 50, 50));
			g2d.setColor(new Color(254, 233, 0));
			g2d.fill(new Rectangle2D.Double(px + 120, py + 400, 110, 40));
			g2d.setColor(new Color(254, 233, 0, 250));
			g2d.setFont(new Font("Tahoma", Font.BOLD, 36));
			g2d.drawString("HIGH SCORES", (int) px + 48, (int) py + 70);
			g2d.setFont(new Font("Tahoma", Font.BOLD, 24));
			
			/*
			porque diabos nao funciona!?
			for (int i = 0, h = (int) py + 110; i < 10; i++, h += 30) {
				g2d.drawString(nomes[i] + " ", (int) px + 80, h);
				g2d.drawString(" " + pts[i], (int) px + 230, h);
			}
			*/
			
			g2d.setColor(Color.black);
			g2d.draw(new Rectangle2D.Double(px + 120, py + 400, 110, 40));
			g2d.drawString("CLOSE", (int) px + 136, (int) py + 430);
		}
	}
	
	public void deactivateScreen() {
		currentlyOnScreen  = false;
		px = 365;
		py = 610;
	}
	
	public void activateScreen() {
		currentlyOnScreen = true;
		if (nomes == null || pts == null) {
			nomes = ManipulaArquivo.getNomes();
			pts = ManipulaArquivo.getPts();
		}
	}
	
	public boolean isActive() {
		return currentlyOnScreen;
	}
	
}

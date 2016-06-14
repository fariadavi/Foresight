package br.unirio.pcs.foresight.domain;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class Recordes {
	
	private double px = 365, py = 610;
	private boolean ativo = false;
	private String[] nomes;
	private int[] pts;

	public Recordes() {
	}

	public boolean ativo() {
		return ativo;
	}

	public void ativar() {
		ativo = true;
		if (nomes == null || pts == null) {
			nomes = ManipulaArquivo.getNomes();
			pts = ManipulaArquivo.getPts();
		}
	}

	public void desativar() {
		ativo = false;
		px = 365;
		py = 610;
	}

	public void update(double dt) {
		if (py > 80)
			py -= 180 * dt;
	}

	public void draw(Graphics2D g2d) {
		if (ativo) {
			
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
			
//			for (int i = 0, h = (int) py + 110; i < 10; i++, h += 30) {
//				g2d.drawString(nomes[i] + " ", (int) px + 80, h);
//				g2d.drawString(" " + pts[i], (int) px + 230, h);
//			}
			
			g2d.setColor(Color.black);
			g2d.draw(new Rectangle2D.Double(px + 120, py + 400, 110, 40));
			g2d.drawString("CLOSE", (int) px + 136, (int) py + 430);
			
		}
		
	}
	
}

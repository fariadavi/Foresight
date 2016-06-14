package br.unirio.pcs.foresight.domain;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class FontAnimation implements ActionListener {

	Timer timer;
	int fontSize = 1;
	float alpha = 1;
	
	public FontAnimation() {
		timer = new Timer(8, this);
		timer.setInitialDelay(1);
		timer.start();
	}
	
	public void draw(Graphics2D graphics2D) {
		graphics2D.setColor(Color.BLACK);
	    Font font = new Font("Dialog", Font.BOLD, fontSize);
	    graphics2D.setFont(font);
	    FontMetrics fontMetrics = graphics2D.getFontMetrics();
	    String gameTitle = "Foresight";
	    int width = 1000;
	    int height = 350;
	    int stringWidth = fontMetrics.stringWidth(gameTitle);
	    graphics2D.drawString(gameTitle, (width - stringWidth) / 2, height / 2);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {		
		fontSize += 1;
		alpha -= 0.0001;
		if (fontSize > 100)
			timer.stop();
	}
	
	public double getFontSize() {
		return fontSize;
	}
	
}

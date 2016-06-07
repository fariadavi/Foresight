package br.unirio.pcs.foresight.interfaces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import br.unirio.pcs.foresight.domain.FirstLevelTileset;
import br.unirio.pcs.foresight.domain.Player;

public class Board extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private Player player;
	private FirstLevelTileset firstLevel;
	
	private Image[] backgroundMenu = new Image[3];
	private boolean mainMenu = true, password = false, recordes = false, start = false;

	public Board() {

		setDoubleBuffered(true);
		setFocusable(true);
		load();
		new Thread(this).start();
	}

	@Override
	public void paintComponent(Graphics graphics) {

		super.paintComponent(graphics);
		draw(graphics);
	}

	public void run() {

		double beforeTime, differenceTime = 0;
		beforeTime = System.currentTimeMillis();
		while (true) {
			update(differenceTime / 1000);
			repaint();
			try {
				Thread.sleep(1);
			} catch (InterruptedException exception) {
				System.out.println(exception.getMessage());
			}
			differenceTime = (System.currentTimeMillis() - beforeTime);
			beforeTime = System.currentTimeMillis();
		}
		
	}
	
	private class KeyboardAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent event) {
            player.key_states[event.getKeyCode()] = false;
        }
        
        @Override
        public void keyPressed(KeyEvent event) {
            player.key_states[event.getKeyCode()] = true;
        }
    }
	
	private void newGame() {
		start = true;
		mainMenu = false;
	}
	
	private void load() {
        setBackground(Color.BLACK);
        backgroundMenu[0] = new ImageIcon("images/backgroundMenu/colored_land.png").getImage();
        backgroundMenu[1] = new ImageIcon("images/imagensMenu/passwordMenu.png").getImage();
        backgroundMenu[2] = new ImageIcon("images/imagensMenu/recordsMenu.png").getImage();
        
        addKeyListener(new KeyboardAdapter());
        
        player = new Player();
        firstLevel = new FirstLevelTileset();
	}
	
	private void update(double differenceTime) {
		if(start) {
			firstLevel.update(differenceTime);
			player.update(differenceTime);
		} else {
			if(mainMenu) {
				if(player.key_states[KeyEvent.VK_N]) {
					newGame();
				} else if(player.key_states[KeyEvent.VK_P]) {
					password = true;
					mainMenu = false;
				} else if(player.key_states[KeyEvent.VK_R]) {
					recordes = true;
					mainMenu = false;
				} else if(player.key_states[KeyEvent.VK_Q]) {
					System.exit(0);
				}
			} else if (password) {
				if(player.key_states[KeyEvent.VK_B]) {
					mainMenu = true;
					password = false;
				}
			} else if (recordes) {
				if(player.key_states[KeyEvent.VK_B]) {
					mainMenu = true;
					recordes = false;
				}
			} 
		}
	}
	
	private void draw(Graphics graphics) {
		
		Graphics2D graphics2D = (Graphics2D)graphics;
		if(start) {
			firstLevel.draw(graphics2D);
			player.draw(graphics2D);
		} else {
			if(mainMenu)
				graphics2D.drawImage(backgroundMenu[0], 0, -100, null);
			else if (password)
				graphics2D.drawImage(backgroundMenu[1], 0, 0, null);
			else if (recordes)
				graphics2D.drawImage(backgroundMenu[2], 0, 0, null);
		}
	}
}

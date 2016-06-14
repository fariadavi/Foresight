package br.unirio.pcs.foresight.interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import br.unirio.pcs.foresight.domain.AudioPlayer;
import br.unirio.pcs.foresight.domain.FirstLevelTileset;
import br.unirio.pcs.foresight.domain.FontAnimation;
import br.unirio.pcs.foresight.domain.PistolProjectile;
import br.unirio.pcs.foresight.domain.Player;
import br.unirio.pcs.foresight.domain.Recordes;

public class Board extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private Player player;
	private PistolProjectile[] pistolProjectile = new PistolProjectile[20];
	private double pistolProjectileFrametime = 0;
	private Recordes recordes;
	private FirstLevelTileset firstLevel;
	private FontAnimation fontAnimation;
	private Font fontMenuOptions = new Font("Verdana", Font.PLAIN, 50);
	private AudioPlayer firstLevelBackgroundMusic;
	private Image[] backgroundMenu = new Image[3];
	private boolean mainMenu = true, password = false, start = false;

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
        for (int i = 0; i < 20; i++)
        	pistolProjectile[i] = new PistolProjectile();
        recordes = new Recordes();
        firstLevelBackgroundMusic = new AudioPlayer("C:/Projetos/Foresight-master/soundtrack/HeroicAge.mp3");
        firstLevel = new FirstLevelTileset(player, firstLevelBackgroundMusic);
        fontAnimation = new FontAnimation();
	}
	
	private void update(double differenceTime) {
		if(start) {
			firstLevel.update(differenceTime);
			player.update(differenceTime);
			pistolProjectileFrametime += differenceTime;
			for (int i = 0; i < 20; i++){
				if (player.getProjectile()){
					if (pistolProjectileFrametime > 0.5){
						if (!pistolProjectile[i].active()){
							pistolProjectile[i].activate();
							pistolProjectile[i].setPositionX(player.getPositionX());
							pistolProjectile[i].setPositionY(player.getPositionY());
							pistolProjectileFrametime = 0;
							break;
						}
					}
				}
				if (pistolProjectile[i].active()){
					pistolProjectile[i].update(differenceTime);
				} else if (pistolProjectile[i].getPositionX() > 900){
					pistolProjectile[i].deactivate();
				}
			}
		} else {
			if(mainMenu) {
				if(player.key_states[KeyEvent.VK_N]) {
					newGame();
				} else if(player.key_states[KeyEvent.VK_P]) {
					password = true;
					mainMenu = false;
				} else if(player.key_states[KeyEvent.VK_R]) {
					recordes.ativar();
					mainMenu = false;
				} else if(player.key_states[KeyEvent.VK_Q]) {
					System.exit(0);
				}
			} else if (password) {
				if(player.key_states[KeyEvent.VK_B]) {
					mainMenu = true;
					password = false;
				}
			} else if (recordes.ativo()) {
				if(player.key_states[KeyEvent.VK_B]) {
					mainMenu = true;
					recordes.desativar();;
				}
			}
		}
	}
	
	private void draw(Graphics graphics) {
		
		Graphics2D graphics2D = (Graphics2D)graphics;
		if(start) {
			firstLevel.draw(graphics2D);
			player.draw(graphics2D);
			for (int i = 0; i < 20; i++) {
				if (pistolProjectile[i].active())
					pistolProjectile[i].draw(graphics2D);
			}
		} else {
			if(mainMenu){
				graphics2D.drawImage(backgroundMenu[0], 0, -100, null);
				fontAnimation.draw(graphics2D);
				if (fontAnimation.getFontSize() == 101){
					graphics2D.setFont(fontMenuOptions);
					graphics2D.drawString("New Game (Press N)", 240, 350);
					graphics2D.drawString("Password (Press P)", 240, 400);
					graphics2D.drawString("Records (Press R)", 240, 450);
					graphics2D.drawString("Quit (Press Q)", 240, 500);
				}
			}
			else if (password)
				graphics2D.drawImage(backgroundMenu[1], 0, 0, null);
			else if (recordes.ativo())
				recordes.draw(graphics2D);
		}
	}
}

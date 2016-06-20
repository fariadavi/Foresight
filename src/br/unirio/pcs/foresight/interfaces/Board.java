package br.unirio.pcs.foresight.interfaces;

/*Fazer um jogo de waves?*/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import br.unirio.pcs.foresight.domain.AudioPlayer;
import br.unirio.pcs.foresight.domain.Barnacle;
import br.unirio.pcs.foresight.domain.FirstLevel;
import br.unirio.pcs.foresight.domain.MainMenu;
import br.unirio.pcs.foresight.domain.Password;
import br.unirio.pcs.foresight.domain.PistolProjectile;
import br.unirio.pcs.foresight.domain.Yellow;
import br.unirio.pcs.foresight.domain.Recordes;
import br.unirio.pcs.foresight.domain.SecondLevel;

public class Board extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1L;
	private Yellow yellow;
	private Barnacle barnacle;
	private PistolProjectile[] pistolProjectile = new PistolProjectile[20];
	
	private MainMenu mainMenu;
	private Password password;
	private Recordes recordes;
	private FirstLevel firstLevel;
	private SecondLevel secondLevel;
	
	private AudioPlayer mainMenuBackgroundMusic;
	private AudioPlayer firstLevelBackgroundMusic;
	private AudioPlayer secondLevelBackgroundMusic;

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
            yellow.key_states[event.getKeyCode()] = false;
        }
        
        @Override
        public void keyPressed(KeyEvent event) {
            yellow.key_states[event.getKeyCode()] = true;
        }
    }
	
	private void load() {
        setBackground(Color.BLACK);
        addKeyListener(new KeyboardAdapter());
        //Carrega player
        yellow = new Yellow();
        barnacle = new Barnacle();
        //Carrega projetil da pistola
        for (int i = 0; i < 20; i++)
        	pistolProjectile[i] = new PistolProjectile(yellow);
        //Carrega musicas do background
        mainMenuBackgroundMusic = new AudioPlayer("E:/Nova pasta/Foresight-master2.0/soundtrack/MainMenu.mp3");
        firstLevelBackgroundMusic = new AudioPlayer("E:/Nova pasta/Foresight-master2.0/soundtrack/FirstLevel.mp3");
        secondLevelBackgroundMusic = new AudioPlayer("E:/Nova pasta/Foresight-master2.0/soundtrack/SecondLevel.mp3");
        //Carrega telas
        mainMenu = new MainMenu(yellow, mainMenuBackgroundMusic);
        password = new Password(mainMenu, yellow);
        recordes = new Recordes(mainMenu, yellow);
        firstLevel = new FirstLevel(mainMenu, yellow, firstLevelBackgroundMusic);
        secondLevel = new SecondLevel(mainMenu, yellow, secondLevelBackgroundMusic);
        mainMenu.setPassword(password);
        mainMenu.setRecordes(recordes);
        mainMenu.setFirstLevel(firstLevel);
        mainMenu.setSecondLevel(secondLevel);
	}
	
	private void update(double differenceTime) {
		if (mainMenu.isActive()) {
			mainMenu.update(differenceTime);
		} else if (password.isActive()){
			password.update(differenceTime);
		} else if (recordes.isActive()) {
			recordes.update(differenceTime);
		} else if (firstLevel.isActive()){
			firstLevel.update(differenceTime);
			yellow.update(differenceTime);
			for (int i = 0; i < 20; i++){
				pistolProjectile[i].update(differenceTime);
			} 
		}
		if (!barnacle.isAlive())
			barnacle.spawn();
		barnacle.update(differenceTime);
	}
	
	private void draw(Graphics graphics) {
		
		Graphics2D graphics2D = (Graphics2D)graphics;
		//Desenha tela do menu
		if (mainMenu.isActive()) {
			mainMenu.draw(graphics2D);
		//Desenha tela do password
		} else if (password.isActive()) {
			password.draw(graphics2D);
		//Desenha tela de recordes
		} else if (recordes.isActive()) {
			recordes.draw(graphics2D);
		//Desenha tela do primeiro level
		} else if (firstLevel.isActive()) {
			firstLevel.draw(graphics2D);
			yellow.draw(graphics2D);
			for (int i = 0; i < 20; i++) {
				pistolProjectile[i].draw(graphics2D);
			}
			barnacle.draw(graphics2D);
		//Desenha tela do segundo level
		} else if (secondLevel.isActive()) {
			secondLevel.draw(graphics2D);
			yellow.draw(graphics2D);
			for (int i = 0; i < 20; i++) {
				pistolProjectile[i].draw(graphics2D);
			}
		}
		
	}
}

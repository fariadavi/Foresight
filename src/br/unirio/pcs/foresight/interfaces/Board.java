package br.unirio.pcs.foresight.interfaces;

/*Fazer um jogo de waves?*/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import br.unirio.pcs.foresight.domain.FirstLevel;
import br.unirio.pcs.foresight.domain.InputListener;
import br.unirio.pcs.foresight.domain.MainMenu;
import br.unirio.pcs.foresight.domain.SecondLevel;

public class Board extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1L;
//	private Yellow yellow;
//	private Bee bee;
//	private Fly fly;
//	private SnakeLava snakeLava;
//	private Barnacle barnacle;
//	private PistolProjectile pistolProjectile;

	private MainMenu mainMenu;
//	private Password password;
//	private Records recordes;
	private FirstLevel firstLevel;
	private SecondLevel secondLevel;
	
//	private AudioPlayer mainMenuBackgroundMusic; //passar audios pra dentro das classes proprietarias? MainMenu
//	private AudioPlayer firstLevelBackgroundMusic; //passar para FirstLevel 
//	private AudioPlayer secondLevelBackgroundMusic; //passar para SecondLevel

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
            InputListener.key_states[event.getKeyCode()] = false;
        }
        
        @Override
        public void keyPressed(KeyEvent event) {
        	InputListener.key_states[event.getKeyCode()] = true;
        }
    }
	
	private void load() {
        setBackground(Color.BLACK);
        addKeyListener(new KeyboardAdapter());
        
//        yellow = new Yellow();
        
//        bee = new Bee();
//        fly = new Fly();
//        snakeLava = new SnakeLava();
//        barnacle = new Barnacle();
        
//        pistolProjectile = new PistolProjectile(yellow, barnacle);
        
//        mainMenuBackgroundMusic = new AudioPlayer("soundtrack/MainMenu.mp3");
//        firstLevelBackgroundMusic = new AudioPlayer("soundtrack/FirstLevel.mp3");
//        secondLevelBackgroundMusic = new AudioPlayer("soundtrack/SecondLevel.mp3");
        
        mainMenu = new MainMenu();
//        password = new Password(mainMenu, inputListener);
//        recordes = new Records(mainMenu, inputListener);
        firstLevel = new FirstLevel(mainMenu);
        secondLevel = new SecondLevel(mainMenu);
        
//        mainMenu.setPassword(password);
//        mainMenu.setRecordes(recordes);
        mainMenu.setFirstLevel(firstLevel);
        mainMenu.setSecondLevel(secondLevel);
	}
	
	private void update(double differenceTime) {
		if (mainMenu.isActive()) {
			mainMenu.update(differenceTime);
//		} else if (password.isActive()){
//			password.update(differenceTime);
//		} else if (recordes.isActive()) {
//			recordes.update(differenceTime);
		} else if (firstLevel.isActive()){
			firstLevel.update(differenceTime);
//			yellow.update(differenceTime);
//			pistolProjectile.update(differenceTime);
		}
//		if (!barnacle.isAlive())
//			barnacle.spawn();
//		barnacle.update(differenceTime);
//		if (!bee.isAlive())
//			bee.spawn();
//		bee.update(differenceTime);
//		if (!fly.isAlive())
//			fly.spawn();
//		fly.update(differenceTime);
//		if (!snakeLava.isAlive())
//			snakeLava.spawn();
//		snakeLava.update(differenceTime);
	}
	
	private void draw(Graphics graphics) {
		
		Graphics2D graphics2D = (Graphics2D)graphics;
		//Desenha tela do menu
		if (mainMenu.isActive()) {
			mainMenu.draw(graphics2D);
		//Desenha tela do password
//		} else if (password.isActive()) {
//			password.draw(graphics2D);
		//Desenha tela de recordes
//		} else if (recordes.isActive()) {
//			recordes.draw(graphics2D);
		//Desenha tela do primeiro level
		} else if (firstLevel.isActive()) {
			firstLevel.draw(graphics2D);
//			yellow.draw(graphics2D);
//			bee.draw(graphics2D);
//			fly.draw(graphics2D);
//			snakeLava.draw(graphics2D);
//			barnacle.draw(graphics2D);
//			pistolProjectile.draw(graphics2D);
		//Desenha tela do segundo level
		} else if (secondLevel.isActive()) {
			secondLevel.draw(graphics2D);
//			yellow.draw(graphics2D);
//			pistolProjectile.draw(graphics2D);
		}
	}
}

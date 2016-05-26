package br.unirio.pcs.foresight.application;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import br.unirio.pcs.foresight.interfaces.Board;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int BOARD_WIDTH = 1024;
	private static final int BOARD_HEIGTH = 768;
	private static final String ICON_PATH = "images/windowIcon.png";

	private MainFrame() {
		
		ImageIcon imageIcon = new ImageIcon(ICON_PATH);
		add(new Board());
	
		setSize(BOARD_WIDTH, BOARD_HEIGTH);
		setMinimumSize(getSize());
		setResizable(false);
		setTitle("Foresight");
		setIconImage(imageIcon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}
	
}

package br.unirio.pcs.foresight.domain;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import br.unirio.pcs.foresight.application.MainFrame;

public class TileSheet {

	private int[][] map;
	private Image tileSheet;
	private Yellow player;
	private double positionX;

	public TileSheet(int width, int height, Yellow player) {
		this.player = player;
		map = new int[height][width];
	}

	public static TileSheet getFromFile(String filePath, String mapPath, Yellow player) {

		TileSheet sheet = null;

		List<List<Integer>> tempLayout = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String currentLine;

			while ((currentLine = br.readLine()) != null) {
				if (currentLine.isEmpty())
					continue;

				List<Integer> row = new ArrayList<Integer>();

				String[] values = currentLine.trim().split("\t");

				for (String string : values) {
					if (!string.isEmpty()) {
						int id = Integer.parseInt(string);

						row.add(id - 1);
					}
				}
				tempLayout.add(row);
			}
		} catch (IOException ex) {
			System.out.println("Num vai da nao");
		}

		int width = tempLayout.get(0).size();
		int height = tempLayout.size();

		sheet = new TileSheet(width, height, player);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				sheet.map[y][x] = tempLayout.get(y).get(x);
			}
		}

		sheet.tileSheet = new ImageIcon(mapPath).getImage();

		return sheet;
	}

	public boolean moveMap() {
		int horizontalDirection = player.getHorizontalDirection();
		return horizontalDirection == -1 && positionX <= 0 || horizontalDirection == 1 && positionX >= ((map[0].length * Tile.TILE_WIDTH - MainFrame.getBoardWidth()) * -1);
	}

	public void update(double differenceTime) {
		if (moveMap())
			positionX += ((player.getHorizontalDirection() * -1) * (player.getSpeedX()/3) * differenceTime);
	}

	
	public void draw(Graphics2D graphics2D) {
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[0].length; x++) {
				int index = map[y][x];
				if (index < 0)
					continue;

				int yOffset = 0;

				while (index > (tileSheet.getWidth(null) / Tile.TILE_WIDTH) - 1) {
					yOffset++;
					index = index - (tileSheet.getWidth(null) / Tile.TILE_WIDTH);
				}

				int x1 = x * Tile.TILE_WIDTH;
				int y1 = y * Tile.TILE_HEIGHT;
				int x2 = (x + 1) * Tile.TILE_WIDTH;
				int y2 = (y + 1) * Tile.TILE_HEIGHT;
				int sourceX0 = index * Tile.TILE_WIDTH;
				int sourceY0 = yOffset * Tile.TILE_HEIGHT;
				int sourceX1 = (index + 1) * Tile.TILE_WIDTH;
				int sourceY1 = (yOffset + 1) * Tile.TILE_HEIGHT;
				
				graphics2D.drawImage(tileSheet, (int) (x1 + positionX), y1, (int) (x2 + positionX), y2, sourceX0, sourceY0, sourceX1, sourceY1, null);
			}
		}
	}
}

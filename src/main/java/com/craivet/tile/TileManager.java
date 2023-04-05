package com.craivet.tile;

import com.craivet.Game;
import com.craivet.World;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.craivet.utils.Constants.*;

/**
 * Adminstra los tiles actualizandolos y renderizandolos.
 */

public class TileManager {

	private final Game game;
	private final World world;

	public TileManager(Game game, World world) {
		this.game = game;
		this.world = world;
	}

	/**
	 * Renderiza los tiles dentro de la vista de la camara aplicando los desplazamientos a cada uno.
	 *
	 * @param g2 componente grafico.
	 */
	public void render(Graphics2D g2) {
		// Calcula los desplazamientos
		int xOffset = game.player.worldX - game.player.screenX; // 1104 - 456 = 648
		int yOffset = game.player.worldY - game.player.screenY;

		// Calcula los tiles que estan dentro de la vista de la camara
		int yStart = Math.max(0, yOffset / tile_size);
		int yEnd = Math.min(MAX_WORLD_ROW, (yOffset + SCREEN_HEIGHT) / tile_size + 1);
		int xStart = Math.max(0, xOffset / tile_size); // 648 / 48 = 13
		int xEnd = Math.min(MAX_WORLD_COL, (xOffset + SCREEN_WIDTH) / tile_size + 1);

		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				final int tileIndex = world.tileIndex[world.map][y][x];
				final BufferedImage tileImage = world.tile[tileIndex].texture;
				g2.drawImage(tileImage, x * tile_size - xOffset, y * tile_size - yOffset, null);
				// g2.drawRect(x * tile_size - xOffset, y * tile_size - yOffset, tile_size, tile_size); // Dibuja una grilla
			}
		}

		if (world.drawPath) {
			g2.setColor(new Color(255, 0, 0, 70));
			for (int i = 0; i < game.aStar.pathList.size(); i++) {
				int worldX = game.aStar.pathList.get(i).col * tile_size;
				int worldY = game.aStar.pathList.get(i).row * tile_size;
				int screenX = worldX - game.player.worldX + game.player.screenX;
				int screenY = worldY - game.player.worldY + game.player.screenY;
				g2.fillRect(screenX, screenY, tile_size, tile_size);
			}
		}
	}

}

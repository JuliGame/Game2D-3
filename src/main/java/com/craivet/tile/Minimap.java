package com.craivet.tile;

import com.craivet.World;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.craivet.utils.Constants.*;

public class Minimap {

    private final World world;

    private BufferedImage[] minimap;
    public boolean miniMapOn;

    public Minimap(World world) {
        this.world = world;
        createMinimap();
    }

    public void createMinimap() {
        minimap = new BufferedImage[MAX_MAP];
        int worldMapWidth = tile_size * MAX_WORLD_COL;
        int worldMapHeight = tile_size * MAX_WORLD_ROW;
        for (int map = 0; map < MAX_MAP; map++) {
            minimap[map] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = minimap[map].createGraphics();
            for (int row = 0; row < MAX_WORLD_ROW; row++) {
                for (int col = 0; col < MAX_WORLD_COL; col++) {
                    int tileIndex = world.tileIndex[map][row][col];
                    int x = tile_size * col;
                    int y = tile_size * row;
                    g2.drawImage(world.tileData[tileIndex].texture, x, y, null);
                }
            }
        }
    }

    public void render(Graphics2D g2) {
        if (miniMapOn) {
            int width = 200;
            int height = 200;
            int x = SCREEN_WIDTH - width - 20;
            int y = 20;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
            g2.drawImage(minimap[world.map], x, y, width, height, null);

            // Dibuja un cuadrado rojo que representa la posicion del player
            double scale = (double) (tile_size * MAX_WORLD_COL) / width;
            int playerX = (int) (x + world.player.worldX / scale);
            int playerY = (int) (y + world.player.worldY / scale);
            int playerSize = (int) (tile_size / scale);
            g2.setColor(Color.red);
            g2.fillRect(playerX, playerY, playerSize, playerSize);
           // g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)); // Restablece el valor alpha
        }
    }

}

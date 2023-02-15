package com.craivet.entity;

import java.awt.*;

import com.craivet.Game;

import static com.craivet.utils.Constants.*;

public class Particle extends Entity {

	Entity generator; // El generador seria por ejemplo el arbol seco o un proyectil
	Color color;
	int size;
	int xd, yd;

	public Particle(Game game, Entity generator, Color color, int size, int speed, int maxLife, int xd, int yd) {
		super(game);
		this.generator = generator;
		this.color = color;
		this.size = size;
		this.speed = speed;
		this.maxLife = maxLife;
		this.xd = xd;
		this.yd = yd;

		life = maxLife;
		int offset = (TILE_SIZE / 2) - (size - 2);
		worldX = generator.worldX + offset;
		worldY = generator.worldY + offset;

	}

	public void update() {
		life--;

		/* Si la vida de la particula es la mitad de su vida maxima, entonces la posicion y aumenta en 1, generando asi
		 * un efecto de gravedad. */
		if (life < maxLife / 3) {
			yd++;
			size--;
		}

		worldX += xd * speed;
		worldY += yd * speed;

		if (life == 0) alive = false;

	}

	public void draw(Graphics2D g2) {
		int screenX = worldX - game.player.worldX + game.player.screenX;
		int screenY = worldY - game.player.worldY + game.player.screenY;

		g2.setColor(color);
		g2.fillRect(screenX, screenY, size, size); // Dibuja un rectangulo como una particula

	}

}

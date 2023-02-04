package com.craivet.entity;

import com.craivet.Game;

public abstract class Projectile extends Entity {

	private Entity entity;

	public Projectile(Game game) {
		super(game);
	}

	public boolean haveResource(Entity entity) {
		return false;
	}

	public void subtractResource(Entity entity) {
	}

	public void set(int worldX, int worldY, String direction, boolean alive, Entity entity) {
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.entity = entity;
		this.life = this.maxLife; // Resetea la vida al valor maximo cada vez que lanza un proyectil
	}

	/**
	 * Actualiza la posicion del proyectil si no colisiona con un mob o si no se termina la vida de este. En caso
	 * contrario, deja de vivir.
	 */
	@Override
	public void update() {

		// Si el player lanza un proyectil
		if (entity instanceof Player) {
			int mobIndex = game.cChecker.checkEntity(this, game.mobs);
			/* Cuando el proyectil colisiona con un mob, establece el estado collisionOn en true. Por lo tanto, cuando
			 * se vuelva a dibujar el proyectil, este se va a mantener en el frame de movimiento 1 ya que en el operador
			 * ternario, la condicion se mantiene en true y nunca cambia a false para poder mostrar el frame de
			 * movimiento 2. La siguiente linea soluciona este problema. */
			collisionOn = false;
			if (mobIndex != -1) {
				game.player.damageMob(mobIndex, attack);
				alive = false;
			}
		}

		// Si el mob lanza un proyectil
		if (!(entity instanceof Player)) {
			boolean contact = game.cChecker.checkPlayer(this);
			if (contact && !game.player.invincible) {
				damagePlayer(true, attack);
				alive = false;
			}
		}

		if (life-- <= 0) alive = false;

		if (alive) {
			switch (direction) {
				case "down":
					worldY += speed;
					break;
				case "up":
					worldY -= speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
			}
			timer.timeMovement(this, 8);
		}
	}

}

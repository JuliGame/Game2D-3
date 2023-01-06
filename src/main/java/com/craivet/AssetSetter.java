package com.craivet;

import com.craivet.entity.Oldman;
import com.craivet.entity.Slime;
import com.craivet.object.OBJ_Door;

public class AssetSetter {

	private final Game game;

	public AssetSetter(Game game) {
		this.game = game;
	}

	public void setObject() {
		game.objs[0] = new OBJ_Door(game);
		game.objs[0].worldX = game.tileSize * 23;
		game.objs[0].worldY = game.tileSize * 35;
	}

	public void setNPC() {
		game.npcs[0] = new Oldman(game);
		game.npcs[0].worldX = game.tileSize * 21;
		game.npcs[0].worldY = game.tileSize * 21;
	}

	public void setMOB() {
		game.mobs[0] = new Slime(game);
		game.mobs[0].worldX = game.tileSize * 23;
		game.mobs[0].worldY = game.tileSize * 32;

		game.mobs[1] = new Slime(game);
		game.mobs[1].worldX = game.tileSize * 23;
		game.mobs[1].worldY = game.tileSize * 37;

	}

}

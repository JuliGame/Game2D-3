package com.craivet.entity.item;

import com.craivet.Game;
import com.craivet.entity.Entity;
import com.craivet.utils.Utils;

import static com.craivet.utils.Constants.*;
import static com.craivet.gfx.Assets.*;

public class Coin extends Item {

	private final Game game;

	// TODO La moneda no aparece en el medio del Slime
	public Coin(Game game) {
		super(game);
		this.game = game;
		name = "Coin";
		type = TYPE_PICKUP_ONLY;
		image = Utils.scaleImage(item_coin, 32, 32);
		value = 1;
	}

	public void use(Entity entity) {
		game.playSound(sound_coin);
		game.ui.addMessage("Coin +" + value);
		entity.coin += value;
	}

}

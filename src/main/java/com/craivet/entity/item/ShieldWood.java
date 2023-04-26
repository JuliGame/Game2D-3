package com.craivet.entity.item;

import com.craivet.Game;
import com.craivet.World;
import com.craivet.utils.Utils;

import static com.craivet.utils.Global.*;
import static com.craivet.gfx.Assets.*;

public class ShieldWood extends Item {

	public static final String item_name = "Wood Shield";

	public ShieldWood(Game game, World world) {
		super(game, world);
		name = item_name;
		type = TYPE_SHIELD;
		image = Utils.scaleImage(item_shield_wood, tile_size, tile_size);
		description = "[" + name + "]\nMade by wood.";
		price = 25;
		defenseValue = 1;
	}

}

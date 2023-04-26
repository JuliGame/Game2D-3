package com.craivet.entity.item;

import com.craivet.Game;
import com.craivet.World;
import com.craivet.utils.Utils;

import static com.craivet.utils.Global.*;
import static com.craivet.gfx.Assets.*;

public class ShieldBlue extends Item {

	public static final String item_name = "Blue Shield";

	public ShieldBlue(Game game, World world) {
		super(game, world);
		name = item_name;
		type = TYPE_SHIELD;
		image = Utils.scaleImage(item_shield_blue, tile_size, tile_size);
		description = "[" + name + "]\nA shiny blue shield.";
		price = 250;
		defenseValue = 2;
	}

}

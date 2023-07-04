package com.craivet.entity.item;

import com.craivet.Game;
import com.craivet.World;
import com.craivet.utils.Utils;

import static com.craivet.gfx.Assets.*;
import static com.craivet.utils.Global.*;

public class Stone extends Item {

    public static final String NAME = "Stone";

    public Stone(Game game, World world, int amount, int... pos) {
        super(game, world, pos.length > 0 ? pos[0] : -1, pos.length > 1 ? pos[1] : -1);
        name = NAME;
        type = TYPE_CONSUMABLE;
        image = Utils.scaleImage(item_stone, tile_size, tile_size);
        description = "[" + name + "]\nIt's just a stone.";
        this.amount = amount;
        stackable = true;
    }

}

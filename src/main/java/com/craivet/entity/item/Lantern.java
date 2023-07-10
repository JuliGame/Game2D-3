package com.craivet.entity.item;

import com.craivet.Game;
import com.craivet.World;
import com.craivet.entity.Type;
import com.craivet.utils.Utils;

import static com.craivet.gfx.Assets.*;
import static com.craivet.utils.Global.*;

public class Lantern extends Item {

    public static final String NAME = "Lantern";

    public Lantern(Game game, World world, int... pos) {
        super(game, world, pos.length > 0 ? pos[0] : -1, pos.length > 1 ? pos[1] : -1);
        name = NAME;
        type = Type.LIGHT;
        image = Utils.scaleImage(item_lantern, tile_size, tile_size);
        description = "[" + name + "]\nIlluminaties your \nsurroundings.";
        price = 200;
        lightRadius = 350;
    }

}

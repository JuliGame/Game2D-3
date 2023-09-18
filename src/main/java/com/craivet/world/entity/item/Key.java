package com.craivet.world.entity.item;

import com.craivet.Game;
import com.craivet.world.World;
import com.craivet.world.entity.Entity;
import com.craivet.world.entity.Type;
import com.craivet.utils.Utils;

import static com.craivet.utils.Global.*;
import static com.craivet.gfx.Assets.*;

/**
 * Para especificar la textura del item sobre el suelo con un tamaño mas chico:
 * <pre>{@code
 *  if (pos.length > 0) image = Utils.scaleImage(key, 16, 16);
 *  else image = Utils.scaleImage(key, tile, tile);
 * }</pre>
 */

public class Key extends Item {

    public static final String NAME = "Key";

    public Key(Game game, World world, int amount, int... pos) {
        super(game, world, pos.length > 0 ? pos[0] : -1, pos.length > 1 ? pos[1] : -1);
        stats.name = NAME;
        stats.type = Type.CONSUMABLE;
        sheet.frame = Utils.scaleImage(key, tile, tile);
        stats.description = "[" + stats.name + "]\nIt opens a door.";
        stats.price = 100;
        stats.amount = amount;
        stats.stackable = true;
        stats.hitbox.x = 2;
        stats.hitbox.y = 0;
        stats.hitbox.width = 27;
        stats.hitbox.height = 32;
        stats.hitboxDefaultX = stats.hitbox.x;
        stats.hitboxDefaultY = stats.hitbox.y;
    }

    @Override
    public boolean use(Entity entity) {
        // Si detecta una puerta, entonces puede usar la llave
        int i = detect(entity, world.items, Door.NAME);
        if (i != -1) {
            game.playSound(sound_door_opening);
            world.items[world.map][i] = null;
            return true;
        }
        return false;
    }

}

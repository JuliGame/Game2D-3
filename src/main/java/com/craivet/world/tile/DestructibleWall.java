package com.craivet.world.tile;

import com.craivet.Game;
import com.craivet.world.World;
import com.craivet.world.entity.item.Item;
import com.craivet.world.entity.item.Stone;
import com.craivet.world.entity.Type;
import com.craivet.utils.Utils;

import java.awt.*;

import static com.craivet.utils.Global.*;
import static com.craivet.gfx.Assets.*;

public class DestructibleWall extends Interactive {

    public DestructibleWall(Game game, World world, int x, int y) {
        super(game, world, x, y);
        sheet.frame = Utils.scaleImage(itile_destructiblewall, tile, tile);
        destructible = true;
        stats.hp = 3;
    }

    public boolean isCorrectWeapon(Item weapon) {
        return weapon.type == Type.PICKAXE;
    }

    public Color getParticleColor() {
        return new Color(131, 130, 130);
    }

    public int getParticleSize() {
        return 6;
    }

    public int getParticleSpeed() {
        return 1;
    }

    public int getParticleMaxLife() {
        return 20;
    }

    public void checkDrop() {
        if (Utils.azar(100) <= PROBABILITY_STONE_DROP) drop(this, new Stone(game, world, 1));
    }

    @Override
    public void playSound() {
        game.playSound(sound_mine);
    }
}

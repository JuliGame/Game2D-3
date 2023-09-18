package com.craivet.world.entity.mob;

import com.craivet.Game;
import com.craivet.world.World;
import com.craivet.world.entity.Type;
import com.craivet.utils.*;
import com.craivet.world.entity.item.Key;

import static com.craivet.gfx.Assets.*;
import static com.craivet.utils.Global.*;

public class Bat extends Mob {

    public Bat(Game game, World world, int x, int y) {
        super(game, world, x, y);
        stats.name = "Bat";
        stats.type = Type.HOSTILE;
        soundHit = sound_hit_bat;
        soundDeath = sound_bat_death;
        stats.speed = stats.defaultSpeed = 3;
        stats.hp = stats.maxHp = 7;
        stats.exp = 7;
        stats.attack = 1;
        stats.defense = 1;
        stats.hitbox.x = 3;
        stats.hitbox.y = 15;
        stats.hitbox.width = tile - stats.hitbox.x;
        stats.hitbox.height = tile - stats.hitbox.y;
        stats.hitboxDefaultX = stats.hitbox.x;
        stats.hitboxDefaultY = stats.hitbox.y;
        sheet.loadMovementFrames(bat, 16, 16, 1);
        sheet.frame = sheet.movement[0];
    }

    @Override
    public void doActions() {
        timer.timeDirection(this, INTERVAL_DIRECTION_BAT);
    }

    @Override
    public void checkDrop() {
        if (Utils.azar(100) <= PROBABILITY_KEY_DROP) dropItem(this, new Key(game, world, 1));
    }

}
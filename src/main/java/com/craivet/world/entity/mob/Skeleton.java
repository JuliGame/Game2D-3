package com.craivet.world.entity.mob;

import com.craivet.Game;
import com.craivet.utils.Utils;
import com.craivet.world.World;
import com.craivet.world.entity.Type;
import com.craivet.world.entity.item.Gold;

import static com.craivet.gfx.Assets.*;
import static com.craivet.utils.Global.*;

public class Skeleton extends Mob {

    public Skeleton(Game game, World world, int x, int y) {
        super(game, world, x, y);
        stats.name = "Skeleton";
        stats.type = Type.HOSTILE;
        soundHit = sound_hit_mob;
        soundDeath = sound_mob_death;
        stats.speed = stats.defaultSpeed = 1;
        stats.hp = stats.maxHp = 50;
        stats.exp = 50;
        stats.attack = 10;
        stats.defense = 2;

        int scale = 5;
        int size = tile * scale;
        stats.hitbox.x = tile;
        stats.hitbox.y = tile;
        stats.hitbox.width = size - tile * 2;
        stats.hitbox.height = size - tile;
        stats.hitboxDefaultX = stats.hitbox.x;
        stats.hitboxDefaultY = stats.hitbox.y;
        stats.attackbox.width = 90;
        stats.attackbox.height = 90;
        stats.motion1 = 25;
        stats.motion2 = 50;
        sheet.loadMovementFrames(skeleton_movement, 32, 32, scale);
        sheet.loadAttackFrames(skeleton_attack, 32, 32, scale);
        sheet.frame = sheet.movement[0];
    }

    @Override
    public void doActions() {
        // TODO Hacer que siga al player cuando el mob este trabado en un tile
        // Si la distancia del player con respecto al mob es menor a 10 tiles
        if (getTileDistance(game.world.player) < 10) moveTowardPlayer(30);
        else timer.timeDirection(this, INTERVAL_DIRECTION);
        if (!flags.hitting) isPlayerWithinAttackRange(60, tile * 6, tile * 4, 60);
    }

    @Override
    public void damageReaction() {
        timer.directionCounter = 0;
    }

    @Override
    public void checkDrop() {
        if (Utils.azar(100) <= PROBABILITY_GOLD_DROP) dropItem(this, new Gold(game, world));
    }


}

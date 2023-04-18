package com.craivet.entity.mob;

import com.craivet.Game;
import com.craivet.World;
import com.craivet.entity.item.Coin;
import com.craivet.utils.Utils;

import static com.craivet.gfx.Assets.*;
import static com.craivet.utils.Global.*;

public class Orc extends Mob {

    public Orc(Game game, World world, int x, int y) {
        super(game, world);
        this.x = x * tile_size;
        this.y = y * tile_size;
        initDefaultValues();
    }

    private void initDefaultValues() {
        name = "Orc";
        type = TYPE_MOB;
        speed = defaultSpeed = 1;
        life = maxLife = 10;
        exp = 2;

        attack = 8;
        defense = 2;

        hitbox.x = 4;
        hitbox.y = 4;
        hitbox.width = 40;
        hitbox.height = 44;
        hitboxDefaultX = hitbox.x;
        hitboxDefaultY = hitbox.y;
        attackbox.width = 48;
        attackbox.height = 48;

        initMovementImages(entity_orc_movement, ENTITY_WIDTH, ENTITY_HEIGHT, tile_size);
        initAttackImages(entity_orc_attack, ENTITY_WIDTH, ENTITY_HEIGHT);

    }

    public void setAction() {
        if (onPath) {
            checkUnfollow(world.player, 15);
            searchPath(getGoalRow(world.player), getGoalCol(world.player));
        } else {
            checkFollow(world.player, 5, 100);
            timer.timeDirection(this, INTERVAL_DIRECTION);
        }
    }

    public void damageReaction() {
        timer.directionCounter = 0;
        onPath = true;
    }

    /**
     * Comprueba si dropeo un item.
     */
    public void checkDrop() {
        if (Utils.azar(100) <= PROBABILIDAD_DROP_ORO) dropItem(new Coin(game, world));
    }

}
package com.craivet.world.entity.item;

import com.craivet.Game;
import com.craivet.world.World;
import com.craivet.world.entity.Entity;
import com.craivet.world.entity.Type;

import static com.craivet.utils.Global.*;
import static com.craivet.gfx.Assets.*;

public class Chest extends Item {

    public static final String NAME = "Chest";

    public Chest(Game game, World world, int... pos) {
        super(game, world, pos.length > 0 ? pos[0] : -1, pos.length > 1 ? pos[1] : -1);
        stats.name = NAME;
        stats.type = Type.OBSTACLE;
        sheet.loadItemFrames(chest, 16, 16, 1);
        sheet.frame = sheet.item[0];
        stats.solid = true;
        stats.hitbox.x = 2;
        stats.hitbox.y = 16;
        stats.hitbox.width = tile - stats.hitbox.x - 3;
        stats.hitbox.height = tile - stats.hitbox.y;
        stats.hitboxDefaultX = stats.hitbox.x;
        stats.hitboxDefaultY = stats.hitbox.y;
    }

    @Override
    public void interact() {
        if (!stats.opened) {
            game.playSound(sound_chest_opening);
            sheet.frame = sheet.item[1];
            stats.opened = true;
            if (world.player.canPickup(stats.loot)) {
                dialogues[0][0] = "You open the chest and find a \n" + stats.loot.stats.name + "!";
                startDialogue(DIALOGUE_STATE, this, 0);
                stats.empty = true;
            } else {
                dialogues[1][0] = "You open the chest and find a \n" + stats.loot.stats.name + "! But you cannot carry \nany more!";
                startDialogue(DIALOGUE_STATE, this, 1);
            }
        } else if (!stats.empty) {
            if (world.player.canPickup(stats.loot)) {
                dialogues[2][0] = "You obtain the " + stats.loot.stats.name + "!";
                startDialogue(DIALOGUE_STATE, this, 2);
                stats.empty = true;
            } else {
                dialogues[3][0] = "You cannot carry any more!";
                startDialogue(DIALOGUE_STATE, this, 3);
            }
        } else {
            dialogues[4][0] = "It's empty.";
            startDialogue(DIALOGUE_STATE, this, 4);
        }
    }

    @Override
    public void setLoot(Entity loot) {
        stats.loot = loot;
    }

}

package com.craivet.world.entity.item;

import com.craivet.Dialogue;
import com.craivet.Game;
import com.craivet.world.World;
import com.craivet.world.entity.Type;
import com.craivet.utils.Utils;

import static com.craivet.gfx.Assets.*;
import static com.craivet.utils.Global.*;

public class DoorIron extends Item {

    public static final String NAME = "Iron Door";

    public DoorIron(Game game, World world, int... pos) {
        super(game, world, pos.length > 0 ? pos[0] : -1, pos.length > 1 ? pos[1] : -1);
        dialogue = new Dialogue();
        type = Type.OBSTACLE;
        stats.name = NAME;
        solid = true;
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = tile - hitbox.x - 1;
        hitbox.height = tile - hitbox.y;
        hitboxDefaultX = hitbox.x;
        hitboxDefaultY = hitbox.y;
        sheet.frame = Utils.scaleImage(door_iron, tile, tile);
        initDialogue();
    }

    @Override
    public void interact() {
        startDialogue(DIALOGUE_STATE, this, 0);
    }

    private void initDialogue() {
        dialogue.dialogues[0][0] = "It won't budge.";
    }

}

package com.craivet;

import com.craivet.entity.Entity;
import com.craivet.entity.item.*;

public class EntityGenerator {

    private final Game game;
    private final World world;

    public EntityGenerator(Game game, World world) {
        this.game = game;
        this.world = world;
    }

    /**
     * Obtiene el item.
     *
     * @param name nombre del item.
     * @return el item.
     */
    public Entity getItem(String name) {
        Entity item = null;
        switch (name) {
            case Axe.item_name -> item = new Axe(game, world);
            case Boots.item_name -> item = new Boots(game, world);
            case Chest.item_name -> item = new Chest(game, world);
            case Coin.item_name -> item = new Coin(game, world);
            case Door.item_name -> item = new Door(game, world);
            case Key.item_name -> item = new Key(game, world, 1);
            case Lantern.item_name -> item = new Lantern(game, world);
            case PotionRed.item_name -> item = new PotionRed(game, world, 1);
            case ShieldBlue.item_name -> item = new ShieldBlue(game, world);
            case ShieldWood.item_name -> item = new ShieldWood(game, world);
            case SwordNormal.item_name -> item = new SwordNormal(game, world);
            case Tent.item_name -> item = new Tent(game, world);
        }
        return item;
    }

}

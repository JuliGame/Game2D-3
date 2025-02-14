package com.craivet;

import com.craivet.world.entity.Entity;

public enum Direction {
    DOWN, UP, LEFT, RIGHT, ANY;

    /**
     * Obtiene la direccion dependiendo de la tecla seleccionada.
     */
    public void get(Entity entity) {
        if (entity.game.keyboard.s) entity.direction = DOWN;
        else if (entity.game.keyboard.w) entity.direction = UP;
        else if (entity.game.keyboard.a) entity.direction = LEFT;
        else if (entity.game.keyboard.d) entity.direction = RIGHT;
    }

    /* Variable temporal que almacena la direccion del atacante al momento del ataque para actualizar la posicion de la
     * entidad mientras el frame de esta se mantiene en la misma direccion. */
    public Direction knockbackDirection;

}

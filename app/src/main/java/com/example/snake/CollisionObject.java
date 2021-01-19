package com.example.snake;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

public abstract class CollisionObject implements Collision, Drawing {
    protected float size;
    protected final Position position = new Position();

    public CollisionObject(final float size) {
        this.size = size;
    }

    @Override
    public boolean onCollisionEnter(final @NotNull CollisionObject object) {
        return position.getX() < object.position.getX() + object.size && position.getX() > object.position.getX() - object.size &&
                position.getY() < object.position.getY() + object.size && position.getY() > object.position.getY() - object.size;
    }
}

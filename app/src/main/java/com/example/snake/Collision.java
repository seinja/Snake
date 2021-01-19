package com.example.snake;

import org.jetbrains.annotations.NotNull;

public interface Collision {
    boolean onCollisionEnter(final @NotNull  CollisionObject object);
}

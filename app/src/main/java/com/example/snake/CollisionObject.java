package com.example.snake;

import org.jetbrains.annotations.NotNull;

public abstract class CollisionObject implements Collision {
    protected float size;
    protected final Position position = new Position();

    @Override
    @NotNull
    public void onCollisionEnter(CollisionObject object) {

    }


    @NotNull
    private boolean isAppleCollision(Apple apple) {
        return (position.getX() - size < apple.getXPosition() + apple.getRadius() * 2 && position.getX() + size > apple.getXPosition() - apple.getRadius() * 2 &&
                position.getY() - size < apple.getYPosition() + apple.getRadius() * 2 && position.getY() + size > apple.getYPosition() - apple.getRadius() * 2);
    }

    @NotNull
    private boolean isSnakeCollision(Snake snake) {
        return (position.getX() - size < snake.getFirstX() + snake.getRadius() && position.getX() + size > snake.getFirstX() - snake.getRadius() &&
                position.getY() - size < snake.getFirstY() + snake.getRadius() && position.getY() + size > snake.getFirstY() - snake.getRadius());
    }

    @NotNull
    private boolean nearSnakeSpawn(Snake snake) {
        return (position.getX()- size < snake.getFirstX() + snake.getRadius() * 6 && position.getX() + size > snake.getFirstX() - snake.getRadius() * 6 &&
                position.getY() - size < snake.getFirstY() + snake.getRadius() * 6 && position.getY() + size > snake.getFirstY() - snake.getRadius() * 6);
    }

    private boolean isItSnake(Snake snake) {
        return position.getX() < snake.getFirstX() + snake.getRadius() && position.getX() > snake.getFirstX() - snake.getRadius() &&
                position.getY() < snake.getFirstY() + snake.getRadius() && position.getY() > snake.getFirstY() - snake.getRadius();
    }
}

package com.example.snake;

public interface Collision {
    void onCollisionEnter(Snake snake);

    void onCollisionEnter(Apple apple);
}

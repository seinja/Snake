package com.example.snake;

import androidx.annotation.NonNull;

public class Position {
    private float x;
    private float y;

    public Position() {
        setPosition(0, 0);
    }

    public Position(final float x, final float y) {
        setPosition(x, y);
    }

    public float getX() {
        return x;
    }

    public void setX(final float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(final float y) {
        this.y = y;
    }

    public void setPosition(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    public void setPosition(final @NonNull Position position) {
        this.x = position.x;
        this.y = position.y;
    }

    @NonNull
    @Override
    public String toString() {
        return x + ", " + y;
    }
}

package com.example.snake;

import android.graphics.Canvas;
import android.graphics.Color;

import androidx.annotation.NonNull;


public class SlowApple extends Apple {
    public SlowApple(final @NonNull MainView parent) {
        super(parent);
        paint.setColor(Color.rgb(24, 242, 231));
    }

    @Override
    public void onDraw(final @NonNull Canvas canvas) {
        canvas.drawCircle(position.getX(), position.getY(), size, paint);
    }

    @Override
    public void collisionAffect(final @NonNull Snake snake) {
        snake.slowSpeed();
        respawn();
        snake.addScore(1);
        snake.downSegments();
    }
}
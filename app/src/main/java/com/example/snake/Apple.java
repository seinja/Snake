package com.example.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.annotation.NonNull;

public class Apple extends CollisionObject {
    @NonNull
    private final MainView parent;

    protected final Paint paint = new Paint();

    public Apple(final @NonNull MainView parent) {
        super(25f);
        this.parent = parent;
        paint.setColor(Color.RED);
    }

    public void onDraw(final Canvas canvas) {
        canvas.drawCircle(position.getX(), position.getY(), size, paint);
    }

    public void respawn() {
        this.position.setPosition((float) ((Math.random()) * parent.getWidth()), (float) ((Math.random()) * parent.getHeight()));
        for (CollisionObject object : parent.obstacleArrayList) {
            if (object.onCollisionEnter(this)) {
                respawn();
                return;
            }
        }

    }

    public void onCollisionEnter(final @NonNull Snake snake) {
        if (super.onCollisionEnter(snake)) {
            collisionAffect(snake);
        }
    }

    public void collisionAffect(final @NonNull Snake snake) {
        snake.upSpeed();
        respawn();
        snake.addScore(2);
        snake.addSegments();
    }
}

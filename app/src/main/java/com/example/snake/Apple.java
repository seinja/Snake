package com.example.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.jetbrains.annotations.NotNull;


public class Apple extends CollisionObject {
    public final Position position = new Position();
    public final float radius = 25f;
    private float canvasHeight;
    private float canvasWidth;

    private final Paint paint = new Paint();

    public Apple() {
        paint.setColor(Color.RED);
    }

    public void onDraw(Canvas canvas) {
        canvas.drawCircle(position.getX(), position.getY(), radius, paint);
        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();
    }

    public void setPos(float x, float y) {
        canvasHeight = y;
        canvasWidth = x;
        this.position.setPosition((float) ((Math.random()) * canvasWidth),(float) ((Math.random()) * canvasHeight));
    }

    @NotNull
    public void onCollisionEnter(Snake snake) {
        super.onCollisionEnter(snake);
    }


    public float getXPosition() {
        return position.getX();
    }

    public float getYPosition() {
        return position.getY();
    }

    public float getRadius() {
        return radius;
    }

    @NotNull
    public void collisionAffect(Snake snake){
        snake.upSpeed();
        setPos(canvasWidth, canvasHeight);
        snake.addScore(2);
        snake.addLength();
    }
}

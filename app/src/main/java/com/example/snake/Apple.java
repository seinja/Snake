package com.example.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Apple implements Collision {
    public float xPosition;
    public float yPosition;
    public final float radius = 25f;
    private float canvasHeight;
    private float canvasWidth;

    Paint paint = new Paint();

    public Apple() {
        paint.setColor(Color.RED);
    }

    public void onDraw(Canvas canvas) {
        canvas.drawCircle(xPosition, yPosition, radius, paint);
        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();
    }

    public void setPos(float x, float y) {
        canvasHeight = y;
        canvasWidth = x;
        this.xPosition = (float) ((Math.random()) * canvasWidth);
        this.yPosition = (float) ((Math.random()) * canvasHeight);
    }

    @Override
    public void onCollisionEnter(Snake snake) {
        if (isInSnakeCollision(snake)) { collisionAffect(snake);}
    }

    public boolean isInSnakeCollision(Snake snake) {
        return xPosition < snake.getFirstX() + snake.getRadius() && xPosition > snake.getFirstX() - snake.getRadius() &&
                yPosition < snake.getFirstY() + snake.getRadius() && yPosition > snake.getFirstY() - snake.getRadius();
    }

    public float getXPosition() {
        return xPosition;
    }

    public float getYPosition() {
        return yPosition;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public void onCollisionEnter(Apple apple) {

    }

    public void collisionAffect(Snake snake){
        snake.upSpeed();
        setPos(canvasWidth, canvasHeight);
        snake.addScore(2);
        snake.addLength();
    }
}

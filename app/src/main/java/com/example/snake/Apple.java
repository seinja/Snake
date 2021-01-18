package com.example.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Apple implements Collision {
    public float x;
    public float y;
    public final float radius = 25f;
    private float canH;
    private float canW;

    Paint paint = new Paint();

    public Apple() {
        paint.setColor(Color.RED);
    }

    public void onDraw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
        canH = canvas.getHeight();
        canW = canvas.getWidth();
    }

    public void setPos(float x, float y) {
        canH = y;
        canW = x;
        this.x = (float) ((Math.random()) * canW);
        this.y = (float) ((Math.random()) * canH);
    }

    @Override
    public void onCollisionEnter(Snake snake) {
        if (isInSnakeCollision(snake)) { collisionAffect(snake);}
    }

    public boolean isInSnakeCollision(Snake snake) {
        return x < snake.getFirstX() + snake.getRadius() && x > snake.getFirstX() - snake.getRadius() &&
                y < snake.getFirstY() + snake.getRadius() && y > snake.getFirstY() - snake.getRadius();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public void onCollisionEnter(Apple apple) {

    }

    public void collisionAffect(Snake snake){
        snake.upSpeed();
        setPos(canW, canH);
        snake.addScore(2);
        snake.addLength();
    }
}

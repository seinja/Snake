package com.example.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class SlowApple extends Apple{
    private float canvasHeight;
    private float canvasWidth;
    private final Paint paint = new Paint();
    private final Position position = new Position();

    public SlowApple() {
        paint.setColor(Color.rgb(24, 242, 231));
    }

    @Override
    public void onDraw(Canvas canvas) {
        float radius = 25f;
        canvas.drawCircle(position.getX(), position.getY(), radius, paint);
        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();
    }

    public void onCollisionEnter(Snake snake) {
        super.onCollisionEnter(snake);
    }

    public void collisionAffect(Snake snake) {
        snake.slowSpeed();
        setPos(canvasWidth, canvasHeight);
        snake.addScore(1);
        snake.downLength();
    }

}

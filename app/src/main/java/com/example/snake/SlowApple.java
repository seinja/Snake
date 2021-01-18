package com.example.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class SlowApple extends Apple implements Collision {
    private float canvasHeight;
    private float canvasWidth;
    private final Paint paint = new Paint();

    public SlowApple() {
        paint.setColor(Color.rgb(24, 242, 231));
    }

    @Override
    public void onDraw(Canvas canvas) {
        float radius = 25f;
        canvas.drawCircle(xPosition, yPosition, radius, paint);
        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();
    }

    @Override
    public void onCollisionEnter(Snake snake) {
        if (isItSnake(snake)) {
            collisionAffect(snake);
        }
    }

    private boolean isItSnake(Snake snake) {
        return xPosition < snake.getFirstX() + snake.getRadius() && xPosition > snake.getFirstX() - snake.getRadius() &&
                yPosition < snake.getFirstY() + snake.getRadius() && yPosition > snake.getFirstY() - snake.getRadius();
    }

    public void collisionAffect(Snake snake) {
        snake.slowSpeed();
        setPos(canvasWidth, canvasHeight);
        snake.addScore(1);
        snake.downLength();
    }

}

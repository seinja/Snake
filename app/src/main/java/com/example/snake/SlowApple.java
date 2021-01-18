package com.example.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class SlowApple extends Apple implements Collision {
    private float canH;
    private float canW;
    private final Paint paint = new Paint();

    public SlowApple() {
        paint.setColor(Color.rgb(24, 242, 231));
    }

    @Override
    public void onDraw(Canvas canvas) {
        float radius = 25f;
        canvas.drawCircle(x, y, radius, paint);
        canH = canvas.getHeight();
        canW = canvas.getWidth();
    }

    @Override
    public void onCollisionEnter(Snake snake) {
        if (isItSnake(snake)) {
            collisionAffect(snake);
        }
    }

    private boolean isItSnake(Snake snake) {
        return x < snake.getFirstX() + snake.getRadius() && x > snake.getFirstX() - snake.getRadius() &&
                y < snake.getFirstY() + snake.getRadius() && y > snake.getFirstY() - snake.getRadius();
    }

    public void collisionAffect(Snake snake) {
        snake.slowSpeed();
        setPos(canW, canH);
        snake.addScore(1);
        snake.downLength();
    }

}

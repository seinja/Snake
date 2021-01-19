package com.example.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import androidx.annotation.NonNull;

public class Obstacle extends CollisionObject {
    private float canvasHeight;
    private float canvasWidth;
    private final Rect obstacle = new Rect();
    private boolean isRespawn = false;
    private static final Paint paint = new Paint();

    static {
        paint.setColor(Color.rgb(252, 5, 228));
    }

    //Устанавливает флаг на отрисовку объекта
    public void setRespawn(final boolean value) {
        if (value != isRespawn) {
            isRespawn = value;
            setPos(position.getX(), position.getY());
        }
    }

    //Конструктор устанавливает цвет и начальное положение квадрата
    public Obstacle() {
        super(75f);
        obstacle.set((int) (position.getX() - size), (int) (position.getY() + size), (int) (position.getX() + size), (int) (position.getY() - size));
    }

    //Установка рандомной позиции
    public void setPos(final float x, final float y) {
        canvasHeight = y;
        canvasWidth = x;
        position.setPosition((float) ((Math.random()) * canvasWidth), (float) ((Math.random()) * canvasHeight));
        obstacle.set((int) (position.getX() - size), (int) (position.getY() + size), (int) (position.getX() + size), (int) (position.getY() - size));
    }

    //Отрисовка препятсвий
    public void onDraw(final @NonNull Canvas canvas) {
        if (obstacle != null) {
            obstacle.set(obstacle);
            canvas.drawRect(obstacle, paint);
            canvasHeight = canvas.getHeight();
            canvasWidth = canvas.getWidth();
        }
    }
}
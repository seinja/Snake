package com.example.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import org.jetbrains.annotations.NotNull;

public class Obstacles extends CollisionObject {
    private float canvasHeight;
    private float canvasWidth;
    private final Rect obstacle;
    private final int obstacleWidth = 75;
    private boolean isEnd = false;
    private boolean isShowed = false;
    private final Paint paint = new Paint();
    private final Position position = new Position();

    //Устанавливает флаг на отрисовку объекта
    public void setShowed(boolean value) {
        if (value != isShowed) {
            isShowed = value;
            setPos(position.getX(), position.getY());
        }
    }

    //Конструктор устанавливает цвет и начальное положение квадрата
    public Obstacles() {
        paint.setColor(Color.rgb(252, 5, 228));
        obstacle = new Rect((int)position.getX() - obstacleWidth, (int)position.getY() + obstacleWidth, (int)position.getX() + obstacleWidth, (int)position.getY() - obstacleWidth);
    }

    //Установка рандомной позиции
    @NotNull
    public void setPos(float x, float y) {
        canvasHeight = y;
        canvasWidth = x;
        position.setPosition((float) ((Math.random()) * canvasWidth),(float) ((Math.random()) * canvasHeight));
        obstacle.set((int)position.getX() - obstacleWidth, (int)position.getY() + obstacleWidth, (int)position.getX() + obstacleWidth, (int)position.getY() - obstacleWidth);
    }

    //Перегруженная версия метода для змеи
    @NotNull
    public void setPos(float x, float y, Snake snake) {
        canvasHeight = y;
        canvasWidth = x;
        position.setPosition((float)((Math.random()) * canvasWidth),(float)((Math.random()) * canvasHeight));
    }

    //Отрисовка препятсвий
    @NotNull
    public void onDraw(Canvas canvas) {
        if (obstacle != null) {
            obstacle.set(obstacle);
            canvas.drawRect(obstacle, paint);
            canvasHeight = canvas.getHeight();
            canvasWidth = canvas.getWidth();
        }

    }

    //Методы колизии из интерфейса
    @NotNull
    public void onCollisionEnter(Snake snake) {
        super.onCollisionEnter(snake);
    }

    //Геттеры
    public boolean getIsEnd() {
        return isEnd;
    }
    public float getWidth(){return obstacleWidth;}
}

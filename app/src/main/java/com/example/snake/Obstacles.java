package com.example.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Obstacles implements Collision {
    private int xPosition;
    private int yPosition;
    private int canvasHeight;
    private int canvasWidth;
    private final Rect obstacle;
    private final int obstacleWidth = 75;
    private boolean isEnd = false;
    private boolean isShowed = false;
    private final Paint paint = new Paint();

    //Устанавливает флаг на отрисовку объекта
    public void setShowed(boolean value) {
        if (value != isShowed) {
            isShowed = value;
            setPos(xPosition, yPosition);
        }
    }

    //Конструктор устанавливает цвет и начальное положение квадрата
    public Obstacles() {
        paint.setColor(Color.rgb(252, 5, 228));
        obstacle = new Rect(xPosition - obstacleWidth, yPosition + obstacleWidth, xPosition + obstacleWidth, yPosition - obstacleWidth);
    }

    //Установка рандомной позиции
    public void setPos(int x, int y) {
        canvasHeight = y;
        canvasWidth = x;
        this.xPosition = (int) ((Math.random()) * canvasWidth);
        this.yPosition = (int) ((Math.random()) * canvasHeight);
        obstacle.set(this.xPosition - obstacleWidth, this.yPosition + obstacleWidth, this.xPosition + obstacleWidth, this.yPosition - obstacleWidth);
    }

    //Перегруженная версия метода для змеи
    public void setPos(int x, int y, Snake snake) {
        canvasHeight = y;
        canvasWidth = x;
        this.xPosition = (int) ((Math.random()) * canvasWidth);
        this.yPosition = (int) ((Math.random()) * canvasHeight);

        if (nearSnakeSpawn(snake)) {
            setPos(canvasWidth, canvasHeight, snake);
        }
    }

    //Отрисовка препятсвий
    public void onDraw(Canvas canvas) {
        if (obstacle != null) {
            obstacle.set(obstacle);
            canvas.drawRect(obstacle, paint);
            canvasHeight = canvas.getHeight();
            canvasWidth = canvas.getWidth();

        }

    }

    //Методы колизии из интерфейса
    public void onCollisionEnter(Snake snake) {
        if (isSnakeCollision(snake)) {
            snake.downLength();
            isEnd = true;
        }
    }

    @Override
    public void onCollisionEnter(Apple apple) {
        if (isAppleCollision(apple)) {
            setPos(xPosition, yPosition);
        }
    }

    private boolean isAppleCollision(Apple apple) {
        return (xPosition - obstacleWidth < apple.getXPosition() + apple.getRadius() * 2 && xPosition + obstacleWidth > apple.getXPosition() - apple.getRadius() * 2 &&
                yPosition - obstacleWidth < apple.getYPosition() + apple.getRadius() * 2 && yPosition + obstacleWidth > apple.getYPosition() - apple.getRadius() * 2);
    }

    private boolean isSnakeCollision(Snake snake) {
        return (xPosition - obstacleWidth < snake.getFirstX() + snake.getRadius() && xPosition + obstacleWidth > snake.getFirstX() - snake.getRadius() &&
                yPosition - obstacleWidth < snake.getFirstY() + snake.getRadius() && yPosition + obstacleWidth > snake.getFirstY() - snake.getRadius());
    }

    private boolean nearSnakeSpawn(Snake snake) {
        return (this.xPosition - obstacleWidth < snake.getFirstX() + snake.getRadius() * 6 && this.xPosition + yPosition > snake.getFirstX() - snake.getRadius() * 6 &&
                this.yPosition - obstacleWidth < snake.getFirstY() + snake.getRadius() * 6 && this.yPosition + obstacleWidth > snake.getFirstY() - snake.getRadius() * 6);
    }

    //Геттеры
    public boolean getIsEnd() {
        return isEnd;
    }
}

package com.example.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Obstacles implements Collision {
    private int x;
    private int y;
    private int canH;
    private int canW;
    private final Rect r;
    private final int rd = 75;
    private boolean isEnd = false;
    private boolean isShowed = false;
    private final Paint paint = new Paint();

    //Устанавливает флаг на отрисовку объекта
    public void setShowed(boolean value) {
        if (value != isShowed) {
            isShowed = value;
            setPos(canW, canH);
        }
    }

    //Конструктор устанавливает цвет и начальное положение квадрата
    public Obstacles() {
        paint.setColor(Color.rgb(252, 5, 228));
        r = new Rect(x - rd, y + rd, x + rd, y - rd);
    }

    //Установка рандомной позиции
    public void setPos(int x, int y) {
        canH = y;
        canW = x;
        this.x = (int) ((Math.random()) * canW);
        this.y = (int) ((Math.random()) * canH);
        r.set(this.x - rd, this.y + rd, this.x + rd, this.y - rd);
    }

    //Перегруженная версия метода для змеи
    public void setPos(int x, int y, Snake snake) {
        canH = y;
        canW = x;
        this.x = (int) ((Math.random()) * canW);
        this.y = (int) ((Math.random()) * canH);

        if (nearSnakeSpawn(snake)) {
            setPos(canW, canH, snake);
        }
    }

    //Отрисовка препятсвий
    public void onDraw(Canvas canvas) {
        if (r != null) {
            r.set(r);
            canvas.drawRect(r, paint);
            canH = canvas.getHeight();
            canW = canvas.getWidth();

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
            setPos(canW, canH);
        }
    }

    private boolean isAppleCollision(Apple apple) {
        return (x - rd < apple.getX() + apple.getRadius() * 2 && x + rd > apple.getX() - apple.getRadius() * 2 &&
                y - rd < apple.getY() + apple.getRadius() * 2 && y + rd > apple.getY() - apple.getRadius() * 2);
    }

    private boolean isSnakeCollision(Snake snake) {
        return (x - rd < snake.getFirstX() + snake.getRadius() && x + rd > snake.getFirstX() - snake.getRadius() &&
                y - rd < snake.getFirstY() + snake.getRadius() && y + rd > snake.getFirstY() - snake.getRadius());
    }

    private boolean nearSnakeSpawn(Snake snake) {
        return (this.x - rd < snake.getFirstX() + snake.getRadius() * 6 && this.x + rd > snake.getFirstX() - snake.getRadius() * 6 &&
                this.y - rd < snake.getFirstY() + snake.getRadius() * 6 && this.y + rd > snake.getFirstY() - snake.getRadius() * 6);
    }

    //Геттеры
    public boolean getIsEnd() {
        return isEnd;
    }
}

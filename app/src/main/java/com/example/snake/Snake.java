package com.example.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private int score = 0;
    private int length = 1;
    private float xVec, yVec;
    private float speed = 0.5f;
    private final float RADIUS = 50;
    private final Paint paint = new Paint();
    private final List<Integer> x = new ArrayList<Integer>();
    private final List<Integer> y = new ArrayList<Integer>();


    //Конструктор устанавливает цвет и первый элемент
    public Snake() {
        setColorOfPen(paint);
        addNullElement();
    }

    //Отрисовка удава
    public void onDraw(Canvas canvas) {
        drawSnakeParts(canvas);
        canvas.drawCircle(x.get(0), y.get(0), RADIUS, paint);
    }

    //Установка позиции
    public void setPosition(int x, int y) {
        this.x.set(0, x);
        this.y.set(0, y);
    }

    //Добавление длинны
    public void addLength() {
        x.add(x.get(x.size() - 1));
        y.add(y.get(y.size() - 1));
        length++;
    }

    //Установка направления к месту нажатия
    public void setVector(float xPos, float yPos) {
        float xWidth = xPos - x.get(0);
        float yHeight = yPos - y.get(0);
        float gb = (float) Math.sqrt(xWidth * xWidth + yHeight * yHeight);
        float K = RADIUS * speed / gb;
        xVec = xWidth * K;
        yVec = yHeight * K;


    }

    //Геттеры сеттеры и т.д
    public void upSpeed() {
        speed += 0.05f;
    }

    public float getFirstX() {
        return x.get(0);
    }

    public float getFirstY() {
        return y.get(0);
    }

    public float getRadius() {
        return RADIUS;
    }

    public String getScore() {
        return String.valueOf(score);
    }

    public void addScore(int variable) {
        score += variable;
    }

    public void slowSpeed() {
        speed -= .1f;
    }

    public void downLength() {
        length--;
    }

    public void addSpeed(float sp) {
        speed += sp;
    }

    public float getSpeed() {
        return speed;
    }

    private void drawSnakeParts(Canvas canvas) {
        for (int i = length - 1; i > 0; i--) {
            x.set(i, x.get(i - 1));
            y.set(i, y.get(i - 1));

            canvas.drawCircle(x.get(i), y.get(i), RADIUS, paint);

        }
        x.set(0, (int) (x.get(0) + xVec));
        y.set(0, (int) (y.get(0) + yVec));


        if (x.get(0) > canvas.getWidth()) {
            x.set(0, x.get(0) % canvas.getWidth());
        }
        if (y.get(0) > canvas.getHeight()) {
            y.set(0, y.get(0) % canvas.getHeight());
        }

        if (x.get(0) < 0) {
            x.set(0, x.get(0) + canvas.getWidth());
        }
        if (y.get(0) < 0) {
            y.set(0, y.get(0) + canvas.getHeight());
        }
    }

    private void setColorOfPen(Paint paint) {
        paint.setColor(Color.rgb(209, 105, 0));
    }

    private void addNullElement() {
        x.add(0);
        y.add(0);
    }
}

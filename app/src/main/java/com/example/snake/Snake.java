package com.example.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private int score = 0;
    private int length = 1;
    private float speed = 0.5f;
    private float vectorX, vectorY;
    private final float RADIUS = 50;
    private final Paint paint = new Paint();
    private final List<Integer> xPosition = new ArrayList<>();
    private final List<Integer> yPosition = new ArrayList<>();


    //Конструктор устанавливает цвет и первый элемент
    public Snake() {
        setColorOfPen(paint);
        addNullElement();
    }

    //Отрисовка удава
    public void onDraw(Canvas canvas) {
        drawSnakeParts(canvas);
        canvas.drawCircle(xPosition.get(0), yPosition.get(0), RADIUS, paint);
    }

    //Установка позиции
    public void setPosition(int x, int y) {
        this.xPosition.set(0, x);
        this.yPosition.set(0, y);
    }

    //Добавление длинны
    public void addLength() {
        xPosition.add(xPosition.get(xPosition.size() - 1));
        yPosition.add(yPosition.get(yPosition.size() - 1));
        length++;
    }

    //Установка направления к месту нажатия
    public void setVector(float xPos, float yPos) {
        float xWidth = xPos - xPosition.get(0);
        float yHeight = yPos - yPosition.get(0);
        float gb = (float) Math.sqrt(xWidth * xWidth + yHeight * yHeight);
        float K = RADIUS * speed / gb;
        vectorX = xWidth * K;
        vectorY = yHeight * K;


    }

    //Геттеры сеттеры и т.д
    public void upSpeed() {
        speed += 0.05f;
    }

    public float getFirstX() {
        return xPosition.get(0);
    }

    public float getFirstY() {
        return yPosition.get(0);
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
            xPosition.set(i, xPosition.get(i - 1));
            yPosition.set(i, yPosition.get(i - 1));

            canvas.drawCircle(xPosition.get(i), yPosition.get(i), RADIUS, paint);

        }
        xPosition.set(0, (int) (xPosition.get(0) + vectorX));
        yPosition.set(0, (int) (yPosition.get(0) + vectorY));


        if (xPosition.get(0) > canvas.getWidth()) {
            xPosition.set(0, xPosition.get(0) % canvas.getWidth());
        }
        if (yPosition.get(0) > canvas.getHeight()) {
            yPosition.set(0, yPosition.get(0) % canvas.getHeight());
        }

        if (xPosition.get(0) < 0) {
            xPosition.set(0, xPosition.get(0) + canvas.getWidth());
        }
        if (yPosition.get(0) < 0) {
            yPosition.set(0, yPosition.get(0) + canvas.getHeight());
        }
    }

    private void setColorOfPen(Paint paint) {
        paint.setColor(Color.rgb(209, 105, 0));
    }

    private void addNullElement() {
        xPosition.add(0);
        yPosition.add(0);
    }
}

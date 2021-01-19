package com.example.snake;

import android.graphics.Canvas;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Snake extends SnakeSegment {

    private int score = 0;
    private float speed = 0.5f;
    private float vectorX, vectorY;
    private boolean isLive = true;

    public boolean isLive() {
        return isLive;
    }

    private final List<SnakeSegment> segments = new ArrayList<>();

    public Snake() {
        super(50);
    }

    //Отрисовка удава
    public void onDraw(final @NotNull Canvas canvas) {
        drawSnakeParts(canvas);

        super.onDraw(canvas);
    }

    //Установка позиции
    public void setPosition(int x, int y) {
        position.setPosition(x, y);
    }

    //Добавление длинны
    public void addSegments() {
        segments.add(new SnakeSegment(size));
        segments.get(segments.size() - 1).setPosition(segments.size() > 1 ? segments.get(segments.size() - 2) : this);
    }

    //Установка направления к месту нажатия
    public void setVector(float xPos, float yPos) {
        float xWidth = xPos - position.getX();
        float yHeight = yPos - position.getY();
        float gb = (float) Math.sqrt(xWidth * xWidth + yHeight * yHeight);
        float K = size * speed / gb;
        vectorX = xWidth * K;
        vectorY = yHeight * K;
    }

    @Override
    public boolean onCollisionEnter(@NotNull CollisionObject object) {
        for (SnakeSegment segment : segments) {
            if (segment.onCollisionEnter(object)) {
                return true;
            }
        }
        return super.onCollisionEnter(object);
    }

    //Геттеры сеттеры и т.д
    public void upSpeed() {
        speed += 0.05f;
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

    public void downSegments() {
        if (segments.size() > 0) {
            segments.remove(segments.size() - 1);
        }
    }

    public void addSpeed(final float sp) {
        speed += sp;
    }

    public float getSpeed() {
        return speed;
    }

    private void move() {
        position.setPosition(position.getX() + vectorX, position.getY() + vectorY);
    }

    private void drawSnakeParts(final @NotNull Canvas canvas) {
        SnakeSegment backupSegment = null;
        for (SnakeSegment segment : segments) {
            segment.onDraw(canvas);
            segment.setPosition(backupSegment != null ? backupSegment : this);
            backupSegment = segment;
        }

        move();
        exitsBounds(canvas);
    }

    private void exitsBounds(final @NotNull Canvas canvas) {
        if (position.getX() > canvas.getWidth()) {
            position.setX(position.getX() % canvas.getWidth());
        }

        if (position.getY() > canvas.getHeight()) {
            position.setY(position.getY() % canvas.getHeight());
        }

        if (position.getX() < 0) {
            position.setX(position.getX() + canvas.getWidth());
        }

        if (position.getY() < 0) {
            position.setY(position.getY() + canvas.getHeight());
        }
    }

    public void kill() {
        isLive = false;
    }
}
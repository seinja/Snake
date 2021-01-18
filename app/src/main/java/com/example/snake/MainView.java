package com.example.snake;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class MainView extends View {

    private boolean isInit = false;
    private boolean isEnd = false;
    private int scoreTemp = 0;

    Snake snake = new Snake();
    Apple apple = new Apple();
    SlowApple slowApple = new SlowApple();
    Paint paint = new Paint();

    private final ArrayList<Obstacles> obstaclesArrayList = new ArrayList<Obstacles>();

    public MainView(Context context, float speed) {
        super(context);
        setClickable(true);
        snake.addSpeed(speed);

    }

    public MainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setClickable(true);

    }

    @Override
    public void onDraw(Canvas can) {
        if (!isEnd) {
            if (!isInit) {
                apple.setPos(getWidth(), getHeight());
                snake.setPosition(getWidth() / 2, getHeight() / 2);
                isInit = true;
            }

            can.drawColor(Color.rgb(163, 224, 101));

            spawnManager(can);

            drawSnake(can);
            apple.onDraw(can);


            drawObstacles(obstaclesArrayList, can);
            drawText(can, paint);

            apple.onCollisionEnter(snake);
            slowApple.onCollisionEnter(snake);

            for (int i = obstaclesArrayList.size() - 1; i >= 0 && !isEnd; i--) {
                obstaclesArrayList.get(i).onCollisionEnter(snake);
                obstaclesArrayList.get(i).onCollisionEnter(apple);
                obstaclesArrayList.get(i).onCollisionEnter(slowApple);
                isEnd = obstaclesArrayList.get(i).getIsEnd();
            }
            invalidate();

        } else {
            sendScoreInfo(snake);
        }


    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        snake.setVector(event.getX(), event.getY());
        return super.onTouchEvent(event);
    }

    private void sendScoreInfo(Snake snake) {
        Intent intent = new Intent(getContext(), ScoreActivity.class);
        intent.putExtra("score", snake.getScore());
        Activity parentActivity = ((Activity) getContext());
        parentActivity.startActivity(intent);
        parentActivity.finish();
    }

    private void drawText(Canvas can, Paint paint) {
        paint.setTextSize(54);
        can.drawText(snake.getScore(), getWidth() * .9f, getHeight() * .05f, paint);
        can.drawText(String.valueOf(snake.getSpeed()), getWidth() * .1f, getHeight() * .05f, paint);
    }

    private void spawnManager(Canvas can) {
        if (Integer.parseInt(snake.getScore()) % 3 == 0) {
            slowApple.onDraw(can);
        }
        if (Integer.parseInt(snake.getScore()) > scoreTemp) {
            apple.setPos(getWidth(), getHeight());
            slowApple.setPos(getWidth(), getHeight());
            obstaclesArrayList.add(new Obstacles());
            obstaclesArrayList.get(obstaclesArrayList.size() - 1).setPos(getWidth(), getHeight(), snake);


            for (Obstacles ob : obstaclesArrayList) {
                ob.setShowed(true);
            }
            scoreTemp++;
        }
    }

    private void drawObstacles(ArrayList<Obstacles> obstaclesArrayList, Canvas can) {
        for (Obstacles ob : obstaclesArrayList) {
            ob.onDraw(can);
        }
    }


    private void drawSnake(Canvas can) {
        snake.onDraw(can);
    }


}

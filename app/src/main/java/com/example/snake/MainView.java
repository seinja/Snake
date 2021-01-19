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

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class MainView extends View {

    private boolean isInit = false;

    private boolean isEnd() {
        return snake.isLive();
    }

    private int scoreTemp = 0;

    private final Snake snake = new Snake();
    private final Apple apple = new Apple(this);
    private final SlowApple slowApple = new SlowApple(this);
    private final Paint paint = new Paint();

    public final ArrayList<Obstacle> obstacleArrayList = new ArrayList<>();

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
    public void onDraw(Canvas canvas) {
        if (isEnd()) {
            if (!isInit) {
                apple.respawn();
                slowApple.respawn();
                snake.setPosition(getWidth() / 2, getHeight() / 2);
                isInit = true;
            }
            canvas.drawColor(Color.rgb(163, 224, 101));
            spawnManager(canvas);
            drawSnake(canvas);
            drawApples(canvas);
            drawObstacles(obstacleArrayList, canvas);
            drawText(canvas, paint);
            addApplesCollision(apple, slowApple, snake);
            addObstaclesCollision(obstacleArrayList, apple, slowApple, snake);
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

    private void sendScoreInfo(@NonNull Snake snake) {
        Intent intent = new Intent(getContext(), ScoreActivity.class);
        intent.putExtra("score", snake.getScore());
        Activity parentActivity = ((Activity) getContext());
        parentActivity.startActivity(intent);
        parentActivity.finish();
    }

    private void drawText(@NonNull Canvas can, @NonNull Paint paint) {
        paint.setTextSize(54);
        can.drawText(snake.getScore(), getWidth() * .9f, getHeight() * .05f, paint);
        can.drawText(String.valueOf(snake.getSpeed()), getWidth() * .1f, getHeight() * .05f, paint);
    }

    private void spawnManager(@NonNull Canvas can) {
        if (Integer.parseInt(snake.getScore()) % 3 == 0) {
            slowApple.onDraw(can);
        }
        if (Integer.parseInt(snake.getScore()) > scoreTemp) {
            apple.respawn();
            slowApple.respawn();
            obstacleArrayList.add(new Obstacle());
            obstacleArrayList.get(obstacleArrayList.size() - 1).setPos(getWidth(), getHeight());

            for (Obstacle ob : obstacleArrayList) {
                ob.setRespawn(true);
            }
            scoreTemp++;
        }
    }

    private void drawObstacles(@NonNull ArrayList<Obstacle> obstacleArrayList, @NonNull Canvas can) {
        for (Obstacle ob : obstacleArrayList) {
            ob.onDraw(can);
        }
    }

    private void drawSnake(@NonNull Canvas can) {
        snake.onDraw(can);
    }


    private void addApplesCollision(@NonNull Apple apple, @NonNull Apple slowApple, @NonNull Snake snake) {
        apple.onCollisionEnter(snake);
        slowApple.onCollisionEnter(snake);
    }

    private void addObstaclesCollision(@NonNull ArrayList<Obstacle> obstacleArrayList, @NonNull Apple apple, @NonNull Apple slowApple, @NonNull Snake snake) {
        for (int i = obstacleArrayList.size() - 1; i >= 0 && isEnd(); i--) {
            if (snake.onCollisionEnter(obstacleArrayList.get(i))) {
                snake.kill();
            }
            obstacleArrayList.get(i).onCollisionEnter(apple);
            obstacleArrayList.get(i).onCollisionEnter(slowApple);
        }
    }

    private void drawApples(Canvas canvas) {
        apple.onDraw(canvas);
        slowApple.onDraw(canvas);
    }

}

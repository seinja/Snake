package com.example.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.jetbrains.annotations.NotNull;

public class SnakeSegment extends CollisionObject {

    private static final @NotNull Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    static {
        paint.setColor(Color.rgb(209, 105, 0));
    }

    public SnakeSegment(final float radius) {
        super(radius);
    }

    @Override
    public void onDraw(final @NotNull Canvas canvas) {
        canvas.drawCircle(position.getX(), position.getY(), size, paint);
    }

    public void setPosition(final @NotNull SnakeSegment segment) {
        position.setPosition(segment.position);
    }
}
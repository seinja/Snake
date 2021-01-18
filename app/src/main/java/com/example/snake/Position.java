package com.example.snake;

public class Position {
    private float x;
    private float y;

    public Position(){
        this.x = 0;
        this.x = 0;
    }

    public Position(float x, float y){
        this.x = x;
        this.y = y;
    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }



}

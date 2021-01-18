package com.example.snake;

import android.app.Activity;
import android.os.Bundle;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends Activity {
    private float snakeSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getIntent().getExtras();
        getDifficultSettings(arguments);


    }

    @NotNull
    private void getDifficultSettings(Bundle arguments) {
        if (arguments != null) {
            snakeSpeed = arguments.getFloat("speed");
        }
            setContentView(new MainView(this, snakeSpeed));

    }
}
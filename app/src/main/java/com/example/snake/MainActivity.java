package com.example.snake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {
    private float speed;
    static int s = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getIntent().getExtras();
        getDifficultSettings(arguments);


    }

    private void getDifficultSettings(Bundle arguments) {
        if (arguments != null) {
            speed = arguments.getFloat("speed");
        }

        if (s <= 0) {
            startActivity(new Intent(this, StartActivity.class));
            s++;
        } else {
            setContentView(new MainView(this, speed));
        }
    }
}
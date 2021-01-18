package com.example.snake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button easy = (Button) findViewById(R.id.isEasy);
        Button normal = (Button) findViewById(R.id.isNormal);
        Button hard = (Button) findViewById(R.id.isHard);

        trowSettingsToGame(easy,normal,hard);
    }

    private void trowSettingsToGame(Button easy,Button normal,Button hard){
        easy.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("speed",0.1f);
            startActivity(intent);
        });
        normal.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("speed",0.3f);
            startActivity(intent);
        });
        hard.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("speed",0.5f);
            startActivity(intent);
        });
    }


}
package com.example.snake;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class ScoreActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Button restart = findViewById(R.id.restart);
        TextView score = findViewById(R.id.scoreTxt);

        Bundle arguments = getIntent().getExtras();

        score.append(arguments.getString("score", "null"));

        restartGame(restart);
    }

    private void restartGame(Button restart){
        restart.setOnClickListener(v -> {
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
        });
    }

}
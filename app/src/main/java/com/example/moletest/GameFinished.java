package com.example.moletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GameFinished extends AppCompatActivity {
    private TextView score, checkName;
    private EditText name;
    private int playerScore;
    private String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_finished);
        score = findViewById(R.id.score);
        name = (EditText) findViewById(R.id.playerName);
        checkName = findViewById(R.id.checkName);

        Intent intent = getIntent();
        int getScore = intent.getIntExtra("score", 0);
        score.setText(String.valueOf(getScore));
    }

    public void next(View view) {
        String playerName = name.getText().toString();
        int playerScore = Integer.valueOf(score.getText().toString());
        if (playerName.isEmpty()){
            checkName.setText("Please enter required field.");
        }else{
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            Record newRecord = new Record(playerScore, playerName);
            dbHelper.addOne(newRecord);
            Intent home = new Intent(this, MainActivity.class);
            startActivity(home);
            finish();
        }
    }
}
package com.example.moletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView volume;
    boolean isMute = true;
    SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        volume = findViewById(R.id.volume);
        pref = getSharedPreferences("game", MODE_PRIVATE);
        isMute = pref.getBoolean("isMute", false);

    }

    public void play(View view) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);

    }

    public void scores(View view) {
        Intent intent = new Intent(this, ViewScores.class);
        startActivity(intent);
    }

    public void quit(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    public void setPref(View view) {
        if(isMute){
            isMute = false;
            volume.setImageResource(R.drawable.ic_volumeon);
        }else{
            isMute = true;
            volume.setImageResource(R.drawable.ic_volumeoff);
        }
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isMute", isMute);
        editor.apply();
    }
}
 package com.example.moletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    private SharedPreferences pref;
    BackgroundSound mBackgroundSound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mBackgroundSound = new BackgroundSound();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        pref = getSharedPreferences("game", Context.MODE_PRIVATE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameView = new GameView(this, point.x, point.y, height, width);

        setContentView(gameView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBackgroundSound.cancel(true);
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!pref.getBoolean("isMute", false))
            mBackgroundSound.execute();
        gameView.resume();
    }

    public class BackgroundSound extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            MediaPlayer player = MediaPlayer.create(GameActivity.this, R.raw.bgmusic2);
            player.setLooping(true); // Set looping
            player.setVolume(0.2f, 0.2f);
            player.start();
            return null;
        }

    }

}
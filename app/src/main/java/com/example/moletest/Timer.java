package com.example.moletest;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Timer {

    int x = 0 , y = 0;
    Bitmap timer;

    Timer(int screenX, int screenY, Resources res){
        timer = BitmapFactory.decodeResource(res, R.drawable.score_and_timer);
        timer = Bitmap.createScaledBitmap(timer, timer.getWidth(), timer.getHeight(), false);
    }
}

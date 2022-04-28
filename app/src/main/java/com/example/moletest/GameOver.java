package com.example.moletest;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameOver {

    int x = 0 , y = 0;
    Bitmap gameover;

    GameOver(int screenX, int screenY, Resources res){
        gameover = BitmapFactory.decodeResource(res, R.drawable.game);
        gameover = Bitmap.createScaledBitmap(gameover, gameover.getWidth(), gameover.getHeight(), false);
    }
}

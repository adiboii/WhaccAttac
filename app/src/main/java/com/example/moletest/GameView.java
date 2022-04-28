package com.example.moletest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import java.util.Random;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying = true;
    private int screenX, screenY;
    private Paint paint, paint2, paintTime, paintStart;
    public static float screenRatioX, screenRatioY;
    private Background background;
    private MoleUp moles[];
    private int onGoingAction;
    private int actionToTake[];
    private Timer timer;
    int locationX, locationY, width;
    int score = 0;
    int action, actionHidden = 0, actionPop = 1, actionHide = 2, actionMidAction = 3, actionIsHit = 4;
    private final GameOver gameover;
    private final GameActivity activity;
    int time = 60 + 4;
    int startTime = 4;
    int totalFrames = time * 23;
    int totalStartTime = startTime * 23;
    private SoundPool soundpool, soundpool2, soundpool3;
    private int sound, heartbeat, count, go, bgmusic;
    private SharedPreferences pref;


    public GameView(GameActivity activity, int screenX, int screenY, int height, int width) {
        super(activity);
        this.activity = activity;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            @SuppressLint("WrongConstant") AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();

            soundpool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .build();

            soundpool2 = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .build();
            soundpool3 = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .build();
        }else{
            soundpool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            soundpool2 = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            soundpool3 = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }


        sound = soundpool.load(activity, R.raw.pop, 1);
        count = soundpool.load(activity, R.raw.countdown, 1);
        go = soundpool.load(activity, R.raw.go, 1);
        heartbeat = soundpool2.load(activity, R.raw.heartbeats, 1);
        bgmusic = soundpool3.load(activity, R.raw.bgmusic2, 1);

        pref = activity.getSharedPreferences("game", Context.MODE_PRIVATE);

        background = new Background(screenX, screenY, getResources());
        timer = new Timer(screenX, screenY, getResources());
        gameover = new GameOver(screenX, screenY, getResources());

        this.screenX = screenX;
        this.screenY = screenY;
        this.width = width;
        screenRatioX = (float)width/ screenX;
        screenRatioY = (float)height / screenY;

        paint = new Paint();
        paint2 = new Paint();
        paintTime = new Paint();
        paintStart = new Paint();

        paint2.setTypeface(ResourcesCompat.getFont(activity, R.font.fredoka));
        paint2.setTextSize(200);
        paint2.setColor(Color.WHITE);

        paintTime.setTextSize(64);
        paintTime.setColor(Color.BLACK);
        paintTime.setTypeface(ResourcesCompat.getFont(activity, R.font.fugaz_one));

        paint.setTextSize(64);
        paint.setColor(Color.BLACK);
        paint.setTypeface(ResourcesCompat.getFont(activity, R.font.fugaz_one));
        int secondary = ContextCompat.getColor(activity, R.color.secondary);
        paintStart.setTextSize(500);
        paintStart.setColor(Color.WHITE);
        paintStart.setShadowLayer(20,0,0, secondary);
        paintStart.setTypeface(ResourcesCompat.getFont(activity, R.font.fredoka));

        moles= new MoleUp[9];
        onGoingAction = 0;
        actionToTake = new int[9];

        for(int i = 0; i < 9; i++)
            moles[i] = new MoleUp(screenY, getResources());

        if(!pref.getBoolean("isMute", false))
            soundpool3.play(bgmusic, 1, 1, 0, 0, 1);


    }


    @Override
    public void run() {
        while(isPlaying){
            draw();
            sleep();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        locationX = (screenX / 2) - (moles[0].width / 4);
        locationY = (screenY / 2) - (moles[0].height / 4);
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //Check if the x and y position of the touch is inside the bitmap
                if (x > locationX && x < locationX + 297 && y > locationY && y < locationY + 262 && moles[0].isWhackable()) {
                    moles[0].setHit();
                    if(!pref.getBoolean("isMute", false))
                        soundpool.play(sound, 1, 1, 0, 0, 1);
                    score++;
                } else if (x > locationX + 400 && x < locationX + 297 + 400 && y > locationY && y < locationY + 262 && moles[1].isWhackable()) {
                    moles[1].setHit();
                    if(!pref.getBoolean("isMute", false))
                        soundpool.play(sound, 1, 1, 0, 0, 1);
                    score++;
                } else if (x > locationX - 400 && x < locationX + 297 - 400 && y > locationY && y < locationY + 262 && moles[2].isWhackable()) {
                    moles[2].setHit();
                    if(!pref.getBoolean("isMute", false))
                        soundpool.play(sound, 1, 1, 0, 0, 1);
                    score++;
                } else if (x > locationX && x < locationX + 297 && y > locationY - 400 && y < locationY + 262 - 400 && moles[3].isWhackable()){
                    moles[3].setHit();
                    if(!pref.getBoolean("isMute", false))
                        soundpool.play(sound, 1, 1, 0, 0, 1);
                    score++;
                }else if(x > locationX + 400 && x < locationX + 297 + 400 && y > locationY - 400 && y < locationY + 262 - 400 && moles[4].isWhackable()) {
                    moles[4].setHit();
                    if(!pref.getBoolean("isMute", false))
                        soundpool.play(sound, 1, 1, 0, 0, 1);
                    score++;
                }else if(x > locationX - 400 && x < locationX + 297 - 400 && y > locationY - 400 && y < locationY + 262 - 400 && moles[5].isWhackable()) {
                    moles[5].setHit();
                    if(!pref.getBoolean("isMute", false))
                        soundpool.play(sound, 1, 1, 0, 0, 1);
                    score++;
                }else if(x > locationX && x < locationX + 297 && y > locationY + 400 && y < locationY + 262 + 400 && moles[6].isWhackable()) {
                    moles[6].setHit();
                    if(!pref.getBoolean("isMute", false))
                        soundpool.play(sound, 1, 1, 0, 0, 1);
                    score++;
                }else if(x > locationX + 400 && x < locationX + 297 + 400 && y > locationY + 400 && y < locationY + 262 + 400 && moles[7].isWhackable()) {
                    moles[7].setHit();
                    if(!pref.getBoolean("isMute", false))
                        soundpool.play(sound, 1, 1, 0, 0, 1);
                    score++;
                }else if(x > locationX - 400 && x < locationX + 297 - 400 && y > locationY + 400 && y < locationY + 262 + 400 && moles[8].isWhackable()) {
                    moles[8].setHit();
                    if(!pref.getBoolean("isMute", false))
                        soundpool.play(sound, 1, 1, 0, 0, 1);
                    score++;
                }
                return true;
        }
        return false;
    }

    private void draw(){
        if(getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();
            float canvasHeight = canvas.getHeight();

            float canvasWidth = canvas.getWidth();

            float centerX = (screenX / 2) - (moles[0].width / 4); // 525, 2392
            float centerY = (screenY / 2) - (moles[0].height / 4);

            float centerX_game_over = (screenX / 2) - (gameover.gameover.getWidth() / 2);
            float centerY_game_over = (screenY / 2) - (gameover.gameover.getHeight() / 2);


            canvas.drawBitmap(background.background, background.x, background.y, paint);


            canvas.drawBitmap(timer.timer, centerX - 400, centerY - 900, paint);

            for(int i = 0; i < 9; i++){
                onGoingAction = moles[i].getAction();

                //if ongoing action is hidden
                //mole can pop or stay hidden
                if(onGoingAction == actionHidden){
                    action = new Random().nextInt(300);

                    if(action < 298) //stay hidden
                        action = actionHidden;
                    else //pop up
                        action = actionPop;
                }

                if(onGoingAction == actionPop) // mole is popping
                    action = actionPop;

                if(onGoingAction == actionHide) // mole is hiding
                    action = actionHide;

                //mole is midaction
                if(onGoingAction == 3){
                    action = new Random().nextInt(300);

                    if(action < 298)
                        action = actionHide;
                    else
                        action = actionMidAction;
                }

                if(onGoingAction == 4){
                    action = actionIsHit;
                }

                actionToTake[i] = action;
            }


            if(startTime >= 0)
                for(int i = 0; i < 9 ; i++)
                    actionToTake[i] = 0;

            canvas.drawBitmap(moles[0].getMole(actionToTake[0]), centerX, centerY, paint); // m0
            canvas.drawBitmap(moles[1].getMole(actionToTake[1]), centerX + 400, centerY, paint); // m1
            canvas.drawBitmap(moles[2].getMole(actionToTake[2]), centerX - 400, centerY, paint); // m2
            canvas.drawBitmap(moles[3].getMole(actionToTake[3]), centerX, centerY - 400, paint); // m3
            canvas.drawBitmap(moles[4].getMole(actionToTake[4]), centerX + 400, centerY - 400, paint); // m4
            canvas.drawBitmap(moles[5].getMole(actionToTake[5]), centerX - 400, centerY - 400, paint); // m5
            canvas.drawBitmap(moles[6].getMole(actionToTake[6]), centerX, centerY + 400, paint); // m6
            canvas.drawBitmap(moles[7].getMole(actionToTake[7]), centerX + 400, centerY + 400, paint); // m7
            canvas.drawBitmap(moles[8].getMole(actionToTake[8]), centerX - 400, centerY + 400, paint); // m8
            totalFrames--;
            if(totalFrames % 23 == 0) {
                time--;
            }
//
//            if(time <= 5)
//                paintTime.setColor(Color.RED);

            if(time ==  5)
                if(!pref.getBoolean("isMute", false))
                    soundpool2.play(heartbeat, 1, 1, 0, 0, 1);


            canvas.drawText(String.valueOf(score), centerX - 215, centerY - 795, paint);
            if(time <= 10)
                canvas.drawText(String.valueOf(time), centerX + 115, centerY - 795, paintTime);

            if(time == 0) {
                canvas.drawBitmap(gameover.gameover, centerX_game_over, centerY_game_over, paint);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                exitToScore();
            }

            if(startTime >= 1){
                canvas.drawText(String.valueOf(startTime), centerX, centerY + 200, paintStart);
            }

            if(startTime == 0){
                canvas.drawText("GO!", centerX - 300, centerY + 200, paintStart);
            }


            if(totalStartTime % 23 == 0) {
                startTime--;
                if(startTime >= 1){
                    if(!pref.getBoolean("isMute", false))
                        soundpool.play(count, 1, 1, 0, 0, 1);
                }else if(startTime == 0){
                    if(!pref.getBoolean("isMute", false))
                        soundpool.play(go, 1, 1, 0, 0, 1);
                }
            }
            totalStartTime--;

            getHolder().unlockCanvasAndPost(canvas);
        }

    }

    private void exitToScore(){
        try {
            Thread.sleep(1000);
            soundpool.release();
            soundpool2.release();
            soundpool3.release();
            soundpool = null;
            soundpool2 = null;
            soundpool3 = null;
            Intent intent = new Intent(activity, GameFinished.class);
            intent.putExtra("score", score);
            activity.startActivity(intent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sleep(){
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume(){
        isPlaying = true;
        thread = new Thread(this);

        thread.start();
    }

    public void pause(){

        try {
            isPlaying = false;
            thread.join();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}




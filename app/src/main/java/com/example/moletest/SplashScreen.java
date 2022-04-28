package com.example.moletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    Thread timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        timer = new Thread(){
            @Override
            public void run() {
                try{
                    synchronized (this){
                        wait(5000);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        };
        timer.start();
    }

}
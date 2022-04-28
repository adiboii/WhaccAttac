package com.example.moletest;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;

import static com.example.moletest.GameView.screenRatioX;
import static com.example.moletest.GameView.screenRatioY;

public class MoleUp {
    int x, y, width, height, moleCounter = 1;
    Bitmap mole1, mole2, mole3, mole4, mole5, mole6, mole7, mole8, mole9, mole10, mole11, mole12;
    boolean canBeWhacked, isPopping, isHiding, isMidAction, isHidden, isHit;

    MoleUp(int screenY, Resources res){
        mole1 = BitmapFactory.decodeResource(res, R.drawable.mole1);
        mole2 = BitmapFactory.decodeResource(res, R.drawable.mole2);
        mole3 = BitmapFactory.decodeResource(res, R.drawable.mole3);
        mole4 = BitmapFactory.decodeResource(res, R.drawable.mole4);
        mole5 = BitmapFactory.decodeResource(res, R.drawable.mole5);
        mole6 = BitmapFactory.decodeResource(res, R.drawable.mole6);
        mole7 = BitmapFactory.decodeResource(res, R.drawable.mole7);
        mole8 = BitmapFactory.decodeResource(res, R.drawable.mole8);
        mole9 = BitmapFactory.decodeResource(res, R.drawable.mole9);
        mole10 = BitmapFactory.decodeResource(res, R.drawable.mole10);
        mole11 = BitmapFactory.decodeResource(res, R.drawable.mole11);
        mole12 = BitmapFactory.decodeResource(res, R.drawable.mole12);


        width = mole1.getWidth();
        height = mole1.getHeight();
        x = width/2;
        y = height/2;


//        width *= (int) screenRatioY;
//        height *= (int) screenRatioX;

        mole1 = Bitmap.createScaledBitmap(mole1, x, y, false);
        mole2 = Bitmap.createScaledBitmap(mole2, x, y, false);
        mole3 = Bitmap.createScaledBitmap(mole3, x, y, false);
        mole4 = Bitmap.createScaledBitmap(mole4, x, y,false);
        mole5 = Bitmap.createScaledBitmap(mole5, x, y, false);
        mole6 = Bitmap.createScaledBitmap(mole6, x, y, false);
        mole7 = Bitmap.createScaledBitmap(mole7, x, y, false);
        mole8 = Bitmap.createScaledBitmap(mole8, x, y, false);
        mole9 = Bitmap.createScaledBitmap(mole9, x, y, false);
        mole10 = Bitmap.createScaledBitmap(mole10, x, y, false);
        mole11 = Bitmap.createScaledBitmap(mole11, x, y, false);
        mole12 = Bitmap.createScaledBitmap(mole12, x, y, false);

        isPopping = false;
        isHiding = false;
        isMidAction = false;
        isHidden = true;
        isHit = false;
        canBeWhacked = false;

    }

    Bitmap getMole(int moleAction){
        Bitmap mole = mole1;

        if (moleAction == 0) {
            isPopping = false;
            isMidAction = false;
            isHiding = false;
            isHidden = true;
            mole = mole1;
        }

        if (moleAction == 1) {
            isPopping = true;
            isMidAction = false;
            isHiding = false;
            isHidden = false;
            switch (moleCounter) {
                case 1:
                    moleCounter++;
                    mole = mole1;
                    break;
                case 2:
                    moleCounter++;
                    mole = mole2;
                    break;
                case 3:
                    moleCounter++;
                    mole = mole3;
                    break;
                case 4:
                    moleCounter++;
                    mole = mole4;
                    break;
                case 5:
                    moleCounter++;
                    mole = mole5;
                    break;
                case 6:
                    moleCounter++;
                    mole = mole6;
                    break;
                case 7:
                    moleCounter++;
                    mole = mole7;
                    break;
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:

                    mole = mole8;
                    moleCounter++;
                    break;
                case 21:
                    isPopping = false;
                    isMidAction = true;
                    isHiding = false;
                    isHidden = false;
                    moleCounter = 1;
                    mole = mole8;
                    break;
            }

        }

        if (moleAction == 2) {
            isPopping = false;
            isMidAction = false;
            isHiding = true;
            isHidden = false;
            switch (moleCounter) {
                case 1:
                    moleCounter++;
                    mole = mole7;
                    break;
                case 2:
                    moleCounter++;
                    mole = mole6;
                    break;
                case 3:
                    moleCounter++;
                    mole = mole5;
                    break;
                case 4:
                    moleCounter++;
                    mole = mole4;
                    break;
                case 5:
                    moleCounter++;
                    mole = mole3;
                    break;
                case 6:
                    moleCounter++;
                    mole = mole2;
                    break;
                case 7:
                    moleCounter++;
                    moleCounter = 1;
                    isPopping = false;
                    isMidAction = false;
                    isHiding = false;
                    isHidden = true;
                    mole = mole1;
                    break;
            }

        }

        if (moleAction == 3) {
            isPopping = false;
            isHidden = false;
            isMidAction = true;
            isHiding = false;

            switch(moleCounter){
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                    mole = mole7;
                    moleCounter++;
                    break;
                case 15:
                    mole = mole7;
                    moleCounter = 1;
                    break;
            }

        }

        if(moleAction == 4){
            isPopping = false;
            isHiding = false;
            isMidAction = false;
            isHidden = false;
            isHit = true;


            switch (moleCounter){
                case 1: mole = mole9;
                        moleCounter++;
                        break;
                case 2 : mole = mole10;
                        moleCounter++;
                        break;
                case 3: mole = mole11;
                        moleCounter++;
                        break;
                case 4: mole = mole12;
                        moleCounter = 1;
                        isHidden = true;
                        isHit = false;
                        break;
            }

        }

        return mole;
    }


    public int getAction(){
        int action = 0;
        if (isHidden) {
            //can only stay hidden or pop
            action = 0;
        }

        if (isPopping) {
            //do not interrupt
            action = 1;
        }

        if(isHiding){
            action = 2;
        }

        if (isMidAction) {
            //can only stay midAction or hide
            action = 3;
        }

        if(isHit){
            //mole is whacked
            action = 4;
        }

        return action;
    }


    public boolean isWhackable()
    {
        if(isPopping || isHiding || isMidAction)
            return true;
        return false;
    }

    public void setHit(){
        isHidden = false;
        isPopping = false;
        isHiding = false;
        isMidAction = false;
        isHit = true;
        moleCounter = 1;
    }







}

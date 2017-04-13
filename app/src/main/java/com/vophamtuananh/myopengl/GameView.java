package com.vophamtuananh.myopengl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * Created by vophamtuananh on 4/12/17.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    protected GameThread gameThread;
    protected SurfaceHolder surfaceHolder;
    protected long expectedDelayTime;

    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void init() {
        surfaceHolder = getHolder();
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }

    public void updateStates(){

    }

    public void updateView(Canvas canvas){
        draw(canvas);
        updateStates();
    }

    public void onResume(){
        gameThread = new GameThread(this, expectedDelayTime);
        gameThread.setRunning(true);
        gameThread.start();
    }

    public void onPause(){
        boolean retry = gameThread != null;
        while(retry){
            try {
                gameThread.setRunning(false);
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gameThread = null;
    }
}

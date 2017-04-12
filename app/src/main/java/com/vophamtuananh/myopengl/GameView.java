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

    GameThread gameThread;
    SurfaceHolder surfaceHolder;

    LancherSprite lancherSprite;
    LancherSprite lancherSprite2;

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

    private void init() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        lancherSprite = new LancherSprite(bitmap, 0, 0);
        lancherSprite2 = new LancherSprite(bitmap, 300, 300);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        int action = event.getAction();

        switch(action){
            case MotionEvent.ACTION_DOWN:
                lancherSprite.setX(x);
                lancherSprite.setY(y);
                break;
            case MotionEvent.ACTION_MOVE:
                lancherSprite.setX(x);
                lancherSprite.setY(y);
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_OUTSIDE:
                break;
            default:
        }
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        lancherSprite.draw(canvas);
        lancherSprite2.draw(canvas);
    }

    public void updateStates(){
        if (lancherSprite.isCollision(lancherSprite2)) {
            Log.e("Collision", "va cham");
        }
    }

    public void updateView(){
        Canvas canvas = null;
        try{
            canvas = surfaceHolder.lockCanvas();
            synchronized (surfaceHolder) {
                updateStates();
                draw(canvas);
            }
        }finally{
            if(canvas != null){
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    public void onResume(){
        surfaceHolder = getHolder();
        getHolder().addCallback(this);
        gameThread = new GameThread(this);
        gameThread.setRunning(true);
        gameThread.start();

    }

    public void onPause(){
        boolean retry = true;
        gameThread.setRunning(false);
        while(retry){
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

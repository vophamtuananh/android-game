package com.vophamtuananh.myopengl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by vophamtuananh on 4/13/17.
 */

public class ForegeoundView extends GameView {

    LancherSprite lancherSprite;
    LancherSprite lancherSprite2;

    public ForegeoundView(Context context) {
        super(context);
    }

    public ForegeoundView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ForegeoundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        super.init();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        lancherSprite = new LancherSprite(bitmap, 0, 0);
        lancherSprite2 = new LancherSprite(bitmap, 300, 300);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        int action = event.getAction();

        switch(action){
            case MotionEvent.ACTION_DOWN:
                synchronized (surfaceHolder) {
                    lancherSprite.setX(x);
                    lancherSprite.setY(y);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                synchronized (surfaceHolder) {
                    lancherSprite.setX(x);
                    lancherSprite.setY(y);
                }
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
        super.draw(canvas);
        lancherSprite.draw(canvas);
        lancherSprite2.draw(canvas);
    }
}

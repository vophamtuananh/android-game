package com.vophamtuananh.myopengl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;

import java.util.Random;

/**
 * Created by vophamtuananh on 4/13/17.
 */

public class BackgroundView extends GameView {

    private Paint paint;
    Bitmap canasBitmap = null;
    Canvas fixedCanvas = null;
    Matrix matrix;
    Point[] points;
    Random random;
    int width;
    int height;

    public BackgroundView(Context context) {
        super(context);
    }

    public BackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BackgroundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        width = getWidth();
        height = getHeight();

        canasBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        fixedCanvas = new Canvas();
        fixedCanvas.setBitmap(canasBitmap);
        matrix = new Matrix();

        if (points == null) {
            points = new Point[100];
            for (int i = 0; i < 100; i++) {
                int x = random.nextInt(width - 1);
                int y = random.nextInt(height - 1);
                int r = random.nextInt(255);
                int g = random.nextInt(255);
                int b = random.nextInt(255);
                points[i] = new Point(x, y, r, g, b);
            }
        }
    }

    @Override
    protected void init() {
        super.init();
        random = new Random();
        expectedDelayTime = 300;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
    }

    @Override
    public void updateStates() {
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            points[i].x = x;
            points[i].y = y;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        fixedCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        for (int i = 0; i < 100; i++) {
            Point p = points[i];
            paint.setColor(0xff000000 + (p.r << 16) + (p.g << 8) + p.b);
            fixedCanvas.drawPoint(p.x, p.y, paint);
        }
        canvas.drawBitmap(canasBitmap, matrix, null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }
}

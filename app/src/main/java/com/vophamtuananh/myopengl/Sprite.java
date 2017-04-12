package com.vophamtuananh.myopengl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

/**
 * Created by vophamtuananh on 4/12/17.
 */

public class Sprite {

    private Bitmap bitmap;
    private int x;
    private int y;
    int width, height, halfWidth, halfHeight;
    Rect rect;

    public Sprite(Bitmap bitmap, int x, int y) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        halfWidth = width / 2;
        halfHeight = height / 2;
        rect = new Rect(this.x, this.y, this.x + width, this.y + height);
    }

    public void setX(int tx){
        x = tx;
    }

    public void setY(int ty){
        y = ty;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, x - halfWidth, y - halfHeight, null);
    }

    public Rect getRect() {
        rect.set(x, y, x + width - 1, y + height - 1);
        return rect;
    }

    public boolean isCollision(Sprite s) {
        Rect r2 = s.getRect();
        Rect r1 = getRect();
        if (Rect.intersects(r1, r2)) {
            Rect collisionBound = getCollisionBound(r1, r2);
            for (int i = collisionBound.left; i < collisionBound.right; i++) {
                for (int j = collisionBound.top; j < collisionBound.bottom; j++) {
                    int pixel1 = bitmap.getPixel(i - x, j - y);
                    int pixel2 = s.getBitmap().getPixel(i - s.getX(), j - s.getY());
                    if (isFilled(pixel1) && isFilled(pixel2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Rect getCollisionBound(Rect rect1, Rect rect2) {
        int left = (int) Math.max(rect1.left, rect2.left);
        int top = (int) Math.max(rect1.top, rect2.top);
        int right = (int) Math.min(rect1.right, rect2.right);
        int bottom = (int) Math.min(rect1.bottom, rect2.bottom);
        return new Rect(left, top, right, bottom);
    }

    private boolean isFilled(int pixel) {
        return pixel != Color.TRANSPARENT;
    }
}

package com.vophamtuananh.myopengl;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by vophamtuananh on 4/12/17.
 */

public class GameThread extends Thread {

    private static int FPS = 30;

    long expectedDelayTime;

    volatile boolean running = false;

    GameView gameView;

    public GameThread(GameView view, long expectedDelayTime) {
        super();
        gameView = view;
        this.expectedDelayTime = expectedDelayTime;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    private void sleeps(long time) throws InterruptedException {
        Thread.sleep(time);
    }

    @Override
    public void run() {

        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;

        while (running) {

            if (!gameView.getHolder().getSurface().isValid()) {
                continue;
            }

            Canvas c = null;
            startTime = System.currentTimeMillis();

            try {
                c = gameView.getHolder().lockCanvas();
                synchronized (gameView.getHolder()) {
                    gameView.updateView(c);
                }
            } catch (NullPointerException ex){}
            finally {
                if (c != null) {
                    gameView.getHolder().unlockCanvasAndPost(c);
                }
            }

            sleepTime = ticksPS - (System.currentTimeMillis() - startTime);

            try {
                if (sleepTime > 0)
                    sleeps(sleepTime + expectedDelayTime);
                else
                    sleep(10 + expectedDelayTime);
            } catch (Exception e) {
                break;
            }
        }

        gameView = null;
    }
}

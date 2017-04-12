package com.vophamtuananh.myopengl;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by vophamtuananh on 4/12/17.
 */

public class GameThread extends Thread {

    private static int FPS = 30;

    long tickFPS = 1000 / FPS;

    int realFPS = 0;

    long contms=0;

    volatile boolean running = false;

    GameView gameView;
    long sleepTime = 500;

    public GameThread(GameView view) {
        super();
        gameView = view;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        long startTime;
        //long sleepTime;
        long lasttimecheck = System.currentTimeMillis();
        while (running) {
            /*long time =  System.currentTimeMillis();
            if(contms > 1000) {
                contms = time - lasttimecheck;
                realFPS=1;
            } else {
                realFPS++;
                contms += time - lasttimecheck;
            }
            lasttimecheck = time;
            startTime = time;*/

            try {
                sleep(sleepTime);
                gameView.updateView();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            /*sleepTime = tickFPS - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 10)
                    sleep(sleepTime);
                else {
                    sleep(10);
                }
            } catch (Exception e) {}*/
        }
    }
}

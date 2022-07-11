package com.iot.a20220711_surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = GameView.class.getName();
    private final SurfaceHolder holder;
    private boolean goOnPlay = true;

    private Thread renderer = new Thread() {

        @Override
        public void run() {
            super.run();

            Drawable bg = getResources().getDrawable(R.drawable.bg_space);
            bg.setBounds(holder.getSurfaceFrame());
            ball.setDelta(15,30);

            while (goOnPlay) {
                ball.move(holder.getSurfaceFrame());
                Canvas canvas = holder.lockCanvas();
                bg.draw(canvas);
                ball.draw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
        }
    };

    private Ball ball;

    public GameView(Context context) {
        super(context);

        Log.i(TAG, "GameView created");
        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        renderer.start();
        ball = new Ball();

        ball.setImage(getResources().getDrawable(R.drawable.red_ball));
        ball.setSize(new Point(100,100));
        ball.setPoint(new Point(0,0));
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        goOnPlay = false;
    }
}

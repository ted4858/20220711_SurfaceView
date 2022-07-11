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
                block1.draw(canvas);
                block2.draw(canvas);
                block3.draw(canvas);
                block4.draw(canvas);
                block5.draw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
        }
    };

    private Ball ball;
    private Block block1, block2, block3, block4, block5;

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
        block1 = new Block();
        block2 = new Block();
        block3 = new Block();
        block4 = new Block();
        block5 = new Block();

        ball.setImage(getResources().getDrawable(R.drawable.red_ball));
        ball.setSize(new Point(50,50));
        ball.setPoint(new Point(240,350));

        block1.setImage(getResources().getDrawable(R.drawable.block1));
        block1.setSize(new Point(110,40));
        block1.setPoint(new Point(20,20));
        block2.setImage(getResources().getDrawable(R.drawable.block2));
        block2.setSize(new Point(110,40));
        block2.setPoint(new Point(130,20));
        block3.setImage(getResources().getDrawable(R.drawable.block3));
        block3.setSize(new Point(110,40));
        block3.setPoint(new Point(240,20));
        block4.setImage(getResources().getDrawable(R.drawable.block4));
        block4.setSize(new Point(110,40));
        block4.setPoint(new Point(350,20));
        block5.setImage(getResources().getDrawable(R.drawable.block5));
        block5.setSize(new Point(110,40));
        block5.setPoint(new Point(180,600));
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        goOnPlay = false;
    }
}

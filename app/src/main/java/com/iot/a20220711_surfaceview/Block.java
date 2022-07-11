package com.iot.a20220711_surfaceview;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class Block {
    private Drawable image = null;
    private Point point = new Point();
    private Point size = new Point();
    private Rect rect = new Rect();

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getSize() {
        return size;
    }

    public void setSize(Point size) {
        this.size = size;
    }

    public void draw(Canvas canvas) {
        image.setBounds(point.x, point.y, point.x+size.x, point.y+size.y);
        image.draw(canvas);
    }
}

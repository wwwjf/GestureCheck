package com.wengjianfeng.gesturecheck;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


public class GestureListener extends GestureDetector.SimpleOnGestureListener
        implements View.OnTouchListener {
    public static final String TAG = GestureListener.class.getSimpleName();
    /**
     * 左右滑动的最短距离
     */
    private int distance = 10;
    /**
     * 左右滑动的最大速度
     */
    private int velocity = 20;

    private GestureDetector gestureDetector;

    public GestureListener(Context context) {
        super();
        gestureDetector = new GestureDetector(context, this);
    }

    /**
     * 向左滑的时候调用的方法，子类应该重写
     *
     * @return
     */
    public boolean left() {
        return false;
    }

    /**
     * 向右滑的时候调用的方法，子类应该重写
     *
     * @return
     */
    public boolean right() {
        return false;
    }

    /**
     * 向上滑
     *
     * @return
     */
    public boolean upwards() {
        return false;
    }

    /**
     * 向下滑
     *
     * @return
     */
    public boolean downwards() {
        return false;
    }

    /**
     * 单击
     *
     * @return
     */
    public boolean click() {
        return false;
    }

    /**
     * 双击
     *
     * @return
     */
    public boolean doubleClick() {
        return false;
    }


    @Override
    public boolean onDown(MotionEvent e) {
        Log.e(TAG, "onDown: " + e.getAction());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.e(TAG, "onShowPress: " + e.getAction());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.e(TAG, "onSingleTapUp: " + e.getAction());
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.e(TAG, "onScroll: e1 " + e1.getAction() + " e2 " + e2.getAction() + " distanceX " + distanceX + " distanceY " + distanceY);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.e(TAG, "onLongPress: " + e.getAction());
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        Log.e(TAG, "onFling: e1 " + e1.getAction() + " e2 " + e2.getAction() + " velocityX " + velocityX + " velocityY " + velocityY);
        // e1：第1个ACTION_DOWN MotionEvent
        // e2：最后一个ACTION_MOVE MotionEvent
        // velocityX：X轴上的移动速度（像素/秒）
        // velocityY：Y轴上的移动速度（像素/秒）

        //竖向距离小于横向距离
        if (Math.abs(e1.getY() - e2.getY()) < Math.abs(e1.getX() - e2.getX())) {
            // 向左滑
            if (e1.getX() - e2.getX() > distance
                    && Math.abs(velocityX) > velocity) {
                left();
            }
            // 向右滑
            if (e2.getX() - e1.getX() > distance
                    && Math.abs(velocityX) > velocity) {
                right();
            }
        } else {
            //向上滑
            if (e1.getY() - e2.getY() > distance
                    && Math.abs(velocityX) > velocity) {
                upwards();
            }

            //向下滑
            if (e2.getY() - e1.getY() > distance
                    && Math.abs(velocityX) > velocity) {
                downwards();
            }
        }
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.e(TAG, "onTouch: " + event.getAction());
        gestureDetector.onTouchEvent(event);
        return true;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public GestureDetector getGestureDetector() {
        return gestureDetector;
    }

    public void setGestureDetector(GestureDetector gestureDetector) {
        this.gestureDetector = gestureDetector;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.e(TAG, "onDoubleTap: " + e.getAction());
        doubleClick();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.e(TAG, "onDoubleTapEvent: " + e.getAction());
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.e(TAG, "onSingleTapConfirmed: " + e.getAction());
        click();
        return true;
    }
}

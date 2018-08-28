package com.wengjianfeng.gesturecheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GestureDetector mGestureDetector;
    private View layoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutView = findViewById(R.id.layout_touch);
        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            //左右滑动的最短距离
            private int distance = 10;

            //左右滑动的最大速度
            private int velocity = 20;

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                //竖向距离小于横向距离
                if (Math.abs(e1.getY() - e2.getY()) < Math.abs(e1.getX() - e2.getX())) {
                    // 向左滑
                    if (e1.getX() - e2.getX() > distance
                            && Math.abs(velocityX) > velocity) {
                        Toast.makeText(MainActivity.this, "左滑", Toast.LENGTH_SHORT).show();
                    }
                    // 向右滑
                    if (e2.getX() - e1.getX() > distance
                            && Math.abs(velocityX) > velocity) {
                        Toast.makeText(MainActivity.this, "右滑", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //向上滑
                    if (e1.getY() - e2.getY() > distance
                            && Math.abs(velocityX) > velocity) {
                        Toast.makeText(MainActivity.this, "上滑", Toast.LENGTH_SHORT).show();
                    }

                    //向下滑
                    if (e2.getY() - e1.getY() > distance
                            && Math.abs(velocityX) > velocity) {
                        Toast.makeText(MainActivity.this, "下滑", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Toast.makeText(MainActivity.this, "双击", Toast.LENGTH_SHORT).show();
                return super.onDoubleTap(e);
            }

            @Override
            public boolean onDown(MotionEvent event) {
                int width = layoutView.getWidth();
                int height = layoutView.getHeight();
                float touchX = event.getX();
                float touchY = event.getY();
                if (touchX < width / 5) {
                    //左边1/5区域
                    Toast.makeText(MainActivity.this, "左边1/5区域", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (touchX > width * 4 / 5) {
                    //右边1/5区域
                    Toast.makeText(MainActivity.this, "右边1/5区域", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (touchX > width / 5 && touchY > height * 4 / 5 &&
                        touchX < width * 4 / 5 && touchY < height) {
                    Toast.makeText(MainActivity.this, "底部中间4/5区域", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return super.onDown(event);
            }
        });
        layoutView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                mGestureDetector.onTouchEvent(event);
                return true;
            }
        });

        /*layoutView.setOnTouchListener(new GestureListener(this) {


            @Override
            public boolean left() {
                Toast.makeText(MainActivity.this, "向左滑", Toast.LENGTH_SHORT).show();
                return super.left();
            }

            @Override
            public boolean right() {
                Toast.makeText(MainActivity.this, "向右滑", Toast.LENGTH_SHORT).show();
                return super.right();
            }

            @Override
            public boolean upwards() {
                Toast.makeText(MainActivity.this, "向上滑", Toast.LENGTH_SHORT).show();
                return super.upwards();
            }

            @Override
            public boolean downwards() {
                Toast.makeText(MainActivity.this, "向下滑", Toast.LENGTH_SHORT).show();
                return super.downwards();
            }

            @Override
            public boolean doubleClick() {
                Toast.makeText(MainActivity.this, "双击", Toast.LENGTH_SHORT).show();
                return super.doubleClick();
            }

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int width = layoutView.getWidth();
                int height = layoutView.getHeight();
                float touchX = event.getX();
                float touchY = event.getY();
                if (touchX < width / 5) {
                    //左边1/5区域
                    Toast.makeText(MainActivity.this, "左边1/5区域", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (touchX > width * 4 / 5) {
                    //右边1/5区域
                    Toast.makeText(MainActivity.this, "右边1/5区域", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (touchX > width / 5 && touchY > height * 3 / 5 &&
                        touchX < width * 4 / 5 && touchY < height) {
                    Toast.makeText(MainActivity.this, "底部中间3/5区域", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return super.onTouch(v, event);
            }
        });*/
    }
}

package com.example.a01378359.testapp.ninepoint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TableLayout;

import com.example.a01378359.testapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 01378359 on 2018/6/21.
 */

public class NineLock extends View {

    private Point[][] mPoints = new Point[3][3];

    private Paint mPaint;
    private Paint mPressPaint;
    private Paint mErrorPaint;
    private Bitmap mNormalBitmap;
    private Bitmap mPressBitmap;
    private Bitmap mErrorBitmap;
    private float mPointRadius;
    private float mX;
    private float mY;
    private List<Point> clickPoints = new ArrayList<Point>();
    private Point nowPoint;

    private Bitmap finger;
    private float rad;
    private OnDrawFinishListener listener;

    private StringBuilder builder = new StringBuilder();

    public void setOnDrawFinishListener(OnDrawFinishListener listener){
        this.listener = listener;
    }

    public interface OnDrawFinishListener{
        public boolean finish(String password);
    }



    private boolean isDraw = false;

    public NineLock(Context context) {
        super(context);
        init();
    }

    public NineLock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NineLock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPoints(canvas);

        if (clickPoints.size()>0) {
            Point oldPoint = clickPoints.get(0);

            for (int i = 1; i < clickPoints.size(); i++) {
                System.out.println("当前的i："+i);
                drawLine(canvas, oldPoint, clickPoints.get(i));
                oldPoint = clickPoints.get(i);
            }

            if (isDraw ){
                drawLine(canvas,clickPoints.get(clickPoints.size()-1),nowPoint);
            }



        }

        if (clickPoints.size()!=0) {

            drawFinger(canvas);
        }
    }

    private void drawFinger(Canvas canvas){


        if (nowPoint!=null) {

            canvas.drawBitmap(finger, nowPoint.mX - rad, nowPoint.mY - rad, mPressPaint);
        }


    }


    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mErrorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 按下状态的画笔
        mPressPaint.setColor(Color.parseColor("#1296db"));
        mPressPaint.setStrokeWidth(7);
        // 错误状态的画笔
        mErrorPaint.setColor(Color.parseColor("#FB0C13"));
        mErrorPaint.setStrokeWidth(7);

        // 加载三种状态图片
        mNormalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.point_normal);
        mPressBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.point_error);
        mErrorBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.point_click);

        mPointRadius = mNormalBitmap.getWidth() / 2;

        finger = BitmapFactory.decodeResource(getResources(),R.drawable.finger);
        System.out.println(finger);

        rad = finger.getWidth()/2;

        // 当前视图的大小
        int width = getWidth();
        int height = getHeight();
        // 九宫格点的偏移量
        int offSet = Math.abs(width - height) / 2;
        // x、y轴上的偏移量
        int offSetX = 0, offSetY = 0;
        int pointItemWidth = 0; // 每个点所占用方格的宽度
        if (width > height) { // 横屏的时候
            offSetX = offSet;
            offSetY = 0;
            pointItemWidth = height / 4;
        }
        if (width < height) { // 竖屏的时候
            offSetX = 0;
            offSetY = offSet;
            pointItemWidth = width / 4;
        }

        // 初始化九个点
        mPoints[0][0] = new Point(offSetX + pointItemWidth, offSetY + pointItemWidth);
        mPoints[0][1] = new Point(offSetX + pointItemWidth * 2, offSetY + pointItemWidth);
        mPoints[0][2] = new Point(offSetX + pointItemWidth * 3, offSetY + pointItemWidth);

        mPoints[1][0] = new Point(offSetX + pointItemWidth, offSetY + pointItemWidth * 2);
        mPoints[1][1] = new Point(offSetX + pointItemWidth * 2, offSetY + pointItemWidth * 2);
        mPoints[1][2] = new Point(offSetX + pointItemWidth * 3, offSetY + pointItemWidth * 2);

        mPoints[2][0] = new Point(offSetX + pointItemWidth, offSetY + pointItemWidth * 3);
        mPoints[2][1] = new Point(offSetX + pointItemWidth * 2, offSetY + pointItemWidth * 3);
        mPoints[2][2] = new Point(offSetX + pointItemWidth * 3, offSetY + pointItemWidth * 3);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mX = event.getX();
        mY = event.getY();
        int[] position;

        Point oldPoint = null;
        Point newPoint = null;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                position = getPosition();

                if (position!=null){

                    int i = position[0];
                    int j = position[1];
                    clickPoints.add(mPoints[i][j]);
                    isDraw = true;
                    builder.append(i);
                    builder.append(j);

                    mPoints[i][j].state = Point.POINT_STATUS_CLICK;
                }

                break;
            case MotionEvent.ACTION_MOVE:

                position = getPosition();

                nowPoint = new Point(event.getX(),event.getY());
                if (position!=null){
                    isDraw = true;
                    mPoints[position[0]][position[1]].state = Point.POINT_STATUS_CLICK;
                    if (!clickPoints.contains(mPoints[position[0]][position[1]])){
                        clickPoints.add(mPoints[position[0]][position[1]]);
                        builder.append(position[0]);
                        builder.append(position[1]);

                    }

                }

                invalidate();

                break;
            case MotionEvent.ACTION_UP:
                isDraw = false;
                System.out.println(builder.toString());
                if (listener.finish(builder.toString())){

                }


                reset();
                break;
            default:
                break;
        }
        return true;
    }

    private void drawLines(Canvas canvas){



    }

    private int[] getPosition(){

        Point point = new Point(mX,mY);

        for (int i = 0; i < mPoints.length; i++) {
            for (int j = 0; j < mPoints[i].length; j++) {
                if (mPoints[i][j].getInstance(point)<=mPointRadius){
                    int[] result = new int[2];
                    result[0]=i;
                    result[1]=j;

                    return result;
                }
            }
        }

        return null;
    }

    private void reset(){

        for (Point point:
                clickPoints) {
            point.state = Point.POINT_STATUS_NORMAL;
        }
        nowPoint = null;
        clickPoints.clear();
        builder = new StringBuilder();
        invalidate();
    }

    private void drawLine(Canvas canvas, Point a, Point b){
        if (a.state == Point.POINT_STATUS_CLICK){
            canvas.drawLine(a.mX, a.mY, b.mX, b.mY, mPressPaint);
        }
        if (a.state == Point.POINT_STATUS_ERROR){
            canvas.drawLine(a.mX, a.mY, b.mX, b.mY, mErrorPaint);
        }
    }
    private void drawPoints(Canvas canvas) {
        for (int i = 0; i < mPoints.length; i++) {
            for (int j = 0; j < mPoints[i].length; j++) {
                Point point = mPoints[i][j];
                // 不同状态绘制点
                switch (point.state) {
                    case Point.POINT_STATUS_NORMAL:
                        canvas.drawBitmap(mNormalBitmap, point.mX - mPointRadius, point.mY - mPointRadius, mPaint);
                        break;
                    case Point.POINT_STATUS_CLICK:
                        canvas.drawBitmap(mPressBitmap, point.mX - mPointRadius, point.mY - mPointRadius, mPaint);
                        break;
                    case Point.POINT_STATUS_ERROR:
                        canvas.drawBitmap(mErrorBitmap, point.mX - mPointRadius, point.mY - mPointRadius, mPaint);
                        break;
                }
            }
        }
    }
}
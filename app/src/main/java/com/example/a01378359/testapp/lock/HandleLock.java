package com.example.a01378359.testapp.lock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.a01378359.testapp.ninepoint.Point;

import java.util.ArrayList;

/**
 * Created by 01378359 on 2018/6/24.
 */

public class HandleLock extends View {

    // 三种画笔
    private Paint mNormalPaint;
    private Paint mClickPaint;
    private Paint mErrorPaint;

    // 点的半径
    private float mRadius;

    // 九个点，使用二维数组
    private Point[][] mPoints = new Point[3][3];

    // 保存手势划过的点
    private ArrayList<Point> mClickPointsList = new ArrayList<Point>();
    // 手势的 x 坐标，y 坐标
    private float mHandleX;
    private float mHandleY;

    private OnDrawFinishListener mListener;

    // 保存滑动路径
    private StringBuilder mRoute = new StringBuilder();

    public HandleLock(Context context) {
        super(context);
        initView();
    }

    public interface OnDrawFinishListener{
        public void drawFinish(String route);
    }

    public void setOnDrawFinishListener(OnDrawFinishListener listener){
        this.mListener = listener;
    }

    public HandleLock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public HandleLock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPoints(canvas);
        drawLines(canvas);
        drawFinger(canvas);
    }


    // 重写点击事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mHandleX = event.getX();
        mHandleY = event.getY();
        int[] position;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                position = getPositions();
                if (position != null) {
                    mClickPointsList.add(mPoints[position[0]][position[1]]);
                    mPoints[position[0]][position[1]].state = Point.POINT_STATUS_CLICK;
                    mRoute.append(position[0]);
                    mRoute.append(position[1]);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                position = getPositions();
                if (position != null) {
                    if (!mClickPointsList.contains(mPoints[position[0]][position[1]])) {
                        mClickPointsList.add(mPoints[position[0]][position[1]]);
                        mPoints[position[0]][position[1]].state = Point.POINT_STATUS_CLICK;
                        mRoute.append(position[0]);
                        mRoute.append(position[1]);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                mListener.drawFinish(mRoute.toString());
                resetData();
                break;
            default:
                break;
        }
        invalidate();

        return true;
    }

    // 获取手指移动中选取的点
    private int[] getPositions() {
        Point point = new Point(mHandleX, mHandleY);
        int[] position = new int[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (mPoints[i][j].getInstance(point) <= mRadius) {
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }

        }
        return null;
    }

    // 初始化数据
    private void initView() {

        // 初始化三种画笔
        mNormalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mNormalPaint.setColor(Color.parseColor("#ABABAB"));
        mClickPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mClickPaint.setColor(Color.parseColor("#1296db"));
        mErrorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mErrorPaint.setColor(Color.parseColor("#FB0C13"));

        // 获取点间隔
        float offset = 0;
        if (getWidth() > getHeight()) {
            // 横屏
            offset = getHeight() / 7;
            mRadius = offset / 2;
            mPoints[0][0] = new Point(getWidth() / 2 - offset * 2, offset + mRadius);
            mPoints[0][1] = new Point(getWidth() / 2, offset + mRadius);
            mPoints[0][2] = new Point(getWidth() / 2 + offset * 2, offset + mRadius);
            mPoints[1][0] = new Point(getWidth() / 2 - offset * 2, offset * 3 + mRadius);
            mPoints[1][1] = new Point(getWidth() / 2, offset * 3 + mRadius);
            mPoints[1][2] = new Point(getWidth() / 2 + offset * 2, offset * 3 + mRadius);
            mPoints[2][0] = new Point(getWidth() / 2 - offset * 2, offset * 5 + mRadius);
            mPoints[2][1] = new Point(getWidth() / 2, offset * 5 + mRadius);
            mPoints[2][2] = new Point(getWidth() / 2 + offset * 2, offset * 5 + mRadius);
        } else {
            // 竖屏
            offset = getWidth() / 7;
            mRadius = offset / 2;
            mPoints[0][0] = new Point(offset + mRadius, getHeight() / 2 - 2 * offset);
            mPoints[0][1] = new Point(offset * 3 + mRadius, getHeight() / 2 - 2 * offset);
            mPoints[0][2] = new Point(offset * 5 + mRadius, getHeight() / 2 - 2 * offset);
            mPoints[1][0] = new Point(offset + mRadius, getHeight() / 2);
            mPoints[1][1] = new Point(offset * 3 + mRadius, getHeight() / 2);
            mPoints[1][2] = new Point(offset * 5 + mRadius, getHeight() / 2);
            mPoints[2][0] = new Point(offset + mRadius, getHeight() / 2 + 2 * offset);
            mPoints[2][1] = new Point(offset * 3 + mRadius, getHeight() / 2 + 2 * offset);
            mPoints[2][2] = new Point(offset * 5 + mRadius, getHeight() / 2 + 2 * offset);
        }


    }

    // 画点
    private void drawPoints(Canvas canvas) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Point point = mPoints[i][j];
                switch (point.state) {
                    case Point.POINT_STATUS_NORMAL:
                        canvas.drawCircle(point.mX, point.mY, mRadius, mNormalPaint);
                        break;
                    case Point.POINT_STATUS_CLICK:
                        canvas.drawCircle(point.mX, point.mY, mRadius, mClickPaint);
                        break;
                    case Point.POINT_STATUS_ERROR:
                        canvas.drawCircle(point.mX, point.mY, mRadius, mErrorPaint);
                        break;
                    default:
                        break;

                }
            }
        }
    }

    private void drawLines(Canvas canvas) {
        if (mClickPointsList.size() > 0) {
            Point prePoint = mClickPointsList.get(0);
            // 已选择点的连线
            for (int i = 1; i < mClickPointsList.size(); i++) {
                if (prePoint.state == Point.POINT_STATUS_CLICK) {
                    mClickPaint.setStrokeWidth(7);
                    canvas.drawLine(prePoint.mX, prePoint.mY, mClickPointsList.get(i).mX, mClickPointsList.get(i).mY, mClickPaint);
                }
                if (prePoint.state == Point.POINT_STATUS_ERROR) {
                    mErrorPaint.setStrokeWidth(7);
                    canvas.drawLine(prePoint.mX, prePoint.mY, mClickPointsList.get(i).mX, mClickPointsList.get(i).mY, mErrorPaint);
                }
                prePoint = mClickPointsList.get(i);
            }
            // 最新点到手指的连线
            canvas.drawLine(mClickPointsList.get(mClickPointsList.size()-1).mX,mClickPointsList.get(mClickPointsList.size()-1).mY,
                    mHandleX,mHandleY,mClickPaint);
        }

    }

    // 画手势点
    private void drawFinger(Canvas canvas){
        // 有选择点后再出现手势点
        if (mClickPointsList.size() > 0) {
            canvas.drawCircle(mHandleX, mHandleY, mRadius / 2, mClickPaint);
        }
    }

    // 重置数据
    private void resetData() {
        for (Point point :
                mClickPointsList) {
            point.state = Point.POINT_STATUS_NORMAL;
        }
        mClickPointsList.clear();
        mRoute = new StringBuilder();
    }
}

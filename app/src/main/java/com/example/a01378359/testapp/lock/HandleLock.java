package com.example.a01378359.testapp.lock;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

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

    // 保存滑动路径
    private StringBuilder mRoute = new StringBuilder();
    // 是否在画错误状态
    private boolean isDrawError = false;

    public HandleLock(Context context) {
        super(context);
        initData();
    }

    private OnDrawFinishListener mListener;

    // 定义绘制完成的接口
    public interface OnDrawFinishListener {
        public boolean drawFinish(String route);
    }

    // 定义绘制完成的方法，传入接口
    public void setOnDrawFinishListener(OnDrawFinishListener listener) {
        this.mListener = listener;
    }

    public HandleLock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    public HandleLock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initData();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 判断是否画错误状态，画错误状态不需要画手势点已经于最新选择点的连线
        if (isDrawError) {
            drawPoints(canvas);
            drawLines(canvas);
        } else {
            drawPoints(canvas);
            drawLines(canvas);
            drawFinger(canvas);
        }
    }


    // 重写点击事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 获取手势的坐标
        mHandleX = event.getX();
        mHandleY = event.getY();
        int[] position;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                position = getPositions();
                // 判断点下时是否选择到点
                if (position != null) {
                    // 添加到已选择点中，并改变其状态
                    mClickPointsList.add(mPoints[position[0]][position[1]]);
                    mPoints[position[0]][position[1]].state = Point.POINT_STATUS_CLICK;
                    // 保存路径，依次保存其横纵下标
                    mRoute.append(position[0]);
                    mRoute.append(position[1]);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                position = getPositions();
                // 判断手势移动时是否选择到点
                if (position != null) {
                    // 判断当前选择的点是否已经被选择过
                    if (!mClickPointsList.contains(mPoints[position[0]][position[1]])) {
                        // 添加到已选择点中，并改变其状态
                        mClickPointsList.add(mPoints[position[0]][position[1]]);
                        mPoints[position[0]][position[1]].state = Point.POINT_STATUS_CLICK;
                        // 保存路径，依次保存其横纵下标
                        mRoute.append(position[0]);
                        mRoute.append(position[1]);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                // 完成时回调绘制完成的方法，返回比对结果，判断手势密码是否正确
                mListener.drawFinish(mRoute.toString());
                // 返回错误，则将所有已选择点状态改为错误
                if (!mListener.drawFinish(mRoute.toString())) {
                    for (Point point :
                            mClickPointsList) {
                        point.state = Point.POINT_STATUS_ERROR;
                    }
                    // 将是否绘制错误设为 true
                    isDrawError = true;
                    // 刷新视图
                    invalidate();
                    // 这里我们使用 handler 异步操作，使其错误状态保持 0.5s
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if (!mListener.drawFinish(mRoute.toString())) {
                                Message message = new Message();
                                message.arg1 = 0;
                                handler.sendMessage(message);
                            }
                        }
                    }).run();
                } else {
                    resetData();
                }
                invalidate();

                break;
            default:
                break;
        }
        invalidate();

        return true;
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.arg1) {
                case 0:
                    try {
                        // 沉睡 0.5s
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 重置数据,并刷新视图
                    resetData();
                    invalidate();
                    break;
                default:
                    break;
            }

        }
    };

    // 获取手指移动中选取的点
    private int[] getPositions() {
        Point point = new Point(mHandleX, mHandleY);
        int[] position = new int[2];
        // 遍历九个点，看手势的坐标是否在九个圆内，有则返回这个点的两个下标
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
    private void initData() {

        // 初始化三种画笔，正常状态为灰色，点下状态为蓝色，错误为红色
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
        // 便利所有的点，并且判断这些点的状态
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
    // 画点与点之间的线
    private void drawLines(Canvas canvas) {
        // 判断手势是否已经划过点了
        if (mClickPointsList.size() > 0) {
            Point prePoint = mClickPointsList.get(0);
            // 将所有已选择点的按顺序连线
            for (int i = 1; i < mClickPointsList.size(); i++) {
                // 判断已选择点的状态
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

        }

    }
    // 画手势点
    private void drawFinger(Canvas canvas) {
        // 有选择点后再出现手势点
        if (mClickPointsList.size() > 0) {
            canvas.drawCircle(mHandleX, mHandleY, mRadius / 2, mClickPaint);
        }
        // 最新点到手指的连线，判断是否有已选择的点，有才能画
        if (mClickPointsList.size() > 0) {
            canvas.drawLine(mClickPointsList.get(mClickPointsList.size() - 1).mX, mClickPointsList.get(mClickPointsList.size() - 1).mY,
                    mHandleX, mHandleY, mClickPaint);
        }
    }

    // 重置数据
    private void resetData() {
        // 将所有选择过的点的状态改为正常
        for (Point point :
                mClickPointsList) {
            point.state = Point.POINT_STATUS_NORMAL;
        }
        // 清空已选择点
        mClickPointsList.clear();
        // 清空保存的路径
        mRoute = new StringBuilder();
        // 不再画错误状态
        isDrawError = false;
    }

}

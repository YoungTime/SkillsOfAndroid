package com.example.a01378359.testapp.lock;

/**
 * Created by 01378359 on 2018/6/21.
 */

public class Point {

    // 点的三种状态
    public static final int POINT_STATUS_NORMAL = 0;
    public static final int POINT_STATUS_CLICK = 1;
    public static final int POINT_STATUS_ERROR = 2;

    // 默认状态
    public int state = POINT_STATUS_NORMAL;

    // 点的坐标
    public float mX;
    public float mY;

    public Point(float x,float y){
        this.mX = x;
        this.mY = y;
    }

    // 获取两个点的距离
    public float getInstance(Point a){
        return (float) Math.sqrt((mX-a.mX)*(mX-a.mX)+(mY-a.mY)*(mY-a.mY));
    }

}

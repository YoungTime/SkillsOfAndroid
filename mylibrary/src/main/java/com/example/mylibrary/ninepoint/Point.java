package com.example.mylibrary.ninepoint;

/**
 * Created by 01378359 on 2018/6/21.
 */

public class Point {

    public static final int POINT_STATUS_NORMAL = 0;
    public static final int POINT_STATUS_CLICK = 1;
    public static final int POINT_STATUS_ERROR = 2;

    int state = POINT_STATUS_NORMAL;

    float mX;
    float mY;

    public Point(float x,float y){
        this.mX = x;
        this.mY = y;
    }

    public float getInstance(Point a){
        return (float) Math.sqrt((mX-a.mX)*(mX-a.mX)+(mY-a.mY)*(mY-a.mY));
    }

}

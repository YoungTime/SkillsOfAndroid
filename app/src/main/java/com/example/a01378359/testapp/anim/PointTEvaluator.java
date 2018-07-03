package com.example.a01378359.testapp.anim;

import android.animation.TypeEvaluator;

import com.example.a01378359.testapp.lock.Point;

/**
 * Created by 01378359 on 2018/7/2.
 */

public class PointTEvaluator implements TypeEvaluator<PointT> {

    // 传进来的 fraction 为动画已经运行的时间，0.0 为开始，1.0 为结束
    // startValue 为动画起始点坐标，endValue 为动画结束点坐标
    @Override
    public PointT evaluate(float fraction, PointT startValue, PointT endValue) {
        // X 轴已运行距离
        float d = fraction*(endValue.getX()-startValue.getX());
        // 设置 X 轴坐标，为起始 X 坐标加上已经运动的 X 距离
        float x = startValue.getX()+d;
        // Y 轴按照正弦函数图像运行，d/200 是为了防止像素过大，运动出边界
        // * 200 是因为正弦函数值域为 -1 到 1，像素大小无法分辨动画
        float y = (float) (startValue.getY() + (Math.sin((d/200)*Math.PI)) * 200);
        return new PointT(x,y);
    }
}

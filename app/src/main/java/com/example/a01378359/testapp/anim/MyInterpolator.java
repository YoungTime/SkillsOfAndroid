package com.example.a01378359.testapp.anim;

import android.annotation.SuppressLint;
import android.view.animation.Interpolator;

/**
 * Created by 01378359 on 2018/7/2.
 */

@SuppressLint("NewApi")
public class MyInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float input) {
        // 这里我们使用高中经常使用的正弦表达式
        return (float) (2*Math.sin(2*Math.PI*input+0.5f*Math.PI));
    }
}

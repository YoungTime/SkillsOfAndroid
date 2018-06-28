package com.example.a01378359.testapp.main;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by 01378359 on 2018/6/26.
 */

@SuppressLint("AppCompatCustomView")
public class HandleTestView extends Button {
    private ObjectAnimator animator;

    public HandleTestView(Context context) {
        super(context);
    }

    public HandleTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HandleTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {



        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:
                int[] pos = new int[2];
                this.getLocationInWindow(pos);
                float x = event.getRawX() - pos[0];
                float y = event.getRawY() - pos[1];
                animator = ObjectAnimator.ofFloat(this,"translationX",x);
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(this,"translationY",y);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(animator1,animator);
                set.start();
                break;
            default:
                break;
        }
        invalidate();

        return true;
    }
}

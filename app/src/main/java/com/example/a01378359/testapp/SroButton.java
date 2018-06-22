package com.example.a01378359.testapp;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by 01378359 on 2018/6/18.
 */

public class SroButton extends android.support.v7.widget.AppCompatButton {

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:

                if (event.getX()>event.getY()){

                }
                break;
            case MotionEvent.ACTION_UP:
                break;




            default:
                break;

        }


        return super.onTouchEvent(event);
    }

    public SroButton(Context context) {
        super(context);
    }
}

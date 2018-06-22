package com.example.a01378359.testapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 01378359 on 2018/6/17.
 */

public class MyDecoration extends RecyclerView.ItemDecoration {

    private int wight;
    private int height;
    private int item_height;
    private int item_padding;
    private Paint paint;

    public MyDecoration(Context context) {

        wight = context.getResources().getDisplayMetrics().widthPixels;
        height = context.getResources().getDisplayMetrics().heightPixels;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint.setColor(Color.BLACK);
        item_height = DensityUtil.dip2px(context, 1);
        item_padding = DensityUtil.dip2px(context, 10);
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = parent.getChildAt(i);
            int top = view.getTop();
            int bottom = top + item_height;
            c.drawRect(0, top, wight, bottom, paint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);


    }
}

package com.example.a01378359.testapp.anim;

import android.widget.ImageView;

/**
 * Created by 01378359 on 2018/7/2.
 */

public class ScaleImage {
    public ImageView mImageView;
    private float mScale;

    public ScaleImage(ImageView imageView){
        this.mImageView = imageView;
    }

    public float getMScale() {
        return mScale;
    }

    public void setMScale(float mScale) {
        this.mScale = mScale;
        mImageView.setScaleX(mScale);
        mImageView.setScaleY(mScale);
    }
}

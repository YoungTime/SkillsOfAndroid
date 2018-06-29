package com.example.a01378359.testapp.anim;

import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.a01378359.testapp.R;

public class FrameAnimActivity extends AppCompatActivity {
    private Button startFrame;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_anim);
        startFrame = findViewById(R.id.btn_frame_start);
        imageView = findViewById(R.id.iv_frame_anim);

        startFrame.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                doFrameByCode();
            }
        });


    }

    // 通过 xml 实现帧动画
    private void doFrameByXML(){
        imageView.setBackgroundResource(R.drawable.anim_frame);
        AnimationDrawable drawable = (AnimationDrawable) imageView.getBackground();
        if (!drawable.isRunning()){
            drawable.start();
        }
    }
    // 通过代码实现帧动画
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void doFrameByCode(){
        AnimationDrawable drawable = new AnimationDrawable();
        drawable.addFrame(getResources().getDrawable(R.drawable.frame1),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame2),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame3),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame4),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame5),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame6),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame7),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame8),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame9),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame10),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame11),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame12),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame13),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame14),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame15),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame16),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame17),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame18),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame19),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame20),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame21),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame22),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame23),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame24),50);
        drawable.addFrame(getResources().getDrawable(R.drawable.frame25),50);
        imageView.setBackground(drawable);
        // 是否只播放一次
        drawable.setOneShot(false);
        if (!drawable.isRunning()){
            drawable.start();
        }
    }
}

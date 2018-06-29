package com.example.a01378359.testapp.anim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.a01378359.testapp.R;

public class TweenAnimActivity extends AppCompatActivity implements View.OnClickListener{

    private Button alpha;
    private Button translate;
    private Button scale;
    private Button rotate;
    private Button group;

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_anim);
        initView();
    }

    private void initView(){
        alpha = findViewById(R.id.btn_tween_alpha);
        alpha.setOnClickListener(this);
        translate = findViewById(R.id.btn_tween_translate);
        translate.setOnClickListener(this);
        scale = findViewById(R.id.btn_tween_scale);
        scale.setOnClickListener(this);
        rotate = findViewById(R.id.btn_tween_rotate);
        rotate.setOnClickListener(this);
        group = findViewById(R.id.btn_tween_group);
        group.setOnClickListener(this);

        image = findViewById(R.id.iv_tween);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_tween_alpha:
                doAlphaByXML();
                break;
            case R.id.btn_tween_translate:
                doTranslateByXML();
                break;
            case R.id.btn_tween_scale:
                doScaleByXML();
                break;
            case R.id.btn_tween_rotate:
                doRotateByXML();
                break;
            case R.id.btn_tween_group:
                doGroupByXML();
                break;
            default:
                break;
        }

    }
    // 通过 xml 完成渐变动画
    private void doAlphaByXML(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_tween_alpha);
        image.startAnimation(animation);
    }
    // 通过代码完成渐变动画
    private void doAlphaByCode(){
        AlphaAnimation animation = new AlphaAnimation(1.0f,0.0f);
        animation.setDuration(1000);
        image.startAnimation(animation);
    }
    // 通过 xml 完成平移动画
    private void doTranslateByXML(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_tween_translate);
        image.startAnimation(animation);
    }
    // 通过代码完成平移动画
    private void doTranslateByCode(){
        TranslateAnimation animation = new TranslateAnimation(0,100,0,100);
        animation.setDuration(1000);
        image.startAnimation(animation);
    }
    // 通过 xml 完成缩放动画
    private void doScaleByXML(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_tween_scale);
        image.startAnimation(animation);
    }
    // 通过代码完成缩放动画
    private void doScaleByCode(){
        ScaleAnimation animation = new ScaleAnimation(0.5f,2,0.5f,2,0,0);
        animation.setDuration(1000);
        image.startAnimation(animation);
    }
    // 通过 xml 完成旋转动画
    private void doRotateByXML(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_tween_rotate);
        image.startAnimation(animation);
    }
    // 通过代码完成旋转动画
    private void doRotateByCode(){
        RotateAnimation animation = new RotateAnimation(0,360,100,100);
        animation.setDuration(1000);
        image.startAnimation(animation);
    }
    // 完成组合动画
    private void doGroupByXML(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_tween_group);
        image.startAnimation(animation);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // 动画开始时
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 动画结束时
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // 动画重复时
            }
        });
    }

}

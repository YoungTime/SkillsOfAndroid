package com.example.a01378359.testapp.anim;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.a01378359.testapp.R;

public class ObjectAnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    private Button doAlpha;
    private Button doTranslate;
    private Button doScale;
    private Button doRotate;
    private Button doGroup;

    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_values_anim);
        initView();

    }

    private void initView(){
        doAlpha = findViewById(R.id.btn_anim_value_alpha);
        doAlpha.setOnClickListener(this);
        doTranslate = findViewById(R.id.btn_anim_value_translate);
        doTranslate.setOnClickListener(this);
        doScale = findViewById(R.id.btn_anim_value_scale);
        doScale.setOnClickListener(this);
        doRotate = findViewById(R.id.btn_anim_value_rotate);
        doRotate.setOnClickListener(this);
        doGroup = findViewById(R.id.btn_anim_value_group);
        doGroup.setOnClickListener(this);
        imageView=findViewById(R.id.iv_animator);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_anim_value_alpha:
                doAlphaByCode();
                break;
            case R.id.btn_anim_value_translate:
                doTranslateXByCode();
                break;
            case R.id.btn_anim_value_scale:
                addProperty();
                break;
            case R.id.btn_anim_value_rotate:
                doRotateByCode();
                break;
            case R.id.btn_anim_value_group:
                doGroupByXML();
            default:
                break;
        }
    }

    // 通过 xml 完成渐变动画
    private void doAlphaByXML(){

    }
    // 通过代码完成渐变动画
    private void doAlphaByCode(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView,"alpha",1.0f,0.0f)
                // 设置动画时常
                .setDuration(1000);
        // 设置重复次数
        animator.setRepeatCount(2);
        animator.start();
    }
    // 通过 xml 完成平移动画
    private void doTranslateByXML(){
    }
    // 通过代码完成平移动画
    // 平移 X 轴
    private void doTranslateXByCode(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView,"translationX",0f,200f)
                .setDuration(1000);
        animator.start();

    }
    // 平移 Y 轴
    private void doTranslateYByCode(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView,"translationY",0f,200f)
                .setDuration(1000);
        animator.start();
    }
    // 通过 xml 完成缩放动画
    private void doScaleByXML(){
    }
    // 通过代码完成缩放动画
    // 缩放 X 轴
    private void doScaleXByCode(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView,"scaleX",1f,3f);
        animator.start();
    }
    // 缩放 Y 轴
    private void doScaleYByCode(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView,"scaleX",1f,3f);
        animator.start();
    }
    // 通过 xml 完成旋转动画
    private void doRotateByXML(){
    }
    // 通过代码完成旋转动画
    private void doRotateByCode(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView,"rotation",0f,360f)
                .setDuration(1000);
        animator.start();
    }
    // 对 ScaleImage 进行属性动画，也叫为 ImageView 添加属性
    private void addProperty(){
        ScaleImage image = new ScaleImage(imageView);
        ObjectAnimator animator =ObjectAnimator.ofFloat(image,"mScale",1.0f,3.0f)
                .setDuration(1000);
        animator.start();
    }

    // 组合动画方式一
    private void doGroupByCode1(){
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView,"TranslationX",0f,200f)
                .setDuration(1000);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView,"alpha",1.0f,0.0f)
                .setDuration(1000);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageView,"rotation",0f,360f)
                .setDuration(1000);
        AnimatorSet set = new AnimatorSet();
        // 串行
        set.playSequentially(animator1,animator2);
        // 并行
        set.playTogether(animator2,animator3);
        set.start();

        /**
         * 这里也可以使用这样
          set.play(animator1).before(animator2);
          set.play(animator2).with(animator3);
         * 或者
          set.play(animator2).with(animator3).after(animator1);
         */
    }

    //组合动画并行1
    private void doGroupByCode2(){
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("translationX",0f,200f);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("rotation",0f,360f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView,holder1,holder2);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            // 想要哪个或者哪几个方法都行
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
    }
    //组合动画并行2
    private void doGroupByCode3(){
        imageView.animate().translationX(200f).rotation(360f).alpha(0.0f).start();
    }
    // 使用 xml 进行组合动画
    private void doGroupByXML(){
        Animator animator = AnimatorInflater.loadAnimator(this,R.animator.animator_group);
        // 设置动画作用对象
        animator.setTarget(imageView);
        animator.start();
    }


}

package com.example.a01378359.testapp.anim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a01378359.testapp.R;

public class AnimMainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button toTween;
    private Button toFrame;
    private Button toValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_main);
        initView();
    }

    private void initView(){
        toTween = findViewById(R.id.btn_anim_tween);
        toTween.setOnClickListener(this);
        toFrame = findViewById(R.id.btn_anim_frame);
        toFrame.setOnClickListener(this);
        toValue = findViewById(R.id.btn_anim_value);
        toValue.setOnClickListener(this);
    }

    private void start(Class context){
        Intent intent = new Intent(AnimMainActivity.this,context);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_anim_tween:
                start(TweenAnimActivity.class);
                break;
            case R.id.btn_anim_frame:
                start(FrameAnimActivity.class);
            case R.id.btn_anim_value:
                start(ObjectAnimatorActivity.class);
            default:
                break;
        }
    }
}

package com.example.a01378359.testapp.materialdesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a01378359.testapp.R;

public class MaterialDesignAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button toFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design_animation);
        initView();
    }

    private void initView(){
        toFeedback = findViewById(R.id.btn_material_touch_feedback);
        toFeedback.setOnClickListener(this);
    }

    private void start(Class context){
        Intent intent = new Intent(MaterialDesignAnimationActivity.this,context);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_material_touch_feedback:
                start(TouchFeedbackActivity.class);
                break;
            default:
                break;
        }
    }
}

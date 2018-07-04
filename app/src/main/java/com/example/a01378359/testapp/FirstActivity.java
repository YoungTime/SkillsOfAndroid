package com.example.a01378359.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a01378359.testapp.anim.AnimMainActivity;
import com.example.a01378359.testapp.fragment.FragmentActivity;
import com.example.a01378359.testapp.lock.LockTestActivity;
import com.example.a01378359.testapp.main.MainActivity;
import com.example.a01378359.testapp.materialdesign.MaterialDesignAnimationActivity;
import com.example.a01378359.testapp.notification.NotificationActivity;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener{
    private Button toMain;
    private Button toFragment;
    private Button toAnim;
    private Button toLock;
    private Button toMaterialDesign;
    private Button toNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initView();
    }

    private void initView(){
        toMain = findViewById(R.id.btn_first_to_main);
        toMain.setOnClickListener(this);
        toFragment = findViewById(R.id.btn_first_to_fragment);
        toFragment.setOnClickListener(this);
        toAnim = findViewById(R.id.btn_first_to_anim);
        toAnim.setOnClickListener(this);
        toLock = findViewById(R.id.btn_first_to_lock);
        toLock.setOnClickListener(this);
        toMaterialDesign = findViewById(R.id.btn_first_to_material_design);
        toMaterialDesign.setOnClickListener(this);
        toNotification=findViewById(R.id.btn_first_to_notification);
        toNotification.setOnClickListener(this);
    }

    private void start(Class context){
        Intent intent = new Intent(FirstActivity.this,context);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_first_to_main:
                start(MainActivity.class);
                break;
            case R.id.btn_first_to_fragment:
                start(FragmentActivity.class);
                break;
            case R.id.btn_first_to_anim:
                start(AnimMainActivity.class);
                break;
            case R.id.btn_first_to_lock:
                start(LockTestActivity.class);
                break;
            case R.id.btn_first_to_material_design:
                start(MaterialDesignAnimationActivity.class);
                break;
            case R.id.btn_first_to_notification:
                start(NotificationActivity.class);
                break;
            default:
                break;

        }
    }
}

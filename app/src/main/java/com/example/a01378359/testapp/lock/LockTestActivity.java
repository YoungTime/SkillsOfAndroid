package com.example.a01378359.testapp.lock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.a01378359.testapp.R;

public class LockTestActivity extends AppCompatActivity {

    private HandleLock handleLock;
    private String password;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_test);
        handleLock = findViewById(R.id.handlelock_test);
        handleLock.setOnDrawFinishListener(new HandleLock.OnDrawFinishListener() {
            @Override
            public boolean drawFinish(String route) {
                // 第一次滑动，则保存密码
                if (count == 0){
                    password = route;
                    count++;
                    Toast.makeText(LockTestActivity.this,"已保存密码",Toast.LENGTH_SHORT).show();
                    return true;
                }else {
                    // 与保存密码比较，返回结果，并且做出相应事件
                    if (password.equals(route)){
                        Toast.makeText(LockTestActivity.this,"密码正确",Toast.LENGTH_SHORT).show();
                        return true;
                    }else {
                        Toast.makeText(LockTestActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            }
        });


    }
}

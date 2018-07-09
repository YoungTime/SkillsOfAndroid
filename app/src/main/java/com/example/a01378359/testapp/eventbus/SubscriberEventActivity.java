package com.example.a01378359.testapp.eventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a01378359.testapp.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SubscriberEventActivity extends AppCompatActivity {
    private Button postEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriber_event);

        postEvent=findViewById(R.id.btn_post_event);

        EventBus.getDefault().register(this);
        postEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new Event("响应事件"));
            }
        });

    }
    // 第二个 Activity 页面响应事件 B，设置 sticky 为 true
    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void onEvent(EventB eventB){
        Toast.makeText(SubscriberEventActivity.this,eventB.getData(),Toast.LENGTH_SHORT).show();
        // 我们在响应该事件后就移除该黏性事件
        EventBus.getDefault().removeStickyEvent(EventB.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

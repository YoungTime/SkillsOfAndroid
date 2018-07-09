package com.example.a01378359.testapp.eventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a01378359.testapp.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusBuilder;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executors;

public class EventBusMainActivity extends AppCompatActivity {

    private Button toSubscriber;
    private Button postEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_main);

        toSubscriber = findViewById(R.id.btn_event_to_subscriber);
        toSubscriber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventBusMainActivity.this,SubscriberEventActivity.class);
                startActivity(intent);
            }
        });

        // 第一种，在 onCreate() 方法中注册
        EventBus.getDefault().register(this);
        // 第二种
        EventBusBuilder busBuilder = EventBus.builder()
                // 设置线程池，默认为动态多线程的线程池
                .executorService(Executors.newSingleThreadExecutor())
                // 事件是否继承，默认 true
                .eventInheritance(true);
        EventBus eventBus = busBuilder.build();
        eventBus.register(this);

        // 第一个 Activity 页面发送事件 B
        postEvent=findViewById(R.id.btn_post_event_b);
        postEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 通过 postSticky() 来发送粘性事件
                EventBus.getDefault().postSticky(new EventB("响应事件B"));
            }
        });
    }


    // 通过注解方式响应事件
    @Subscribe(threadMode = ThreadMode.POSTING,priority = 1,sticky = false)
    public void onEvent(Event event ) {
        // 响应事件
        Toast.makeText(EventBusMainActivity.this,event.getData(),Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

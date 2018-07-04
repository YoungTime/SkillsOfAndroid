package com.example.a01378359.testapp.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.a01378359.testapp.R;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button simpleNoti;
    private Button normalNoti;
    private Button progressNoti;
    private Button btnNoti;
    private Button btnCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initView();
        parseIntent();
    }

    private void initView() {
        simpleNoti = findViewById(R.id.btn_notification_simple);
        simpleNoti.setOnClickListener(this);
        normalNoti = findViewById(R.id.btn_notification_normal);
        normalNoti.setOnClickListener(this);
        progressNoti=findViewById(R.id.btn_notification_progress);
        progressNoti.setOnClickListener(this);
        btnNoti = findViewById(R.id.btn_notification_btn);
        btnNoti.setOnClickListener(this);
        btnCustom = findViewById(R.id.btn_notification_custom);
        btnCustom.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_notification_simple:
                setSimpleNoti();
                break;
            case R.id.btn_notification_normal:
                setNormalNoti();
                break;
            case R.id.btn_notification_progress:
                setProgress();
                break;
            case R.id.btn_notification_btn:
                setButton();
                break;
            case R.id.btn_notification_custom:
                setCustomNoti();
                break;
            default:
                break;
        }
    }

    // 简单通知
    private void setSimpleNoti() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        // 设置标题
        builder.setContentTitle("简单通知");
        // 设置内容
        builder.setContentText("通知内容");
        // 设置小图标，没有大图标时，小图标会放在大图标的位置
        builder.setSmallIcon(R.mipmap.ic_launcher);
        // 简单通知栏设置 Intent
        builder.setContentIntent(setPendingIntent("简单通知"));
        // 通知栏点击后自动消失
        builder.setAutoCancel(true);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        // tag 和 id 是为了用来区分 notification
        managerCompat.notify("NOTIFICATION", 0101, builder.build());
    }

    // 普通通知
    private void setNormalNoti() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        // 设置标题
        builder.setContentTitle("普通通知");
        // 设置内容
        builder.setContentText("通知内容");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        // 设置大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        // 设置通知灯光（LIGHTS）、铃声（SOUND）、震动（VIBRATE）、（ALL 表示都设置）
        builder.setDefaults(Notification.DEFAULT_ALL);
        // 灯光三个参数，颜色（argb）、亮时间（毫秒）、暗时间（毫秒）,灯光与设备有关
        builder.setLights(Color.RED, 200, 200);
        // 铃声,传入铃声的 Uri（可以本地或网上）我这没有铃声就不传了
        builder.setSound(Uri.parse(""));
        // 震动，传入一个 long 型数组，表示 停、震、停、震 ... （毫秒）
        builder.setVibrate(new long[]{0, 200, 200, 200, 200, 200});

        // 普通通知栏设置 Intent
        builder.setContentIntent(setPendingIntent("普通通知"));
        // 通知栏点击后自动消失
        builder.setAutoCancel(true);
        // 后台运行，无法右滑删除
        builder.setOngoing(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify("NOTIFICATION", 0102, builder.build());
        // 强制消失，传入 tag 和 id
        // managerCompat.cancel("notification",0101);
    }
    // 进度条通知栏
    private void setProgress(){
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("源码下载");
        builder.setContentText("等待下载中...");
        builder.setSmallIcon(R.mipmap.ic_launcher);
//        builder.setOngoing(true);
        final NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify("notification",0103,builder.build());
        // 使用子线程，模拟下载
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    // 设置进度条，三个参数表示：进度条最大值、当前值、确定在干啥：false
                    builder.setProgress(100,i,false);
                    builder.setContentText("文件已下载："+i+"%");
                    managerCompat.notify("notification",0103,builder.build());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                builder.setContentText("文件下载完成");
                managerCompat.notify("notification",0103,builder.build());
            }
        }).start();
    }

    // 设置按钮
    private void setButton(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("按钮测试");
        builder.setContentText("点一下按钮");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        // 悬浮通知栏，HIGH 或者 MAX 都可以
        builder.setDefaults(NotificationCompat.PRIORITY_HIGH);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        // 添加按钮，这三个参数相信大家都能明白
        builder.addAction(R.mipmap.ic_launcher,"按钮一",setPendingIntent("执行按钮1"));
        builder.addAction(R.mipmap.ic_launcher,"按钮二",setPendingIntent("执行按钮2"));
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify("notification",0104,builder.build());
    }

    // 自定义通知栏
    private void setCustomNoti(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        // 实例化 RemoteViews，传入包名和布局 id
        RemoteViews views = new RemoteViews(getPackageName(),R.layout.notification_view);
        // 改变布局的 Text 的属性，传入 id 和 属性，也可以设置颜色，字体大小等
        views.setTextViewText(R.id.tv_custom_notification,"自定义通知栏");
        // 改变布局的图片，有不同的方法，可以传入 id、bitmap 等
        views.setImageViewResource(R.id.iv_custom_notification,R.mipmap.ic_launcher);
        // 设置点击事件，传入 id 和 PendingIntent
        views.setOnClickPendingIntent(R.id.btn_custom_notification_clean,setPendingIntent("自定义按钮"));
        // 设置 View
        builder.setContent(views);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify("notification",0105,builder.build());
    }

    // 设置 pendingIntent
    private PendingIntent setPendingIntent(String msg) {
        // 普通的 Intent，可以传值
        Intent intent = new Intent(NotificationActivity.this, NotificationActivity.class);
        intent.putExtra("notification", msg);
        intent.setAction("notification:" + msg);
        // 获取 pendingIntent，传入四个参数，这些参数大家都能看懂，第四个是一些 Flag，表示你这个通知栏会怎么变化，一般使用这个
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }


    // 解析 Intent
    private void parseIntent() {
        String msg = getIntent().getStringExtra("notification");
        if (msg != null) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}

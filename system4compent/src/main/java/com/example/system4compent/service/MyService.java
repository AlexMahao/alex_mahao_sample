package com.example.system4compent.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.system4compent.R;

/**
 *
 * Created by MH on 2016/6/20.
 */
public class MyService extends Service {

    private static final  String TAG = "INFO";

    private MyBinder myBinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate");

        // 创建一个延时意图，即点击通知跳转到该应用
        Intent notificationIntent = new Intent(this, ServiceMainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        // 通知
        Notification notification1 = new Notification.Builder(this)
                .setContentTitle("这是通知的标题")
                .setContentText("这是通知的内容")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent).build();

        // 启动通知
        NotificationManager nService = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nService.notify(1,notification1);

        // 使Service 不被杀死。
        startForeground(1,notification1);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // 绑定之后会回调此方法
        return myBinder;
    }

    /**
     * 绑定之后回传给Activity 的对象实例
     */
    public class MyBinder extends Binder{
        public void down(){
            Log.i(TAG,"down.....");
        }
    }
}

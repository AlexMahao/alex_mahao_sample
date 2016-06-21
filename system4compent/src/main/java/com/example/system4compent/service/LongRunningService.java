package com.example.system4compent.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by MH on 2016/6/21.
 */
public class LongRunningService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(){
            @Override
            public void run() {
                // 网络请求等轮询更新操作
                Log.i("info","更新数据");
            }
        }.start();


        //启动一个时钟
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //设置提醒的时间，就类似于闹钟，设置一个叫醒的实现
        int anHour = 2 * 1000;
        // 该时间获取的是系统开机到现在的时间，区分 System.currentTimeMillis()
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        //延时意图
        Intent i = new Intent(this,LongRunningReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);

        //设置闹钟
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);

        return super.onStartCommand(intent, flags, startId);
    }
}

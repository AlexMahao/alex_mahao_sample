package com.example.system4compent.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by MH on 2016/6/21.
 */
public class MyIntentService extends IntentService {


    // 必须实现父类的构造方法， 同时传入的参数代表线程的名字
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i("info","onCreate");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("info","onStartCommand");
        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    protected void onHandleIntent(Intent intent) {

        // 根据不同的参数启动不同的服务，执行不同的任务
        String params = intent.getStringExtra("params");

        if(params.equals("1"))
            Log.i("info","run service1");

        if(params.equals("2"))
            Log.i("info","run service2");

        if(params.equals("3"))
            Log.i("info","run service13");


        //让服务休眠2秒
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){e.printStackTrace();}
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i("info","onDestory");
    }
}

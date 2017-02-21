package com.example.system4compent.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.system4compent.R;

/**
 *
 * Service 必须要在即没有和任何Activity 有关联又处理停止状态时候才会被销毁
 *
 *    service 和 Thread 线程之间的比较
 *      - service 和Thread 没有任何联系。
 *      - Thread 开启一个子线程，执行耗时操作，不会阻塞主线程
 *      - service 运行在主线程,如果运行耗时操作也会导致程序阻塞,ANR;
 *
 *    service 和 Activity 的比较。
 *
 *    - 两者都是运行在主线程
 *    - 如果在Activity 中启动线程， 则线程不可控，且不可共享
 *    - Service 可以与多个Activity绑定，获取到Service 对象，便于操作线程
 *
 *
 *  service使用总结：
 *
 *
 * Created by MH on 2016/6/20.
 */
public class ServiceMainActivity extends AppCompatActivity {

    private MyService.MyBinder myBinder;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 建立联系时回调
            myBinder = (MyService.MyBinder) service;
            myBinder.down();
            Log.i("INFO","onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 绑定的服务被异常销毁时
            Log.i("INFO","onServiceDisconnected");
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_service);

    }


    public void start(View view) {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }


    public void stop(View view) {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }


    public void bind(View view) {
        // 绑定服务

        Intent intent = new Intent(this, MyService.class);

        bindService(intent,conn,BIND_AUTO_CREATE);
    }


    public void unbind(View view){
        //解绑服务

        unbindService(conn);
    }



    public void intentService(View view){

        // 定义三个不同的后台任务
        Intent intent1 = new Intent(this,MyIntentService.class);
        intent1.putExtra("params","1");

        Intent intent2 = new Intent(this,MyIntentService.class);
        intent2.putExtra("params","2");

        Intent intent3 = new Intent(this,MyIntentService.class);
        intent3.putExtra("params","3");


        // 启动服务
        startService(intent1);
        startService(intent2);
        startService(intent3);
    }

    public void longRunning(View view){
        Intent intent = new Intent(this,LongRunningService.class);

        startService(intent);
    }


    public void aidl_binder(View view){

    }
}

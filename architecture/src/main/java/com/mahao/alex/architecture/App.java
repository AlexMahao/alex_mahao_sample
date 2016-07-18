package com.mahao.alex.architecture;

import android.app.Application;

import com.mahao.alex.architecture.dagger2.AppComponent;
import com.mahao.alex.architecture.dagger2.AppModule;
import com.mahao.alex.architecture.dagger2.DaggerAppComponent;

/**
 * Created by MH on 2016/7/18.
 */
public class App extends Application {

    // 为什么可以使用静态
    public static AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        // 实例化
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

    }
}

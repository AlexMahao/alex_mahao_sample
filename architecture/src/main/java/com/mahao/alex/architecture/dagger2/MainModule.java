package com.mahao.alex.architecture.dagger2;

import android.content.Context;
import android.util.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MH on 2016/7/14.
 */
@Module   //提供依赖对象的实例
public class MainModule {

    private Context mContext;

    public MainModule(Context context){
        mContext = context;
    }


    @Provides
    Context providesContext(){
        // 提供上下文对象
        return mContext;
    }

    @Provides // 关键字，标明该方法提供依赖对象
    @Singleton
    Person providerPerson(Context context){

        return new Person(context);
    }

}

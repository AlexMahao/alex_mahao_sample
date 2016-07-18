package com.mahao.alex.architecture.dagger2;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MH on 2016/7/18.
 */

@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context){
        mContext = context;
    }

    @Provides
    @PerApp  // 添加该标记表明该方法只产生一个实例
    Context providesContext(){
        // 提供上下文对象
        return mContext;
    }

}

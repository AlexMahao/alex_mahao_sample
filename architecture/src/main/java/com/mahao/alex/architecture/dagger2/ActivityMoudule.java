package com.mahao.alex.architecture.dagger2;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 *
 * 下层Module类
 * Created by MH on 2016/7/18.
 */
@Module
public class ActivityMoudule {

    @PersonForContext
    @Provides
    @PerActivity  // 添加标记，生命其所构造的对象单例
    Person providePersonContext(Context context){
        //　此方法需要Context 对象
        return new Person(context);
    }


    @PersonForName  // 通过name创建Person 对象
    @Provides

    Person providePersonName(){
        //　此方法需要Context 对象
        return new Person("123");
    }
}

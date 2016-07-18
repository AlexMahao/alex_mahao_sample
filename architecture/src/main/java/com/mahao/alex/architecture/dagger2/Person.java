package com.mahao.alex.architecture.dagger2;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by MH on 2016/7/14.
 */
public class Person {

    private Context mContext;

    public Person(Context context){
        mContext = context;
        Log.i("dagger","create");
    }

    public Person(String name){
        Log.i("dagger",name);
    }
}

package com.mahao.alex.architecture.di;

/**
 * Created by MH on 2016/7/12.
 */
public class Classes implements BoyInjection {
    //....

    private Boy boy;

    @Override
    public void inject(Boy boy) {
        this.boy = boy;
    }

    //....

}

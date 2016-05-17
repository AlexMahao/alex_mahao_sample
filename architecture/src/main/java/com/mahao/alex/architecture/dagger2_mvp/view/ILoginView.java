package com.mahao.alex.architecture.dagger2_mvp.view;

/**
 * Created by Alex_MaHao on 2016/5/17.
 */
public interface ILoginView {

    String getName();

    String getPassword();

    void clearName();

    void clearPassword();

    void showMessage(String msg);

    void intent2Success();

}

package com.mahao.alex.architecture.dagger2_mvp.view;

import android.widget.TextView;
import android.widget.Toast;

import com.mahao.alex.architecture.R;
import com.mahao.alex.architecture.dagger2_mvp.BaseActivity;
import com.mahao.alex.architecture.dagger2_mvp.presenter.ILoginPresenter;
import com.mahao.alex.architecture.dagger2_mvp.presenter.ILoginPresenterImp;

/**
 * Created by Alex_MaHao on 2016/5/17.
 */
public class LoginActivity extends BaseActivity implements ILoginView {


    private TextView mNameTv;
    private TextView mPasswordTv;

    private ILoginPresenter presenter;

    @Override
    protected void afterViewCreate() {
        mNameTv = ((TextView) findViewById(R.id.name));

        mPasswordTv = ((TextView) findViewById(R.id.password));

        presenter = new ILoginPresenterImp(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dagger2_mvp_login;
    }

    @Override
    public String getName() {
        return mNameTv.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return mPasswordTv.getText().toString().trim();
    }

    @Override
    public void clearName() {
        mNameTv.setText("");
    }

    @Override
    public void clearPassword() {
        mPasswordTv.setText("");
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void intent2Success() {
        //跳转成功页面，暂不做操作

    }
}

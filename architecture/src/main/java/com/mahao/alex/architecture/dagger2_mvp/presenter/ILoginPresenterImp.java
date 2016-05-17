package com.mahao.alex.architecture.dagger2_mvp.presenter;

import com.mahao.alex.architecture.dagger2_mvp.UserBean;
import com.mahao.alex.architecture.dagger2_mvp.model.IUserModel;
import com.mahao.alex.architecture.dagger2_mvp.model.IUserModelImp;
import com.mahao.alex.architecture.dagger2_mvp.presenter.ILoginPresenter;
import com.mahao.alex.architecture.dagger2_mvp.view.ILoginView;

/**
 * Created by Alex_MaHao on 2016/5/17.
 */
public class ILoginPresenterImp implements ILoginPresenter {

    private IUserModel model;

    private ILoginView view;

    public ILoginPresenterImp(ILoginView view) {
        this.view = view;
        model = new IUserModelImp();
    }

    @Override
    public void login() {
        String name = view.getName();
        String password = view.getPassword();

        String result = model.netLogin(new UserBean(name, password));

        if(result.equals("ok")){
            view.showMessage("成功");
            view.intent2Success();
        }else if(result.equals("error")){
            view.showMessage("失败");
        }
    }

    @Override
    public void clear() {
        view.clearName();

        view.clearPassword();
    }
}

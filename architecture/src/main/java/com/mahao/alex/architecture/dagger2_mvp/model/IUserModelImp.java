package com.mahao.alex.architecture.dagger2_mvp.model;

import com.mahao.alex.architecture.dagger2_mvp.UserBean;
import com.mahao.alex.architecture.dagger2_mvp.model.IUserModel;

/**
 * Created by Alex_MaHao on 2016/5/17.
 */
public class IUserModelImp implements IUserModel {


    @Override
    public String netLogin(UserBean userBean) {

        if("admin".equals(userBean.getName())&&"123456".equals(userBean.getPasswrod())){
            return "ok";
        }

        return "error";
    }
}

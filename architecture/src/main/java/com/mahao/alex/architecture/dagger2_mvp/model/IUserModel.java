package com.mahao.alex.architecture.dagger2_mvp.model;

import com.mahao.alex.architecture.dagger2_mvp.UserBean;

/**
 * Created by Alex_MaHao on 2016/5/17.
 */
public interface IUserModel {

    /**
     * 网络请求
     * @param userBean
     * @return  请求结果
     */
    String netLogin(UserBean userBean);


}

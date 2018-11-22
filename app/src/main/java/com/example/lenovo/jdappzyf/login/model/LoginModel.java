package com.example.lenovo.jdappzyf.login.model;

import com.example.lenovo.jdappzyf.login.bean.LoginBean;
import com.example.lenovo.jdappzyf.utils.Api;
import com.example.lenovo.jdappzyf.utils.RetrofitManager;
import com.facebook.drawee.components.RetryManager;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/11/7.
 */

public class LoginModel {

    public Observable<LoginBean> login(String mobile,String password){
        Api api = RetrofitManager.getInstance().create(Api.class);
        Observable<LoginBean> loginBeanObservable = api.login1(mobile, password);
        return loginBeanObservable;
    }

}

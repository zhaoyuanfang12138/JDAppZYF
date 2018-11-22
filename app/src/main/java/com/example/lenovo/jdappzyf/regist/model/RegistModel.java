package com.example.lenovo.jdappzyf.regist.model;

import com.example.lenovo.jdappzyf.login.bean.LoginBean;
import com.example.lenovo.jdappzyf.regist.bean.RegistBean;
import com.example.lenovo.jdappzyf.regist.presenter.RegistPresenter;
import com.example.lenovo.jdappzyf.utils.Api;
import com.example.lenovo.jdappzyf.utils.RetrofitManager;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/11/7.
 */

public class RegistModel {

    public Observable<RegistBean> regist(String mobile ,String password){
        Api api = RetrofitManager.getInstance().create(Api.class);
        Observable<RegistBean> registBeanObservable = api.regist1(mobile, password);
        return registBeanObservable;
    }


}

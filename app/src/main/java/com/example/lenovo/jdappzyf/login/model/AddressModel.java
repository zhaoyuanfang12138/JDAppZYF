package com.example.lenovo.jdappzyf.login.model;

import com.example.lenovo.jdappzyf.login.bean.AddreseeBean;
import com.example.lenovo.jdappzyf.utils.Api;
import com.example.lenovo.jdappzyf.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/11/19.
 */

public class AddressModel {

    public Observable<AddreseeBean> getAddr(int uid){
        Api api = RetrofitManager.getInstance().create(Api.class);
        Observable<AddreseeBean> addreseeBeanObservable = (Observable<AddreseeBean>) api.getAddr(uid);
        return addreseeBeanObservable;
    }

}

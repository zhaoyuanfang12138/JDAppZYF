package com.example.lenovo.jdappzyf.login.model;

import com.example.lenovo.jdappzyf.login.bean.AddAddressBean;
import com.example.lenovo.jdappzyf.login.bean.AddreseeBean;
import com.example.lenovo.jdappzyf.utils.Api;
import com.example.lenovo.jdappzyf.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/11/20.
 */

public class AddAddressModel {

    public Observable<AddAddressBean> addAddress(int uid,String addr,String mobile,String name){
        Api api = RetrofitManager.getInstance().create(Api.class);
        Observable<AddAddressBean> AddAddressBeanObservable =  api.addAddress(uid,addr,mobile,name);
        return AddAddressBeanObservable;
    }

}

package com.example.lenovo.jdappzyf.first.model;

import com.example.lenovo.jdappzyf.first.bean.AddCart;
import com.example.lenovo.jdappzyf.utils.Api;
import com.example.lenovo.jdappzyf.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/11/15.
 */

public class WebViewModel {

    public Observable<AddCart> getAddCart(int uid, int pid){
        Api api = RetrofitManager.getInstance().create(Api.class);

        Observable<AddCart> addCartObservable = api.getAddCart(uid,pid);

        return addCartObservable;
    }



}

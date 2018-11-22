package com.example.lenovo.jdappzyf.cart.model;

import com.example.lenovo.jdappzyf.cart.bean.Cart;
import com.example.lenovo.jdappzyf.utils.Api;
import com.example.lenovo.jdappzyf.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/11/13.
 */

public class CartModel {

    public Observable<Cart> getcart(int uid){
        Api api = RetrofitManager.getInstance().create(Api.class);
        Observable<Cart> cartbeanObservable = api.cart1(uid);

        return cartbeanObservable;
    }

}

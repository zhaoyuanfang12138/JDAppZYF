package com.example.lenovo.jdappzyf.first.presenter;


import com.example.lenovo.jdappzyf.first.bean.AddCart;
import com.example.lenovo.jdappzyf.first.model.WebViewModel;
import com.example.lenovo.jdappzyf.first.view.IWebView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/11/15.
 */

public class WebViewPresenter {


    private WebViewModel model;

    private IWebView iv;

    public void attach(IWebView iv){
        this.iv = iv;
        model = new WebViewModel();
    }


    public void getAddCart(int uid,int pid){

        model.getAddCart(uid,pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddCart>() {
                    @Override
                    public void accept(AddCart addCart) throws Exception {
                        if (addCart != null && "0".equals(addCart.getCode())) {
                            if (iv != null) {
                                iv.getAddCart(addCart);
                            }
                            return;
                        }
                        if (iv != null) {
                            iv.faild(new Throwable("服务器为响应"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iv != null) {
                            iv.faild(throwable);
                        }
                    }
                });

    }

}

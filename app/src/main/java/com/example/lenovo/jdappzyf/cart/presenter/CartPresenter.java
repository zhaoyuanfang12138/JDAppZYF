package com.example.lenovo.jdappzyf.cart.presenter;


import com.example.lenovo.jdappzyf.cart.bean.Cart;
import com.example.lenovo.jdappzyf.cart.model.CartModel;
import com.example.lenovo.jdappzyf.cart.view.CartIView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/11/13.
 */

public class CartPresenter {

    private CartIView iv;
    private CartModel model;

    public void attach(CartIView iv){
        this.iv = iv;
        model = new CartModel();
    }


    public void getCart(int uid){

        model.getcart(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Cart>() {
                    @Override
                    public void accept(Cart cart) throws Exception {
                        if (cart != null & "0".equals(cart.getCode())) {
                            if (iv != null) {
                                iv.getCart(cart.getData());
                            }
                            return;
                        }
                        if (iv != null) {
                            iv.faild(new Throwable("" + cart.getMsg()));
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


    public void detach() {
        this.iv = null;
    }


}

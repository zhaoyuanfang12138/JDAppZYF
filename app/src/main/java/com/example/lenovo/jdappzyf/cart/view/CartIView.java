package com.example.lenovo.jdappzyf.cart.view;

import com.example.lenovo.jdappzyf.cart.bean.Cart;

import java.util.List;

/**
 * Created by lenovo on 2018/11/13.
 */

public interface CartIView {


    void getCart( List<Cart.DataBean> dataBeans);

    void faild(Throwable t);
}

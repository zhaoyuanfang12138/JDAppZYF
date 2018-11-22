package com.example.lenovo.jdappzyf.first.view;

import com.example.lenovo.jdappzyf.first.bean.AddCart;

/**
 * Created by lenovo on 2018/11/15.
 */

public interface IWebView {

    void getAddCart(AddCart addCart);

    void faild(Throwable t);

}

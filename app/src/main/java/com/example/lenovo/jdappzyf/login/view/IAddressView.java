package com.example.lenovo.jdappzyf.login.view;

import com.example.lenovo.jdappzyf.login.bean.AddreseeBean;


/**
 * Created by lenovo on 2018/11/19.
 */

public interface IAddressView {

    void getAddresee(AddreseeBean addreseeBean);

    void faild(Throwable t);
}

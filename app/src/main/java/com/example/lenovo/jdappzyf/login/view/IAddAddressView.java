package com.example.lenovo.jdappzyf.login.view;

import com.example.lenovo.jdappzyf.login.bean.AddAddressBean;

/**
 * Created by lenovo on 2018/11/20.
 */

public interface IAddAddressView {

    void success(AddAddressBean addAddressBean);

    void failed(Throwable t);

}

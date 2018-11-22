package com.example.lenovo.jdappzyf.regist.view;


import com.example.lenovo.jdappzyf.regist.bean.RegistBean;

/**
 * Created by lenovo on 2018/11/7.
 */

public interface RegistView{

    void success(RegistBean registBean);
    void failed(Exception e);

}

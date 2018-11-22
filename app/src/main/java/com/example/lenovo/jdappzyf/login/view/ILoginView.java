package com.example.lenovo.jdappzyf.login.view;

import android.content.Context;

import com.example.lenovo.jdappzyf.base.IView;
import com.example.lenovo.jdappzyf.login.bean.LoginBean;

/**
 * Created by lenovo on 2018/11/7.
 */

public interface ILoginView  {

    void success(LoginBean loginBean);
    void failed(Throwable t);

    void CheckDate(boolean isChecked);

    String getMobile();

    String getPassword();


    void fish();

    void goToRegist();


    Context context();
}

package com.example.lenovo.jdappzyf.login.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.lenovo.jdappzyf.login.bean.LoginBean;
import com.example.lenovo.jdappzyf.login.model.LoginModel;
import com.example.lenovo.jdappzyf.login.view.ILoginView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/11/7.
 */

public class LoginPresenter {

    private LoginModel model;
    private ILoginView iv;

    public void attach(ILoginView iv){
        this.iv = iv;
        model = new LoginModel();
    }


    public void detach(){
        if (iv != null){
            iv = null;
        }
    }


    public void CheckDate() {
        if (TextUtils.isEmpty(iv.getMobile()) || TextUtils.isEmpty(iv.getPassword())) {
            iv.CheckDate(false);
        } else {
            iv.CheckDate(true);
        }
    }

    public void goToRegist() {
        iv.goToRegist();
    }

   public void login(){

       final String mobile = iv.getMobile();
       final String password = iv.getPassword();;

          model.login(mobile,password)
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Consumer<LoginBean>() {
                      @Override
                      public void accept(LoginBean loginBean) throws Exception {
                          if (loginBean != null && "0".equals(loginBean.getCode())) {
                              if (iv != null) {
                                  SharedPreferences sp = iv.context().getSharedPreferences("Login", Context.MODE_PRIVATE);
                                  iv.success(loginBean);
                                  int uid = loginBean.getData().getUid();
                                  sp.edit().putString("mobile",mobile)
                                          .putString("password",password)
                                          .putBoolean("isFirst",true)
                                          .putInt("uid",uid)
                                          .commit();
                                  iv.fish();
                                  return;
                              }
                          }
                          if (iv != null) {
                              iv.failed(new Exception("服务器未响应"));
                          }
                      }
                  }, new Consumer<Throwable>() {
                      @Override
                      public void accept(Throwable throwable) throws Exception {
                          if (iv != null){
                              iv.failed(new Exception("网络异常"));
                          }
                      }
                  });

   }



}

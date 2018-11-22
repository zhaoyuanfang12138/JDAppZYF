package com.example.lenovo.jdappzyf.regist.presenter;


import com.example.lenovo.jdappzyf.regist.bean.RegistBean;
import com.example.lenovo.jdappzyf.regist.model.RegistModel;
import com.example.lenovo.jdappzyf.regist.view.RegistView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/11/7.
 */

public class RegistPresenter {

    private RegistModel model;
    private RegistView iv;

    public void attach(RegistView iv){
        this.iv = iv;
        model = new RegistModel();
    }


    public void regist(String mobile,String password){
             model.regist(mobile, password)
                     .subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe(new Consumer<RegistBean>() {
                         @Override
                         public void accept(RegistBean registBean) throws Exception {
                             if (registBean != null && "1".equals(registBean.getCode())) {
                                 if (iv != null) {
                                     iv.success(registBean);
                                 }
                                 return;
                             }
                             if (iv != null) {
                                 iv.failed(new Exception("服务器未响应"));
                             }
                         }
                     }, new Consumer<Throwable>() {
                         @Override
                         public void accept(Throwable throwable) throws Exception {
                             if (iv != null){
                                 iv.failed(new Exception("网络请求失败"));
                             }
                         }
                     });
    }








    public void detach(){
        if (iv != null){
            iv = null;
        }
    }


}

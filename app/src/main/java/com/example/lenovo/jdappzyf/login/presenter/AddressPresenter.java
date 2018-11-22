package com.example.lenovo.jdappzyf.login.presenter;

import com.example.lenovo.jdappzyf.login.bean.AddreseeBean;
import com.example.lenovo.jdappzyf.login.model.AddressModel;
import com.example.lenovo.jdappzyf.login.view.IAddressView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/11/19.
 */

public class AddressPresenter {

 private AddressModel model;

 private IAddressView iv;

 public void attach(IAddressView iv){
     this.iv = iv;
     model = new AddressModel();
 }

  public void getAddr(int uid){

     model.getAddr(uid)
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(new Consumer<AddreseeBean>() {
                 @Override
                 public void accept(AddreseeBean addreseeBean) throws Exception {
                     if (addreseeBean != null & "0".equals(addreseeBean.getCode())) {
                         if (iv != null) {
                             iv.getAddresee(addreseeBean);
                             return;
                         }

                     }
                     if (iv != null) {
                         iv.faild(new Throwable("服务器未响应"));
                     }
                 }
             });

  }

}

package com.example.lenovo.jdappzyf.login.presenter;

import com.example.lenovo.jdappzyf.login.bean.AddAddressBean;
import com.example.lenovo.jdappzyf.login.model.AddAddressModel;
import com.example.lenovo.jdappzyf.login.view.IAddAddressView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/11/20.
 */

public class AddAddressPresenter {

    private AddAddressModel model;

    private IAddAddressView iv;

    public void attach(IAddAddressView iv){
        this.iv =iv;
        model = new AddAddressModel();
    }

    public void detach(){
        if (iv != null){
            iv=null;
        }
    }


    public void addAddress(int uid,String addr,String mobile,String name){

        model.addAddress(uid,addr,mobile,name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddAddressBean>() {
                    @Override
                    public void accept(AddAddressBean addAddressBean) throws Exception {
                        if (addAddressBean != null & "0".equals(addAddressBean.getCode())) {
                            if (iv != null) {
                                iv.success(addAddressBean);
                                return;
                            }
                        }
                        if (iv != null) {
                            iv.failed(new Exception("服务器异常"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iv != null) {
                            iv.failed(new Exception("网络异常"));
                        }
                    }
                });

    }



}

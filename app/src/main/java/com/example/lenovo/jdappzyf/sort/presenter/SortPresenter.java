package com.example.lenovo.jdappzyf.sort.presenter;

import com.example.lenovo.jdappzyf.sort.bean.CataLeft;
import com.example.lenovo.jdappzyf.sort.bean.CataRight;
import com.example.lenovo.jdappzyf.sort.bean.DetailBean;
import com.example.lenovo.jdappzyf.sort.model.SortModel;
import com.example.lenovo.jdappzyf.sort.view.IView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/11/9.
 */

public class SortPresenter {

    private SortModel model;

    private IView iv;

    public void attach(IView iv){
        this.iv = iv;
        model = new SortModel();
    }


    public void getCataLeft(){

        model.cataleft()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CataLeft>() {
                    @Override
                    public void accept(CataLeft cataLeft) throws Exception {
                        if (cataLeft != null & "0".equals(cataLeft.getCode())) {
                            if (iv != null)
                                iv.getCataLeft(cataLeft);
                            return;
                        }
                        if (iv != null)
                            iv.Failed(new Throwable("网络未响应"));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if ( iv != null)
                            iv.Failed(new Throwable("网络异常"));
                    }
                });

    }


    public void getCataRight(int cid){

        model.cataright(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CataRight>() {
                    @Override
                    public void accept(CataRight cataRight) throws Exception {
                        if (cataRight != null & "0".equals(cataRight.getCode())) {
                            if (iv != null)
                                iv.getCataRight(cataRight);
                            return;
                        }
                        if (iv != null)
                            iv.Failed(new Throwable("网络未响应"));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if ( iv != null)
                            iv.Failed(new Throwable("网络异常"));
                    }
                });

    }



    public void getDetail(int id){

        model.detail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DetailBean>() {
                    @Override
                    public void accept(DetailBean detailBean) throws Exception {
                        if (detailBean != null & "0".equals(detailBean.getCode())) {
                            if (iv != null)
                                iv.getDetail(detailBean.getData());
                            return;
                        }
                        if (iv != null)
                            iv.Failed(new Throwable("网络未响应"));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if ( iv != null)
                            iv.Failed(new Throwable("网络异常"));
                    }
                });

    }



}

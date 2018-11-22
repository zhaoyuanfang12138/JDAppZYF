package com.example.lenovo.jdappzyf.sort.model;

import com.example.lenovo.jdappzyf.sort.bean.CataLeft;
import com.example.lenovo.jdappzyf.sort.bean.CataRight;
import com.example.lenovo.jdappzyf.sort.bean.DetailBean;
import com.example.lenovo.jdappzyf.utils.Api;
import com.example.lenovo.jdappzyf.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/11/9.
 */

public class SortModel {


    public Observable<CataLeft> cataleft(){
        Api api = RetrofitManager.getInstance().create(Api.class);
        Observable<CataLeft> cataLeftObservable = api.cataleft1();

        return cataLeftObservable;
    }

    public Observable<CataRight> cataright(int cid){
        Api api = RetrofitManager.getInstance().create(Api.class);
        Observable<CataRight> cataRightObservable = api.cataright1(cid);

        return cataRightObservable;
    }

    public Observable<DetailBean> detail(int pscid){
        Api api = RetrofitManager.getInstance().create(Api.class);
        Observable<DetailBean> detailObservable = api.detail1(pscid);

        return detailObservable;
    }

}

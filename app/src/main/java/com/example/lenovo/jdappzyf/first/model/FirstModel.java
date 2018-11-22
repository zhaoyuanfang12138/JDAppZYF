package com.example.lenovo.jdappzyf.first.model;


import com.example.lenovo.jdappzyf.first.bean.CarouselBean;
import com.example.lenovo.jdappzyf.first.bean.Product;
import com.example.lenovo.jdappzyf.first.bean.Sudoku;
import com.example.lenovo.jdappzyf.utils.Api;
import com.example.lenovo.jdappzyf.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/11/7.
 */

public class FirstModel {

    public Observable<CarouselBean> carousel(){
        Api api = RetrofitManager.getInstance().create(Api.class);
        Observable<CarouselBean> carouselBeanObservable = api.carousel1();

        return carouselBeanObservable;

    }

    public Observable<Sudoku> sudoku(){
        Api api = RetrofitManager.getInstance().create(Api.class);
        Observable<Sudoku> sudokuObservable = api.sudoku1();

        return sudokuObservable;
    }

    public Observable<Product> product(int uid){
        Api api = RetrofitManager.getInstance().create(Api.class);
        Observable<Product> productObservable = api.product1(uid,"android");

        return productObservable;
    }

}

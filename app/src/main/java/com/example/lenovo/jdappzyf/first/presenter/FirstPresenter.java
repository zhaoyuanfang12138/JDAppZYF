package com.example.lenovo.jdappzyf.first.presenter;

import com.example.lenovo.jdappzyf.first.bean.CarouselBean;
import com.example.lenovo.jdappzyf.first.bean.Product;
import com.example.lenovo.jdappzyf.first.bean.Sudoku;
import com.example.lenovo.jdappzyf.first.model.FirstModel;
import com.example.lenovo.jdappzyf.first.view.FirstView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/11/7.
 */

public class FirstPresenter{

    private FirstModel model;

    private FirstView iv;

    public void attach(FirstView iv){
        this.iv=iv;
        model = new FirstModel();
    }


  public void onSuccess(){

        model.carousel()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CarouselBean>() {
                    @Override
                    public void accept(CarouselBean carouselBean) throws Exception {
                        if (carouselBean != null & "0".equals(carouselBean.getCode())) {
                            if (iv != null)
                                iv.onSuccess(carouselBean);
                            return;
                        }
                        if (iv != null)
                            iv.onFailed(new Throwable("服务器未响应"));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iv !=null)
                            iv.onFailed(new Throwable("网络异常"));
                    }
                });

  }


    public void getSudoku(){

        model.sudoku()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Sudoku>() {
                    @Override
                    public void accept(Sudoku sudoku) throws Exception {
                        if (sudoku != null & "0".equals(sudoku.getCode())) {
                            if (iv != null)
                                iv.getSudoku(sudoku);
                            return;
                        }
                        if (iv != null)
                            iv.onFailed(new Throwable("服务器未响应"));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iv !=null)
                            iv.onFailed(new Throwable("网络异常"));
                    }
                });

    }



    public void getProduct(int id){

        model.product(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Product>() {
                    @Override
                    public void accept(Product product) throws Exception {
                        if (product != null & "0".equals(product.getCode())) {
                            if (iv != null)
                                iv.getProduct(product);
                            return;
                        }
                        if (iv != null)
                            iv.onFailed(new Throwable("服务器未响应"));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iv !=null)
                            iv.onFailed(new Throwable("网络异常"));
                    }
                });

    }



    public void detach(){
        this.iv = null;

    }


}

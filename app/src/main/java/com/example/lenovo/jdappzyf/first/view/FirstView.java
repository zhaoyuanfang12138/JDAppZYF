package com.example.lenovo.jdappzyf.first.view;

import com.example.lenovo.jdappzyf.base.IView;
import com.example.lenovo.jdappzyf.first.bean.CarouselBean;
import com.example.lenovo.jdappzyf.first.bean.Product;
import com.example.lenovo.jdappzyf.first.bean.Sudoku;

/**
 * Created by lenovo on 2018/11/7.
 */

public interface FirstView extends IView {

    void onSuccess(CarouselBean carouselBean);

    void onFailed(Throwable t);

    void getSudoku(Sudoku sudoku);

    void getProduct(Product product);

}

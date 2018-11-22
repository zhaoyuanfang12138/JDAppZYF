package com.example.lenovo.jdappzyf.sort.view;

import com.example.lenovo.jdappzyf.sort.bean.CataLeft;
import com.example.lenovo.jdappzyf.sort.bean.CataRight;
import com.example.lenovo.jdappzyf.sort.bean.DetailBean;

import java.util.List;

/**
 * Created by lenovo on 2018/11/12.
 */

public interface IView {

    void getCataLeft(CataLeft cataLeft);

    void Failed(Throwable t);

    void getCataRight(CataRight cataRight);

    void getDetail(List<DetailBean.DataBean> list);

}

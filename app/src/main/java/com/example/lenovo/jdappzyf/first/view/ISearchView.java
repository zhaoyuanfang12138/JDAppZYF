package com.example.lenovo.jdappzyf.first.view;

import com.example.lenovo.jdappzyf.first.bean.SearchPage;

import java.util.List;

/**
 * Created by lenovo on 2018/11/15.
 */

public interface ISearchView {

    void search(List<SearchPage.DataBean> dataBeans);

    void faid(Throwable t);
}

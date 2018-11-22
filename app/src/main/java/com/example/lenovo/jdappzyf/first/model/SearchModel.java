package com.example.lenovo.jdappzyf.first.model;

import com.example.lenovo.jdappzyf.first.bean.SearchPage;
import com.example.lenovo.jdappzyf.utils.Api;
import com.example.lenovo.jdappzyf.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/11/15.
 */

public class SearchModel {


    public Observable<SearchPage> getSearchPage(String keywords, int page, String sou){
        Api api = RetrofitManager.getInstance().create(Api.class);

        Observable<SearchPage> searchPage1 = api.getSearchPage1(keywords, page, sou);

        return searchPage1;
    }

}

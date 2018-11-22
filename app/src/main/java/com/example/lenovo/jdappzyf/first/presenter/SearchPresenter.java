package com.example.lenovo.jdappzyf.first.presenter;

import com.example.lenovo.jdappzyf.first.bean.SearchPage;
import com.example.lenovo.jdappzyf.first.model.SearchModel;
import com.example.lenovo.jdappzyf.first.view.ISearchView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/11/15.
 */

public class SearchPresenter {

    private SearchModel model ;

    private ISearchView iv;

    public void attach(ISearchView iv){
        this.iv = iv;
        model = new SearchModel();
    }

    public void getSearchPage(String keywords,int page,String sou){

        model.getSearchPage(keywords,page,sou)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SearchPage>() {
                    @Override
                    public void accept(SearchPage searchPage) throws Exception {
                        if (searchPage != null & "0".equals(searchPage.getCode())) {
                            if (iv != null) {
                                iv.search(searchPage.getData());
                            }
                            return;
                        }
                        if (iv != null) {
                            iv.faid(new Throwable("服务器未响应"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iv!=null){
                            iv.faid(throwable);
                        }
                    }
                });


    }


}

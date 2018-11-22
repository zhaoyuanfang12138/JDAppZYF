package com.example.lenovo.jdappzyf.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/11/7.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IView {

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayoutId());
        ButterKnife.bind(this);
        presenter = providePresenter();
        attachIView();
        initView();
        initListener();
        initData();

    }

    protected abstract void initData();

    protected abstract void initListener();

    //初始化布局
    protected void initView(){

    }

    private void attachIView() {
        if (presenter != null) {
            presenter.attach(this);
        }
    }


    private void dettachIView(){
        if (presenter !=null){
            presenter.attach(this);
        }
    }

    protected abstract P providePresenter();

    protected abstract int provideLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dettachIView();
    }
}


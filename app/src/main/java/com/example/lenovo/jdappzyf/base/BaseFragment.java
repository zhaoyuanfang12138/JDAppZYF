package com.example.lenovo.jdappzyf.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/11/7.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IView{

    protected P presenter;
    private int contextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(getContextView(), container, false);
        ButterKnife.bind(this,v);
        initView(v);
        return v;
    }

    protected abstract void initView(View v);


    protected  abstract  int getContextView();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = getPresenter();
        attachView();
        setListener();
        initData();

    }

    protected abstract void initData();


    private void attachView() {
        if (presenter != null) {
            presenter.attach(this);
        }
    }

    protected void setListener() {
    }

    protected abstract P getPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        detach();
    }

    private void detach() {
        if (presenter != null) {
            presenter.detach();
        }
    }
}

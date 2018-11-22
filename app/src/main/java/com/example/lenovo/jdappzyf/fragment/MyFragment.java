package com.example.lenovo.jdappzyf.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.lenovo.jdappzyf.first.adapter.ProductAdapter;
import com.example.lenovo.jdappzyf.first.bean.CarouselBean;
import com.example.lenovo.jdappzyf.first.bean.Product;
import com.example.lenovo.jdappzyf.first.bean.Sudoku;
import com.example.lenovo.jdappzyf.first.presenter.FirstPresenter;
import com.example.lenovo.jdappzyf.first.view.FirstView;
import com.example.lenovo.jdappzyf.first.view.HomeActivity;
import com.example.lenovo.jdappzyf.first.view.WebViewActivity;
import com.example.lenovo.jdappzyf.login.view.CloseActivity;
import com.example.lenovo.jdappzyf.login.view.LoginActivity;

import java.util.ArrayList;
import java.util.List;
import com.example.lenovo.jdappzyf.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by lenovo on 2018/11/6.
 */
                                   //继承FirstView接口 实现FirstFragment底部的瀑布流效果
public class MyFragment extends Fragment implements FirstView {

    @BindView(R.id.img_tou)
    ImageView imgTou;
    @BindView(R.id.denglu)
    TextView denglu;
    @BindView(R.id.lines)
    LinearLayout lines;
    @BindView(R.id.Rv_list)
    RecyclerView RvList;
    Unbinder unbinder;
    private List<Product.DataBean.ListBean> productlist;
    private FirstPresenter presenter;
    private ProductAdapter productAdapter;
    private SharedPreferences sp;
    private String login;
    private String mobile;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_my, container, false);

        unbinder = ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter = new FirstPresenter();
        initData();


    }

    private void initData() {
        presenterData();
        product();

        sp = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
    }


    private void presenterData() {
        presenter.attach(this);
        presenter.getProduct(71);
    }


    //为你推荐的适配器
    private void product() {
        productlist = new ArrayList<>();
       // MyFragment底部瀑布流
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        RvList.setLayoutManager(layoutManager);

        productAdapter = new ProductAdapter(getActivity(), productlist);

        productAdapter.setItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void OnClick(int position, View view) {
                Product.DataBean.ListBean listBean = productlist.get(position);
                   //点击跳转到WebViewActivity 和FirstFragment同步跳转 然后加入购物车
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url", listBean.getDetailUrl());
                intent.putExtra("pid", listBean.getPid());
                startActivity(intent);
            }
        });

        RvList.setAdapter(productAdapter);
    }


    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public void onSuccess(CarouselBean carouselBean) {

    }

    @Override
    public void onFailed(Throwable t) {
        Toast.makeText(getActivity(), "网络异常", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSudoku(Sudoku sudoku) {

    }

    //获取瀑布流
    @Override
    public void getProduct(Product product) {
        productlist.clear();
        for (int i = 1; i < product.getData().size(); i++) {
            productlist.addAll(product.getData().get(i).getList());
        }
        RvList.setAdapter(productAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.detach();
    }

    @Override
    public void onResume() {
        super.onResume();
        sp = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        mobile = sp.getString("mobile", null);

        if (sp.getBoolean("isFirst",false)) {
            denglu.setText(mobile);
        } else {
            denglu.setText("登录/注册>");
        }
    }

    @OnClick(R.id.denglu)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.denglu:
                if (sp.getBoolean("isFirst",false)){
                    intent = new Intent(getContext(), CloseActivity.class);
                    startActivity(intent);
                }else {
                    intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }

                break;
        }
    }
}

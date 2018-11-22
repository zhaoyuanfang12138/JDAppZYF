package com.example.lenovo.jdappzyf.sort.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lenovo.jdappzyf.R;
import com.example.lenovo.jdappzyf.sort.adapter.DetailAdapter;
import com.example.lenovo.jdappzyf.sort.bean.CataLeft;
import com.example.lenovo.jdappzyf.sort.bean.CataRight;
import com.example.lenovo.jdappzyf.sort.bean.DetailBean;
import com.example.lenovo.jdappzyf.sort.presenter.SortPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements IView{

    @BindView(R.id.rv_detail)
    RecyclerView rvDetail;

    private List<DetailBean.DataBean> list;
    private SortPresenter presenter;
    private DetailAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        int cid = intent.getIntExtra("cid", 0);
        presenter = new SortPresenter();
        presenter.attach(this);
        presenter.getDetail(cid);
        list = new ArrayList<>();
        adapter = new DetailAdapter(this,list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvDetail.setLayoutManager(layoutManager);
        rvDetail.setAdapter(adapter);


    }

    @Override
    public void getCataLeft(CataLeft cataLeft) {

    }

    @Override
    public void Failed(Throwable t) {

    }

    @Override
    public void getCataRight(CataRight cataRight) {

    }

    @Override
    public void getDetail(List<DetailBean.DataBean> detabeans) {

        if (detabeans != null){
            list.clear();
            list.addAll(detabeans);
            adapter.notifyDataSetChanged();
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

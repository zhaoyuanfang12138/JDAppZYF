package com.example.lenovo.jdappzyf.first.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.lenovo.jdappzyf.R;
import com.example.lenovo.jdappzyf.first.adapter.SearchAdapter;
import com.example.lenovo.jdappzyf.first.bean.SearchPage;
import com.example.lenovo.jdappzyf.first.presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchPageActivity extends AppCompatActivity implements ISearchView{

    @BindView(R.id.rl_search)
    RecyclerView rlSearch;
    private List<SearchPage.DataBean> searchList;
    private SearchPresenter presenter;
    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        ButterKnife.bind(this);

        initView();




    }

    private void initView() {
        presenter = new SearchPresenter();
        presenter.attach(this);
        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        presenter.getSearchPage(text, 1,"android");
        searchList = new ArrayList<>();
        //适配器
        adapter = new SearchAdapter(this,searchList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rlSearch.setLayoutManager(layoutManager);
         adapter.setItemClickListener(new SearchAdapter.onItemClickListener() {
             @Override
             public void onClick(int position, View v) {
                 SearchPage.DataBean dataBean = searchList.get(position);
                 Intent intent = new Intent(SearchPageActivity.this,WebViewActivity.class);
                 intent.putExtra("url", dataBean.getDetailUrl());
                 intent.putExtra("pid", dataBean.getPid());
                 startActivity(intent);

             }
         });
        rlSearch.setAdapter(adapter);


    }

    @Override
    public void search(List<SearchPage.DataBean> dataBeans) {
        if (dataBeans != null) {
            searchList.clear();
            searchList.addAll(dataBeans);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void faid(Throwable t) {
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
    }
}

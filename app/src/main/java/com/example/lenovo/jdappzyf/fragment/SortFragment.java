package com.example.lenovo.jdappzyf.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.lenovo.jdappzyf.R;
import com.example.lenovo.jdappzyf.first.view.ItemActivity;
import com.example.lenovo.jdappzyf.sort.adapter.LeftAdapter;
import com.example.lenovo.jdappzyf.sort.adapter.MyAdapter;
import com.example.lenovo.jdappzyf.sort.adapter.RightAdapter;
import com.example.lenovo.jdappzyf.sort.bean.CataLeft;
import com.example.lenovo.jdappzyf.sort.bean.CataRight;
import com.example.lenovo.jdappzyf.sort.bean.DetailBean;
import com.example.lenovo.jdappzyf.sort.presenter.SortPresenter;
import com.example.lenovo.jdappzyf.sort.view.DetailActivity;
import com.example.lenovo.jdappzyf.sort.view.IView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2018/11/6.
 */

public class SortFragment extends Fragment implements IView {

    private SortPresenter presenter;
    private List<CataRight.DataBean> catalist;
    private List<CataLeft.DataBean> list;
    private LeftAdapter leftAdapter;
    private RightAdapter adapter;

    @BindView(R.id.img_ar_code)
    ImageView imgArCode;
    @BindView(R.id.ed_search_box)
    EditText edSearchBox;
    @BindView(R.id.ed_message)
    ImageView edMessage;
    @BindView(R.id.rv_left)
    RecyclerView rvLeft;
    @BindView(R.id.rv_right)
    RecyclerView rvRight;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_sort, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter = new SortPresenter();
        initData();
    }

    private void initData() {
        presenterData();
        cataleft();
        cataright();
    }

    private void presenterData() {
        presenter.attach(this);
        presenter.getCataLeft();
        presenter.getCataRight(1);
    }

    private void cataright() {

        catalist = new ArrayList<>();
        adapter = new RightAdapter(getActivity(), catalist);
        RecyclerView.LayoutManager rlayoutManager = new LinearLayoutManager(getActivity());

        rvRight.setLayoutManager(rlayoutManager);

        adapter.setChildClickListener(new MyAdapter.OnChildClickListener() {
            @Override
            public void OnChildClickListener(int posotion, View v) {

                for (CataRight.DataBean dataBean : catalist) {
                    int pscid = dataBean.getList().get(posotion).getPscid();

                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra("cid", pscid);
                    startActivity(intent);
                    return;
                }

            }
        });


        rvRight.setAdapter(adapter);

    }

    private void cataleft() {

        list = new ArrayList<>();
        //左边适配器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvLeft.setLayoutManager(layoutManager);
        leftAdapter = new LeftAdapter(getActivity(), list);
        rvLeft.setAdapter(leftAdapter);


        leftAdapter.setOnItemClickListener(new LeftAdapter.OnItemClickListener() {
            @Override
            public void OnClick(View itemView, int position) {
                int cid = list.get(position).getCid();
                presenter.getCataRight(cid);
            }
        });

    }


    @Override
    public void getCataLeft(CataLeft cataLeft) {

        if (cataLeft != null) {
            list.addAll(cataLeft.getData());
            leftAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void Failed(Throwable t) {

    }

    @Override
    public void getCataRight(CataRight cataRight) {

        if (cataRight != null) {
            catalist.clear();
            catalist.addAll(cataRight.getData());
            adapter.notifyDataSetChanged();
        }


    }

    @Override
    public void getDetail(List<DetailBean.DataBean> list) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.ed_search_box)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.ed_search_box:
                startActivity(new Intent(getActivity(), ItemActivity.class));
                break;
        }
    }
}

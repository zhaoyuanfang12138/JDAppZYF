package com.example.lenovo.jdappzyf.login.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.lenovo.jdappzyf.R;
import com.example.lenovo.jdappzyf.login.adapter.AddressAdapter;
import com.example.lenovo.jdappzyf.login.bean.AddreseeBean;
import com.example.lenovo.jdappzyf.login.presenter.AddressPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends AppCompatActivity implements IAddressView {

    @BindView(R.id.address_back)
    ImageView addressBack;
    @BindView(R.id.re)
    RelativeLayout re;
    @BindView(R.id.rv_addresee)
    RecyclerView rvAddresee;
    @BindView(R.id.btn_address)
    Button btnAddress;
    private List<AddreseeBean.DataBean> list;

    private int uid;
    private AddressPresenter presenter;
    private AddressAdapter addressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);

        initView();
        initData();

    }

    private void initView() {
        presenter = new AddressPresenter();
        presenter.attach(this);
        SharedPreferences sp = getSharedPreferences("Login", Context.MODE_PRIVATE);
            uid = sp.getInt("uid",0);
            presenter.getAddr(uid);

    }


    private void initData() {

        presenter.getAddr(uid);
        list = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        addressAdapter = new AddressAdapter(this, list);
        rvAddresee.setLayoutManager(layoutManager);

        rvAddresee.setAdapter(addressAdapter);


    }


    @Override
    public void getAddresee(AddreseeBean addreseeBean) {
        list.clear();
        list.addAll(addreseeBean.getData());
        addressAdapter.notifyDataSetChanged();
    }

    @Override
    public void faild(Throwable t) {

    }

    @OnClick({R.id.btn_address,R.id.address_back})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btn_address:
                startActivity(new Intent(AddressActivity.this,AddAddressActivity.class));
                break;


            case R.id.address_back:
                finish();
                break;
        }
    }
}

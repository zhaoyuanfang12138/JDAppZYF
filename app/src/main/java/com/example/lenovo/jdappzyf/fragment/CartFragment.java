package com.example.lenovo.jdappzyf.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.jdappzyf.R;
import com.example.lenovo.jdappzyf.cart.adapter.CartAdapter;
import com.example.lenovo.jdappzyf.cart.adapter.ChildAdapter;
import com.example.lenovo.jdappzyf.cart.bean.Cart;
import com.example.lenovo.jdappzyf.cart.presenter.CartPresenter;
import com.example.lenovo.jdappzyf.cart.view.CartIView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by lenovo on 2018/11/6.
 */

public class CartFragment extends Fragment implements CartIView {


    @BindView(R.id.txt_edit)
    TextView txtEdit;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.rv_shopper)
    RecyclerView rvShopper;
    @BindView(R.id.cb_total_select)
    CheckBox cbTotalSelect;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.btn_calu)
    Button btnCalu;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;
    Unbinder unbinder;
    private List<Cart.DataBean> list;
    private CartPresenter presenter;
    private CartAdapter cartAdapter;
    private SharedPreferences sp;
    private int uid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_cart, container, false);

        unbinder = ButterKnife.bind(this, v);
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sp = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        uid = sp.getInt("uid",0);
      //  Toast.makeText(getActivity(), ""+uid, Toast.LENGTH_SHORT).show();
        initData();
    }

    private void initData() {
         //调用P层的内容
        presenter = new CartPresenter();
        presenter.attach(this);
        presenter.getCart(uid);


        list = new ArrayList<>();
        //商品的适配器
        cartAdapter = new CartAdapter(getActivity(), list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvShopper.setLayoutManager(layoutManager);
        rvShopper.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        cartAdapter.setCartClickListener(new CartAdapter.onCartClickListener() {
            @Override
            public void onCartListener(int position, boolean isChecked) {
                if (!isChecked) {
                    cbTotalSelect.setChecked(false);
                } else {
                    boolean isAllCartSelected = true;
                    for (Cart.DataBean dataBean : list) {
                        if (!dataBean.isChecked()) {
                            isAllCartSelected = false;
                            break;
                        }
                    }
                    cbTotalSelect.setChecked(isAllCartSelected);
                    AllSum();
                }
            }
        });

        cartAdapter.setAddCreateClickListener(new ChildAdapter.onAddCreateClickListener() {
            @Override
            public void onChange(int position, int num) {
                AllSum();
            }
        });


        rvShopper.setAdapter(cartAdapter);
        cbTotalSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = cbTotalSelect.isChecked();
                for (Cart.DataBean dataBean : list) {
                    dataBean.setChecked(checked);
                    for (Cart.DataBean.ListBean listBean : dataBean.getList()) {
                        listBean.setChecked(checked);
                    }
                }
                AllSum();
                cartAdapter.notifyDataSetChanged();
            }
        });


    }


    private void AllSum() {
        float sum = 0;
        for (Cart.DataBean dataBean : list) {
            for (Cart.DataBean.ListBean listBean : dataBean.getList()) {
                if (listBean.isChecked()) {
                    sum += listBean.getPrice() * listBean.getNum();
                }
            }
        }
        txtPrice.setText("总价:" + sum);
    }


                 //成功方法
    @Override
    public void getCart(List<Cart.DataBean> dataBeans) {


        if (dataBeans != null){
            list.clear();
            if (uid != 0){
                list.addAll(dataBeans);
                cartAdapter.notifyDataSetChanged();
            }

        }

    }

           //失败
    @Override
    public void faild(Throwable t) {
        Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.detach();
    }
}

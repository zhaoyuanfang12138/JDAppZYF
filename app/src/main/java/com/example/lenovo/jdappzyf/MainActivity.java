package com.example.lenovo.jdappzyf;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.jdappzyf.fragment.CartFragment;
import com.example.lenovo.jdappzyf.fragment.FindFragment;
import com.example.lenovo.jdappzyf.fragment.FirstFragment;
import com.example.lenovo.jdappzyf.fragment.MyFragment;
import com.example.lenovo.jdappzyf.fragment.SortFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.txt_first)
    ImageView txtFirst;
    @BindView(R.id.txt_scot)
    ImageView txtScot;
    @BindView(R.id.txt_find)
    ImageView txtFind;
    @BindView(R.id.txt_cart)
    ImageView txtCart;
    @BindView(R.id.txt_my)
    ImageView txtMy;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.vp_fragment)
    FrameLayout vpFragment;
    private FirstFragment tf;
    private SortFragment sf;
    private FindFragment ff;
    private CartFragment cf;
    private MyFragment mf;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        setListener();

       initData();

    }

    private void initData() {
        manager = getSupportFragmentManager();
        tf = new FirstFragment();
        sf = new SortFragment();
        ff = new FindFragment();
        cf = new CartFragment();
        mf = new MyFragment();
        manager.beginTransaction()
                .add(R.id.vp_fragment,tf)
                .commit();

    }

    private void setListener() {
        txtFirst.setOnClickListener(this);
        txtScot.setOnClickListener(this);
        txtFind.setOnClickListener(this);
        txtCart.setOnClickListener(this);
        txtMy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_first:

                setBack(0);
                manager.beginTransaction()
                        .replace(R.id.vp_fragment,tf)
                        .commit();

                break;

            case R.id.txt_scot:
                setBack(1);
                manager.beginTransaction()
                        .replace(R.id.vp_fragment,sf)
                        .commit();
                break;

            case R.id.txt_find:
                setBack(2);
                manager.beginTransaction()
                        .replace(R.id.vp_fragment,ff)
                        .commit();
                break;

            case R.id.txt_cart:
                setBack(3);
                manager.beginTransaction()
                        .replace(R.id.vp_fragment,cf)
                        .commit();
                break;

            case R.id.txt_my:
                setBack(4);
                manager.beginTransaction()
                        .replace(R.id.vp_fragment,mf)
                        .commit();
                break;
        }
    }


    public void setBack(int index) {
        Glide.with(this).load(index==0?R.drawable.am0:R.drawable.first).into(txtFirst);
        Glide.with(this).load(index==1?R.drawable.alw:R.drawable.sort).into(txtScot);
        Glide.with(this).load(index==2?R.drawable.aly:R.drawable.find).into(txtFind);
        Glide.with(this).load(index==3?R.drawable.alu:R.drawable.cart).into(txtCart);
        Glide.with(this).load(index==4?R.drawable.am2:R.drawable.mine).into(txtMy);
    }

}

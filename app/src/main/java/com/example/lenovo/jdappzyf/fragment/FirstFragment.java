package com.example.lenovo.jdappzyf.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.lenovo.jdappzyf.R;
import com.example.lenovo.jdappzyf.first.adapter.BannerAdapter;
import com.example.lenovo.jdappzyf.first.adapter.ProductAdapter;
import com.example.lenovo.jdappzyf.first.adapter.SudokuAdapter;
import com.example.lenovo.jdappzyf.first.bean.CarouselBean;
import com.example.lenovo.jdappzyf.first.bean.Product;
import com.example.lenovo.jdappzyf.first.bean.Sudoku;
import com.example.lenovo.jdappzyf.first.presenter.FirstPresenter;
import com.example.lenovo.jdappzyf.first.view.FirstView;
import com.example.lenovo.jdappzyf.first.view.HomeActivity;
import com.example.lenovo.jdappzyf.first.view.ItemActivity;
import com.example.lenovo.jdappzyf.first.view.WebViewActivity;
import com.example.lenovo.jdappzyf.first.view.weight.ObservableScrollView;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2018/11/6.
 */

public class FirstFragment extends Fragment implements FirstView {
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    private RelativeLayout rela;
    private ObservableScrollView scrollView;
    //设置渐变高度，一般为导航图片高度，自己控制
    private int imageHeight = 300;

    @BindView(R.id.img_ar_code)
    ImageView imgArCode;
    @BindView(R.id.ed_search_box)
    SearchView edSearchBox;
    @BindView(R.id.ed_message)
    ImageView edMessage;
    @BindView(R.id.vp_banner)
    ViewPager vpBanner;
    @BindView(R.id.rv_sudoku)
    RecyclerView rvSudoku;
    @BindView(R.id.rv_show)
    RecyclerView rvShow;
    Unbinder unbinder;

    private List<CarouselBean.DataBean> bannerlist;
    private List<Sudoku.DataBean> sudokulist;
    private List<Product.DataBean.ListBean> productlist;

    private FirstPresenter presenter;
    private BannerAdapter bannerAdapter;

    private SudokuAdapter sudokuAdapter;
    public static final int FLAG = 123;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == FLAG) {
                int index = vpBanner.getCurrentItem();
                if (index < bannerlist.size() - 1) {
                    index++;
                } else {
                    index = 0;
                }
                vpBanner.setCurrentItem(index);
                sendEmptyMessageDelayed(FLAG, 4000);
            }

        }
    };
    private ProductAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first, container, false);
        initView(v);
        unbinder = ButterKnife.bind(this, v);

        //跑马灯
        //查找控件
        rela = (RelativeLayout) v.findViewById(R.id.rela);
        scrollView = (ObservableScrollView) v.findViewById(R.id.scrollView);
        //搜索框在布局最上面
        rela.bringToFront();

        //滑动监听
        scrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    rela.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
                } else if (y > 0 && y <= imageHeight) {
                    float scale = (float) y / imageHeight;
                    float alpha = (255 * scale);
                    // 只是layout背景透明
                    rela.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
                } else {
                    rela.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
                }
            }
        });

        return v;
    }

    protected void initView(View v) {

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new FirstPresenter();
        initData();
    }

    private void initData() {
        presenterData();
        bannerData();
        paomadeng();
        product();

        //九宫格网格布局
        sudokulist = new ArrayList<>();
        RecyclerView.LayoutManager gridlayout = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        rvSudoku.setLayoutManager(gridlayout);
        sudokuAdapter = new SudokuAdapter(getActivity(), sudokulist);

    }

    private void paomadeng() {
        List<String> info = new ArrayList<>();
        info.add("欢迎访问京东app");
        info.add("潮牌大乐购");
        info.add("我型我秀");
        info.add("高通5G正式名单");
        info.add("大家为什么突然不想换手机了?");
        info.add("小米MINI3:感谢荣耀Magic2的力作");
        marqueeView.startWithList(info);

    }

    //FirstFragment底部瀑布流
    private void product() {

        productlist = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvShow.setLayoutManager(layoutManager);

        adapter = new ProductAdapter(getActivity(), productlist);

        //点击条目跳转
        adapter.setItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void OnClick(int position, View v) {

                Product.DataBean.ListBean listBean = productlist.get(position);
                //点击跳转到WebViewActivity   这里和MyFragment的操作是一样的 然后加入购物车
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                //传对应的url和pid
                intent.putExtra("url", listBean.getDetailUrl());
                intent.putExtra("pid", listBean.getPid());
                startActivity(intent);

            }
        });


        rvShow.setAdapter(adapter);


    }

    private void presenterData() {
        presenter.attach(this);
        presenter.onSuccess();
        presenter.getSudoku();
        presenter.getProduct(71);
    }

    //轮播图适配器
    private void bannerData() {
        bannerlist = new ArrayList<>();
        bannerAdapter = new BannerAdapter(getActivity(), bannerlist);
        handler.sendEmptyMessageDelayed(FLAG, 4000);
    }


    @Override
    public Context context() {
        return getActivity();
    }

    //轮播图
    @Override
    public void onSuccess(CarouselBean carouselBean) {
        if (carouselBean != null) {
            bannerlist.addAll(carouselBean.getData());
        }
        vpBanner.setAdapter(bannerAdapter);
    }

    @Override
    public void onFailed(Throwable t) {
        Toast.makeText(getActivity(), "网络异常", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSudoku(Sudoku sudoku) {

        if (sudoku != null) {
            sudokulist.addAll(sudoku.getData());
        }
        rvSudoku.setAdapter(sudokuAdapter);

    }

    //瀑布流
    @Override
    public void getProduct(Product product) {
        productlist.clear();
        for (int i = 1; i < product.getData().size(); i++) {
            productlist.addAll(product.getData().get(i).getList());
        }
        rvShow.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
//        unbinder.unbind();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacksAndMessages(null);
        presenter.detach();
        unbinder.unbind();
    }

    //点击搜索框跳转搜索页面
    @OnClick(R.id.ed_search_box)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.ed_search_box:
                 startActivity(new Intent(getActivity(), ItemActivity.class));
                break;
        }

    }
}

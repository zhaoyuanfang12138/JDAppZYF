package com.example.lenovo.jdappzyf.first.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.jdappzyf.R;
import com.example.lenovo.jdappzyf.first.bean.AddCart;
import com.example.lenovo.jdappzyf.first.presenter.WebViewPresenter;
import com.example.lenovo.jdappzyf.first.view.weight.HeadView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewActivity extends AppCompatActivity implements IWebView {


    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.bar)
    RelativeLayout bar;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.jiaru)
    Button jiaru;
    @BindView(R.id.liji)
    Button liji;
    @BindView(R.id.shop_close)
    ImageView shopClose;
    @BindView(R.id.shop_image)
    ImageView shopImage;
    @BindView(R.id.shop_price)
    TextView shopPrice;
    @BindView(R.id.layout_top)
    RelativeLayout layoutTop;
    @BindView(R.id.shop_title)
    TextView shopTitle;
    @BindView(R.id.sure)
    Button sure;
    @BindView(R.id.layout_shop_add_car)
    RelativeLayout layoutShopAddCar;
    @BindView(R.id.hv)
    HeadView hv;
    private int uid;
    private int pid;
    private WebViewPresenter presenter;


    private WebSettings settings;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {

        presenter = new WebViewPresenter();
        presenter.attach(this);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        pid = intent.getIntExtra("pid", 0);
        sp = getSharedPreferences("Login", Context.MODE_PRIVATE);
        uid = sp.getInt("uid", 0);
        try {
            setData(url);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void getAddCart(AddCart addCart) {
        Toast.makeText(this, "" + addCart.getMsg(), Toast.LENGTH_SHORT).show();
        hintShopCar();
    }

    @Override
    public void faild(Throwable t) {
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void setData(String url) {
        settings = webview.getSettings();
        settings.setUseWideViewPort(true);//设置加载进来的页面自适应屏幕
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setUseWideViewPort(false);//禁止webview做自动缩放
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(false);
        settings.setDisplayZoomControls(false);
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setSupportMultipleWindows(false);
        settings.setAppCachePath(this.getDir("cache", Context.MODE_PRIVATE).getPath());
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webview.setFocusable(true);
        webview.requestFocus();
        webview.setWebChromeClient(new WebChromeClient());  //解决android与H5协议交互,弹不出对话框问题
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //页面加载完成之后
                bar.setVisibility(View.GONE);
            }

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webview.loadUrl(url);
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
                return true;

            }
        });
        webview.loadUrl(url);
    }

    //添加购物车
    private void doGet(int pid) {
      //  Toast.makeText(this,""+pid+uid,Toast.LENGTH_LONG).show();
        presenter.getAddCart(uid, pid);

    }

    //显示
    private void showShopCar() {
        int height = getResources().getDisplayMetrics().heightPixels;
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(layoutShopAddCar, "translationY", height, 600);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
        layoutShopAddCar.setVisibility(View.VISIBLE);

    }


    //关闭

    private void hintShopCar() {
        int height = getResources().getDisplayMetrics().heightPixels;
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(layoutShopAddCar, "translationY", 600, height);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutShopAddCar.setVisibility(View.GONE);
            }
        }, 1000);

    }

    @OnClick({R.id.jiaru, R.id.shop_close, R.id.sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jiaru:
                showShopCar();
                break;
            case R.id.shop_close:
                //关闭
                hintShopCar();

                break;
            case R.id.sure:

                //确定
                doGet(pid);
                break;
        }
    }
}

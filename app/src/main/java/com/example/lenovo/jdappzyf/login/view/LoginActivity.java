package com.example.lenovo.jdappzyf.login.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.jdappzyf.R;
import com.example.lenovo.jdappzyf.login.bean.LoginBean;
import com.example.lenovo.jdappzyf.login.presenter.LoginPresenter;
import com.example.lenovo.jdappzyf.regist.view.RegistActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginView {


    @BindView(R.id.ed_mobile)
    EditText edMobile;
    @BindView(R.id.ed_password)
    EditText edPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.cha_back)
    ImageView chaBack;
    @BindView(R.id.txt_regist)
    TextView txtRegist;
    private LoginPresenter presenter;
    private String mobile;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenter();
        presenter.attach(this);


    }


    @Override
    public void success(LoginBean loginBean) {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
       sp.edit().putString("mobile",loginBean.getData().getMobile()).putString("login","true").putInt("uid",loginBean.getData().getUid()).commit();
        finish();
    }

    @Override
    public void failed(Throwable t) {
        Toast.makeText(this, "登录失败", Toast.LENGTH_LONG).show();
     //   return;
    }

    @Override
    public void CheckDate(boolean isChecked) {
        if (isChecked) {
            presenter.login();
        }
    }

    @Override
    public String getMobile() {
        return edMobile.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return edPassword.getText().toString();
    }

    @Override
    public void fish() {
         finish();
    }

    @Override
    public void goToRegist() {
        Intent intent = new Intent(this, RegistActivity.class);
        startActivity(intent);
    }

    @Override
    public Context context() {
        return this;
    }

    @OnClick({R.id.cha_back, R.id.btn_login, R.id.txt_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cha_back:
                finish();
                break;
            case R.id.btn_login:
             presenter.CheckDate();
                break;
            case R.id.txt_regist:

            presenter.goToRegist();

                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detach();
        }
    }
}

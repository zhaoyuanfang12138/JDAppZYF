package com.example.lenovo.jdappzyf.regist.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.jdappzyf.R;
import com.example.lenovo.jdappzyf.login.view.LoginActivity;
import com.example.lenovo.jdappzyf.regist.bean.RegistBean;
import com.example.lenovo.jdappzyf.regist.presenter.RegistPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistActivity extends AppCompatActivity implements RegistView {

    @BindView(R.id.txt_regist_back)
    TextView txtRegistBack;
    @BindView(R.id.txt_qiyezhuce)
    TextView txtQiyezhuce;
    @BindView(R.id.txt_regist_mobile)
    EditText txtRegistMobile;
    @BindView(R.id.txt_regist_password)
    EditText txtRegistPassword;
    @BindView(R.id.btn_regist)
    Button btnRegist;
    @BindView(R.id.lef)
    TextView lef;
    @BindView(R.id.txt_lianxikefu)
    TextView txtLianxikefu;
    private RegistPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);

        presenter = new RegistPresenter();
        presenter.attach(this);


    }


    @Override
    public void success(RegistBean registBean) {

        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this, "注册失败,请重新注册" + e, Toast.LENGTH_LONG).show();
    }


    @OnClick({R.id.txt_regist_back, R.id.btn_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_regist_back:
                finish();
                break;
            case R.id.btn_regist:

                String mobile = txtRegistMobile.getText().toString().trim();
                String password = txtRegistPassword.getText().toString();
                if (!TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(password)) {
                    if (mobile.length() != 11) {
                        Toast.makeText(this, "请核查手机号是否正确", Toast.LENGTH_LONG).show();
                    } else {
                        presenter.regist(mobile,password);

                    }
                } else {
                    Toast.makeText(this, "手机号或密码不能为空", Toast.LENGTH_LONG).show();
                }

                break;
        }
    }
}

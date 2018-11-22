package com.example.lenovo.jdappzyf.login.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.jdappzyf.R;
import com.example.lenovo.jdappzyf.login.bean.AddAddressBean;
import com.example.lenovo.jdappzyf.login.presenter.AddAddressPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAddressActivity extends AppCompatActivity implements IAddAddressView {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.input_name)
    EditText inputName;
    @BindView(R.id.input_mobile)
    EditText inputMobile;
    @BindView(R.id.input_address)
    EditText inputAddress;
    @BindView(R.id.btn_add_address)
    Button btnAddAddress;
    private AddAddressPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);

        presenter = new AddAddressPresenter();
        presenter.attach(this);

    }

    @Override
    public void success(AddAddressBean addAddressBean) {
        Toast.makeText(this, "添加成功", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, AddressActivity.class);
        startActivity(intent);

        finish();
    }

    @Override
    public void failed(Throwable t) {
        Toast.makeText(this, "添加失败"+t, Toast.LENGTH_LONG).show();
    }

    @OnClick({R.id.img_back, R.id.btn_add_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_add_address:

                SharedPreferences sp = getSharedPreferences("Login",MODE_PRIVATE);
                int uid = sp.getInt("uid",0);
                String Address = inputAddress.getText().toString();
                String mobile = inputMobile.getText().toString();
                String Name = inputName.getText().toString();

                if (TextUtils.isEmpty(Address)){
                    Toast.makeText(this, "收货地址不能为空！", Toast.LENGTH_LONG).show();
                }else if (TextUtils.isEmpty(mobile)){
                    Toast.makeText(this, "手机号不能为空！", Toast.LENGTH_LONG).show();
                }else if (TextUtils.isEmpty(Name)){
                    Toast.makeText(this, "买主的名字不能为空啦！", Toast.LENGTH_LONG).show();
                }

                if (uid != 0){
                    presenter.addAddress(uid,Address,mobile,Name);
                }

                break;
        }
    }
}

package com.example.lenovo.jdappzyf.login.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.jdappzyf.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CloseActivity extends AppCompatActivity {

    @BindView(R.id.img_return)
    ImageView imgReturn;
    @BindView(R.id.txt_service)
    TextView txtService;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.btn_logout)
    Button btnLogout;
    @BindView(R.id.txt_address)
    TextView txtAddress;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close);
        ButterKnife.bind(this);
        sp = getSharedPreferences("Login", Context.MODE_PRIVATE);
        String username = sp.getString("username", "");
        txtName.setText(username);

    }

    @OnClick({R.id.img_return, R.id.btn_logout,R.id.txt_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_return:
                finish();
                break;
            case R.id.btn_logout:
                sp.edit().clear().commit();
                finish();
                break;

            case R.id.txt_address:
             startActivity(new Intent(CloseActivity.this,AddressActivity.class));
                break;
        }
    }
}

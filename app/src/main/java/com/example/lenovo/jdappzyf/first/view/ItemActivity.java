package com.example.lenovo.jdappzyf.first.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.jdappzyf.R;
import com.example.lenovo.jdappzyf.first.view.weight.FlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ItemActivity extends AppCompatActivity {

    @BindView(R.id.img_search_back)
    ImageView imgSearchBack;
    @BindView(R.id.ed_search_box)
    EditText edSearchBox;
    @BindView(R.id.txt_search)
    TextView txtSearch;
    @BindView(R.id.rela)
    RelativeLayout rela;
    @BindView(R.id.fl_search)
    FlowLayout flSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.img_search_back, R.id.txt_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_search_back:
                finish();
                break;
            case R.id.txt_search:

                String text = edSearchBox.getText().toString().trim();
                Intent intent = new Intent(this, SearchPageActivity.class);
                intent.putExtra("text", text);
                startActivity(intent);
                if (!TextUtils.isEmpty(text)) {
                    TextView txt = new TextView(this);
                    txt.setText(text);
                    txt.setPadding(50, 20, 50, 20);
                    txt.setBackgroundResource(R.drawable.txt_bg);
                    txt.setTextColor(Color.WHITE);
                    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    txt.setLayoutParams(layoutParams);
                    flSearch.addView(txt);
                    edSearchBox.setText("");
                }

                break;
        }
    }
}

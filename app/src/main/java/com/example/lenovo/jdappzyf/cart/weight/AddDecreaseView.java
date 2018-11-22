package com.example.lenovo.jdappzyf.cart.weight;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.jdappzyf.R;

import butterknife.BindView;

/**
 * Created by lenovo on 2018/11/13.
 */

public class AddDecreaseView extends LinearLayout implements View.OnClickListener {

    @BindView(R.id.txt_decrease)
    TextView txtDecrease;
    @BindView(R.id.txt_add)
    TextView txtAdd;
    @BindView(R.id.txt_num)
    TextView txtNum;

    private int num;



    //接口回调
    public interface OnAddDecreaseClickListener {
        void add(int num);

        void decrease(int num);
    }

    private OnAddDecreaseClickListener clickListener;

    public void setClickListener(OnAddDecreaseClickListener clickListener) {
        this.clickListener = clickListener;
    }


    public AddDecreaseView(Context context) {
        this(context, null);
    }

    public AddDecreaseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddDecreaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.item_add, this);
        txtAdd = findViewById(R.id.txt_add);
        txtDecrease = findViewById(R.id.txt_decrease);
        txtNum = findViewById(R.id.txt_num);
        txtAdd.setOnClickListener(this);
        txtDecrease.setOnClickListener(this);

    }

    public void setNum(int num){
        this.num = num;
        txtNum.setText(num+"");
    }

    public int getNum(){
        return num;
    }

    //点击事件
    @Override
    public void onClick(View view) {
         switch (view.getId()){
             case R.id.txt_add:
                 num++;
                 txtNum.setText(num+"");
                 if (clickListener != null){
                     clickListener.add(num);
                 }
                 break;

             case R.id.txt_decrease:
                if (num>1){
                    num--;
                }
                txtNum.setText(num+"");
                if (clickListener!=null){
                    clickListener.decrease(num);
                }
                 break;

         }
    }

}

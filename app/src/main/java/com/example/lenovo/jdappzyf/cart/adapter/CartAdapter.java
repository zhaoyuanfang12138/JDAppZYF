package com.example.lenovo.jdappzyf.cart.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.lenovo.jdappzyf.R;
import com.example.lenovo.jdappzyf.cart.bean.Cart;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/11/13.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Holder> {

    //一级列表(商家) 发生变化的接口
    public interface onCartClickListener {
        void onCartListener(int position, boolean isChecked);
    }

    private onCartClickListener cartClickListener;

    public void setCartClickListener(onCartClickListener cartClickListener) {
        this.cartClickListener = cartClickListener;
    }



    // 二级列表的加减器监听
    private ChildAdapter.onAddCreateClickListener addCreateClickListener;

    public void setAddCreateClickListener(ChildAdapter.onAddCreateClickListener addCreateClickListener) {
        this.addCreateClickListener = addCreateClickListener;
    }



    private Context context;
    private List<Cart.DataBean> list;

    public CartAdapter(Context context, List<Cart.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_cart, null);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        final Cart.DataBean dataBean = list.get(position);
        holder.txtShopperName.setText(dataBean.getSellerName());

        //产品的列表
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        final ChildAdapter childAdapter = new ChildAdapter(context, dataBean.getList());
        holder.rvProduct.setLayoutManager(layoutManager);

        // 给二级列表添加一个加减器的监听
        if (addCreateClickListener!=null){
            childAdapter.setAddCreateClickListener(addCreateClickListener);
        }

        // 二级条目（商品）复选框点击事件
        childAdapter.setChildClickListener(new ChildAdapter.onChildClickListener() {
            @Override
            public void onChildListener(int position, boolean isChecked) {
                if (!isChecked) {
                    dataBean.setChecked(false);
                    cartClickListener.onCartListener(position, false);
                } else {
                    boolean isAllChildSelected = true;
                    for (Cart.DataBean.ListBean listBean : dataBean.getList()) {
                        if (!listBean.isChecked()) {
                            isAllChildSelected = false;
                            break;
                        }
                    }
                    dataBean.setChecked(isAllChildSelected);
                    cartClickListener.onCartListener(position, true);
                }
                notifyDataSetChanged();
            }
        });
        holder.rvProduct.setAdapter(childAdapter);

        // 先取消掉之前的点击变化监听
        holder.cbShopper.setOnCheckedChangeListener(null);
        // 设置好初始化的状态
        holder.cbShopper.setChecked(dataBean.isChecked());
        holder.cbShopper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                dataBean.setChecked(isChecked);
                for (Cart.DataBean.ListBean listBean : dataBean.getList()) {
                    listBean.setChecked(isChecked);
                }
                // 子类商品的适配器刷新
                childAdapter.notifyDataSetChanged();
                // 当点击一级条目的时候，外部的全选按钮状态发生变化
                if (cartClickListener != null) {
                    cartClickListener.onCartListener(position, isChecked);
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.cb_shopper)
        CheckBox cbShopper;
        @BindView(R.id.txt_shopper_name)
        TextView txtShopperName;
        @BindView(R.id.rv_product)
        RecyclerView rvProduct;
        public Holder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }

}

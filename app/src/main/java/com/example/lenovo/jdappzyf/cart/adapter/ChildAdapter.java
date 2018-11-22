package com.example.lenovo.jdappzyf.cart.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.lenovo.jdappzyf.R;
import com.example.lenovo.jdappzyf.cart.bean.Cart;
import com.example.lenovo.jdappzyf.cart.weight.AddDecreaseView;
import com.example.lenovo.jdappzyf.utils.ImgUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/11/13.
 */

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.Holder> {



    //二级条目（商品）点击监听
    public interface onChildClickListener {
        void onChildListener(int position, boolean isChecked);
    }

    private onChildClickListener childClickListener;

    public void setChildClickListener(onChildClickListener childClickListener) {
        this.childClickListener = childClickListener;
    }


    // 加减器发生变化的监听
    public interface onAddCreateClickListener {
        void onChange(int position, int num);
    }

    private onAddCreateClickListener addCreateClickListener;

    public void setAddCreateClickListener(onAddCreateClickListener addCreateClickListener) {
        this.addCreateClickListener = addCreateClickListener;
    }


    private Context context;
    private List<Cart.DataBean.ListBean> list;

    public ChildAdapter(Context context, List<Cart.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = View.inflate(context, R.layout.item_cart_child, null);


        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        final Cart.DataBean.ListBean listBean = list.get(position);
        holder.txtProductName.setText(listBean.getTitle());
        holder.txtSinglePrice.setText(listBean.getPrice() + "");
        holder.imgProduct.setImageURI(Uri.parse(ImgUtils.getImg(listBean.getImages())));
        holder.advProduct.setNum(listBean.getNum());

        //商品的复选框
        holder.cbProduct.setOnCheckedChangeListener(null);
        holder.cbProduct.setChecked(listBean.isChecked());
        holder.cbProduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                listBean.setChecked(isChecked);

                if (childClickListener != null) {
                    childClickListener.onChildListener(position, isChecked);
                }
            }
        });



        //加减器添加点击事件
        holder.advProduct.setClickListener(new AddDecreaseView.OnAddDecreaseClickListener() {
            @Override
            public void add(int num) {
                listBean.setNum(num);
                if (addCreateClickListener != null) {
                    addCreateClickListener.onChange(position, num);
                }
            }
            @Override
            public void decrease(int num) {
                listBean.setNum(num);
                if (addCreateClickListener != null) {
                    addCreateClickListener.onChange(position, num);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.cb_product)
        CheckBox cbProduct;
        @BindView(R.id.img_product)
        SimpleDraweeView imgProduct;
        @BindView(R.id.adv_product)
        AddDecreaseView advProduct;
        @BindView(R.id.txt_product_name)
        TextView txtProductName;
        @BindView(R.id.txt_single_price)
        TextView txtSinglePrice;
        public Holder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }

}

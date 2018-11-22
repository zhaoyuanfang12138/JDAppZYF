package com.example.lenovo.jdappzyf.first.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.jdappzyf.R;
import com.example.lenovo.jdappzyf.first.bean.Product;
import com.example.lenovo.jdappzyf.utils.ImgUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/11/8.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Holder> {

    //接口回调
 public interface OnItemClickListener{
        void OnClick(int position,View view);
    }

    private OnItemClickListener listener;

 public void setItemClickListener(OnItemClickListener listener){
     this.listener = listener;
 }


    private Context context;
    private List<Product.DataBean.ListBean> list;

    public ProductAdapter(Context context, List<Product.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.first_item_product, null);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
            holder.txtTitle.setText(list.get(position).getTitle());
            holder.imgProduct.setImageURI(Uri.parse(ImgUtils.getImg(list.get(position).getImages())));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.OnClick(position,view);
                }
            });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_product)
        SimpleDraweeView imgProduct;
        @BindView(R.id.txt_title)
        TextView txtTitle;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}

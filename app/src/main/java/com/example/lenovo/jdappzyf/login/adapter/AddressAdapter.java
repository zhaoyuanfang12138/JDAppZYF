package com.example.lenovo.jdappzyf.login.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.jdappzyf.R;
import com.example.lenovo.jdappzyf.login.bean.AddreseeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/11/19.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.Holder> {


    private Context context;
    private List<AddreseeBean.DataBean> list;

    public AddressAdapter(Context context, List<AddreseeBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = View.inflate(context, R.layout.item_address, null);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.txtName.setText(list.get(position).getName());
        holder.txtMobile.setText(String.valueOf(list.get(position).getMobile()));
        holder.txtAddr.setText(list.get(position).getAddr());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_mobile)
        TextView txtMobile;
        @BindView(R.id.txt_addr)
        TextView txtAddr;
        @BindView(R.id.img_upd)
        ImageView imgUpd;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

package com.example.lenovo.jdappzyf.sort.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.jdappzyf.R;
import com.example.lenovo.jdappzyf.sort.bean.DetailBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/11/11.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.Holder> {


    private Context context;
    private List<DetailBean.DataBean> list;

    public DetailAdapter(Context context, List<DetailBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_detail, null);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        holder.txtTitles.setText(list.get(position).getTitle());
        holder.img.setImageURI(Uri.parse(list.get(position).getImages()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        SimpleDraweeView img;
        @BindView(R.id.txt_titles)
        TextView txtTitles;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

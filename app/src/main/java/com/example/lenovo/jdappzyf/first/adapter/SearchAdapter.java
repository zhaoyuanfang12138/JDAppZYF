package com.example.lenovo.jdappzyf.first.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.jdappzyf.R;
import com.example.lenovo.jdappzyf.first.bean.SearchPage;
import com.example.lenovo.jdappzyf.utils.ImgUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/11/15.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.Holder> {

    public interface onItemClickListener {
        void onClick(int position, View v);
    }

    private onItemClickListener itemClickListener;

    public void setItemClickListener(onItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }



    private Context context;
    private List<SearchPage.DataBean> list;

    public SearchAdapter(Context context, List<SearchPage.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = View.inflate(context, R.layout.adapter_search, null);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        holder.imgLogo.setImageURI(Uri.parse(ImgUtils.getImg(list.get(position).getImages())));

        holder.txtTitle.setText(list.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null){
                    itemClickListener.onClick(position,view);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_logo)
        SimpleDraweeView imgLogo;
        @BindView(R.id.txt_title)
        TextView txtTitle;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

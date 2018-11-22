package com.example.lenovo.jdappzyf.sort.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.jdappzyf.R;
import com.example.lenovo.jdappzyf.sort.bean.CataRight;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/11/10.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {


    //接口回调
    public interface OnChildClickListener{
        void OnChildClickListener(int posotion, View v);
    }

    private OnChildClickListener listener;

    public void setChildClickListener(OnChildClickListener listener){
        this.listener=listener;
    }



    private Context context;
    private List<CataRight.DataBean.ListBean> lists;

    public MyAdapter(Context context, List<CataRight.DataBean.ListBean> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = View.inflate(context, R.layout.sort_right_child, null);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        holder.txtName.setText(lists.get(position).getName());
        holder.imgLogo.setImageURI(Uri.parse(lists.get(position).getIcon()));
     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             if (listener != null){
                 listener.OnChildClickListener(position,view);
             }
         }
     });

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_logo)
        SimpleDraweeView imgLogo;
        @BindView(R.id.txt_name)
        TextView txtName;
        public Holder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}

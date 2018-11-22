package com.example.lenovo.jdappzyf.sort.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.jdappzyf.R;
import com.example.lenovo.jdappzyf.sort.bean.CataLeft;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/11/9.
 */

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.Holder> {


    //接口回调
    public interface OnItemClickListener{
        void OnClick(View itemView, int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }




    private Context context;
    private List<CataLeft.DataBean> list;

    public LeftAdapter(Context context, List<CataLeft.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.sort_left, null);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
       holder.txtTitle.setText(list.get(position).getName());

       holder.txtTitle.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               listener.OnClick(view,position);
           }
       });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_title)
        TextView txtTitle;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

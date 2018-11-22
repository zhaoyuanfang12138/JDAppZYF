package com.example.lenovo.jdappzyf.sort.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.jdappzyf.R;
import com.example.lenovo.jdappzyf.sort.bean.CataRight;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/11/10.
 */

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.Holder> {

    private MyAdapter.OnChildClickListener listener;

    public void setChildClickListener(MyAdapter.OnChildClickListener listener){
           this.listener = listener;

    }



    private Context context;
    private List<CataRight.DataBean> catalist;

    public RightAdapter(Context context, List<CataRight.DataBean> catalist) {
        this.context = context;
        this.catalist = catalist;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.sort_right, null);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        List<CataRight.DataBean.ListBean> lists = this.catalist.get(position).getList();
         MyAdapter adapter = new MyAdapter(context,lists);

        GridLayoutManager layoutManager = new GridLayoutManager(context,5);

        holder.sortRvRight.setLayoutManager(layoutManager);

       if(listener!=null){
           adapter.setChildClickListener(listener);
       }

        holder.sortRvRight.setAdapter(adapter);



    }

    @Override
    public int getItemCount() {
        return catalist.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.sort_rv_right)
        RecyclerView sortRvRight;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}

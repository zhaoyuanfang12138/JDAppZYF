package com.example.lenovo.jdappzyf.first.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.jdappzyf.R;
import com.example.lenovo.jdappzyf.first.bean.Sudoku;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/11/8.
 */

public class SudokuAdapter extends RecyclerView.Adapter<SudokuAdapter.Holder> {


    private Context context;
    private List<Sudoku.DataBean> data;

    public SudokuAdapter(Context context, List<Sudoku.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_sudoku, null);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        holder.imgSudoku.setImageURI(Uri.parse(data.get(position).getIcon()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_sudoku)
        SimpleDraweeView imgSudoku;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

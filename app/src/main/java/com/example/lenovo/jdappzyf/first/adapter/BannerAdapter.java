package com.example.lenovo.jdappzyf.first.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lenovo.jdappzyf.first.bean.CarouselBean;
import com.example.lenovo.jdappzyf.utils.ImgUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by lenovo on 2018/11/7.
 */

public class BannerAdapter extends PagerAdapter{

    private Context context;
    private List<CarouselBean.DataBean> list;

    public BannerAdapter(Context context, List<CarouselBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SimpleDraweeView img=new SimpleDraweeView(context);
        Log.i("2134545", "instantiateItem: "+list.get(position).getIcon());
        img.setImageURI(Uri.parse(ImgUtils.getImg(list.get(position).getIcon())));
        container.addView(img);
        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);

    }
}

package com.sunmeng.myapplication.adapter;

import android.app.Activity;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzhiyun on 2017-01-07.
 */
public class CarouselAdapter extends PagerAdapter{

    //日志，用于调试
    private static final String LOG = "NEWS_LOG";

    private Activity context ;
    private List<Carousel> diagrams ;
    private List<SimpleDraweeView> images = new ArrayList<SimpleDraweeView>();

    public CarouselAdapter(Activity context, List<Carousel> diagrams){
        this.context = context;
        if (diagrams == null || diagrams.size() == 0) {
            this.diagrams = new ArrayList<>();
        } else {
            this.diagrams = diagrams;
        }

        for (int i = 0; i < diagrams.size(); i++) {
            SimpleDraweeView image = new SimpleDraweeView(context);
            Uri uri = Uri.parse(diagrams.get(i).getImgUrl());
            image.setImageURI(uri);
            images.add(image);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(images.get(position));
        return images.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(images.get(position));
    }

    @Override
    public int getCount() {
        return diagrams.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        Log.i(LOG, "in isViewFromObject view: " + view + " object: "
                + object + " equal: " + (view == (View) object));
        return view == (View) object;
    }
}

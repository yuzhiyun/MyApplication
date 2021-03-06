package com.sunmeng.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunmeng.myapplication.R;

import java.util.List;

/**
 * Created by yuzhiyun on 2017-01-07.
 */
public class BroadcastAdapter extends ArrayAdapter<Broadcast> {
    private int srcID;
    public BroadcastAdapter(Context context, int textViewResourceID, List<Broadcast> objects){
        super(context,textViewResourceID,objects);
        srcID = textViewResourceID ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Broadcast item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(srcID,null);
        TextView title = (TextView) view.findViewById(R.id.braodcast_item_title);
        TextView date = (TextView) view.findViewById(R.id.braodcast_item_date);
        title.setText(item.getTitle());
        date.setText(item.getDate());
        //这个条目用于登陆时学校的选择和成绩查询时考试的选择，所以有设置默认图标和指定图标之分
        int id = item.getSrcID() ;
        if(id != 0){
            ImageView img = (ImageView)view.findViewById(R.id.braodcast_item_img);
            img.setImageResource(id);
        }
        return view;
    }
}

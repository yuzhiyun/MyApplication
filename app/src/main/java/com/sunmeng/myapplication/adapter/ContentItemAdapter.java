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
 * Created by yuzhiyun on 2016-12-18.
 */
public class ContentItemAdapter extends ArrayAdapter<ContentItem> {

    private int srcID;

    public ContentItemAdapter(Context context, int textViewResourceID, List<ContentItem> objects){
        super(context,textViewResourceID,objects);
        srcID = textViewResourceID ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContentItem item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(srcID,null);
        ImageView image = (ImageView) view.findViewById(R.id.content_item_img);
        TextView title = (TextView)view.findViewById(R.id.content_item_title);
        TextView time = (TextView)view.findViewById(R.id.content_item_time);
        TextView author = (TextView)view.findViewById(R.id.content_item_author);
        image.setImageResource(item.getImgID());
        title.setText(item.getTitle());
        time.setText(item.getTime());
        author.setText(item.getAuthor());
        return view;
    }
}

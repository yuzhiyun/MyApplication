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
 * Created by yuzhiyun on 2016-12-11.
 */
public class ChatItemAdapter extends ArrayAdapter<ChatItem> {
    private int srcID;
    public ChatItemAdapter(Context context, int textViewResourceID, List<ChatItem> objects){
        super(context,textViewResourceID,objects);
        srcID = textViewResourceID ;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatItem item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(srcID,null);
        ImageView image = (ImageView) view.findViewById(R.id.chat_item_img);
        TextView title = (TextView) view.findViewById(R.id.chat_item_title);
        TextView time = (TextView) view.findViewById(R.id.chat_item_time);
        TextView area = (TextView) view.findViewById(R.id.chat_item_area);
        TextView src = (TextView) view.findViewById(R.id.chat_item_src);
        image.setImageResource(item.getImgID());
        title.setText(item.getTitle());
        time.setText(item.getTime());
        area.setText(item.getArea());
        src.setText(item.getSrc());
        return view;
    }
}

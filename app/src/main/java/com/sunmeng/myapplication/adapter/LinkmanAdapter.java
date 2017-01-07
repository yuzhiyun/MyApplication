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
public class LinkmanAdapter extends ArrayAdapter<LinkmanItem> {
    private int srcID;

    public LinkmanAdapter(Context context, int textViewResourceID, List<LinkmanItem> objects){
        super(context,textViewResourceID,objects);
        srcID = textViewResourceID ;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinkmanItem item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(srcID,null);
        ImageView image = (ImageView) view.findViewById(R.id.linkman_item_img);
        TextView name = (TextView)view.findViewById(R.id.linkman_item_name);
        TextView descrp = (TextView)view.findViewById(R.id.linkman_item_discrp);
        image.setImageResource(item.getImgID());
        name.setText(item.getName());
        descrp.setText(item.getDscrp());
        return view;
    }
}

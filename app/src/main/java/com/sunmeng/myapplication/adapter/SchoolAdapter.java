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
 * Created by yuzhiyun on 2017-01-09.
 */
public class SchoolAdapter extends ArrayAdapter<School> {
    private int srcID;
    public SchoolAdapter(Context context, int textViewResourceID, List<School> objects){
        super(context,textViewResourceID,objects);
        srcID = textViewResourceID ;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        School item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(srcID,null);
        TextView  name = (TextView) view.findViewById(R.id.school_name);
        name.setText(item.getName());
        return view;
    }
}

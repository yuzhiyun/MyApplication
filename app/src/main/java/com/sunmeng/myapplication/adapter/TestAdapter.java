package com.sunmeng.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sunmeng.myapplication.R;

import java.util.List;

/**
 * Created by yuzhiyun on 2017-01-09.
 */
public class TestAdapter extends ArrayAdapter<TestItem> {
    private int srcID;
    public TestAdapter(Context context, int textViewResourceID, List<TestItem> objects){
        super(context,textViewResourceID,objects);
        srcID = textViewResourceID ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TestItem item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(srcID,null);
        TextView title = (TextView) view.findViewById(R.id.test_item_title);
        TextView money = (TextView) view.findViewById(R.id.test_item_money);
        TextView number = (TextView) view.findViewById(R.id.test_item_person_num);
        title.setText(item.getTitle());
        money.setText(item.getMoney());
        number.setText(item.getNumber());
        return view;
    }
}

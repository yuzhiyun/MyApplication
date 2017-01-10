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
 * Created by yuzhiyun on 2017-01-10.
 */
public class QuestionItemAdapter extends ArrayAdapter<QuestionItem> {
    private int srcID;
    public QuestionItemAdapter(Context context, int textViewResourceID, List<QuestionItem> objects){
        super(context,textViewResourceID,objects);
        srcID = textViewResourceID ;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        QuestionItem item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(srcID,null);
        ImageView image = (ImageView) view.findViewById(R.id.img_per_question_number);
        TextView content = (TextView) view.findViewById(R.id.per_question_content);
        image.setImageResource(item.getImgID());
        content.setText(item.getContent());
        return view;
    }
}

package com.sunmeng.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sunmeng.myapplication.MainActivity;
import com.sunmeng.myapplication.R;
import com.sunmeng.myapplication.adapter.ChatItem;
import com.sunmeng.myapplication.adapter.ChatItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class chatFragment extends Fragment {

    private List<ChatItem> items = new ArrayList<ChatItem>();

    public chatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        super.onCreateView(inflater,container,savedInstanceState);

        return inflater.inflate(R.layout.fragment_chat, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        init();
        MainActivity activity = (MainActivity) getActivity();
        ChatItemAdapter adapter = new ChatItemAdapter(activity.context ,R.layout.chat_item , items);
        ListView listView = (ListView)this.getView().findViewById(R.id.broadcast);
        listView.setAdapter(adapter);
    }

    public void init(){
        ChatItem item1 = new ChatItem(R.drawable.chat1,"2017全国大学生英语竞赛",
                "2016-12-07 17:20","高等学校大学外语教学指导委员会","全国");
        items.add(item1);
        items.add(item1);
        items.add(item1);
        items.add(item1);
        items.add(item1);
        items.add(item1);
        items.add(item1);
        items.add(item1);
        items.add(item1);
        items.add(item1);
    }


}

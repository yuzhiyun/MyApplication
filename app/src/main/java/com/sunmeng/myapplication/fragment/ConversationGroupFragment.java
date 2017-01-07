package com.sunmeng.myapplication.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sunmeng.myapplication.MainActivity;
import com.sunmeng.myapplication.R;
import com.sunmeng.myapplication.adapter.LinkmanAdapter;
import com.sunmeng.myapplication.adapter.LinkmanItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversationGroupFragment extends Fragment {


    private List<LinkmanItem> groupItems = new ArrayList<LinkmanItem>();
    private ListView listView;
    private View view;

    public ConversationGroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view =  inflater.inflate(R.layout.fragment_conversation_group, container, false);
        listView = (ListView)view.findViewById(R.id.group_list);
        showGroupMsgList();
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getActivity(),"成功",Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }

    public void showGroupMsgList(){
        initGroupList();
        MainActivity activity = (MainActivity) getActivity();
        //activity.getApplicationContext()才可以，但是activity.context是空值
        LinkmanAdapter adapter = new LinkmanAdapter(activity.getApplicationContext().getApplicationContext() ,R.layout.group_item , groupItems);
        listView.setAdapter(adapter);
    }

    public void initGroupList(){
        LinkmanItem item1 = new LinkmanItem(R.drawable.chat1,"孙萌", "萌萌家长");
        groupItems.add(item1);
        groupItems.add(item1);
        groupItems.add(item1);
        groupItems.add(item1);
        groupItems.add(item1);
        groupItems.add(item1);
        groupItems.add(item1);
        groupItems.add(item1);
        groupItems.add(item1);
        groupItems.add(item1);
        groupItems.add(item1);
        groupItems.add(item1);
        groupItems.add(item1);
        groupItems.add(item1);
        groupItems.add(item1);
    }
}

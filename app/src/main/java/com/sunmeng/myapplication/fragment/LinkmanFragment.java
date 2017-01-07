package com.sunmeng.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sunmeng.myapplication.MainActivity;
import com.sunmeng.myapplication.R;
import com.sunmeng.myapplication.adapter.LinkmanAdapter;
import com.sunmeng.myapplication.adapter.LinkmanItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LinkmanFragment extends Fragment {

    private List<LinkmanItem> linkmanItems = new ArrayList<LinkmanItem>();
    private ListView listView;
    private View view ;

    public LinkmanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view =  inflater.inflate(R.layout.fragment_linkman, container, false);
        showLinkmanList();
        return view;
    }

    public void showLinkmanList(){
        initLinknameList();
        MainActivity activity = (MainActivity) getActivity();
        //activity.getApplicationContext()才可以，但是activity.context是空值
        LinkmanAdapter adapter = new LinkmanAdapter(activity.getApplicationContext().getApplicationContext() ,R.layout.linkmain_item , linkmanItems);
        listView = (ListView)view.findViewById(R.id.linkman);
        listView.setAdapter(adapter);
    }

    public void initLinknameList(){
        LinkmanItem item1 = new LinkmanItem(R.drawable.chat1,"孙萌", "萌萌家长");
        linkmanItems.add(item1);
        linkmanItems.add(item1);
        linkmanItems.add(item1);
        linkmanItems.add(item1);
        linkmanItems.add(item1);
        linkmanItems.add(item1);
        linkmanItems.add(item1);
        linkmanItems.add(item1);
        linkmanItems.add(item1);
        linkmanItems.add(item1);
        linkmanItems.add(item1);
        linkmanItems.add(item1);
        linkmanItems.add(item1);
        linkmanItems.add(item1);
        linkmanItems.add(item1);
        linkmanItems.add(item1);
        linkmanItems.add(item1);
    }
}

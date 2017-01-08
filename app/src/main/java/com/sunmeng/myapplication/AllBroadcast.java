package com.sunmeng.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.sunmeng.myapplication.adapter.Broadcast;
import com.sunmeng.myapplication.adapter.BroadcastAdapter;

import java.util.ArrayList;
import java.util.List;

public class AllBroadcast extends AppCompatActivity {

    private List<Broadcast> brodcastItems = new ArrayList<Broadcast>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        showBroadcastList();
    }

    public void showBroadcastList(){
        initBroadcastList();
        //activity.getApplicationContext()才可以，但是activity.context是空值
        BroadcastAdapter adapter = new BroadcastAdapter(this.getApplicationContext() ,R.layout.broadcast_item ,brodcastItems );
        listView = (ListView)this.findViewById(R.id.all_broadcast);
        listView.setAdapter(adapter);
    }

    public void initBroadcastList(){
        Broadcast item1 = new Broadcast("明天运动会", "2016-12-12");
        brodcastItems.add(item1);
        brodcastItems.add(item1);
        brodcastItems.add(item1);
    }
}

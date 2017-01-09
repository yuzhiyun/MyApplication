package com.sunmeng.myapplication;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sunmeng.myapplication.adapter.School;
import com.sunmeng.myapplication.adapter.SchoolAdapter;

import java.util.ArrayList;
import java.util.List;

public class AllSchool extends NewBaseActivity implements AdapterView.OnItemClickListener {

    private ListView listView ;
    private List<School> schoolItems = new ArrayList<School>();

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_all_school);
    }

    @Override
    protected void findView() {
        listView = (ListView)findViewById(R.id.school_list);
    }

    @Override
    protected void setListener() {
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void initOther() {
        showSchoolList();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(new Intent(AllSchool.this , Login.class));
    }

    public void showSchoolList(){
        initSchoolList();
        //activity.getApplicationContext()才可以，但是activity.context是空值
        SchoolAdapter adapter = new SchoolAdapter(this.getApplicationContext() ,R.layout.school_item , schoolItems);
        listView = (ListView)findViewById(R.id.school_list);
        listView.setAdapter(adapter);
    }

    public void initSchoolList(){
        School item1 = new School( "中南大学铁道学院");
        schoolItems.add(item1);
        schoolItems.add(item1);
        schoolItems.add(item1);
        schoolItems.add(item1);
        schoolItems.add(item1);
        schoolItems.add(item1);
        schoolItems.add(item1);
        schoolItems.add(item1);
        schoolItems.add(item1);
        schoolItems.add(item1);
        schoolItems.add(item1);

    }
}

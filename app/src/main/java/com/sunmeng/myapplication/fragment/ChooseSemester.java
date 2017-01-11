package com.sunmeng.myapplication.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sunmeng.myapplication.R;
import com.sunmeng.myapplication.adapter.Broadcast;
import com.sunmeng.myapplication.adapter.BroadcastAdapter;
import com.sunmeng.myapplication.adapter.School;
import com.sunmeng.myapplication.adapter.SchoolAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseSemester extends BaseFragment implements View.OnClickListener,AdapterView.OnItemClickListener,AdapterView.OnItemSelectedListener{

    private Spinner spYear ;
    private Spinner spSemester ;

    private Button clickOk ;

    private TextView checkDiagram ;

    private ListView listView ;

    private List<Broadcast> examItems = new ArrayList<Broadcast>();
    private List<String>  years = new ArrayList<String>();
    private List<String> semesters = new ArrayList<String>();
    private ArrayAdapter<String> adapterYear;
    private ArrayAdapter<String> adapterSemester;

    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void setActivity() {
        activity = getActivity();
    }

    @Override
    protected void setLayoutId() {
        layout_id = R.layout.fragment_choose_semester ;
    }

    @Override
    protected void findView(View view) {
        spYear = (Spinner)view.findViewById(R.id.spinner_year);
        spSemester = (Spinner)view.findViewById(R.id.spinner_semester);

        clickOk = (Button)view.findViewById(R.id.semester_ok);

        checkDiagram = (TextView)view.findViewById(R.id.tv_check_diagram);

        listView = (ListView)view.findViewById(R.id.exam_list);
    }

    @Override
    protected void setListener() {
        spYear.setOnItemSelectedListener(this);
        spSemester.setOnItemSelectedListener(this);

        clickOk.setOnClickListener(this);
        checkDiagram.setOnClickListener(this);

        listView.setOnItemClickListener(this);
    }

    @Override
    protected void initOther() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setAdapter() {
        initYears();
        initSemesters();
        //将可选内容与适配器关联
        adapterYear=new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item,years);
        //设置下拉列表的风格
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //添加适配器到spinner控件中
        spYear.setAdapter(adapterYear);

        adapterSemester=new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item,semesters);
        adapterSemester.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSemester.setAdapter(adapterSemester);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.semester_ok:
                //打开进入图表的接口，这里还要判定是否成功
                checkDiagram.setText("查看成绩曲线图");
                //返回具体的查询信息
                showExamList();
                Toast.makeText(activity,"点击确认",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_check_diagram:
                Toast.makeText(activity,"折线图",Toast.LENGTH_SHORT).show();
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.fragment_check_grade,new ShowDiagram());
                ft.commit();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.exam_list:
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.fragment_check_grade,new ShowDetail());
                ft.commit();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.spinner_year:
                Toast.makeText(activity,years.get(i),Toast.LENGTH_SHORT).show();
                adapterView.setVisibility(View.VISIBLE);
                break;
            case R.id.spinner_semester:
                Toast.makeText(activity,semesters.get(i),Toast.LENGTH_SHORT).show();
                adapterView.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void initYears(){
        years.add("2013学年-2014学年");
        years.add("2014学年-2015学年");
        years.add("2015学年-2016学年");
        years.add("2016学年-2017学年");
    }

    private void initSemesters(){
        semesters.add("上下学期");
        semesters.add("上学期");
        semesters.add("下学期");
    }

    private void showExamList(){
        initExamList();
        BroadcastAdapter adapter = new BroadcastAdapter(this.getContext() ,R.layout.broadcast_item , examItems);
        listView.setAdapter(adapter);
    }

    private void initExamList(){
        Broadcast item1 = new Broadcast(R.drawable.exam, "第一次月考","2016-1-1");
        examItems.add(item1);
        examItems.add(item1);
        examItems.add(item1);
        examItems.add(item1);
        examItems.add(item1);
        examItems.add(item1);
        examItems.add(item1);
        examItems.add(item1);

    }
}

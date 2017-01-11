package com.sunmeng.myapplication.fragment;



import android.support.v4.app.Fragment;

import android.view.View;

import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sunmeng.myapplication.R;
import com.sunmeng.myapplication.adapter.GradeTableAdapter;
import com.sunmeng.myapplication.adapter.GradeTableAdapter.TableCell;
import com.sunmeng.myapplication.adapter.GradeTableAdapter.TableRow;
import com.sunmeng.myapplication.adapter.SchoolAdapter;

import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowDetail extends BaseFragment {

    private ArrayList<TableRow> table = new ArrayList<TableRow>();
    //设置每行有4个单元格
    private GradeTableAdapter.TableCell[] titles = new GradeTableAdapter.TableCell[4];
    private int width ;

    private ListView listView ;

    public ShowDetail() {
        // Required empty public constructor
    }

    @Override
    protected void setActivity() {
        activity = getActivity();
        width = activity.getWindowManager().getDefaultDisplay().getWidth()/titles.length;
    }

    @Override
    protected void setLayoutId() {
        layout_id = R.layout.fragment_show_detail ;
    }

    @Override
    protected void findView(View view) {
        listView = (ListView)view.findViewById(R.id.grade_list);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initOther() {

    }

    @Override
    protected void initView() {
        initTableHead();
        initTableData();
    }

    @Override
    protected void setAdapter() {

    }

    private void initTableHead(){
        // 定义标题
        titles[0] = new TableCell("学科",
                width-50,
                LayoutParams.FILL_PARENT,
                TableCell.STRING);
        titles[1] = new TableCell("成绩",
                width-50,
                LayoutParams.FILL_PARENT,
                TableCell.STRING);
        titles[2] = new TableCell("班均分/班排名",
                width+50,
                LayoutParams.FILL_PARENT,
                TableCell.STRING);
        titles[3] = new TableCell("校均分/校排名",
                width+50,
                LayoutParams.FILL_PARENT,
                TableCell.STRING);
        //将表头添加进去
        table.add(new TableRow(titles));
    }

    private void initTableData(){
        // 每行的数据
        TableCell[] cells = new TableCell[4];// 每行4个单元
        for (int i = 0; i < cells.length ; i++) {
            cells[i] = new TableCell("No." + String.valueOf(i),
                    titles[i].width,
                    LayoutParams.FILL_PARENT,
                    TableCell.STRING);
        }

        // 把表格的行添加到表格
        for (int i = 0; i < 12; i++)
            table.add(new TableRow(cells));
        GradeTableAdapter tableAdapter = new GradeTableAdapter(activity, table);
        listView.setAdapter(tableAdapter);
        listView.setOnItemClickListener(new ItemClickEvent());
    }

    class ItemClickEvent implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            Toast.makeText(activity, "选中第"+String.valueOf(arg2)+"行", Toast.LENGTH_SHORT).show();
        }
    }
}



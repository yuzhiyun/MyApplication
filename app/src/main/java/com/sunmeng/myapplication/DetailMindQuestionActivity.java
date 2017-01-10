package com.sunmeng.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sunmeng.myapplication.adapter.QuestionItem;
import com.sunmeng.myapplication.adapter.QuestionItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class DetailMindQuestionActivity extends NewBaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener {

    private ImageView img_pre ;
    private ImageView img_next ;

    private TextView tv_pre ;
    private TextView tv_next ;

    private ListView lv_question ;

    private TextView test_title;
    private TextView test_current_num ;
    private TextView test_num ;
    private int testNum = 1 ;
    private TextView question_content ;

    private List<QuestionItem> questionItems = new ArrayList<QuestionItem>();

    private int[] imgSources = new int[]{R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h};
    private int[] imgPresses = new int[]{R.drawable.a_press,R.drawable.b_press,R.drawable.c_press,R.drawable.d_press,R.drawable.e_press,R.drawable.f_press,R.drawable.g_press,R.drawable.h_press};

    @Override
    protected void setLayoutView(){
        setContentView(R.layout.activity_detail_mind_question);
    }

    @Override
    protected void findView() {
        img_pre = (ImageView)findViewById(R.id.pre_question);
        img_next = (ImageView)findViewById(R.id.next_question);

        tv_pre = (TextView)findViewById(R.id.tv_pre);
        tv_next = (TextView)findViewById(R.id.tv_next);

        lv_question = (ListView)findViewById(R.id.lv_detail_question);

        test_title = (TextView) findViewById(R.id.detail_mind_title);
        test_current_num = (TextView) findViewById(R.id.detail_question_current_number);
        test_num = (TextView) findViewById(R.id.detail_question_number);
        question_content = (TextView) findViewById(R.id.detail_question_title);

    }

    @Override
    protected void setListener() {
        img_pre.setOnClickListener(this);
        img_next.setOnClickListener(this);
        tv_pre.setOnClickListener(this);
        tv_next.setOnClickListener(this);

        lv_question.setOnItemClickListener(this);
    }

    @Override
    protected void initOther() {
        initConcreteInfo("数组专项练习","10");
        question_content.setText("线性表是具有n个的");
        test_current_num.setText("1");
        showQuestion();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.next_question :
                testNum ++ ;
                question_content.setText("下一个题目");
                test_current_num.setText(testNum+"");
                //另外一个题目的相关答案
                showQuestion();
                //此处还要有一个判断，判定是否到了最后一题
                break;
            case R.id.pre_question :
                testNum -- ;
                question_content.setText("上一个题目");
                test_current_num.setText(testNum+"");
                //另外一个题目的相关答案
                showQuestion();
                //此处还要有一个判断，判定是否是第一题
                break;
            case R.id.tv_next :
                testNum ++ ;
                question_content.setText("下一个题目");
                test_current_num.setText(testNum+"");
                //另外一个题目的相关答案
                showQuestion();
                //此处还要有一个判断，判定是否到了最后一题
                break;
            case R.id.tv_pre :
                testNum -- ;
                question_content.setText("上一个题目");
                test_current_num.setText(testNum+"");
                //另外一个题目的相关答案
                showQuestion();
                //此处还要有一个判断，判定是否是第一题
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ImageView imgNum = (ImageView) view.findViewById(R.id.img_per_question_number);
        imgNum.setImageResource(imgPresses[i]);
    }

    private void initConcreteInfo(String title , String number ){
        test_title.setText(title);
        test_num.setText("/"+number);
    }

    private void showQuestion(){
        initQuestionItems(5);
        QuestionItemAdapter adapter = new QuestionItemAdapter(this.getApplicationContext() ,R.layout.template_mind_question , questionItems);
        lv_question.setAdapter(adapter);
    }

    private void initQuestionItems(int answer_num){
        QuestionItem item ;
        for(int i = 0 ; i < answer_num ; i ++){
            item  = new QuestionItem(imgSources[i],"表元素");
            questionItems.add(item);
        }

    }
}

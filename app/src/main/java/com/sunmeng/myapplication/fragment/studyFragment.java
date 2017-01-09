package com.sunmeng.myapplication.fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sunmeng.myapplication.MainActivity;
import com.sunmeng.myapplication.R;
import com.sunmeng.myapplication.adapter.ChatItem;
import com.sunmeng.myapplication.adapter.ChatItemAdapter;
import com.sunmeng.myapplication.adapter.ContentItem;
import com.sunmeng.myapplication.adapter.ContentItemAdapter;
import com.sunmeng.myapplication.adapter.StudyPgAdapter;
import com.sunmeng.myapplication.adapter.TestAdapter;
import com.sunmeng.myapplication.adapter.TestItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class studyFragment extends BaseFragment implements AdapterView.OnItemClickListener,ViewPager.OnPageChangeListener,View.OnClickListener{
    private List<TestItem> testItems = new ArrayList<TestItem>();
    private List<ContentItem> knowledgeItems = new ArrayList<ContentItem>();
    private List<ChatItem> mindActivityItems = new ArrayList<ChatItem>();
    private List<ChatItem> askItems = new ArrayList<ChatItem>();

    private ViewPager viewPager;
    private ImageView imageView;
    private StudyPgAdapter adapter ;

    private List<View> viewItems = new ArrayList<View>();
    private Bitmap cursor;
    private int offSet;
    private int currentItem;
    private Matrix matrix = new Matrix();
    private int bmWidth;
    private Animation animation;

    private TextView test;
    private TextView knowledge;
    private TextView mindActivity;
    private TextView ask;

    private View view ;

    private ListView lv_test;
    private ListView lv_knowledge ;
    private ListView lv_activity;
    private ListView lv_ask ;

    @Override
    protected void setActivity() {
        activity = this.getActivity();
    }

    @Override
    protected void setLayoutId() {
        layout_id = R.layout.fragment_study ;
    }

    @Override
    protected void findView(View view) {
        test = (TextView)(view.findViewById(R.id.tv_test));
        knowledge = (TextView)(view.findViewById(R.id.tv_knowledge));
        mindActivity = (TextView)(view.findViewById(R.id.tv_mind_activity));
        ask = (TextView)(view.findViewById(R.id.tv_ask));

        imageView = (ImageView)(view.findViewById(R.id.cursor));

        viewPager = (ViewPager) (view.findViewById(R.id.vp_mind));

        viewItems.add(inflater.inflate(R.layout.content_test, null));
        viewItems.add(inflater.inflate(R.layout.content_knowledge, null));
        viewItems.add(inflater.inflate(R.layout.content_mind_activity, null));
        viewItems.add(inflater.inflate(R.layout.content_ask, null));

        lv_test = (ListView)(viewItems.get(0).findViewById(R.id.content_test)) ;
        lv_knowledge = (ListView)(viewItems.get(1).findViewById(R.id.content_knowledge));
        lv_activity = (ListView)(viewItems.get(2).findViewById(R.id.content_mind_activity));
        lv_ask = (ListView)(viewItems.get(3).findViewById(R.id.content_ask));

    }

    @Override
    protected void setListener() {
        test.setOnClickListener(this);
        knowledge.setOnClickListener(this);
        mindActivity.setOnClickListener(this);
        ask.setOnClickListener(this);

        viewPager.setOnPageChangeListener(this);

        lv_test.setOnItemClickListener(this);
        lv_knowledge.setOnItemClickListener(this);
        lv_activity.setOnItemClickListener(this);
        lv_ask.setOnItemClickListener(this);
    }

    @Override
    protected void initOther() {
        initeCursor();
    }

    @Override
    protected void initView() {
        showTestList();
        showKnowledgeList();
        showMindActivityList();
        showAskList();
    }

    @Override
    protected void setAdapter() {
        adapter = new StudyPgAdapter(viewItems);
        viewPager.setAdapter(adapter);
    }

    //这里的view的id为什么获取的是布局？？？？
    //因为这里获取的是点击组件的句柄
    //发现这个地方只有用适配器类型才能有效的判断，要不view都是studyFragment的布局
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.content_test :
                Toast.makeText(activity,"测试",Toast.LENGTH_SHORT).show();
                break;
            case R.id.content_knowledge :
                Toast.makeText(activity,"知识",Toast.LENGTH_SHORT).show();
                break;
            case R.id.content_mind_activity :
                Toast.makeText(activity,"心理活动",Toast.LENGTH_SHORT).show();
                break;
            case R.id.content_ask :
                Toast.makeText(activity,"咨询",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_test :
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv_knowledge :
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv_mind_activity :
                viewPager.setCurrentItem(2);
                break;
            case R.id.tv_ask :
                viewPager.setCurrentItem(3);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int arg0) {
        resetTextColor();
        switch (arg0) {
            case 0:
                test.setTextColor(getResources().getColor(R.color.main_color));
                if (currentItem == 1) {
                    animation = new TranslateAnimation(offSet * 2 + bmWidth, 0, 0, 0);
                }else if (currentItem == 2) {
                    animation = new TranslateAnimation(offSet * 4 + 2 * bmWidth, 0, 0, 0);
                }else if(currentItem == 3){
                    animation = new TranslateAnimation(offSet * 6 + 3 * bmWidth, 0, 0, 0);
                }
                break;
            case 1:
                knowledge.setTextColor(getResources().getColor(R.color.main_color));
                if (currentItem == 0) {
                    animation = new TranslateAnimation(0, offSet * 2 + bmWidth, 0, 0);
                }else if (currentItem == 2) {
                    animation = new TranslateAnimation(4 * offSet + 2 * bmWidth, offSet * 2 + bmWidth, 0, 0);
                }else if (currentItem == 3) {
                    animation = new TranslateAnimation(6 * offSet + 3 * bmWidth, offSet * 2 + bmWidth, 0, 0);
                }
                break;
            case 2:
                mindActivity.setTextColor(getResources().getColor(R.color.main_color));
                if (currentItem == 0) {
                    animation = new TranslateAnimation(0,   offSet* 4 + 2 * bmWidth, 0, 0);
                } else if (currentItem == 1) {
                    animation = new TranslateAnimation(offSet * 2 + bmWidth,  offSet* 4 + 2 * bmWidth, 0, 0);
                }else if (currentItem == 3) {
                    animation = new TranslateAnimation(offSet * 6 + bmWidth * 3,  offSet* 4 + 2 * bmWidth, 0, 0);
                }
                break;
            case 3:
                ask.setTextColor(getResources().getColor(R.color.main_color));
                if (currentItem == 0) {
                    animation = new TranslateAnimation(0, offSet * 6 + 3 * bmWidth, 0, 0);
                } else if (currentItem == 1) {
                    animation = new TranslateAnimation(offSet * 2 + bmWidth,  offSet* 6 + 3 * bmWidth, 0, 0);
                }else if (currentItem == 2) {
                    animation = new TranslateAnimation(offSet * 4 + bmWidth * 2,  offSet* 6 + 3 * bmWidth, 0, 0);
                }
                break;
        }
        currentItem = arg0;
        animation.setDuration(150); // 光标滑动速度
        animation.setFillAfter(true);
        imageView.startAnimation(animation);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    private void initeCursor() {
        cursor = BitmapFactory.decodeResource(getResources(), R.drawable.cursor);
        bmWidth = cursor.getWidth();

        DisplayMetrics dm = getResources().getDisplayMetrics();
        // 设置图标的起始位置(4个textview。)
        offSet = (dm.widthPixels - 4 * bmWidth) / 8;
        matrix.setTranslate(offSet, 0);
        test.setTextColor(getResources().getColor(R.color.main_color));
        imageView.setImageMatrix(matrix); // 需要imageView的scaleType为matrix
        currentItem = 0;
    }

    public void resetTextColor(){
        test.setTextColor(getResources().getColor(R.color.black));
        knowledge.setTextColor(getResources().getColor(R.color.black));
        mindActivity.setTextColor(getResources().getColor(R.color.black));
        ask.setTextColor(getResources().getColor(R.color.black));
    }

    public void showTestList(){
        initTestList();
        MainActivity activity = (MainActivity) getActivity();
        TestAdapter adapter = new TestAdapter(activity.getApplicationContext(),R.layout.test_item,testItems);
        lv_test.setAdapter(adapter);
    }

    public void showKnowledgeList(){
        initKnowledgeList();
        MainActivity activity = (MainActivity) getActivity();
        ContentItemAdapter adapter = new ContentItemAdapter(activity.getApplicationContext() ,R.layout.content_item , knowledgeItems);
        lv_knowledge.setAdapter(adapter);
    }

    public void showMindActivityList(){
        initMindAcyivityList();
        MainActivity activity = (MainActivity) getActivity();
        ChatItemAdapter adapter = new ChatItemAdapter(activity.getApplicationContext() ,R.layout.chat_item , mindActivityItems);
        lv_activity.setAdapter(adapter);
    }

    public void showAskList(){
        initAskList();
    }

    public void initTestList(){
        TestItem item1 = new TestItem(R.drawable.chat1,"霍兰德职业倾向测试", "￥600","1001");
        testItems.add(item1);
        testItems.add(item1);
        testItems.add(item1);
        testItems.add(item1);
        testItems.add(item1);
        testItems.add(item1);
    }

    public void initKnowledgeList(){
        ContentItem item1 = new ContentItem(R.drawable.chat1,"格林童话背后的智慧", "2015-11-11","颖儿老师");
        knowledgeItems.add(item1);
        knowledgeItems.add(item1);
        knowledgeItems.add(item1);
        knowledgeItems.add(item1);
        knowledgeItems.add(item1);
        knowledgeItems.add(item1);
        knowledgeItems.add(item1);
        knowledgeItems.add(item1);
        knowledgeItems.add(item1);
        knowledgeItems.add(item1);
        knowledgeItems.add(item1);
        knowledgeItems.add(item1);
        knowledgeItems.add(item1);
        knowledgeItems.add(item1);
        knowledgeItems.add(item1);
    }

    public void initMindAcyivityList(){
        ChatItem item1 = new ChatItem(R.drawable.chat1,"长沙第一次岳麓活动", "中南大学校团委","铁道学院","2016-1-1");
        mindActivityItems.add(item1);
        mindActivityItems.add(item1);
        mindActivityItems.add(item1);
        mindActivityItems.add(item1);
        mindActivityItems.add(item1);
        mindActivityItems.add(item1);
        mindActivityItems.add(item1);
        mindActivityItems.add(item1);
        mindActivityItems.add(item1);
    }

    public void initAskList(){

    }
}

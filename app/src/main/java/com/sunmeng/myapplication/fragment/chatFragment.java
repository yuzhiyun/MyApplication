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
import com.sunmeng.myapplication.adapter.ChatPgAdapter;
import com.sunmeng.myapplication.adapter.ContentItem;
import com.sunmeng.myapplication.adapter.ContentItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class chatFragment extends BaseFragment implements AdapterView.OnItemClickListener,ViewPager.OnPageChangeListener,View.OnClickListener {

    private List<ChatItem> activityItems = new ArrayList<ChatItem>();
    private List<ContentItem> studyItems = new ArrayList<ContentItem>();

    private TextView tvChat ;
    private TextView tvStudy ;

    private ViewPager viewPager;
    private ImageView imageView;
    private ChatPgAdapter adapter ;

    private List<View> viewItems = new ArrayList<View>();
    private Bitmap cursor;
    private int offSet;
    private int currentItem;
    private Matrix matrix = new Matrix();
    private int bmWidth;
    private Animation animation;

    private View view ;

    private ListView lv_chat;
    private ListView lv_study ;

    @Override
    protected void setActivity() {
        activity = this.getActivity();
    }

    @Override
    protected void setLayoutId() {
        layout_id = R.layout.fragment_chat ;
    }

    @Override
    protected void findView(View view) {
        tvChat = (TextView)(view.findViewById(R.id.tv_chat));
        tvStudy = (TextView)(view.findViewById(R.id.tv_study));

        imageView = (ImageView)(view.findViewById(R.id.cursor1));

        viewPager = (ViewPager) (view.findViewById(R.id.vp_chat));

        viewItems.add(inflater.inflate(R.layout.content_activity, null));
        viewItems.add(inflater.inflate(R.layout.content_study, null));

        lv_chat =  (ListView)(viewItems.get(0).findViewById(R.id.content_chat_activity)) ;
        lv_study = (ListView)(viewItems.get(1).findViewById(R.id.content_study)) ;
    }

    @Override
    protected void setListener() {
        tvChat.setOnClickListener(this);
        tvStudy.setOnClickListener(this);

        viewPager.setOnPageChangeListener(this);

        lv_study.setOnItemClickListener(this);
        lv_chat.setOnItemClickListener(this);
    }

    @Override
    protected void initOther() {
        initeCursor();
    }

    @Override
    protected void initView() {
        showChatItems();
        showStudyItems();
    }

    @Override
    protected void setAdapter() {
        adapter = new ChatPgAdapter(viewItems);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_chat :
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv_study :
                viewPager.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.content_chat_activity :
                Toast.makeText(activity,"岳麓沙龙",Toast.LENGTH_SHORT).show();
                break;
            case R.id.content_study :
                Toast.makeText(activity,"在线学习",Toast.LENGTH_SHORT).show();
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
                tvChat.setTextColor(getResources().getColor(R.color.main_color));
                if (currentItem == 1) {
                    animation = new TranslateAnimation(offSet * 3 + bmWidth, offSet, 0, 0);
                }
                break;
            case 1:
                tvStudy.setTextColor(getResources().getColor(R.color.main_color));
                if (currentItem == 0) {
                    animation = new TranslateAnimation(offSet, offSet * 3 + bmWidth, 0, 0);
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
        offSet = (dm.widthPixels - 2 * bmWidth) / 4;
        matrix.setTranslate(offSet, 0);
        tvChat.setTextColor(getResources().getColor(R.color.main_color));
        imageView.setImageMatrix(matrix); // 需要imageView的scaleType为matrix
        currentItem = 0;
    }

    public void resetTextColor(){
        tvChat.setTextColor(getResources().getColor(R.color.black));
        tvStudy.setTextColor(getResources().getColor(R.color.black));
    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState){
//        super.onActivityCreated(savedInstanceState);
//        init();
//        MainActivity activity = (MainActivity) getActivity();
//        ChatItemAdapter adapter = new ChatItemAdapter(activity.context ,R.layout.chat_item , items);
//        ListView listView = (ListView)this.getView().findViewById(R.id.broadcast);
//        listView.setAdapter(adapter);
//    }

    public void showChatItems(){
        initChatItems();
        MainActivity activity = (MainActivity) getActivity();
        ChatItemAdapter adapter = new ChatItemAdapter(activity.getApplicationContext() ,R.layout.chat_item , activityItems);
        lv_chat.setAdapter(adapter);
    }

    public void showStudyItems(){
        initStudyItems();
        MainActivity activity = (MainActivity) getActivity();
        ContentItemAdapter adapter = new ContentItemAdapter(activity.getApplicationContext() ,R.layout.content_item , studyItems);
        lv_study.setAdapter(adapter);
    }

    public void initChatItems(){
        ChatItem item1 = new ChatItem(R.drawable.chat1,"2017全国大学生英语竞赛",
                "2016-12-07 17:20","高等学校大学外语教学指导委员会","全国");
        activityItems.add(item1);
        activityItems.add(item1);
        activityItems.add(item1);
        activityItems.add(item1);
        activityItems.add(item1);
        activityItems.add(item1);
        activityItems.add(item1);
        activityItems.add(item1);
        activityItems.add(item1);
    }

    public void initStudyItems(){
        ContentItem item1 = new ContentItem(R.drawable.chat1,"格林童话背后的智慧", "2015-11-11","颖儿老师");
        studyItems.add(item1);
        studyItems.add(item1);
        studyItems.add(item1);
        studyItems.add(item1);
        studyItems.add(item1);
        studyItems.add(item1);
        studyItems.add(item1);
        studyItems.add(item1);
        studyItems.add(item1);
        studyItems.add(item1);
        studyItems.add(item1);
        studyItems.add(item1);

    }


}

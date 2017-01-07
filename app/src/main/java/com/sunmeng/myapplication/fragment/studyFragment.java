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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sunmeng.myapplication.MainActivity;
import com.sunmeng.myapplication.R;
import com.sunmeng.myapplication.adapter.ContentItem;
import com.sunmeng.myapplication.adapter.ContentItemAdapter;
import com.sunmeng.myapplication.adapter.StudyPgAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class studyFragment extends Fragment {
    private List<ContentItem> educationItems = new ArrayList<ContentItem>();
    private List<ContentItem> historyItems = new ArrayList<ContentItem>();
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
    private TextView history;
    private TextView educatition;
    private View view ;
    private ListView listView;

    public studyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_study, container, false);

        imageView = (ImageView)(view.findViewById(R.id.cursor));
        history = (TextView)(view.findViewById(R.id.tv_history));
        educatition = (TextView)(view.findViewById(R.id.tv_education));

        initeCursor();

        viewItems.add(inflater.inflate(R.layout.content_history, null));
        viewItems.add(inflater.inflate(R.layout.content, null));

        adapter = new StudyPgAdapter(viewItems);
        viewPager = (ViewPager) (view.findViewById(R.id.vp_study));
        viewPager.setAdapter(adapter);

        showHistoryList();
        showEducationList();

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            // 当滑动式，顶部的imageView是通过animation缓慢的滑动
            @Override
            public void onPageSelected(int arg0) {
                switch (arg0) {
                    case 0:
                        if (currentItem == 1) {
                            animation = new TranslateAnimation(offSet * 2 + bmWidth, 0, 0, 0);
                        }
                        break;
                    case 1:
                        if (currentItem == 0) {
                            animation = new TranslateAnimation(0, offSet * 2 + bmWidth, 0, 0);
                        }
                        break;

                }
                currentItem = arg0;
                animation.setDuration(150); // 光标滑动速度
                animation.setFillAfter(true);
                imageView.startAnimation(animation);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }
            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(0);
            }
        });

        educatition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(1);
            }
        });

        return view;
    }

    private void initeCursor() {
        cursor = BitmapFactory.decodeResource(getResources(), R.drawable.cursor);
        bmWidth = cursor.getWidth();

        DisplayMetrics dm = getResources().getDisplayMetrics();
        // 设置图标的起始位置(3:3个textview。)
        offSet = (dm.widthPixels - 2 * bmWidth) / 4;
        matrix.setTranslate(offSet, 0);
        imageView.setImageMatrix(matrix); // 需要imageView的scaleType为matrix
        currentItem = 0;
    }

    public void showHistoryList(){
        initHistoryList();
        MainActivity activity = (MainActivity) getActivity();
        //activity.getApplicationContext()才可以，但是activity.context是空值
        ContentItemAdapter adapter = new ContentItemAdapter(activity.getApplicationContext(),R.layout.content_item,historyItems);
        listView = (ListView)viewItems.get(0).findViewById(R.id.content_history);
        listView.setAdapter(adapter);
    }

    public void showEducationList(){
        initEducationList();
        MainActivity activity = (MainActivity) getActivity();
        //activity.getApplicationContext()才可以，但是activity.context是空值
        ContentItemAdapter adapter = new ContentItemAdapter(activity.getApplicationContext() ,R.layout.content_item , educationItems);
        listView = (ListView)viewItems.get(1).findViewById(R.id.content_education);
        listView.setAdapter(adapter);
    }

    public void initHistoryList(){
        ContentItem item1 = new ContentItem(R.drawable.chat1,"郑和下西洋背后的故事", "2016-11-11","晓茎");
        historyItems.add(item1);
        historyItems.add(item1);
        historyItems.add(item1);
        historyItems.add(item1);
        historyItems.add(item1);
        historyItems.add(item1);
        historyItems.add(item1);
        historyItems.add(item1);
        historyItems.add(item1);
        historyItems.add(item1);
        historyItems.add(item1);
    }

    public void initEducationList(){
        ContentItem item1 = new ContentItem(R.drawable.chat1,"格林童话背后的智慧", "2015-11-11","颖儿老师");
        educationItems.add(item1);
        educationItems.add(item1);
        educationItems.add(item1);
        educationItems.add(item1);
        educationItems.add(item1);
        educationItems.add(item1);
        educationItems.add(item1);
        educationItems.add(item1);
        educationItems.add(item1);
        educationItems.add(item1);
        educationItems.add(item1);
        educationItems.add(item1);
        educationItems.add(item1);
        educationItems.add(item1);
        educationItems.add(item1);
    }
}

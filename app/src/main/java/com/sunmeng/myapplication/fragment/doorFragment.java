package com.sunmeng.myapplication.fragment;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.sunmeng.myapplication.MainActivity;
import com.sunmeng.myapplication.R;
import com.sunmeng.myapplication.adapter.Carousel;
import com.sunmeng.myapplication.adapter.CarouselAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class doorFragment extends Fragment {

    private static final String ARTICLE_LATEST_PARAM = "param";

    private static final int UPTATE_VIEWPAGER = 0;

    //轮播的最热新闻图片
    private  ViewPager vpHottest;
    //轮播图片下面的小圆点
    private LinearLayout llHottestIndicator;
    private View view ;
    //存储的参数
    private String mParam;

    //获取 fragment 依赖的 Activity，方便使用 Context
    private Activity mAct;

    //设置当前 第几个图片 被选中
    private int autoCurrIndex = 0;

    private ImageView[] mBottomImages;//底部只是当前页面的小圆点

    private Timer timer = new Timer(); //为了方便取消定时轮播，将 Timer 设为全局

    //定时轮播图片，需要在主线程里面修改 UI
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPTATE_VIEWPAGER:
                    if (msg.arg1 != 0) {
                        vpHottest.setCurrentItem(msg.arg1);
                    } else {
                        //false 当从末页调到首页是，不显示翻页动画效果，
                        vpHottest.setCurrentItem(msg.arg1, false);
                    }
                    break;
            }
        }
    };

    public static doorFragment newInstance(String param) {
        doorFragment fragment = new doorFragment();
        Bundle args = new Bundle();
        args.putString(ARTICLE_LATEST_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mParam = savedInstanceState.getString(ARTICLE_LATEST_PARAM);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_door, container, false);
        mAct = getActivity();
        vpHottest = (ViewPager) view.findViewById(R.id.vp_hottest);
        llHottestIndicator = (LinearLayout) view.findViewById(R.id.ll_hottest_indicator);
        Fresco.initialize(mAct);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new ImageTask().execute();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        vpHottest = (ViewPager) view.findViewById(R.id.vp_hottest);
        llHottestIndicator = (LinearLayout) view.findViewById(R.id.ll_hottest_indicator);
    }


    private void setUpViewPager(final List<Carousel> headerArticles) {
        CarouselAdapter imageAdapter = new CarouselAdapter(mAct, headerArticles);
        vpHottest.setAdapter(imageAdapter);

        //创建底部指示位置的导航栏
        mBottomImages = new ImageView[headerArticles.size()];

        for (int i = 0; i < mBottomImages.length; i++) {
            ImageView imageView = new ImageView(mAct);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
            params.setMargins(5, 0, 5, 0);
            imageView.setLayoutParams(params);
            if (i == 0) {
                imageView.setBackgroundResource(R.drawable.indicator_select);
            } else {
                imageView.setBackgroundResource(R.drawable.indicator_not_select);
            }

            mBottomImages[i] = imageView;
            //把指示作用的原点图片加入底部的视图中
            llHottestIndicator.addView(mBottomImages[i]);

        }

        vpHottest.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                                              //图片左右滑动时候，将当前页的圆点图片设为选中状态
                                              @Override
                                              public void onPageSelected(int position) {
                                                  // 一定几个图片，几个圆点，但注意是从0开始的
                                                  int total = mBottomImages.length;
                                                  for (int j = 0; j < total; j++) {
                                                      if (j == position) {
                                                          mBottomImages[j].setBackgroundResource(R.drawable.indicator_select);
                                                      } else {
                                                          mBottomImages[j].setBackgroundResource(R.drawable.indicator_not_select);
                                                      }
                                                  }

                                                  //设置全局变量，currentIndex为选中图标的 index
                                                  autoCurrIndex = position;
                                              }

                                              @Override
                                              public void onPageScrolled(int i, float v, int i1) {
                                              }

                                              @Override
                                              public void onPageScrollStateChanged(int state) {
                                              }
                                          }
        );

        // 设置自动轮播图片，5s后执行，周期是5s
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = UPTATE_VIEWPAGER;
                if (autoCurrIndex == headerArticles.size() - 1) {
                    autoCurrIndex = -1;
                }
                message.arg1 = autoCurrIndex + 1;
                mHandler.sendMessage(message);
            }
        }, 5000, 5000);
    }


    class ImageTask extends AsyncTask<String, Void, List<Carousel>> {
        @Override
        protected List<Carousel> doInBackground(String... params) {
            List<Carousel> articles = new ArrayList<Carousel>();
            articles.add(
                    new Carousel(1123, "http://***20151231105648_11790.jpg","www.baidu.com"));
            articles.add(
                    new Carousel(1123, "http://***20151230152544_36663.jpg","www.baidu.com"));
            articles.add(
                    new Carousel(1123, "http://***20151229204329_75030.jpg","www.baidu.com"));
            articles.add(
                    new Carousel(1123, "http://***20151221151031_36136.jpg","www.baidu.com"));
            return articles;
        }

        @Override
        protected void onPostExecute(List<Carousel> articles) {
            //这儿的 是 url 的集合
            super.onPostExecute(articles);
            setUpViewPager(articles);

        }
    }
//    private List<Carousel> articles = new ArrayList<Carousel>();
//    private ImageView[] mBottomImages;//底部只是当前页面的小圆点
//    private View view ;
//    private ViewPager pager ;
//
//    public doorFragment() {
//        // Required empty public constructor
//
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        view = inflater.inflate(R.layout.fragment_door, container, false);
//        showArticle();
//        return view ;
//    }
//
//    public void showArticle(){
//        initArticle();
//        MainActivity activity = (MainActivity) getActivity();
//        //activity.getApplicationContext()才可以，但是activity.context是空值
//        CarouselAdapter adapter = new CarouselAdapter(activity, articles);
//        pager = (ViewPager)view.findViewById(R.id.vp_hottest);
//        pager.setAdapter(adapter);
//    }
//
//    public void initArticle(){
//
//    }
}

package com.sunmeng.myapplication.fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.sunmeng.myapplication.AllBroadcast;
import com.sunmeng.myapplication.MainActivity;
import com.sunmeng.myapplication.R;
import com.sunmeng.myapplication.adapter.Broadcast;
import com.sunmeng.myapplication.adapter.BroadcastAdapter;
import com.sunmeng.myapplication.adapter.Carousel;
import com.sunmeng.myapplication.adapter.CarouselAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class doorFragment extends BaseFragment implements View.OnClickListener,AdapterView.OnItemClickListener{


    private static final String ARTICLE_LATEST_PARAM = "param";

    private static final int UPTATE_VIEWPAGER = 0;

    //轮播的图片
    private  ViewPager vpHottest;
    //轮播图片下面的小圆点
    private LinearLayout llHottestIndicator;
    //存储的参数
    private String mParam;
    //设置当前 第几个图片 被选中
    private int autoCurrIndex = 0;
    //底部只是当前页面的小圆点
    private ImageView[] mBottomImages;
    //为了方便取消定时轮播，将 Timer 设为全局
    private Timer timer = new Timer();

    private List<Broadcast> brodcastItems = new ArrayList<Broadcast>();
    private ListView listView;

    private TextView tvMore;
    private ImageView imgMore;

    //最先运行，在创建洁敏啊之前，所以一定要直接引用
    //为什么一直显示没有mIntent实例呢
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.text_more:
                startActivity(new Intent(getActivity() , AllBroadcast.class));
                break;
            case R.id.more:
                startActivity(new Intent(getActivity() , AllBroadcast.class));
                break;


        }
    }

    //这里不能关联R.id.broadcast，因为点击后获取的view是关系布局不是broadcast
    //这里应当跳转的是详情页，而且可能要传值给下一个activity
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        if(view.getId()==R.id.broadcast){
            if(i!=-1){
                startActivity(new Intent(getActivity() , AllBroadcast.class));

        }
    }

    //    public static doorFragment newInstance(String param) {
//        doorFragment fragment = new doorFragment();
//        Bundle args = new Bundle();
//        args.putString(ARTICLE_LATEST_PARAM, param);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mParam = savedInstanceState.getString(ARTICLE_LATEST_PARAM);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new ImageTask().execute();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected void setListener() {
        imgMore.setOnClickListener(this);
        tvMore.setOnClickListener(this);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void setActivity() {
        activity = getActivity();
    }

    @Override
    protected void setLayoutId() {
        layout_id = R.layout.fragment_door ;
    }

    @Override
    protected void findView(View view) {
        vpHottest = (ViewPager) view.findViewById(R.id.vp_hottest);
        llHottestIndicator = (LinearLayout) view.findViewById(R.id.ll_hottest_indicator);
        tvMore = (TextView) view.findViewById(R.id.text_more);
        imgMore = (ImageView) view.findViewById(R.id.more);
        listView = (ListView)view.findViewById(R.id.broadcast);
    }

    @Override
    protected void initOther() {
        Fresco.initialize(activity);
    }

    @Override
    protected void initView() {
        showBroadcastList();
    }

    public void showBroadcastList(){
        initBroadcastList();
        MainActivity activity = (MainActivity) getActivity();
        //activity.getApplicationContext()才可以，但是activity.context是空值
        BroadcastAdapter adapter = new BroadcastAdapter(activity.getApplicationContext().getApplicationContext() ,R.layout.broadcast_item ,brodcastItems );
        listView.setAdapter(adapter);
    }

    public void initBroadcastList(){
        Broadcast item1 = new Broadcast("明天运动会", "2016-12-12");
        brodcastItems.add(item1);
        brodcastItems.add(item1);
        brodcastItems.add(item1);
    }

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


    //////////////////////////////
    //-------该函数还没有给后端提供相应的接口
    class ImageTask extends AsyncTask<String, Void, List<Carousel>> {
        @Override
        protected List<Carousel> doInBackground(String... params) {
            List<Carousel> articles = new ArrayList<Carousel>();
            articles.add(
                    new Carousel(1123, "http://pic.pp3.cn/uploads//201510/2015101803.jpg","https://www.baidu.com/"));
            articles.add(
                    new Carousel(1123, "http://pic.90sjimg.com/back_pic/qk/back_origin_pic/00/01/48/4fb5342c58c88aac8e400ac0279cc29e.jpg","https://www.baidu.com/"));
            articles.add(
                    new Carousel(1123, "http://pic.pp3.cn/uploads//201510/2015101803.jpg","https://www.baidu.com/"));
//            articles.add(
//                    new Carousel(1123, "http://pic.90sjimg.com/back_pic/qk/back_origin_pic/00/01/48/4fb5342c58c88aac8e400ac0279cc29e.jpg","https://www.baidu.com/"));
            return articles;
        }

        @Override
        protected void onPostExecute(List<Carousel> articles) {
            //这儿的 是 url 的集合
            super.onPostExecute(articles);
            setUpViewPager(articles);

        }
    }

    private void setUpViewPager(final List<Carousel> headerArticles) {
        CarouselAdapter imageAdapter = new CarouselAdapter(activity, headerArticles);
        vpHottest.setAdapter(imageAdapter);

        //创建底部指示位置的导航栏
        mBottomImages = new ImageView[headerArticles.size()];

        for (int i = 0; i < mBottomImages.length; i++) {
            ImageView imageView = new ImageView(activity);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(50, 50);
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
}

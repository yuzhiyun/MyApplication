package com.sunmeng.myapplication.fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
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
import android.widget.TextView;

import com.sunmeng.myapplication.R;
import com.sunmeng.myapplication.adapter.SchoolPgAdapter;
import com.sunmeng.myapplication.adapter.StudyPgAdapter;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

/**
 * A simple {@link Fragment} subclass.
 */
public class schoolFragment extends Fragment {
//    private List<LinkmanItem> linkmanItems = new ArrayList<LinkmanItem>();
//    private List<LinkmanItem> groupItems = new ArrayList<LinkmanItem>();

    private ViewPager viewPager;
    private ImageView imageView;
//    private StudyPgAdapter adapter ;
//    private List<View> viewItems = new ArrayList<View>();
    private List<Fragment> fragments = new ArrayList<Fragment>();

    private Bitmap cursor;
    private int offSet;
    private int currentItem;
    private Matrix matrix = new Matrix();
    private int bmWidth;
    private Animation animation;
    private TextView message;
    private TextView linkman;
    private TextView groupMsg;
    private View view ;
//    private ListView listView;

    public schoolFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        showMessageList();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_school, container, false);

        imageView = (ImageView)(view.findViewById(R.id.cursor));
        message = (TextView)(view.findViewById(R.id.tv_massage));
        linkman = (TextView)(view.findViewById(R.id.tv_linkman));
        groupMsg = (TextView)(view.findViewById(R.id.tv_groupMsg));
        viewPager = (ViewPager) (view.findViewById(R.id.vp_school));

        // 初始化滑动图片位置
        initeCursor();
        showMessageList();

//        viewItems.add(inflater.inflate(R.layout.activity_conversation_list, null));
//        viewItems.add(inflater.inflate(R.layout.linkman, null));
//        viewItems.add(inflater.inflate(R.layout.conversation_group, null));

//        adapter = new StudyPgAdapter(viewItems);
//        viewPager = (ViewPager) (view.findViewById(R.id.vp_school));
//        viewPager.setAdapter(adapter);


//        showLinkmanList();
//        showGroupMsgList();

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            // 当滑动式，顶部的imageView是通过animation缓慢的滑动
            @Override
            public void onPageSelected(int arg0) {
                switch (arg0) {
                    case 0:
                        if (currentItem == 1) {
                            animation = new TranslateAnimation(offSet * 2 + bmWidth, 0, 0, 0);
                        } else if (currentItem == 2) {
                            animation = new TranslateAnimation(offSet * 4 + 2 * bmWidth, 0, 0, 0);
                        }
                        break;
                    case 1:
                        if (currentItem == 0) {
                            animation = new TranslateAnimation(0, offSet * 2 + bmWidth, 0, 0);
                        } else if (currentItem == 2) {
                            animation = new TranslateAnimation(4 * offSet + 2 * bmWidth, offSet * 2 + bmWidth, 0, 0);
                        }
                        break;
                    case 2:
                        if (currentItem == 0) {
                            animation = new TranslateAnimation(0, 4 * offSet + 2 * bmWidth, 0, 0);
                        } else if (currentItem == 1) {
                            animation = new TranslateAnimation(offSet * 2 + bmWidth, 4 * offSet + 2 * bmWidth, 0, 0);
                        }
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
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(0);
            }
        });

        linkman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(1);
            }
        });

        groupMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(2);
            }
        });
        return view;
    }

//    public void showLinkmanList(){
//        initLinknameList();
//        MainActivity activity = (MainActivity) getActivity();
//        //activity.getApplicationContext()才可以，但是activity.context是空值
//        LinkmanAdapter adapter = new LinkmanAdapter(activity.getApplicationContext() ,R.layout.linkmain_item , linkmanItems);
//        listView = (ListView)viewItems.get(1).findViewById(R.id.linkman);
//        listView.setAdapter(adapter);
//    }

//    public void showGroupMsgList(){
//        initGroupList();
//        MainActivity activity = (MainActivity) getActivity();
//        //activity.getApplicationContext()才可以，但是activity.context是空值
//        LinkmanAdapter adapter = new LinkmanAdapter(activity.getApplicationContext() ,R.layout.group_item , groupItems);
//        listView = (ListView)viewItems.get(2).findViewById(R.id.group_list);
//        listView.setAdapter(adapter);
//    }

//    public void initLinknameList(){
//        LinkmanItem item1 = new LinkmanItem(R.drawable.chat1,"孙萌", "萌萌家长");
//        linkmanItems.add(item1);
//        linkmanItems.add(item1);
//        linkmanItems.add(item1);
//        linkmanItems.add(item1);
//        linkmanItems.add(item1);
//        linkmanItems.add(item1);
//        linkmanItems.add(item1);
//        linkmanItems.add(item1);
//        linkmanItems.add(item1);
//        linkmanItems.add(item1);
//        linkmanItems.add(item1);
//        linkmanItems.add(item1);
//        linkmanItems.add(item1);
//        linkmanItems.add(item1);
//        linkmanItems.add(item1);
//        linkmanItems.add(item1);
//        linkmanItems.add(item1);
//    }

//    public void initGroupList(){
//        LinkmanItem item1 = new LinkmanItem(R.drawable.chat1,"孙萌", "萌萌家长");
//        groupItems.add(item1);
//        groupItems.add(item1);
//        groupItems.add(item1);
//        groupItems.add(item1);
//        groupItems.add(item1);
//        groupItems.add(item1);
//        groupItems.add(item1);
//        groupItems.add(item1);
//        groupItems.add(item1);
//        groupItems.add(item1);
//        groupItems.add(item1);
//        groupItems.add(item1);
//        groupItems.add(item1);
//        groupItems.add(item1);
//        groupItems.add(item1);
//    }

    private void initeCursor() {
        cursor = BitmapFactory.decodeResource(getResources(), R.drawable.cursor);
        bmWidth = cursor.getWidth();

        DisplayMetrics dm = getResources().getDisplayMetrics();
        // 设置图标的起始位置(3:3个textview。)
        offSet = (dm.widthPixels - 3 * bmWidth) / 6;
        // offSet = dm.widthPixels / 6 - bmWidth / 3;
        matrix.setTranslate(offSet, 0);
        imageView.setImageMatrix(matrix); // 需要imageView的scaleType为matrix
        currentItem = 0;
    }

    //使用rongyun 来显示消息列表
    public void showMessageList() {
        ConversationListFragment fragment = new ConversationListFragment();
        Uri uri = Uri.parse("rong://" + getActivity().getApplicationInfo().packageName).buildUpon().appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")
                .build();
        fragment.setUri(uri);

        initViewPager(fragment);
    }

    private void initViewPager(ConversationListFragment fragment) {
        if (viewPager.getAdapter()==null) {
            fragments.add(fragment);
            fragments.add(new LinkmanFragment());
            fragments.add(new ConversationGroupFragment());
            SchoolPgAdapter adapter = new SchoolPgAdapter(getFragmentManager(), fragments);
//            viewPager = (ViewPager) (view.findViewById(R.id.vp_school));
            viewPager.setAdapter(adapter);
        }
    }

}

package com.sunmeng.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunmeng.myapplication.fragment.*;


public class MainActivity extends BaseActivity implements View.OnClickListener{

    private TextView tvTitle;
    private TextView tvDoor;
    private TextView tvStudy;
    private TextView tvChat;
    private TextView tvSchool;
    private TextView tvMine;
    private LinearLayout door;
    private LinearLayout study;
    private LinearLayout chat;
    private LinearLayout school;
    private LinearLayout mine;

    private ImageButton imgDoor;
    private ImageButton imgStudy;
    private ImageButton imgChat;
    private ImageButton imgSchool;
    private ImageButton imgMine;

    private Fragment doorF;
    private Fragment studyF;
    private Fragment chatF;
    private Fragment schoolF;
    private Fragment mineF;

    //设定初始布局
    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_main);
    }

    //activity的创建函数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
    }


//    //关于标题栏中的菜单组件，暂时不用管，忽略
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    //关于标题栏中的菜单组件，暂时不用管，忽略
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    //将类中声明的各个资源变量进行资源的绑定
    @Override
    protected void findView() {
        FragmentManager fManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            doorF = (doorFragment) fManager.findFragmentByTag("doorFragment");
            chatF = (chatFragment) fManager.findFragmentByTag("chatFragment");
            schoolF = (studyFragment) fManager.findFragmentByTag("studyFragment");
            studyF = (studyFragment) fManager.findFragmentByTag("studyFragment");
            mineF = (mineFragment) fManager.findFragmentByTag("mineFragment");
        }

        //标题栏
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvDoor = (TextView) findViewById(R.id.door_text);
        tvChat = (TextView) findViewById(R.id.chat_text);
        tvSchool = (TextView) findViewById(R.id.school_text);
        tvStudy = (TextView) findViewById(R.id.learn_text);
        tvMine = (TextView) findViewById(R.id.mine_text);

        //五个tab
        door = (LinearLayout) findViewById(R.id.door);
        chat= (LinearLayout) findViewById(R.id.chat);
        school = (LinearLayout) findViewById(R.id.school);
        study = (LinearLayout) findViewById(R.id.learn);
        mine = (LinearLayout) findViewById(R.id.mine);
        //五个imagebutton
        imgDoor = (ImageButton) findViewById(R.id.door_img);
        imgChat = (ImageButton) findViewById(R.id.chat_img);
        imgSchool = (ImageButton) findViewById(R.id.school_img);
        imgStudy = (ImageButton) findViewById(R.id.learn_img);
        imgMine = (ImageButton) findViewById(R.id.mine_img);

    }


    //为每一个面板都设定一个监听器
    @Override
    protected void setListener() {
        door.setOnClickListener(this);
        chat.setOnClickListener(this);
        school.setOnClickListener(this);
        study.setOnClickListener(this);
        mine.setOnClickListener(this);
    }

    //初始化页面，表示刚开是activity启动时，显示的是第一个面板
    @Override
    protected void initOther() {

        setSelect(0);
//        toolbar.setVisibility(View.GONE);
//        tvTitle.setText("校内咨询");
//        tvDoor.setTextColor(getResources().getColor(R.color.main_color));
    }

    //设定activity中所有的view的点击事件
    @Override
    public void onClick(View view) {
        resetImgsAndText();
        switch (view.getId()) {
            case R.id.door:
//                tvTitle.setText("校内咨询");
                toolbar.setVisibility(View.GONE);
                tvDoor.setTextColor(getResources().getColor(R.color.main_color));
                setSelect(0);
                break;
            case R.id.chat:
                tvTitle.setText("教育专区");
                toolbar.setVisibility(View.VISIBLE);
                tvChat.setTextColor(getResources().getColor(R.color.main_color));
                setSelect(1);
                break;
            case R.id.school:
                tvTitle.setText("家校通");
                toolbar.setVisibility(View.VISIBLE);
                tvSchool.setTextColor(getResources().getColor(R.color.main_color));
                setSelect(2);
                break;
            case R.id.learn:
                tvTitle.setText("心理专区");
                toolbar.setVisibility(View.VISIBLE);
                tvStudy.setTextColor(getResources().getColor(R.color.main_color));
                setSelect(3);
                break;
            case R.id.mine:
                toolbar.setVisibility(View.GONE);
                tvMine.setTextColor(getResources().getColor(R.color.main_color));
                setSelect(4);
                break;
            default:
                break;
        }
    }

    //关键的函数：用来进行面板的切换
    private void setSelect(int i) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        //隐藏所有显示过的Fragment
        hideFragment(transaction);
        // 把图片设置为亮的
        // 设置内容区域
        switch (i) {
            case 0:
                if (doorF == null) {
                    doorF = new doorFragment();
                    /**这里要 注意Fragment的导入包要统一，本文件的Fragment如果 是import android.support.v4.app.Fragment;
                     *那么WeixinFragment.java的导入包也是import android.support.v4.app.Fragment;不能是import android.app.Fragment;
                     * */
                    transaction.add(R.id.id_content, doorF, "doorFragment");//把mTab01那个fragment添加到id_content这个布局里面。
                    /**如果使用android.app.Fragment，add 和show 方法都会有问题*/
                } else {
                    transaction.show(doorF);
                }
                imgDoor.setImageResource(R.drawable.door_press);

                break;
            case 1:
                if (chatF == null) {
                    chatF = new chatFragment();
                    transaction.add(R.id.id_content, chatF, "chatFragment");
                } else {
                    transaction.show(chatF);
                }
                imgChat.setImageResource(R.drawable.chat_press);
                break;
            case 2:
                if (schoolF == null) {
                    schoolF = new schoolFragment();
                    transaction.add(R.id.id_content, schoolF, "schoolFragment");
                } else {
                    transaction.show(schoolF);
                }
                imgSchool.setImageResource(R.drawable.study_press);
                break;
            case 3:
                if (studyF == null) {
                    studyF = new studyFragment();
                    transaction.add(R.id.id_content, studyF, "studyFragment");
                } else {
                    transaction.show(studyF);
                }
                imgStudy.setImageResource(R.drawable.coffee_press);

                break;
            case 4:
                if (mineF == null) {
                    mineF = new mineFragment();
                    transaction.add(R.id.id_content, mineF, "mineFragment");
                } else {
                    transaction.show(mineF);
                }
                imgMine.setImageResource(R.drawable.mine_press);

                break;

            default:
                break;
        }

//        提交事物
        transaction.commit();

    }

    //将所有的面板隐藏起来
    private void hideFragment(FragmentTransaction transaction) {
        if (doorF != null) {
            transaction.hide(doorF);
        }
        if (chatF != null) {
            transaction.hide(chatF);
        }
        if (schoolF != null) {
            transaction.hide(schoolF);
        }
        if (studyF != null) {
            transaction.hide(studyF);
        }
        if (mineF != null) {
            transaction.hide(mineF);
        }
    }

    //将底部栏所有的图片和文字都设置为未激活状态
    private void resetImgsAndText() {
        imgDoor.setImageResource(R.drawable.door);
        imgSchool.setImageResource(R.drawable.study);
        imgChat.setImageResource(R.drawable.chat);
        imgStudy.setImageResource(R.drawable.coffee);
        imgMine.setImageResource(R.drawable.mine);
        tvChat.setTextColor(getResources().getColor(R.color.light_grey_color));
        tvDoor.setTextColor(getResources().getColor(R.color.light_grey_color));
        tvMine.setTextColor(getResources().getColor(R.color.light_grey_color));
        tvSchool.setTextColor(getResources().getColor(R.color.light_grey_color));
        tvStudy.setTextColor(getResources().getColor(R.color.light_grey_color));
    }



}

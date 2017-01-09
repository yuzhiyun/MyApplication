package com.sunmeng.myapplication.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract  class BaseFragment extends Fragment {

    //当活动间进行跳转时需要使用
    protected Context context;

    //用于日志
    protected String TAG=this.toString();

    //方便复用
    protected LayoutInflater inflater;
    protected ViewGroup container;

    //方便绑定界面
    protected int layout_id;

    protected Activity activity ;

    @Override
    public void onStart() {
        super.onStart();
        setListener();
        initOther();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater=inflater;
        this.container=container;
        context=this.getContext();

        setLayoutId();
        setActivity();

        View view =  inflater.inflate(layout_id, container, false);

        findView(view);

        setAdapter();

        initView();
        return view;
    }

    protected  abstract  void setActivity();
    //设置当前java类绑定的界面
    protected abstract  void setLayoutId();
    //将界面中的可用组件绑定到java类中
    protected abstract  void findView(View view);
    //为每个组件设置监听事件
    protected abstract void setListener();
    //初始化一些动作
    protected abstract void initOther();
    //初始化一些界面
    protected abstract void initView();
    //用来设置适配器
    protected abstract void setAdapter();
}

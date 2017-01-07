package com.sunmeng.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by yuzhiyun on 2016-12-08.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public Context context;
    public Toolbar toolbar;
    public Bundle savedInstanceState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState=savedInstanceState;
        setLayoutView();
        context=this;
        findView();
        setListener();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setBackgroundColor(getResources().getColor(R.color.main_color));

        setSupportActionBar(toolbar);
//        进行其他初始化操作
        initOther();

    }
    protected abstract  void setLayoutView() ;
    protected abstract  void findView() ;
    protected abstract void setListener();
    protected abstract void initOther();
}

package com.sunmeng.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yuzhiyun on 2017-01-09.
 */
public abstract class NewBaseActivity extends AppCompatActivity {
    public Context context;
    public Bundle savedInstanceState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState=savedInstanceState;
        setLayoutView();
        context=this;
        findView();
        setListener();

//        进行其他初始化操作
        initOther();

    }
    protected abstract  void setLayoutView() ;
    protected abstract  void findView() ;
    protected abstract void setListener();
    protected abstract void initOther();
}

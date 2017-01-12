package com.sunmeng.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Map;

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
    //这是一个工具方法
    protected String appendParameter(String url,Map<String,String> params){
        Uri uri = Uri.parse(url);
        Uri.Builder builder = uri.buildUpon();
        for(Map.Entry<String,String> entry:params.entrySet()){
            builder.appendQueryParameter(entry.getKey(),entry.getValue());
        }
        return builder.build().getQuery();
    }

    protected abstract  void setLayoutView() ;
    protected abstract  void findView() ;
    protected abstract void setListener();
    protected abstract void initOther();
}

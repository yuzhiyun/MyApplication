package com.sunmeng.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sunmeng.myapplication.util.JsonHelper;
import com.sunmeng.myapplication.util.MyJsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Welcome extends NewBaseActivity implements View.OnClickListener {
    private ImageView img ;
    private Button login ;
    private Button register ;

    private RequestQueue mQueue ;
    private MyJsonObjectRequest request ;
    private List<Map<String,Object>> list ;

    private Response.Listener<JSONObject> listener ;
    private Response.ErrorListener errorListener;

    private JSONObject myResponse ;

    private String host = "http://192.168.0.109:8080" ;
    private String uri  = "/schoolCon/api/sch/school/get" ;
    private String appid = "03a8f0ea6a" ;
    private String appsecret = "b4a01f5a7dd4416c" ;

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void findView() {
        img = (ImageView)findViewById(R.id.welcome);
        login = (Button)findViewById(R.id.login1);
        register = (Button)findViewById(R.id.register1);
    }

    @Override
    protected void setListener() {
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    protected void initOther() {
        getSupportActionBar().hide();
        mQueue = Volley.newRequestQueue(context);
        postInfo(host+uri+"?appId="+appid+"&appSecret="+appsecret);
//        mQueue.add(stringRequest);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.login1:
                intent.setClass(Welcome.this , AllSchool.class);
//                intent.putExtra("schoolLists", (Serializable)list);
//                Log.d("list**",(Serializable)list+"");
                intent.putExtra("type",1);
                startActivity(intent);
                break;
            case R.id.register1:
                intent.setClass(Welcome.this , AllSchool.class);
                startActivity(intent);
                break;
        }
    }

    private void postInfo(String url){
        initPostListner();
//        request = new JsonObjectRequest(url, null,listener,errorListener);

        //构造了post方法
        Map<String, String> map = new HashMap<String, String>();
        map.put("appId", appid);
        map.put("appSecret", appsecret);

        //使用了父类的工具类方法进行访问路径的拼接
        String params = appendParameter(host+uri,map);

        request = new MyJsonObjectRequest(host+uri, params,listener,errorListener);

        mQueue.add(request);
    }

    private void initPostListner(){
        listener = new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                myResponse = response ;
                analyzeJSONData(myResponse) ;
                Log.d("TAG1", response.toString());
            }
        };
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG1", error.getMessage(), error);
            }
        };
    }

    private void analyzeJSONData(JSONObject o){
        try {
            JSONObject object = o.getJSONObject("data");
            JSONArray jsonlist = object.getJSONArray("schools");
//            list = new ArrayList<Map<String,Object>>();
            list= JsonHelper.toList(jsonlist);
            Log.d("list***",list.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
//
//    StringRequest stringRequest = new StringRequest("https://www.baidu.com",
//            new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    Log.d("TAG", response);
//                }
//            }, new Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//            Log.e("TAG", error.getMessage(), error);
//        }
//    });
}

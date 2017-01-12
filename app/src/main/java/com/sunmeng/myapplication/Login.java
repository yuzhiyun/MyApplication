package com.sunmeng.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends NewBaseActivity implements View.OnClickListener {

    private Button btn ;

    private AppCompatEditText ev_name ;
    private AppCompatEditText ev_pwd ;

    private TextView tv_forget ;

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void findView() {
        btn = (Button)findViewById(R.id.login);
        ev_name = (AppCompatEditText)findViewById(R.id.et_username);
        ev_pwd = (AppCompatEditText)findViewById(R.id.et_password);
        tv_forget = (TextView)findViewById(R.id.forget_pwd);
    }

    @Override
    protected void setListener() {
        btn.setOnClickListener(this);
        tv_forget.setOnClickListener(this);
    }

    @Override
    protected void initOther() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login :
                startActivity(new Intent(Login.this , MainActivity.class));
                break;
            case R.id.forget_pwd :
                startActivity(new Intent(Login.this , FindPWD.class));
                break;
        }
    }
}

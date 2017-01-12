package com.sunmeng.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;

public class FindPWD extends NewBaseActivity implements View.OnClickListener,View.OnFocusChangeListener {

    private AppCompatEditText user ;
    private AppCompatEditText pwd ;
    private AppCompatEditText ok_pwd ;
    private AppCompatEditText validate ;

    private Button btnValidate ;
    private Button btnPwd ;

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_find_pwd);
    }

    @Override
    protected void findView() {
        user = (AppCompatEditText)findViewById(R.id.et_fp_username);
        pwd = (AppCompatEditText)findViewById(R.id.et_fp_password);
        ok_pwd = (AppCompatEditText)findViewById(R.id.et_fp_ok_password);
        validate = (AppCompatEditText)findViewById(R.id.et_fp_validate);

        btnValidate = (Button)findViewById(R.id.bt_fp_validate);
        btnPwd = (Button)findViewById(R.id.btn_change_pwd);
    }

    @Override
    protected void setListener() {
        ok_pwd.setOnFocusChangeListener(this);

        btnValidate.setOnClickListener(this);
        btnPwd.setOnClickListener(this);
    }

    @Override
    protected void initOther() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_fp_validate:
                break;
            case R.id.btn_change_pwd :
                break;
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        // 此处为得到焦点时的处理内容
        if(b) {

        }
        // 此处为失去焦点时的处理内容
        else {

        }
    }

}

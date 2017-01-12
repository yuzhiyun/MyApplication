package com.sunmeng.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;

public class Register extends NewBaseActivity implements View.OnClickListener,View.OnFocusChangeListener {

    private AppCompatEditText user ;
    private AppCompatEditText pwd ;
    private AppCompatEditText ok_pwd ;
    private AppCompatEditText validate ;

    private Button btnValidate ;
    private Button btnRegister ;

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void findView() {
        user = (AppCompatEditText)findViewById(R.id.et_rg_username);
        pwd = (AppCompatEditText)findViewById(R.id.et_rg_password);
        ok_pwd = (AppCompatEditText)findViewById(R.id.et_rg_ok_password);
        validate = (AppCompatEditText)findViewById(R.id.et_rg_validate);

        btnValidate = (Button)findViewById(R.id.bt_rg_validate);
        btnRegister = (Button)findViewById(R.id.register);
    }

    @Override
    protected void setListener() {
        ok_pwd.setOnFocusChangeListener(this);

        btnValidate.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    protected void initOther() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_rg_validate:
                break;
            case R.id.register :
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

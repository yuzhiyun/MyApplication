package com.sunmeng.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends NewBaseActivity implements View.OnClickListener {

    private Button btn ;

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void findView() {
        btn = (Button)findViewById(R.id.login);
    }

    @Override
    protected void setListener() {
        btn.setOnClickListener(this);
    }

    @Override
    protected void initOther() {

    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(Login.this , MainActivity.class));
    }
}

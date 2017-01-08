package com.sunmeng.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class Welcome extends BaseActivity implements View.OnClickListener {
    ImageView img ;
    Button login ;
    Button register ;

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

    }

    @Override
    public void onClick(View view) {

    }
}

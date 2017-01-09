package com.sunmeng.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class Welcome extends NewBaseActivity implements View.OnClickListener {
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
        getSupportActionBar().hide();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login1:
                startActivity(new Intent(Welcome.this , AllSchool.class));
                break;
            case R.id.register1:
                startActivity(new Intent(Welcome.this , AllSchool.class));
                break;
        }
    }
}

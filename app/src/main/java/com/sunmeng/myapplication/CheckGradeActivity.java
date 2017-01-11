package com.sunmeng.myapplication;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.sunmeng.myapplication.fragment.ChooseSemester;


public class CheckGradeActivity extends NewBaseActivity {

    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_check_grade);
    }

    @Override
    protected void findView() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initOther() {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.fragment_check_grade,new ChooseSemester());
        ft.commit();
    }

}

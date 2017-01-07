package com.sunmeng.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yuzhiyun on 2016-12-19.
 */
public class SchoolPgAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public SchoolPgAdapter(FragmentManager fm) {
        super(fm);
    }

    public SchoolPgAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}




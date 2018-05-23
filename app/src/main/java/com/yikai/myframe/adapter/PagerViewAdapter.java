package com.yikai.myframe.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */

public class PagerViewAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;
    public PagerViewAdapter(FragmentManager fm,List fragment) {
        super(fm);
        this.mFragments = fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}

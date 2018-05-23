package com.yikai.myframe.fragment.newfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yikai.myframe.R;
import com.yikai.myframe.adapter.PagerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/22.
 */

public class NewFragment extends Fragment {

    private static NewFragment mNewFragment = null;
    private View view;
    private ViewPager viewPager;
    private List<Fragment> fragments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.new_fragment, container, false);
        initView();
        initData();

        return view;
    }

    private void initView() {
        viewPager = view.findViewById(R.id.vp);

    }

    private void initData() {
        fragments =new ArrayList<>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        viewPager.setAdapter(new PagerViewAdapter(getChildFragmentManager(),fragments));
    }

    public static NewFragment getInstance(){
        if(mNewFragment == null){
            mNewFragment = new NewFragment();
        }
        return mNewFragment;
    }

}

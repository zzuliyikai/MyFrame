package com.yikai.myframe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yikai.myframe.R;

/**
 * Created by Administrator on 2018/5/22.
 */

public class NewFragment extends Fragment {

    private static NewFragment mNewFragment = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.new_fragment, container, false);

        return view;
    }

    public static NewFragment getInstance(){
        if(mNewFragment == null){
            mNewFragment = new NewFragment();
        }
        return mNewFragment;
    }

}

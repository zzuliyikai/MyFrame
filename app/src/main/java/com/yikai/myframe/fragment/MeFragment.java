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

public class MeFragment extends Fragment {

    private static MeFragment MmeFragment = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_fragment, container, false);
        return view;
    }

    public static MeFragment getInstance(){
        if (MmeFragment == null){
            MmeFragment = new MeFragment();
        }
        return MmeFragment;
    }

}

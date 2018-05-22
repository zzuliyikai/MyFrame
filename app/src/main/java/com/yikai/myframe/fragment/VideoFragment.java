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

public class VideoFragment extends Fragment {
    private static VideoFragment mVideoFragment = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.video_fragment, container, false);

        return view;
    }

    public static VideoFragment getInstance(){

        if(mVideoFragment == null){
            mVideoFragment = new VideoFragment();
        }
        return mVideoFragment;
    }
}

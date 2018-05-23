package com.yikai.myframe.fragment.newfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yikai.myframe.R;
import com.yikai.myframe.adapter.FristAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */

public class Fragment1 extends Fragment {

    private View view;
    private RecyclerView rv;
    private List<String> mLists;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, container, false);
        initView();
        initData();
        return view;
    }

    private void initView() {
        rv = view.findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    private void initData() {
        mLists = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            mLists.add("我是第"+i+"块");
        }

        rv.setAdapter(new FristAdapter(mLists));
    }
}

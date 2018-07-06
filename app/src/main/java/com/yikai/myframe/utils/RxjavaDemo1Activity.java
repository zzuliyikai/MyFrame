package com.yikai.myframe.utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yikai.myframe.R;

import java.util.ArrayList;
import java.util.List;

public class RxjavaDemo1Activity extends AppCompatActivity {

    private RecyclerView mRv;
    private List<String> list = new ArrayList<>();
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_demo1);

        mRv = findViewById(R.id.recycler);

        mRv.setLayoutManager(new LinearLayoutManager(this));

        list.add("优先加载缓存，然后使用网络数据");

        mMyAdapter = new MyAdapter(this, list);

        mMyAdapter.setItemOnClickListener(new MyAdapter.ItemOnClickListener() {
            @Override
            public void ItemOnclickListener(View v, int position) {
                if (position == 0)
                    RxjavaDemo1Activity.this.startActivity(
                            new Intent(RxjavaDemo1Activity.this,
                                    RxjavaRequestDataActivity.class));

            }
        });
        mRv.setAdapter(mMyAdapter);

    }
}

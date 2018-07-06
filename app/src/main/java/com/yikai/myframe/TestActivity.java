package com.yikai.myframe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yikai.myframe.base.CommonAdapter;
import com.yikai.myframe.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    RecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        mRv = findViewById(R.id.recycler_view);
        mRv.setLayoutManager(new LinearLayoutManager(this));


        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i+ "");
        }


        mRv.setAdapter(new CommonAdapter<String>(this, R.layout.item, list) {
            @Override
            protected void covert(ViewHolder holder, String s, final int p) {

                TextView tv1 = holder.getView(R.id.tv1);

                tv1.setText(s);

                holder.setOnClickListener(R.id.tv1, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "点击" + p, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}

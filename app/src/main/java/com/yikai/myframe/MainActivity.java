package com.yikai.myframe;
import android.os.Bundle;
import com.yikai.myframe.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 禁止侧滑删除
     */
    @Override
    protected void initSlidable() {

    }
}

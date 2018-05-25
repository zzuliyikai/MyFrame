package com.yikai.myframe;

import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;

import com.yikai.myframe.base.BaseActivity;

public class HomeActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String ss = "1234567890123456";
        TextPaint mTextPaint = new TextPaint();
        int textWidth = (int) mTextPaint.measureText(ss);
        Log.d("yikai123456 textWidth",textWidth+"");

        Log.d("yikai123456",ss.length()+"");
    }


}

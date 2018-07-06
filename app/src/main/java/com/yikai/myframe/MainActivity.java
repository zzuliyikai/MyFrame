package com.yikai.myframe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.yikai.myframe.base.BaseActivity;
import com.yikai.myframe.fragment.mefragment.MeFragment;
import com.yikai.myframe.fragment.newfragment.NewFragment;
import com.yikai.myframe.fragment.photofragment.PhotoFragment;
import com.yikai.myframe.fragment.videofragment.VideoFragment;
import com.yikai.myframe.helper.BottomNavigationViewHelper;

public class MainActivity extends BaseActivity {

    private Toolbar mToolbar;
    private static final int FRAGMENT_NEWS = 0;
    private static final int FRAGMENT_PHOTO = 1;
    private static final int FRAGMENT_VIDEO = 2;
    private static final int FRAGMENT_MEDIA = 3;

    private MeFragment mMeFragment;
    private PhotoFragment mPhotoFragment;
    private VideoFragment mVideoFragment;
    private NewFragment mNewFragment;
    private BottomNavigationView bottom_navigation;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        showFragment(FRAGMENT_NEWS);


    }

    private void showFragment(int index) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hintFragment(ft);

        switch (index) {
            case FRAGMENT_NEWS:
                mToolbar.setTitle("新闻");
                if (mNewFragment == null) {
                    mNewFragment = NewFragment.getInstance();
                    ft.add(R.id.container, mNewFragment, NewFragment.class.getName());
                } else {
                    ft.show(mNewFragment);
                }
                break;
            case FRAGMENT_PHOTO:
                mToolbar.setTitle("图片");
                if (mPhotoFragment == null) {
                    mPhotoFragment = PhotoFragment.getInstance();
                    ft.add(R.id.container, mPhotoFragment, PhotoFragment.class.getName());
                } else {
                    ft.show(mPhotoFragment);
                }
                break;
            case FRAGMENT_VIDEO:
                mToolbar.setTitle("视频");
                if (mVideoFragment == null) {
                    mVideoFragment = VideoFragment.getInstance();
                    ft.add(R.id.container, mVideoFragment, VideoFragment.class.getName());
                } else {
                    ft.show(mVideoFragment);
                }
                break;
            case FRAGMENT_MEDIA:
                mToolbar.setTitle("我的");
                if (mMeFragment == null) {
                    mMeFragment = MeFragment.getInstance();
                    ft.add(R.id.container, mMeFragment, MeFragment.class.getName());
                } else {
                    ft.show(mMeFragment);
                }
                break;
        }
        ft.commit();
    }

    private void hintFragment(FragmentTransaction ft) {

        if (mNewFragment != null) {
            ft.hide(mNewFragment);
        }
        if (mPhotoFragment != null) {
            ft.hide(mPhotoFragment);
        }
        if (mVideoFragment != null) {
            ft.hide(mVideoFragment);
        }
        if (mMeFragment != null) {
            ft.hide(mMeFragment);
        }
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("头条");
        setSupportActionBar(mToolbar);
        bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottom_navigation);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_news:
                        showFragment(FRAGMENT_NEWS);
                        break;
                    case R.id.action_photo:
                        showFragment(FRAGMENT_PHOTO);
                        break;
                    case R.id.action_video:
                        showFragment(FRAGMENT_VIDEO);
                        break;
                    case R.id.action_media:
                        showFragment(FRAGMENT_MEDIA);
                        break;
                }

                return true;
            }
        });
    }

    /**
     * 禁止侧滑删除
     */
    @Override
    protected void initSlidable() {

    }
}
